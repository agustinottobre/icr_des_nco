package despacho.dominio;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import dto.ItemOrdenDespachoDTO;
import dto.ItemSolicitudArticuloDTO;

@Entity(name = "ItemsSolicitudArticulo")
public class ItemSolicitudArticulo {

	@Id
	//@GeneratedValue (strategy = GenerationType.AUTO)
	private int idItemSA;
	
	@OneToOne (cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Articulo articulo;
	
	private int cantidad;

	public int getIdItemSA() {
		return idItemSA;
	}

	public void setIdItemSA(int idItemSA) {
		this.idItemSA = idItemSA;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public ItemSolicitudArticuloDTO getDTO() {
		ItemSolicitudArticuloDTO itemSolicitudArticuloDTO = new ItemSolicitudArticuloDTO();
		
		itemSolicitudArticuloDTO.setArticulo(this.getArticulo().getDTO());
		itemSolicitudArticuloDTO.setCantidad(this.getCantidad());
		itemSolicitudArticuloDTO.setIdItemSA(this.getIdItemSA());
		
		return itemSolicitudArticuloDTO;
	}	
	
}
