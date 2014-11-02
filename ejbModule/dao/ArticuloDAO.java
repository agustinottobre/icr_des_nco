package dao;


import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;

import despacho.dominio.Articulo;

public class ArticuloDAO {
	
	@PersistenceContext(unitName ="JPADB")
	private EntityManager em;
	
	public void guardarArticulo(Articulo articulo){
		em.persist(articulo);
	}
	
	
	
	
}
