package despacho.dominio;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import dto.ItemOrdenDespachoDTO;
import dto.ItemSolicitudArticuloDTO;

@Entity
@Table(name = "ItemsSolicitudArticulo")
public class ItemSolicitudArticulo implements Serializable{

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int idItemSA;
	
	@OneToOne (fetch=FetchType.EAGER)
	@JoinColumn(name = "idArticulo")
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
