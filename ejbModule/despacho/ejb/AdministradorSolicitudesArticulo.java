package despacho.ejb;

import javax.ejb.Remote;

@Remote
public interface AdministradorSolicitudesArticulo {

	public long obtenerCantidadSolicitudesArticulo();
}
