package despacho.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import despacho.dominio.Articulo;
import despacho.dominio.OrdenDespacho;
import despacho.dominio.OrdenVenta;
import despacho.dominio.Portal;
import despacho.ejb.interfaces.remotas.AdministradorArticulos;
import dto.ArticuloDTO;

/**
 * Session Bean implementation class AdministradorArticulosBean
 */
@Stateless
@Startup
@LocalBean
public class AdministradorArticulosBean implements AdministradorArticulos{

    /**
     * Default constructor. 
     */
	@PersistenceContext(unitName = "JPADB")
	private EntityManager em;
		
    //--Metodos--
	
	public AdministradorArticulosBean() {}
    
    private void guardarArticulo(Articulo articulo){
		em.persist(articulo);
	}
    
	@Override
	public boolean altaArticulo(ArticuloDTO articuloDTO) {
		
		//Busco el articulo en la BD, si ya existe un Articulo con el mismo ID, no lo persiste 
		System.out.println("###### Comienza busqueda de Articulo");
		Articulo articulo;

		articulo = this.BuscarArticulo(articuloDTO.getIdArticulo());
		if (articulo != null) {
			System.out.println("### Ya hay un producto con ID: " + articuloDTO.getIdArticulo());
			return false;
		
		//Si el articulo NO existe en la Base de Datos, PERSISTO
		} else {
			System.out.println("###### Comienza Alta de Articulo con el ID: " + String.valueOf(articuloDTO.getIdArticulo()));
		
		
			articulo = new Articulo();
			
			articulo.setIdArticulo(articuloDTO.getIdArticulo());
			articulo.setDescripcion(articuloDTO.getDescripcion());
			articulo.setIdDeposito(articuloDTO.getIdDeposito());
			
			try {
				em.persist(articulo);
				
			} catch (Exception e) {
				System.out.println("### Fallo alta Articulo");
				e.printStackTrace();
				return false;
			}
			System.out.println("### Se dio de alta Articulo con ID: " + articuloDTO.getIdArticulo());
			return true;	
		
		}
		

	}

	public Articulo BuscarArticulo(int idArticulo) {

		Articulo articulo;
		
		try {
			articulo = em.find(Articulo.class, idArticulo);
		} catch (NoResultException e) {
			System.out.println("### No hay un articulo con ID: " + idArticulo);
			return null;
		}
		
		System.out.println("### Se encontro articulo con ID: " + idArticulo);
		return articulo;
	}

	@Override
	public ArticuloDTO actualizarArticulo(ArticuloDTO articulo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArticuloDTO listarArticulo(int idArticulo){
		Articulo art = this.BuscarArticulo(idArticulo);
		ArticuloDTO articuloSalida = null;
		if(art == null){
			System.out.println("No se encontraron articulos con el ID ingresado");
		}
		else{
			articuloSalida = art.getDTO();
		}	
		return articuloSalida;
	}
	
	public List<ArticuloDTO> articulosPorDeposito (int idDepo){
		List<ArticuloDTO> listaArticulosSalida = null;
		Query q = em.createQuery("Select A from Articulo where A.idDeposito = :idDeposito");
    	q.setParameter("idDeposito", idDepo);
    	try{
    		List<Articulo> articulos = q.getResultList();
    		System.out.println("Se encontraron articulos con el idDeposito ingresado");
    		listaArticulosSalida = new ArrayList<ArticuloDTO>();
    		for(int i=0; i < articulos.size(); i++){
    			listaArticulosSalida.add(articulos.get(i).getDTO());
    		}
    	}
    	catch (Exception e){
    		System.out.println("No fue posible listar articulos por idDeposito");
    		e.printStackTrace();
    	}
    	return listaArticulosSalida;
	}
	
	public List<ArticuloDTO> listar(){
		List<ArticuloDTO> listaArticulosSalida = null;
		Query q = em.createQuery("Select A from Articulo A");
    	try{
    		List<Articulo> articulos = q.getResultList();
    		System.out.println("Se encontraron " + articulos.size() + " articulos ");
    		listaArticulosSalida = new ArrayList<ArticuloDTO>();
    		for(Articulo articulo : articulos){
    			listaArticulosSalida.add(articulo.getDTO());
    		}
    	}catch (Exception e){
    		System.out.println("No fue posible listar articulos");
    		e.printStackTrace();
    		return null;
    	}
    	return listaArticulosSalida;
	}
}
