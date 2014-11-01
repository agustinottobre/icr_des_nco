package despacho.dominio;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity(name = "OrdenesVenta")
public class OrdenVenta {
	
	@Id
	//@GeneratedValue (strategy = GenerationType.AUTO)
	private int idOrdenVenta;
	
	@OneToOne (cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Portal portal;

}
