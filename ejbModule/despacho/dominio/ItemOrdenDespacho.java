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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import dto.ItemOrdenDespachoDTO;
import dto.OrdenDespachoDTO;

@Entity
@Table(name = "itemsOrdenDespacho")
public class ItemOrdenDespacho implements Serializable{

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
		private int idItemOD;
	
//	 @ManyToOne(optional=false)
//     @JoinColumn(name="idOrdenDespacho",referencedColumnName="idOrdenDespacho")
//		private OrdenDespacho ordenDespacho;
	private int idOrdenDespacho;
	
	@OneToOne (fetch=FetchType.EAGER)
	@JoinColumn(name = "idArticulo")
		private Articulo articulo;
		private int cantidad;
		
		@Column (columnDefinition="nvarchar")
		private String estadoItems;
	
		public ItemOrdenDespacho() {
			super();
		}

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
			itemOrdenDespachoDTO.setIdOrdenDespacho(this.getIdOrdenDespacho());
			
			return itemOrdenDespachoDTO;
		}

		public int getIdOrdenDespacho() {
			return idOrdenDespacho;
		}

		public void setIdOrdenDespacho(int idOrdenDespacho) {
			this.idOrdenDespacho = idOrdenDespacho;
		}	
		
}
