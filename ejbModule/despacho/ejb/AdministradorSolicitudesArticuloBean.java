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
import despacho.rest.bindings.ArticulosRecibidos;
import despacho.rest.bindings.Item;
import dto.ArticuloDTO;
import dto.ItemOrdenDespachoDTO;
import dto.ItemSolicitudArticuloDTO;
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

		return solicitudArticuloDTO;

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

		SolicitudArticulo solicitud = this
				.buscarSolicitudArticulo(idSolicitudArticulo);
		if (solicitud == null) {
			System.out
			.println("###  Se cancela la modificación de la Solicitud");
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
			
			//------->>>>> SI TODOS LOS ITEMS FUERON RECIBIDOS, ACTUALIZO EL ESTADO EN LA CABECERA DE LA ORDEN 
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

}
