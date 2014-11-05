package despacho.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import despacho.dominio.Articulo;
import despacho.ejb.interfaces.remotas.AdministradorArticulos;
import despacho.ejb.interfaces.remotas.ClienteJmsParaDeposito;
import dto.ArticuloDTO;
import dto.ItemSolicitudArticuloDTO;
import dto.SolicitudArticuloDTO;

/**
 * Session Bean implementation class AdministradorArticulosBean
 */
@Stateless
@Startup
@LocalBean
public class AdministradorArticulosBean implements AdministradorArticulos{

    /**
     * Default constructor. 
     */
	@PersistenceContext(unitName = "JPADB")
	private EntityManager em;
	
	@EJB
	private ClienteJmsParaDeposito clienteJmsParaDeposito;
		
    //--Metodos--
	
	public AdministradorArticulosBean() {}
    
    public String testEJB(){
    	SolicitudArticuloDTO solicitudArticuloDTO = new SolicitudArticuloDTO();
    	solicitudArticuloDTO.setEstadoSolicitud("pendiente");
    	solicitudArticuloDTO.setIdSolicitud(453);
    	solicitudArticuloDTO.setidDeposito(33);
    	List<ItemSolicitudArticuloDTO> items = new ArrayList<ItemSolicitudArticuloDTO>();
    	ItemSolicitudArticuloDTO itemSolicitudArticuloDTO = new ItemSolicitudArticuloDTO();
    	ArticuloDTO articuloDTO = new ArticuloDTO();
    	articuloDTO.setIdArticulo(655);
    	articuloDTO.setDescripcion("un articulo para solicitar");
    	itemSolicitudArticuloDTO.setArticulo(articuloDTO);
    	itemSolicitudArticuloDTO.setCantidad(5);
    	items.add(itemSolicitudArticuloDTO);
    	solicitudArticuloDTO.setItems(items);
    	clienteJmsParaDeposito.enviarSolicitudesArticulos(solicitudArticuloDTO);
    	return "Test EJB OK !";
    }
    private void guardarArticulo(Articulo articulo){
		em.persist(articulo);
	}
    
	@Override
	public ArticuloDTO altaArticulo(ArticuloDTO articuloDTO) {
		// TODO Auto-generated method stub
		Articulo articulo = new Articulo();
		articulo.setIdArticulo(articuloDTO.getIdArticulo());
		articulo.setDescripcion(articuloDTO.getDescripcion());
		this.guardarArticulo(articulo);
		return null;
	}

	@Override
	public ArticuloDTO BuscarArticulo(int idArticulo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArticuloDTO actualizarArticulo(ArticuloDTO articulo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ArticuloDTO> ArticulosPorDeposito(int idDeposito) {
		// TODO Auto-generated method stub
		return null;
	}	
}
