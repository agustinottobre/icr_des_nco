package despacho.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import despacho.ejb.interfaces.remotas.AdministradorOrdenesDespacho;
import despacho.ws.servicios.consumidos.ServidorEstadoEntregaBean;
import despacho.ws.servicios.consumidos.ServidorEstadoEntregaBeanService;
import dto.OrdenDespachoDTO;

/**
 * Session Bean implementation class AdministradorOrdenesDespachoBean
 */
@Stateless
@LocalBean
public class AdministradorOrdenesDespachoBean implements AdministradorOrdenesDespacho {


    public AdministradorOrdenesDespachoBean() {}

	@Override
	public OrdenDespachoDTO altaOrdenDespacho(OrdenDespachoDTO ordenDespachoDTO) {
		System.out.println("## altaOrdenDespacho SIN IMPLEMENTACION" );
//		this.notificarEntregaDespacho(777);
		return null;
	}
	
	@Override
	public String notificarEntregaDespacho (int idOrdenDespacho){
        System.out.println("***********************");
        System.out.println("Creando Cliente Web Service para Portal...");
        ServidorEstadoEntregaBeanService service1 = new ServidorEstadoEntregaBeanService();
        System.out.println("Creando Web Service...");
        ServidorEstadoEntregaBean port1 = service1.getServidorEstadoEntregaBeanPort();
        System.out.println("LLamada al Web Service de Portal... notificarEntregaDespacho");
        String respuesta = port1.notificarEntregaDespacho(idOrdenDespacho);
        System.out.println("Server said: " + respuesta);
		return respuesta;
	}
    
}
