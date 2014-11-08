package despacho.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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
public class AdministradorSolicitudesArticuloBean implements AdministradorSolicitudesArticulo {

    @PersistenceContext(unitName = "JPADB")
    private EntityManager em;
    
    public AdministradorSolicitudesArticuloBean() {}

	@Override
	public SolicitudArticuloDTO altaSolicitudArticulo(SolicitudArticuloDTO solicitudArticuloDTO) {
		// TODO Auto-generated method stub
		SolicitudArticulo solicitudArticulo = new SolicitudArticulo();
		solicitudArticulo.setEstadoSolicitud(solicitudArticuloDTO.getEstadoSolicitud());
		solicitudArticulo.setidDeposito(solicitudArticuloDTO.getidDeposito());
		solicitudArticulo.setIdSolicitud(solicitudArticuloDTO.getIdSolicitud());
		
		OrdenDespacho ordenDespacho = new OrdenDespacho();
		ordenDespacho.setIdOrdenDespacho(solicitudArticuloDTO.getIdOrdenDespacho());
		solicitudArticulo.setOrdenDespacho(ordenDespacho);
		
		ItemSolicitudArticulo itemSA;
		List<ItemSolicitudArticulo> items = new ArrayList<ItemSolicitudArticulo>();
		Articulo articulo;
		for (ItemSolicitudArticuloDTO item : solicitudArticuloDTO.getItems())
		{
			
			articulo = new Articulo();
			articulo.setIdArticulo(item.getArticulo().getIdArticulo());
			
			itemSA = new ItemSolicitudArticulo();
			itemSA.setArticulo(articulo);
			itemSA.setCantidad(item.getCantidad());
			
			items.add(itemSA);
		}
		solicitudArticulo.setItems(items);
		
		em.persist(solicitudArticulo);

		
//		FALTA VERIFICAR SI SE HACE BIEN EL ALTA Y DEVOLVER EL DTO EN CADA CASO
		return solicitudArticuloDTO;
	}

	
	private SolicitudArticuloDTO buscarSolicitudArticulo(
			String idSolicitudArticulo) {
		// TODO Auto-generated method stub

		Query q = em.createQuery("SELECT FROM SolicitudesArticulo sol WHERE sol.idSolicitudArticulo = :id");
		q.setParameter("id", idSolicitudArticulo);
		
		SolicitudArticulo solicitud = (SolicitudArticulo)q.getSingleResult();

		return solicitud.getDTO();
	}
    
	
	//Voy a recibir el id de la solicitud de articulo, codigos de articulos y cantidades desde los depositos.
	// Debo modificar el estado de la
	public boolean registrarRecepcionDeStock(ArticulosRecibidos articulosRecibidos){

	List<Item> item = articulosRecibidos.getItems();
	ItemSolicitudArticulo itemSA = new ItemSolicitudArticulo();
	int idSolicitudArticulo = Integer.valueOf(articulosRecibidos.getIdSolicitud());
	String estado = "Pendiente";

	//Recorro los items y actualizo el estado en la Base de Datos
	for (Item item2 : item) {

	//Busco el item solicitud de articulo en la BD y la traigo.
	Query q = em.createQuery("SELECT FROM ItemSolicitudesArticulo sol WHERE sol.idSolicitudArticulo = :id and sol.idArticulo =:ida");
	q.setParameter("id", idSolicitudArticulo);
	q.setParameter("ida", item2.getCodigo());

	//Traigo la solicitud, cambio el estado y la mergeo
	//Si la cantidad es igual a la cantidad enviada, doy por cerrada.
	itemSA = (ItemSolicitudArticulo) q.getSingleResult();

	if(itemSA.getCantidad() == item2.getCantidad()){
	//Agregar campo en la BD
	//itemSA.getEstado("Recibido");

	try{
	em.merge(itemSA);
	}catch (Exception e) {
	System.out.println("### Fallo, revisar");
	e.printStackTrace();
	return false;
	}
	}

	}
	//Recorri todos los items y pude verificar el estado
	return true;

	}
	
	
	 //Listar Solicitudes de Articulos por Estado
	public List<SolicitudArticuloDTO> listaSolicitudesArticuloPorEstado(String estado){

	List<SolicitudArticulo> solicitudArticulos = new ArrayList<SolicitudArticulo>();
	List<SolicitudArticuloDTO> solicitudArticulosDTO = new ArrayList<SolicitudArticuloDTO>();

	Query q = em.createQuery("SELECT FROM SolicitudArticulo sa where sa.estadoSolicitud := estado");
	q.setParameter("estado", estado);
	solicitudArticulos = q.getResultList();

	//Recorro y conviero a DTO
	for (SolicitudArticulo solicitudArticulo : solicitudArticulos) {
	solicitudArticulosDTO.add(solicitudArticulo.getDTO());
	}

	return solicitudArticulosDTO;
	}



}
