package despacho.dominio;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity(name = "itemsOrdenDespacho")
public class itemOrdenDespacho {

	@Id
	//@GeneratedValue (strategy = GenerationType.AUTO)
	
		private int idItemOD;
	
	@OneToOne (cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
		private Articulo articulo;
		private int cantidad;
		
		@Column (columnDefinition="nvarchar")
		private String estadoItems;
		
}
