package despacho.ejb;

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
    
    public String testEJB(){
    	return "Test EJB OK !";
    }


    private void guardarArticulo(Articulo articulo){
		em.persist(articulo);
	}
    
	@Override
	public ArticuloDTO altaArticulo(String codigo, String nombre, String idModulo) {
		// TODO Auto-generated method stub
		Articulo articulo = new Articulo();
		articulo.setIdArticulo(Integer.parseInt(codigo));
		articulo.setDescripcion(nombre);
		this.guardarArticulo(articulo);
		return null;
	}

	@Override
	public ArticuloDTO BuscarArticulo(int idArticulo) {
		// TODO Auto-generated method stub
		return null;
	}
	
/*
	@Override
	public ArticuloDTO altaArticulo(String codigo, String nombre,
			String idModulo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArticuloDTO BuscarArticulo(int idArticulo) {
		// TODO Auto-generated method stub
		return null;
	}
*/
}
