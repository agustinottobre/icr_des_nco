package despacho.ejb;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import despacho.dominio.Articulo;
import despacho.dominio.ItemOrdenDespacho;
import despacho.dominio.ItemSolicitudArticulo;
import despacho.dominio.OrdenDespacho;
import despacho.dominio.SolicitudArticulo;
import despacho.ejb.interfaces.remotas.AdministradorSolicitudesArticulo;
import despacho.ejb.interfaces.remotas.ClienteRestParaLogistica;
import despacho.rest.bindings.ArticulosRecibidos;
import despacho.rest.bindings.Item;
import dto.ArticuloDTO;
import dto.ItemOrdenDespachoDTO;
import dto.ItemSolicitudArticuloDTO;
import dto.OrdenDespachoDTO;
import dto.SolicitudArticuloDTO;

/**
 * Session Bean implementation class AdministradorSolicitudArticulosBean
 */
@Stateless
@LocalBean
public class AdministradorSolicitudesArticuloBean implements
		AdministradorSolicitudesArticulo {

	@EJB
	private AdministradorOrdenesDespachoBean administradorOrdenesDespachoBean;
	
	@EJB
	private ClienteRestParaLogistica clienteRestLogistica;
	
	@PersistenceContext(unitName = "JPADB")
	private EntityManager em;

	public AdministradorSolicitudesArticuloBean() {
	}

	@Override
	public SolicitudArticuloDTO altaSolicitudArticulo(
			SolicitudArticuloDTO solicitudArticuloDTO) {
		// TODO Auto-generated method stub
		SolicitudArticulo solicitudArticulo = new SolicitudArticulo();
		solicitudArticulo.setEstadoSolicitud(solicitudArticuloDTO
				.getEstadoSolicitud());
		solicitudArticulo.setidDeposito(solicitudArticuloDTO.getidDeposito());
		solicitudArticulo.setIdSolicitud(solicitudArticuloDTO.getIdSolicitud());

		OrdenDespacho ordenDespacho = new OrdenDespacho();
		ordenDespacho.setIdOrdenDespacho(solicitudArticuloDTO
				.getIdOrdenDespacho());
		solicitudArticulo.setOrdenDespacho(ordenDespacho);

		ItemSolicitudArticulo itemSA;
		Set<ItemSolicitudArticulo> items = new HashSet<ItemSolicitudArticulo>();
		Articulo articulo;
		for (ItemSolicitudArticuloDTO item : solicitudArticuloDTO.getItems()) {

			articulo = new Articulo();
			articulo.setIdArticulo(item.getArticulo().getIdArticulo());

			itemSA = new ItemSolicitudArticulo();
			itemSA.setArticulo(articulo);
			itemSA.setCantidad(item.getCantidad());
			itemSA.setIdSolicitudArticulo(solicitudArticuloDTO.getIdSolicitud());

			items.add(itemSA);
		}
		solicitudArticulo.setItems(items);

		em.persist(solicitudArticulo);
		em.flush();

		return solicitudArticulo.getDTO();

	}

	private SolicitudArticulo buscarSolicitudArticulo(int idSolicitudArticulo) {

		SolicitudArticulo solicitudArticulo;

		try {
			solicitudArticulo = em.find(SolicitudArticulo.class,
					idSolicitudArticulo);
		} catch (NoResultException e) {
			System.out.println("### No hay una Solicitud de Articulo con ID: "
					+ idSolicitudArticulo);
			return null;
		}

		if (solicitudArticulo == null) {
			System.out.println("### No hay una Solicitud de Articulo con ID: "
					+ idSolicitudArticulo);
			return null;
		} else {
			System.out
					.println("### Se encontro una Solicitud de Articulo con ID: "
							+ idSolicitudArticulo);
			return solicitudArticulo;
		}

	}

	@Override
	public boolean registrarRecepcionDeStock(ArticulosRecibidos articulosRecibidos) {

		int idSolicitudArticulo = Integer.valueOf(articulosRecibidos
				.getIdSolicitud());
		String estado = "Recibido";
		boolean completa = true; 

		SolicitudArticulo solicitud = this
				.buscarSolicitudArticulo(idSolicitudArticulo);
		if (solicitud == null) {
			System.out
			.println("###  Se cancela la modificaci�n de la Solicitud");
			return false;
		} else {
			solicitud.setEstadoSolicitud(estado);
			try {
				em.merge(solicitud);

			} catch (Exception e) {
				System.out.println("### Fallo Update estado Solicitud Articulo");
				e.printStackTrace();
				return false;
			}
			System.out.println("### Se hizo Update estado Solicitud Articulo con ID: "+ solicitud.getIdSolicitud());
			OrdenDespacho ordenDespacho = administradorOrdenesDespachoBean.buscarOrdenDespacho(solicitud.getOrdenDespacho().getIdOrdenDespacho());
			//----------->>>>> 	ACTUALIZAR EL ESTADO DEL ITEM DE LA ORDEN DE DESPACHO			
			for (ItemSolicitudArticulo itemSolicitud : solicitud.getItems()) {
				for (ItemOrdenDespacho itemOrden : ordenDespacho.getItems()) {
					if ((itemOrden.getArticulo().getIdArticulo() == itemSolicitud.getArticulo().getIdArticulo()) &&
							(itemOrden.getCantidad() == itemSolicitud.getCantidad())) {
					itemOrden.setEstadoItems(estado);
					try {
						em.merge(ordenDespacho);

					} catch (Exception e) {
						System.out.println("### Fallo Update estado Item Orden Despacho");
						e.printStackTrace();
						return false;
						}
					}
					
				}
			}
			
			//------->>>>> SI TODOS LOS ITEMS FUERON RECIBIDOS, ACTUALIZO EL ESTADO EN LA CABECERA DE LA ORDEN 
			for (ItemOrdenDespacho itemOrden : ordenDespacho.getItems()) {
				if (itemOrden.getEstadoItems() != estado){
					completa = false;
				}
			}
			
			if (completa) {
				ordenDespacho.setEstadoOrden(estado);
				try {
					em.merge(ordenDespacho);

				} catch (Exception e) {
					System.out.println("### Fallo Update estado Orden Despacho");
					e.printStackTrace();
					return false;
					}
			
				System.out.println("### La Orden de Despacho " + ordenDespacho.getIdOrdenDespacho() + " esta completa, se envia mensaje a Logistica");
				clienteRestLogistica.enviarCambioEstado(ordenDespacho.getDTO());
				administradorOrdenesDespachoBean.notificarEntregaDespacho(ordenDespacho.getDTO());
				
			}
			
			return true;
		}

	}

	// Listar Solicitudes de Articulos por Estado
	public List<SolicitudArticuloDTO> listaSolicitudesArticuloPorEstado(
			String estado) {

		List<SolicitudArticulo> solicitudArticulos = new ArrayList<SolicitudArticulo>();
		List<SolicitudArticuloDTO> solicitudArticulosDTO = new ArrayList<SolicitudArticuloDTO>();

		Query q = em
				.createQuery("SELECT FROM SolicitudArticulo sa where sa.estadoSolicitud := estado");
		q.setParameter("estado", estado);
		solicitudArticulos = q.getResultList();

		// Recorro y conviero a DTO
		for (SolicitudArticulo solicitudArticulo : solicitudArticulos) {
			solicitudArticulosDTO.add(solicitudArticulo.getDTO());
		}

		return solicitudArticulosDTO;
	}

	public List<SolicitudArticuloDTO> listar(){
		List<SolicitudArticuloDTO> listaSolicitudArticulosSalida = null;
		Query q = em.createQuery("Select S from SolicitudArticulo S");
    	try{
    		List<SolicitudArticulo> solicitudesArticulos = q.getResultList();
    		System.out.println("Se encontraron " + solicitudesArticulos.size() + " solicitudes de articulos ");
    		listaSolicitudArticulosSalida = new ArrayList<SolicitudArticuloDTO>();
    		for(SolicitudArticulo solicitudArticulo : solicitudesArticulos){
    			listaSolicitudArticulosSalida.add(solicitudArticulo.getDTO());
    		}
    	}catch (Exception e){
    		System.out.println("No fue posible listar solicitudes de articulos");
    		e.printStackTrace();
    		return null;
    	}
    	return listaSolicitudArticulosSalida;
	}


}
