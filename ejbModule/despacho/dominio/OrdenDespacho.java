package despacho.dominio;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity(name = "OrdenesDespacho")
public class OrdenDespacho {

	@Id
	//@GeneratedValue (strategy = GenerationType.AUTO)
	private int idOrdenDespacho;
	
	@OneToOne (cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private OrdenVenta ordenVenta;
	
	@Column (columnDefinition="nvarchar")
	private String estadoOrden;
	private Date fechaRecepcion;
	
	@OneToMany (cascade = CascadeType.ALL)
	@JoinColumn(name = "idOrdenDespacho")
	private List<itemOrdenDespacho> items;
	
}
