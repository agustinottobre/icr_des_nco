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
import dto.OrdenDespachoDTO;

@Entity(name = "itemsOrdenDespacho")
public class ItemOrdenDespacho {

	@Id
	//@GeneratedValue (strategy = GenerationType.AUTO)
	
		private int idItemOD;
	
	@OneToOne (cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
		private Articulo articulo;
		private int cantidad;
		
		@Column (columnDefinition="nvarchar")
		private String estadoItems;

		public int getIdItemOD() {
			return idItemOD;
		}

		public void setIdItemOD(int idItemOD) {
			this.idItemOD = idItemOD;
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

		public String getEstadoItems() {
			return estadoItems;
		}

		public void setEstadoItems(String estadoItems) {
			this.estadoItems = estadoItems;
		}
		
		public ItemOrdenDespachoDTO getDTO() {
			ItemOrdenDespachoDTO itemOrdenDespachoDTO = new ItemOrdenDespachoDTO();
			
			itemOrdenDespachoDTO.setArticulo(this.getArticulo().getDTO());
			itemOrdenDespachoDTO.setCantidad(this.getCantidad());
			itemOrdenDespachoDTO.setEstadoItems(this.getEstadoItems());
			itemOrdenDespachoDTO.setIdItemOD(this.getIdItemOD());
			
			return itemOrdenDespachoDTO;
		}	
		
}
