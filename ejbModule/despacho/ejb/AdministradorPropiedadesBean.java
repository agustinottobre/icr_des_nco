package despacho.ejb;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import despacho.dominio.Articulo;
import despacho.ejb.interfaces.remotas.AdministradorArticulos;
import despacho.ejb.interfaces.remotas.AdministradorPropiedades;
import dto.ArticuloDTO;

@Singleton
@Startup
public class AdministradorPropiedadesBean implements AdministradorPropiedades{
	
	Properties propiedades = new Properties();
	
	public AdministradorPropiedadesBean() {}

	public Properties getPropiedades() {
		return propiedades;
	}
	
	@Override
	public Object get(String key) {
		Object propiedad = propiedades.get(key); 
		System.out.println("##propiedades.get " + key + ": " + propiedad);
		return propiedad;
	}
	
	@Override
	public void put(String key, Object value) {
		System.out.println("##propiedades.put " + key + ": " + value);
		propiedades.put(key, value);
	}
	
	@PostConstruct
	public void defaults(){
		Properties propiedadesDefault = this.getDefaultsFromFile();
		if(null == propiedadesDefault){
			System.out.println("##AdministradorPropiedadesBean no encuentra propiedades defaults!");
		}
		propiedades.putAll(propiedadesDefault);
		System.out.println("##AdministradorPropiedadesBean iniciado con defaults!");
	}
	
	public Properties getDefaultsFromFile(){
	    Properties propiedadesDefault = new Properties();
	    InputStream input;
	    try {
//	    	input = new FileInputStream("defaults.properties");
	    	input = getClass().getClassLoader().getResourceAsStream("META-INF/defaults.properties");
	        propiedadesDefault.load(input);
	        input.close();
	        return propiedadesDefault;
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
}
