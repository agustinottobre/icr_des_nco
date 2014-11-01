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

import dto.OrdenDespachoDTO;
import dto.SolicitudArticuloDTO;

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

	public int getIdSolicitud() {
		return idSolicitud;
	}

	public void setIdSolicitud(int idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	public String getEstadoSolicitud() {
		return estadoSolicitud;
	}

	public void setEstadoSolicitud(String estadoSolicitud) {
		this.estadoSolicitud = estadoSolicitud;
	}

	public int getIdDepostio() {
		return idDepostio;
	}

	public void setIdDepostio(int idDepostio) {
		this.idDepostio = idDepostio;
	}

	public List<ItemSolicitudArticulo> getItems() {
		return items;
	}

	public void setItems(List<ItemSolicitudArticulo> items) {
		this.items = items;
	}
	
	public SolicitudArticuloDTO getDTO() {
		SolicitudArticuloDTO solicitudArticuloDTO = new SolicitudArticuloDTO();
		
		solicitudArticuloDTO.setEstadoSolicitud(this.getEstadoSolicitud());
		solicitudArticuloDTO.setIdDepostio(this.getIdDepostio());
		solicitudArticuloDTO.setIdSolicitud(this.getIdSolicitud());
		for (ItemSolicitudArticulo item : this.getItems())
		{
			solicitudArticuloDTO.getItems().add(item.getDTO());
		}
		
		return solicitudArticuloDTO;
	}

}
