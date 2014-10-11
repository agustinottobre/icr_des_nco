package despacho.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import despacho.dominio.Usuario;

public class UsuarioDao {

	public List<Usuario> obtenerlistaUsuarios(){
		return new ArrayList<Usuario>();
	}
	
	public Usuario registrarUsuario(String nombre, String password){
		return new Usuario();
	}
	
	public boolean obtenerEstadoUsuario(long id){
		return true;
	}
	
	public boolean cambiarEstadoUsuario(long id, boolean estado){
		return true;
	}
}
