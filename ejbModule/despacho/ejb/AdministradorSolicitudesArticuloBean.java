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
		solicitudArticulo.setIdDepostio(solicitudArticuloDTO.getIdDepostio());
		solicitudArticulo.setIdSolicitud(solicitudArticuloDTO.getIdSolicitud());
		
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

	@Override
	public SolicitudArticuloDTO buscarSolicitudArticulo(
			String idSolicitudArticulo) {
		// TODO Auto-generated method stub

		Query q = em.createQuery("SELECT FROM SolicitudesArticulo sol WHERE sol.idSolicitudArticulo = :id");
		q.setParameter("id", idSolicitudArticulo);
		
		SolicitudArticulo solicitud = (SolicitudArticulo)q.getSingleResult();

		return solicitud.getDTO();
	}
    


}
