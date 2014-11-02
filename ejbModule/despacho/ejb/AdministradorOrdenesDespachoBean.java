package despacho.ejb;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import despacho.dominio.Articulo;
import despacho.dominio.ItemOrdenDespacho;
import despacho.dominio.OrdenDespacho;
import despacho.dominio.OrdenVenta;
import despacho.dominio.Portal;
import despacho.ejb.interfaces.remotas.AdministradorOrdenesDespacho;
import dto.ItemOrdenDespachoDTO;

//import despacho.ws.servicios.consumidos.ServidorEstadoEntregaBean;
//import despacho.ws.servicios.consumidos.ServidorEstadoEntregaBeanService;

import dto.ItemSolicitudArticuloDTO;
import dto.OrdenDespachoDTO;
import dto.SolicitudArticuloDTO;

/**
 * Session Bean implementation class AdministradorOrdenesDespachoBean
 */
@Stateless
@LocalBean
public class AdministradorOrdenesDespachoBean implements AdministradorOrdenesDespacho {

    @PersistenceContext(unitName = "JPADB")
    private EntityManager em;


    public AdministradorOrdenesDespachoBean() {}



	@Override
	public OrdenDespachoDTO altaOrdenDespacho(OrdenDespachoDTO ordenDespachoDTO) {
		
		//Buscar el objeto en la base de datos.
		OrdenDespachoDTO ordenBaseDatos;
		ordenBaseDatos = this.buscarOrdenDespacho(String.valueOf(ordenDespachoDTO.getIdOrdenDespacho()));
		
		//Si la orden existe en la Base de Datos muestro un mensaje
		if (ordenBaseDatos != null) {
			System.out.println("Ya existe una Orden de Despacho con el mismo ID");
			return null;
			
		} else {
			System.out.println("Se da de alta una Orden de Despacho con el ID: " + String.valueOf(ordenDespachoDTO.getIdOrdenDespacho()));
		
			Portal portal = new Portal();
			portal.setIdPortal(ordenDespachoDTO.getOrdenVenta().getPortal().getIdPortal());
			portal.setDescripcion(ordenDespachoDTO.getOrdenVenta().getPortal().getDescripcion());
		
			OrdenVenta ordenVenta = new OrdenVenta();
			ordenVenta.setIdOrdenVenta(ordenDespachoDTO.getOrdenVenta().getIdOrdenVenta());
			ordenVenta.setPortal(portal);
			
			OrdenDespacho ordenDespacho = new OrdenDespacho();
			ordenDespacho.setIdOrdenDespacho(ordenDespachoDTO.getIdOrdenDespacho());
			ordenDespacho.setEstadoOrden(ordenDespachoDTO.getEstadoOrden());
			DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
			try {
				ordenDespacho.setFechaRecepcion(df.parse(ordenDespachoDTO.getFechaRecepcion()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			ItemOrdenDespacho itemOD;
			List<ItemOrdenDespacho> items = new ArrayList<ItemOrdenDespacho>();
			Articulo articulo;
			for (ItemOrdenDespachoDTO item : ordenDespachoDTO.getItems())
			{
				
				articulo = new Articulo();
				articulo.setIdArticulo(item.getArticulo().getIdArticulo());
				
				itemOD = new ItemOrdenDespacho();
				itemOD.setArticulo(articulo);
				itemOD.setCantidad(item.getCantidad());
				itemOD.setEstadoItems(item.getEstadoItems());
				
				items.add(itemOD);
			}
			ordenDespacho.setItems(items);
			
			em.persist(ordenDespacho);
		
//			FALTA VERIFICAR SI SE HACE BIEN EL ALTA Y DEVOLVER lo que corresponda

			return ordenDespachoDTO;	
		
		}	
		
	}



	@Override
	public OrdenDespachoDTO buscarOrdenDespacho(String idOrdenDespacho) {
		// TODO Auto-generated method stub
		
		Query q = em.createQuery("SELECT FROM OrdenesDespacho o WHERE o.idOrdenDespacho = :id");
				q.setParameter("id", idOrdenDespacho);
				
		OrdenDespacho ordenDespacho = (OrdenDespacho)q.getSingleResult();
	
		return ordenDespacho.getDTO();
	}



	@Override
	public List<SolicitudArticuloDTO> generarSolicitudArticuloPorDeposito(OrdenDespachoDTO ordenDespachoDTO) {
		// TODO Auto-generated method stub
		
		OrdenDespachoDTO ordenDespachoDTOAux = ordenDespachoDTO;
		List<SolicitudArticuloDTO> solicitudesGeneradas = new ArrayList<SolicitudArticuloDTO>();
		
		SolicitudArticuloDTO solicitudGenerada;
		int deposito1, deposito2;
		for (ItemOrdenDespachoDTO item1 : ordenDespachoDTOAux.getItems())
		{
			Boolean procesado = false;
			deposito1 = item1.getArticulo().getIdDeposito();
			
			solicitudGenerada = new SolicitudArticuloDTO();
			solicitudGenerada.setEstadoSolicitud("Nueva");
			solicitudGenerada.setIdDepostio(deposito1);
			
			
			for (ItemOrdenDespachoDTO item2 : ordenDespachoDTOAux.getItems())
			{			
				deposito2 = item2.getArticulo().getIdDeposito();
				ItemSolicitudArticuloDTO itemSolicitud;
				if (deposito1 == deposito2) {
					procesado = true;
					itemSolicitud = new ItemSolicitudArticuloDTO();
					itemSolicitud.setArticulo(item2.getArticulo());
					itemSolicitud.setCantidad(item2.getCantidad());
					solicitudGenerada.getItems().add(itemSolicitud);
					ordenDespachoDTOAux.getItems().remove(item2);	
				}		
			}
			
			if (procesado) {
				solicitudesGeneradas.add(solicitudGenerada);
			}
			
		}
			
		return solicitudesGeneradas;
	}
	
	
	
	
	/*@Override
	public String notificarEntregaDespacho (int idOrdenDespacho){
        System.out.println("***********************");
        System.out.println("Creando Cliente Web Service para Portal...");
        ServidorEstadoEntregaBeanService service1 = new ServidorEstadoEntregaBeanService();
        System.out.println("Creando Web Service...");
        ServidorEstadoEntregaBean port1 = service1.getServidorEstadoEntregaBeanPort();
        System.out.println("LLamada al Web Service de Portal... notificarEntregaDespacho");
        String respuesta = port1.notificarEntregaDespacho(idOrdenDespacho);
        System.out.println("Server said: " + respuesta);
		return respuesta;
	}*/
    
}
