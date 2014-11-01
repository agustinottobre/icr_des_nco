package despacho.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import despacho.ejb.interfaces.remotas.AdministradorOrdenesDespacho;
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
		return null;
	}
    
	

}
