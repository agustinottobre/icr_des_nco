package despacho.ejb;

import javax.ejb.Remote;

import despacho.dominio.Portal;

//interfaz del session bean AdministradorArticulos
@Remote
public interface AdministradorTestJPA {
	
	public void persistirPortal(Portal portal);
}
