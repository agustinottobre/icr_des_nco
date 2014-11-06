package despacho.ejb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import despacho.dominio.Articulo;
import despacho.ejb.interfaces.remotas.AdministradorArticulos;
import despacho.ejb.interfaces.remotas.AdministradorPropiedades;
import dto.ArticuloDTO;

@Singleton
@Startup
@LocalBean
public class AdministradorPropiedadesBean implements AdministradorPropiedades{
	
	Map<String, String> propiedades = new HashMap<String, String>();

	public Map<String, String> getPropiedades() {
		return propiedades;
	}
}
