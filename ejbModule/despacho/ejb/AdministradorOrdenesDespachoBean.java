package despacho.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import despacho.ejb.interfaces.remotas.AdministradorOrdenesDespacho;

/**
 * Session Bean implementation class AdministradorOrdenesDespachoBean
 */
@Stateless
@LocalBean
public class AdministradorOrdenesDespachoBean implements AdministradorOrdenesDespacho {


    public AdministradorOrdenesDespachoBean() {}

	@Override
	public String altaOrdenDespacho(String codigo_despacho,
			String codigo_venta, String id_portal, String id_monitoreo,
			List<String[][]> items) {
		// TODO Auto-generated method stub
		return null;
	}
    
	

}
