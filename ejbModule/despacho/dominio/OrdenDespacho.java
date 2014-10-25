package despacho.dominio;

import java.util.Date;
import java.util.List;

public class OrdenDespacho {

	private int idOrdenDespacho;
	private int idOrdenventa;
	private String estadoOrden;
	private Date fechaRecepcion;
	private List<itemOrdenDespacho> items;
	
}
