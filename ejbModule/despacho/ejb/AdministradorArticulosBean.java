package despacho.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.ejb.Stateless;

import despacho.ejb.interfaces.remotas.AdministradorArticulos;

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
}
