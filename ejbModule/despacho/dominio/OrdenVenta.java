package despacho.dominio;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import dto.OrdenDespachoDTO;
import dto.OrdenVentaDTO;

@Entity
@Table(name = "OrdenesVenta")
public class OrdenVenta implements Serializable{
	
	@Id
	//@GeneratedValue (strategy = GenerationType.AUTO)
	private int idOrdenVenta;
	
	@OneToOne (cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "idPortal")
	private Portal portal;

	public int getIdOrdenVenta() {
		return idOrdenVenta;
	}

	public void setIdOrdenVenta(int idOrdenVenta) {
		this.idOrdenVenta = idOrdenVenta;
	}

	public Portal getPortal() {
		return portal;
	}

	public void setPortal(Portal portal) {
		this.portal = portal;
	}

	public OrdenVentaDTO getDTO() {
		 OrdenVentaDTO  ordenVentaDTO = new  OrdenVentaDTO();

		 ordenVentaDTO.setIdOrdenVenta(this.getIdOrdenVenta());
		 ordenVentaDTO.setPortal(this.getPortal().getDTO());
		 
		 return ordenVentaDTO;
	}
}
