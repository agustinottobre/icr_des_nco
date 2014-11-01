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

@Entity(name = "SolicitudesArticulo")
public class SolicitudArticulo {
	
	@Id
	//@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name = "idSolicitudArticulo")
	private int idSolicitud;
	
	@Column (columnDefinition="nvarchar")
	private String estadoSolicitud;
	
	private int idDepostio;
	
	@OneToMany (cascade = CascadeType.ALL)
	@JoinColumn(name = "idSolicitudArticulo")
	private List<ItemSolicitudArticulo> items;

}
