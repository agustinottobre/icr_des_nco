package despacho.dao;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import despacho.dominio.OrdenDespacho;

public class OrdenDespachoDao {

	private static Logger logger = LoggerFactory.getLogger(OrdenDespacho.class);

	public List<OrdenDespacho> obtenerlistaOrdenesDespacho(){
		return new ArrayList<OrdenDespacho>();
	}
	
	public OrdenDespacho buscarOrdenDespachoPorId(long id){
		return new OrdenDespacho();
	}
	
	public void testLog(){
		logger.info("TEST DEL LOG INFO !!!!!!!!!!");
		logger.error("TEST DEL LOG ERROR !!!!!!!!!!");
	}
}
