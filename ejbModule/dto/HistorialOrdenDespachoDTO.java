package dto;

import java.util.Date;

public class HistorialOrdenDespachoDTO {

	private String timestamp;
	private OrdenDespachoDTO orden;
	private String evento;
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public OrdenDespachoDTO getOrden() {
		return orden;
	}
	public void setOrden(OrdenDespachoDTO orden) {
		this.orden = orden;
	}
	public String getEvento() {
		return evento;
	}
	public void setEvento(String evento) {
		this.evento = evento;
	} 
	
}
