package despacho.dominio;

import java.util.Date;

import dto.ArticuloDTO;
import dto.HistorialOrdenDespachoDTO;

public class HistorialOrdenDespacho {

	private Date timestamp;
	private OrdenDespacho orden;
	private String evento;
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public OrdenDespacho getOrden() {
		return orden;
	}
	public void setOrden(OrdenDespacho orden) {
		this.orden = orden;
	}
	public String getEvento() {
		return evento;
	}
	public void setEvento(String evento) {
		this.evento = evento;
	} 
	
	public HistorialOrdenDespachoDTO getDTO() {
		HistorialOrdenDespachoDTO historialOrdenDespachoDTO = new HistorialOrdenDespachoDTO();
		
		historialOrdenDespachoDTO.setEvento(this.getEvento());
		historialOrdenDespachoDTO.setOrden(this.getOrden().getDTO());
		historialOrdenDespachoDTO.setTimestamp(this.getTimestamp().toString());
		
		return historialOrdenDespachoDTO;
	}	
	
}
