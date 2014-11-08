package despacho.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import despacho.dominio.Portal;
import despacho.ejb.interfaces.remotas.AdministradorPropiedades;
import despacho.ejb.interfaces.remotas.AdministradorTest;
import despacho.ejb.interfaces.remotas.ClienteJmsParaDeposito;
import despacho.ejb.interfaces.remotas.SimClienteRestParaDeposito;
import despacho.ejb.interfaces.remotas.SimuladorLogisticaClienteJmsParaDespacho;
import dto.ArticuloDTO;
import dto.ItemSolicitudArticuloDTO;
import dto.SolicitudArticuloDTO;

@Stateless
public class AdministradorTestBean implements AdministradorTest{

    @PersistenceContext(unitName = "JPADB")
    private EntityManager entityManager;
    
    @EJB
    private AdministradorPropiedades administradorPropiedades;
    
	@EJB
	private ClienteJmsParaDeposito clienteJmsParaDeposito;
	@EJB
	private SimuladorLogisticaClienteJmsParaDespacho simuladorLogisticaClienteJmsParaDespacho;
	@EJB
	private SimClienteRestParaDeposito simClienteRestParaDeposito;
    
    public String testEJB(){
		  if(null == administradorPropiedades){
			  System.out.println("##administradorPropiedades = NULL !!!!");
			  return "administradorPropiedades = NULL !!!!";
		  }
		  System.out.println("##administradorPropiedades NOT NULL !!!!");
		  System.out.println("##props:" + administradorPropiedades.getPropiedades());
		  

    	return "Test EJB OK !";
    }
    
    public String testEnviarSolicitudArticuloDepositoClienteJMS(){
    	SolicitudArticuloDTO solicitudArticuloDTO = new SolicitudArticuloDTO();
    	solicitudArticuloDTO.setEstadoSolicitud("pendiente");
    	solicitudArticuloDTO.setIdSolicitud(453);
    	solicitudArticuloDTO.setidDeposito(33);
    	List<ItemSolicitudArticuloDTO> items = new ArrayList<ItemSolicitudArticuloDTO>();
    	ItemSolicitudArticuloDTO itemSolicitudArticuloDTO = new ItemSolicitudArticuloDTO();
    	ArticuloDTO articuloDTO = new ArticuloDTO();
    	articuloDTO.setIdArticulo(655);
    	articuloDTO.setDescripcion("un articulo para solicitar");
    	itemSolicitudArticuloDTO.setArticulo(articuloDTO);
    	itemSolicitudArticuloDTO.setCantidad(5);
    	items.add(itemSolicitudArticuloDTO);
    	solicitudArticuloDTO.setItems(items);
    	clienteJmsParaDeposito.enviarSolicitudesArticulos(solicitudArticuloDTO);
    	return "##TEST testEnviarSolicitudArticuloDepositoClienteJMS OK!";
    }
    
    public String testRecibirOrdenDespachoPorWSDesdeLogistica(){
    	return simuladorLogisticaClienteJmsParaDespacho.testRecibirOrdenDespachoPorWSDesdeLogistica();
    }
    
    public String testRecibirArticulosPorRESTDesdeDeposito(){
    	return simClienteRestParaDeposito.testRecibirArticulosPorRESTDesdeDeposito();
    }

    
	@Override
	public void persistirPortal(String descripcionPortal) {
		// TODO Auto-generated method stub
		Portal portal = new Portal();
		portal.setDescripcion(descripcionPortal);
		entityManager.persist(portal);
		entityManager.flush();
//		entityManager.close();
	}

}
