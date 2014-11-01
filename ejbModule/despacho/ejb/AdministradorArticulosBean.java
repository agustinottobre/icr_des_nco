package despacho.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.ejb.Stateless;

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
    public AdministradorArticulosBean() {}
    
    public String testEJB(){
    	return "Test EJB OK !";
    }

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
}
