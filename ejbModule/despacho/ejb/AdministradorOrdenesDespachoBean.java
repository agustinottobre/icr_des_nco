package despacho.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import despacho.ejb.interfaces.remotas.AdministradorOrdenesDespacho;

/**
 * Session Bean implementation class AdministradorOrdenesDespachoBean
 */
@Stateless
@LocalBean
public class AdministradorOrdenesDespachoBean implements AdministradorOrdenesDespacho {

    /**
     * Default constructor. 
     */
    public AdministradorOrdenesDespachoBean() {}
    
    public long obtenerCantidadOrdenesDespacho(){
    	return 0;
    }

}
