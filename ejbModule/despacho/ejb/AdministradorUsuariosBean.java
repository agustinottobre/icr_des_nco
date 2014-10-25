package despacho.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;

import despacho.ejb.interfaces.remotas.AdministradorUsuarios;

/**
 * Session Bean implementation class AdministradorUsuariosBean
 */
@Stateful
@LocalBean
public class AdministradorUsuariosBean implements AdministradorUsuarios {

    /**
     * Default constructor. 
     */
    public AdministradorUsuariosBean() {
        // TODO Auto-generated constructor stub
    }

}
