package dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import despacho.dominio.Articulo;

/**
 * Session Bean implementation class ArticulosDAO
 */
@Stateless
@LocalBean
public class ArticuloDAO {

	@PersistenceContext(unitName = "JPADB")
	private EntityManager em;
	

    public ArticuloDAO() {
        // TODO Auto-generated constructor stub
    }
    
    public void guardarArticulo(Articulo articulo){
    	em.persist(articulo);
    }

    public Articulo buscarArticulo(int idArticulo){
    	Articulo articulo = em.find(Articulo.class, idArticulo);
    	return articulo;
    }
    
    //Actualizar Articulo
    //Guarda el articulo recibodo, pisando el ya existente.
    
    //Articulos por deposito
    public List<Articulo> articulosPorDeposito(int idDeposito){
    	Query q = em.createQuery("Select A frome Articulos where A.idDeposito =  :idDeposito");
    	q.setParameter("idDeposito", idDeposito);
    	List<Articulo> articulos = q.getResultList();
    	return articulos;
    }
    
}
