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
import despacho.ejb.interfaces.remotas.AsyncNotificarLogistica;
import despacho.ejb.interfaces.remotas.ClienteJmsParaDeposito;
import despacho.ejb.interfaces.remotas.ClienteRestParaLogistica;
import dto.OrdenDespachoDTO;
import dto.SolicitudArticuloDTO;

@Stateless
@Asynchronous
@LocalBean
public class AsyncNotificarLogisticaBean implements AsyncNotificarLogistica {
	
	@EJB
	private ClienteRestParaLogistica clienteRestLogistica;

    public AsyncNotificarLogisticaBean() {}
    
    @Asynchronous
	@Override
	public void notificarLogistica(OrdenDespachoDTO ordenDespachoDTO) {
			System.out.println("##Async - Comienza notificarLogistica - llamada asyncronica");
			clienteRestLogistica.enviarCambioEstado(ordenDespachoDTO);
			System.out.println("##Async - Finaliza notificarLogistica - llamada asyncronica");
	}
}
