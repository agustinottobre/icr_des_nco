package despacho.ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import despacho.dominio.Portal;

@Stateless
public class AdministradorTestJPABean implements AdministradorTestJPA{

    @PersistenceContext(unitName = "JPADB")
    private EntityManager entityManager;
    
	@Override
	public void persistirPortal(Portal portal) {
		// TODO Auto-generated method stub
		entityManager.persist(portal);
		entityManager.close();
	}

}
