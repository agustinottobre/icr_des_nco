package despacho.dao;

import java.util.ArrayList;
import java.util.List;

import despacho.dominio.SolicitudArticulo;

public class SolicitudArticuloDao {
	
	public List<SolicitudArticulo> obtenerlistaSolicitudArticulo(){
		return new ArrayList<SolicitudArticulo>();
	}
	
	public List<SolicitudArticulo> obtenerlistaSolicitudesArticulosPorOrdenDespacho(){
		return new ArrayList<SolicitudArticulo>();
	}
	
	public SolicitudArticulo buscarSolicitudArticuloPorId(long id){
		return new SolicitudArticulo();
	}
}
