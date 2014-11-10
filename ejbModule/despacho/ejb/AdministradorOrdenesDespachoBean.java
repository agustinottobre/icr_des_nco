package despacho.ejb;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
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
import despacho.dominio.OrdenDespacho;
import despacho.dominio.OrdenVenta;
import despacho.dominio.Portal;
import despacho.ejb.interfaces.remotas.AdministradorOrdenesDespacho;
import dto.ItemOrdenDespachoDTO;
import despacho.ws.servicios.consumidos.ServidorEstadoEntregaBean;
import despacho.ws.servicios.consumidos.ServidorEstadoEntregaBeanService;
import dto.ArticuloDTO;
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
    
	@EJB
	AdministradorSolicitudesArticuloBean administradorSolicitudesArticulo = new AdministradorSolicitudesArticuloBean();
	
	@EJB
	AdministradorArticulosBean administradorArticulosBean = new AdministradorArticulosBean();
	
    public AdministradorOrdenesDespachoBean() {}

//--------------------------------------------------------------------    
//-----------------------------------ALTA ORDENES DE DESPACHO	
    @Override
	public boolean altaOrdenDespacho(OrdenDespachoDTO ordenDespachoDTO) {
		
		//Busco la OD en la BD, si ya existe una con el mismo ID, no la persiste 
		System.out.println("###### Comienza busqueda de Orden de Despacho");
		OrdenDespacho ordenBaseDatos;
		ordenBaseDatos = this.buscarOrdenDespacho(ordenDespachoDTO.getIdOrdenDespacho());
		if (ordenBaseDatos != null) {
			System.out.println("### Ya hay una orden de Despacho con ID: " + ordenDespachoDTO.getIdOrdenDespacho());
			return false;
		
		//Si la OD NO existe en la Base de Datos, PERSISTO
		} else {
			System.out.println("###### Comienza Alta de Orden de Despacho con el ID: " + String.valueOf(ordenDespachoDTO.getIdOrdenDespacho()));
		
			Portal portal = new Portal();
			portal.setIdPortal(ordenDespachoDTO.getOrdenVenta().getPortal().getIdPortal());
			portal.setDescripcion(ordenDespachoDTO.getOrdenVenta().getPortal().getDescripcion());
		
			OrdenVenta ordenVenta = new OrdenVenta();
			ordenVenta.setIdOrdenVenta(ordenDespachoDTO.getOrdenVenta().getIdOrdenVenta());
			ordenVenta.setPortal(portal);
			
			OrdenDespacho ordenDespacho = new OrdenDespacho();
			ordenDespacho.setIdOrdenDespacho(ordenDespachoDTO.getIdOrdenDespacho());
			ordenDespacho.setEstadoOrden("Nueva");
			ordenDespacho.setFechaRecepcion(new Date());
			ordenDespacho.setOrdenVenta(ordenVenta);
			
			ItemOrdenDespacho itemOD;
			Set<ItemOrdenDespacho> items = new HashSet<ItemOrdenDespacho>();
			Articulo articulo;
			for (ItemOrdenDespachoDTO item : ordenDespachoDTO.getItems())
			{
				
				articulo = administradorArticulosBean.BuscarArticulo(item.getArticulo().getIdArticulo());
				if (articulo == null) {
					System.out.println("### Fallo alta orden de Despacho - No existe el articulo: " + item.getArticulo().getIdArticulo());
					return false;
				}
		
				itemOD = new ItemOrdenDespacho();
				itemOD.setIdOrdenDespacho(item.getIdOrdenDespacho());
				itemOD.setArticulo(articulo);
				itemOD.setCantidad(item.getCantidad());
				itemOD.setEstadoItems(item.getEstadoItems());
				
				items.add(itemOD);
			}
			ordenDespacho.setItems(items);
			
			try {
				em.merge(ordenDespacho);
				
			} catch (Exception e) {
				System.out.println("### Fallo alta orden de Despacho");
				e.printStackTrace();
				return false;
			}
			System.out.println("### Se dio de alta orden de Despacho con ID: " + ordenDespachoDTO.getIdOrdenDespacho());
			return true;	
		}	
		
	}



	public OrdenDespacho buscarOrdenDespacho(int idOrdenDespacho) {
		
		OrdenDespacho ordenDespacho;
		
		try {
			ordenDespacho = em.find(OrdenDespacho.class, idOrdenDespacho);
		} catch (NoResultException e) {
			System.out.println("### No hay una orden de Despacho con ID: " + idOrdenDespacho);
			return null;
		}
		
		if (ordenDespacho == null) {
			System.out.println("### No hay una orden de Despacho con ID: " + idOrdenDespacho);
			return null;
		} else {
			System.out.println("### Se encontro orden de Despacho con ID: " + idOrdenDespacho);
			return ordenDespacho;
		}

	}



	


//--------------------------------------------------------------------  	
//-----------------------------------PROCESAR ORDENES DE DESPACHO
	@Override
	public List<SolicitudArticuloDTO> procesarSolicitudDespacho(OrdenDespachoDTO ordenDespachoDTO) {
		
		List<SolicitudArticuloDTO> solicitudesGeneradas = new ArrayList<SolicitudArticuloDTO>();
		
		SolicitudArticuloDTO solicitudGenerada;
		int deposito;
		Articulo articulo;
		for (ItemOrdenDespachoDTO item : ordenDespachoDTO.getItems())
		{
			articulo = administradorArticulosBean.BuscarArticulo(item.getArticulo().getIdArticulo());
			deposito = articulo.getIdDeposito();
			
			solicitudGenerada = new SolicitudArticuloDTO();
			solicitudGenerada.setEstadoSolicitud("Nueva");
			solicitudGenerada.setidDeposito(deposito);
			solicitudGenerada.setIdOrdenDespacho(ordenDespachoDTO.getIdOrdenDespacho());
			solicitudGenerada.setItems(new HashSet<ItemSolicitudArticuloDTO>());
	
			ItemSolicitudArticuloDTO itemSolicitud = new ItemSolicitudArticuloDTO();
			itemSolicitud.setArticulo(item.getArticulo());
			itemSolicitud.setCantidad(item.getCantidad());
			solicitudGenerada.getItems().add(itemSolicitud);

			solicitudGenerada = administradorSolicitudesArticulo.altaSolicitudArticulo(solicitudGenerada);
			solicitudesGeneradas.add(solicitudGenerada);
			

		}
		
		return solicitudesGeneradas;
	}


	
//	 //Recorro todas las ordenes de Despacho con estado "Nueva"
//	public void verificarEstadoOrdenesDeDespacho(){
//
//	List<OrdenDespacho> ordenesDespacho = new ArrayList<OrdenDespacho>();
//	Query q = em.createQuery("SELECT FROM OrdenesDespacho od where od.estadoItems := id");
//	q.setParameter("id", "Nueva");
//	ordenesDespacho = q.getResultList();
//
//	for (OrdenDespacho ordenDespacho : ordenesDespacho) {
//	verificarEstadoOrdenDeDespacho(ordenDespacho.getIdOrdenDespacho());
//	}
//
//	}


//	//Chequeo que una Orden de Despacho se encuentre completa, para cambiar el estado.
//	private boolean verificarEstadoOrdenDeDespacho(int idOrdenDespacho){
//
//	//Busco Orden de Despacho
//	OrdenDespacho od = buscarOrdenDespacho(idOrdenDespacho);
//
//	//Levanto los Items
//	Set<ItemOrdenDespacho> ordenes = new HashSet<ItemOrdenDespacho>();
//	ordenes = od.getItems();
//
//	//Recorro todos los items, cambio el Flag, si alg�n item no est� completo.
//	boolean flag = true;
//	for (ItemOrdenDespacho itemOrdenDespacho : ordenes) {
//	if(itemOrdenDespacho.getEstadoItems().equals("Pendiente")){
//	flag = false;
//	}
//	}
//
//	if(flag==true){
//	// Si el flag queda en TRUE, quiere decir que la Orden de Despacho tiene todos
//	// los items completos. Cambio el Estado!
//	cambiarEstadoOrdenDeDespacho(idOrdenDespacho);
//	return true;
//	}
//	return false;
//
//	}


//	//Busco una Orden de Despacho por su ID y cambio el estado de "Nueva" a "Entregada"
//	private void cambiarEstadoOrdenDeDespacho (int idOrdenDespacho){
//
//	OrdenDespacho od = buscarOrdenDespacho(idOrdenDespacho);
//	od.setEstadoOrden("Entregada");
//
//	try{
//	em.merge(od);
//	}catch (Exception e) {
//	System.out.println("### Fallo, no pudo grabar la orden de despacho con el nuevo estado");
//	e.printStackTrace();
//	}
//
//	// IMPORTANTE - Enviar� la notificaci�n del cambio de estado de la Orden de Despacho
//	notificarEntregaDespacho(idOrdenDespacho);
//
//	}
	
	//Listar Ordenes de Despacho por Estado
	public List<OrdenDespachoDTO> listarOrdenesDespacho(String estado){

		List<OrdenDespacho> ordenesDespacho = new ArrayList<OrdenDespacho>();
		List<OrdenDespachoDTO> ordenesDespachoDTO = new ArrayList<OrdenDespachoDTO>();

		Query q = em.createQuery("SELECT FROM OrdenDespacho od where od.estadoOrden := estado");
		q.setParameter("estado", estado);
		ordenesDespacho = q.getResultList();

		//Recorro y conviero a DTO
		for (OrdenDespacho ordenDespacho : ordenesDespacho) {
			ordenesDespachoDTO.add(ordenDespacho.getDTO());
		}

		return ordenesDespachoDTO;
	}

	public List<OrdenDespachoDTO> listar(){
		List<OrdenDespachoDTO> listaOrdenesDespachoSalida = null;
		Query q = em.createQuery("Select O from OrdenDespacho O");
    	try{
    		List<OrdenDespacho> ordenesDespacho = q.getResultList();
    		System.out.println("Se encontraron " + ordenesDespacho.size() + " ordenes de despacho ");
    		listaOrdenesDespachoSalida = new ArrayList<OrdenDespachoDTO>();
    		for(OrdenDespacho ordenDespacho : ordenesDespacho){
    			listaOrdenesDespachoSalida.add(ordenDespacho.getDTO());
    		}
    	}catch (Exception e){
    		System.out.println("No fue posible listar Ordenes de despacho");
    		e.printStackTrace();
    		return null;
    	}
    	return listaOrdenesDespachoSalida;
	}

	
	public String notificarEntregaDespacho (int idOrdenDespacho){
        System.out.println("***********************");
        System.out.println("Creando Cliente Web Service para Portal...");
        ServidorEstadoEntregaBeanService service1 = new ServidorEstadoEntregaBeanService();
        System.out.println("Creando Web Service...");
        ServidorEstadoEntregaBean port1 = service1.getServidorEstadoEntregaBeanPort();
        System.out.println("LLamada al Web Service de Portal... notificarEntregaDespacho");
        String respuesta = port1.notificarEntregaDespacho(idOrdenDespacho);
        System.out.println("Respuesta Portal: " + respuesta);
		return respuesta;
	}
    
}
