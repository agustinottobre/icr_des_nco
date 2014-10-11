package despacho.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class AdministradorSolicitudArticulosBean
 */
@Stateless
@LocalBean
public class AdministradorSolicitudesArticuloBean implements AdministradorSolicitudesArticulo {

    /**
     * Default constructor. 
     */
    public AdministradorSolicitudesArticuloBean() {}
    
    public long obtenerCantidadSolicitudesArticulo(){
    	return 0;
    }

}
