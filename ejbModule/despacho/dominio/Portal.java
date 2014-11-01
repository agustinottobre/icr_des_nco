package despacho.dominio;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import dto.OrdenVentaDTO;
import dto.PortalDTO;

@Entity(name = "Portales")
public class Portal {
	@Id
	//@GeneratedValue (strategy = GenerationType.AUTO)
	private int idPortal;
	@Column (name = "nombrePortal", columnDefinition="nvarchar")
	private String descripcion;
	
	public int getIdPortal() {
		return idPortal;
	}
	public void setIdPortal(int idPortal) {
		this.idPortal = idPortal;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public PortalDTO getDTO() {
		PortalDTO  portalDTO = new  PortalDTO();

		portalDTO.setDescripcion(this.getDescripcion());
		portalDTO.setIdPortal(this.getIdPortal());
		 
		 return portalDTO;
	}
}
