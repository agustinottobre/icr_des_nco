package despacho.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import despacho.dominio.Articulo;
import despacho.ejb.interfaces.remotas.AdministradorArticulos;
import dto.ArticuloDTO;

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
		
    //--Metodos--
	
	public AdministradorArticulosBean() {}
    
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
