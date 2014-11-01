package despacho.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import despacho.dominio.OrdenDespacho;
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
		// TODO Auto-generated method stub
		return null;
	}
    
	

}
