package despacho.dominio;

import java.util.Date;
import java.util.List;

public class OrdenDespacho {

	private long id;
	private Portal portal;
	private String estado;
	private Date fechaCreacion;
	private Date fechaEntrega;
	private List<SolicitudArticulo> articulosSolicitados;
}
