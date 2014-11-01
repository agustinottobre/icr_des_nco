package despacho.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import dto.ArticuloDTO;

@Entity(name = "Articulos")
public class Articulo {
	
	@Id
	//@GeneratedValue (strategy = GenerationType.AUTO)
	
	private int idArticulo;
	@Column (columnDefinition="nvarchar")
	
	private String descripcion;
	
	@Column (columnDefinition="nvarchar")
	private String marca;
	
	@Column (columnDefinition="nvarchar")
	private String modelo;
	
	public ArticuloDTO getDTO() {
		ArticuloDTO articuloDTO = new ArticuloDTO();
		
		
		
		return articuloDTO;
	}
	
}
