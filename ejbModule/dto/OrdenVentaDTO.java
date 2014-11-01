package dto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity(name = "OrdenesVenta")
public class OrdenVentaDTO {
	
	@Id
	//@GeneratedValue (strategy = GenerationType.AUTO)
	private int idOrdenVenta;
	
	@OneToOne (cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private PortalDTO portal;

	public int getIdOrdenVenta() {
		return idOrdenVenta;
	}

	public void setIdOrdenVenta(int idOrdenVenta) {
		this.idOrdenVenta = idOrdenVenta;
	}

	public PortalDTO getPortal() {
		return portal;
	}

	public void setPortal(PortalDTO portal) {
		this.portal = portal;
	}

}
