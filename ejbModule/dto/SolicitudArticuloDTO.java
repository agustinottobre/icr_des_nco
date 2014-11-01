package dto;

import java.util.Date;
import java.util.List;

public class SolicitudArticuloDTO {
	
	private int idSolicitud;
	private String estadoSolicitud;
	private int idDepostio;
	private List<ItemSolicitudArticuloDTO> items;
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
	public List<ItemSolicitudArticuloDTO> getItems() {
		return items;
	}
	public void setItems(List<ItemSolicitudArticuloDTO> items) {
		this.items = items;
	}

}
