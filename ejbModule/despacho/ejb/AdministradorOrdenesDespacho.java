package despacho.ejb;

import javax.ejb.Remote;

@Remote
public interface AdministradorOrdenesDespacho {
	
	public long obtenerCantidadOrdenesDespacho();

}
