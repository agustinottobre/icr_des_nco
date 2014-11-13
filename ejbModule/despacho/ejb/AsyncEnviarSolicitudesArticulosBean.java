package despacho.ejb;

import java.util.List;
import java.util.Map;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

import despacho.dominio.OrdenDespacho;
import despacho.ejb.interfaces.remotas.AdministradorUsuarios;
import despacho.ejb.interfaces.remotas.AsyncEnviarSolicitudesArticulos;
import despacho.ejb.interfaces.remotas.ClienteJmsParaDeposito;
import despacho.ejb.interfaces.remotas.ClienteRestParaLogistica;
import dto.OrdenDespachoDTO;
import dto.SolicitudArticuloDTO;

@Stateless
@Asynchronous
@LocalBean
public class AsyncEnviarSolicitudesArticulosBean implements AsyncEnviarSolicitudesArticulos {
	
	@EJB
	private ClienteJmsParaDeposito clienteJmsParaDeposito ;

    public AsyncEnviarSolicitudesArticulosBean() {}
    
    @Asynchronous
    @Override
    public void enviarSolicitudesArticulos(
    		List<SolicitudArticuloDTO> listaSolicitudesArticulosDTO) {
    	System.out.println("##Async - Comienza enviarSolicitudesArticulos - llamada asyncronica");
		//Enviar a deposito por JMS las solicitudes
		for(SolicitudArticuloDTO solicitudArticuloDTO : listaSolicitudesArticulosDTO){
			clienteJmsParaDeposito.enviarSolicitudesArticulos(solicitudArticuloDTO);	
		}
//		try{
//		Thread.sleep(5000);
//		}catch(Exception e){
//				System.out.println("Async - ##Excepcion !!! enviarSolicitudesArticulos - llamada asyncronica");	
//		}
    	System.out.println("##Async - Finaliza enviarSolicitudesArticulos - llamada asyncronica");
    }
}
