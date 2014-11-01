package despacho.ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import despacho.dominio.Portal;
import despacho.ejb.interfaces.remotas.AdministradorTestJPA;

@Stateless
public class AdministradorTestJPABean implements AdministradorTestJPA{

    @PersistenceContext(unitName = "JPADB")
    private EntityManager entityManager;
    
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
