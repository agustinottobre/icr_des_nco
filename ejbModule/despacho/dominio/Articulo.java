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
	
	public int getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(int idArticulo) {
		this.idArticulo = idArticulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public ArticuloDTO getDTO() {
		ArticuloDTO articuloDTO = new ArticuloDTO();
		
		articuloDTO.setDescripcion(this.getDescripcion());
		articuloDTO.setIdArticulo(this.getIdArticulo());
		articuloDTO.setMarca(this.getMarca());
		articuloDTO.setModelo(this.getModelo());
	
		return articuloDTO;
	}	
}
