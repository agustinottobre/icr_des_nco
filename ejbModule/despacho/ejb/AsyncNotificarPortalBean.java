package despacho.ejb;

import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

import despacho.dominio.OrdenDespacho;
import despacho.ejb.interfaces.remotas.AdministradorPropiedades;
import despacho.ejb.interfaces.remotas.AdministradorUsuarios;
import despacho.ejb.interfaces.remotas.AsyncEnviarSolicitudesArticulos;
import despacho.ejb.interfaces.remotas.AsyncNotificarPortal;
import despacho.ejb.interfaces.remotas.ClienteJmsParaDeposito;
import despacho.ejb.interfaces.remotas.ClienteRestParaLogistica;
import despacho.ws.servicios.consumidos.ServidorEstadoEntregaBean;
import despacho.ws.servicios.consumidos.ServidorEstadoEntregaBeanService;
import dto.OrdenDespachoDTO;
import dto.SolicitudArticuloDTO;

@Stateless
@Asynchronous
@LocalBean
public class AsyncNotificarPortalBean implements AsyncNotificarPortal {
	
	@EJB
	private AdministradorOrdenesDespachoBean administradorOrdenesDespachoBean;
	
	@EJB
	private AdministradorPropiedades administradorPropiedades;
	
	private String WS_URL;

    public AsyncNotificarPortalBean() {}

    @Asynchronous
	@Override
	public void notificarPortal(OrdenDespachoDTO ordenDespachoDTO) {
			System.out.println("##Async - Comienza notificarPortal - llamada asyncronica");
//			administradorOrdenesDespachoBean.notificarEntregaDespacho(ordenDespachoDTO);
			
			System.out.println("***********************");
	        System.out.println("Creando Cliente Web Service para Portal...");
	        ServidorEstadoEntregaBeanService service1;
	        String respuesta = null;
	        
			try {
				
//				if (ordenDespachoDTO.getOrdenVenta().getPortal().getIdPortal() == 1) {
				if (ordenDespachoDTO.getOrdenVenta().getPortal().getIdPortal() == Integer.parseInt((String)administradorPropiedades.get("portal-ws-id1"))) {
					WS_URL = (String)administradorPropiedades.get("portal-ws-host1") + (String)administradorPropiedades.get("portal-ws-path1");
				}else
				
//				if (ordenDespachoDTO.getOrdenVenta().getPortal().getIdPortal() == 7) {
				if (ordenDespachoDTO.getOrdenVenta().getPortal().getIdPortal() == Integer.parseInt((String)administradorPropiedades.get("portal-ws-id2"))) {
					WS_URL = (String)administradorPropiedades.get("portal-ws-host2") + (String)administradorPropiedades.get("portal-ws-path2");
				}else

//				if (ordenDespachoDTO.getOrdenVenta().getPortal().getIdPortal() == 9) {
				if (ordenDespachoDTO.getOrdenVenta().getPortal().getIdPortal() == Integer.parseInt((String)administradorPropiedades.get("portal-ws-id3"))) {
					WS_URL = (String)administradorPropiedades.get("portal-ws-host3") + (String)administradorPropiedades.get("portal-ws-path3");
				}else
				
//				if (ordenDespachoDTO.getOrdenVenta().getPortal().getIdPortal() == 8) {
				if (ordenDespachoDTO.getOrdenVenta().getPortal().getIdPortal() == Integer.parseInt((String)administradorPropiedades.get("portal-ws-id4"))) {
					WS_URL = (String)administradorPropiedades.get("portal-ws-host4") + (String)administradorPropiedades.get("portal-ws-path4");
				}
				
				System.out.println("##URL Portal " + WS_URL);
				if(null == WS_URL){
					System.out.println("##Portal DESCONOCIDO!!!!!!!!!!!!! ");
					return;
				}
				
				service1 = new ServidorEstadoEntregaBeanService(new URL(WS_URL));

	        System.out.println("Creando Web Service...");
	        ServidorEstadoEntregaBean port1 = service1.getServidorEstadoEntregaBeanPort();
	        System.out.println("LLamada al Web Service de Portal... notificarEntregaDespacho");
	        respuesta = port1.notificarEntregaDespacho(ordenDespachoDTO.getIdOrdenDespacho());
	        System.out.println("Respuesta Portal: " + respuesta);
			} catch (MalformedURLException e) {
				System.out.println("##notificarPortal - MalformedURLException");
				return;
//		  	} catch (ConnectException e) {
//		  		System.out.println("##notificarPortal - Connection Timeout");
//		  		return;
//		    } catch (SocketTimeoutException e) {
//		    	System.out.println("##notificarPortal - Socket Timeout");
//		    	return;
			} catch (Exception e) {
				System.out.println("##notificarPortal - Exception");
				e.printStackTrace();
				return;
			}
//			return respuesta;

			
			
			System.out.println("##Async - Finaliza notificarPortal - llamada asyncronica");
	}
    
}
