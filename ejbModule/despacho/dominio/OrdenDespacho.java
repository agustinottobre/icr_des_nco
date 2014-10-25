package despacho.dominio;

import java.util.Date;
import java.util.List;

public class OrdenDespacho {

	private int idOrdenDespacho;
	private OrdenVenta ordenVenta;
	private String estadoOrden;
	private Date fechaRecepcion;
	private List<itemOrdenDespacho> items;
	
}
