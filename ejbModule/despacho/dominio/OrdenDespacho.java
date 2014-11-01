package despacho.dominio;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import dto.HistorialOrdenDespachoDTO;
import dto.ItemOrdenDespachoDTO;
import dto.OrdenDespachoDTO;

@Entity(name = "OrdenesDespacho")
public class OrdenDespacho {

	@Id
	//@GeneratedValue (strategy = GenerationType.AUTO)
	private int idOrdenDespacho;
	
	@OneToOne (cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private OrdenVenta ordenVenta;
	
	@Column (columnDefinition="nvarchar")
	private String estadoOrden;
	private Date fechaRecepcion;
	
	@OneToMany (cascade = CascadeType.ALL)
	@JoinColumn(name = "idOrdenDespacho")
	private List<ItemOrdenDespacho> items;

	public int getIdOrdenDespacho() {
		return idOrdenDespacho;
	}

	public void setIdOrdenDespacho(int idOrdenDespacho) {
		this.idOrdenDespacho = idOrdenDespacho;
	}

	public OrdenVenta getOrdenVenta() {
		return ordenVenta;
	}

	public void setOrdenVenta(OrdenVenta ordenVenta) {
		this.ordenVenta = ordenVenta;
	}

	public String getEstadoOrden() {
		return estadoOrden;
	}

	public void setEstadoOrden(String estadoOrden) {
		this.estadoOrden = estadoOrden;
	}

	public Date getFechaRecepcion() {
		return fechaRecepcion;
	}

	public void setFechaRecepcion(Date fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

	public List<ItemOrdenDespacho> getItems() {
		return items;
	}

	public void setItems(List<ItemOrdenDespacho> items) {
		this.items = items;
	}
	
	public OrdenDespachoDTO getDTO() {
		OrdenDespachoDTO ordenDespachoDTO = new OrdenDespachoDTO();
		
		ordenDespachoDTO.setEstadoOrden(this.getEstadoOrden());
		ordenDespachoDTO.setFechaRecepcion(this.getFechaRecepcion().toString());
		ordenDespachoDTO.setIdOrdenDespacho(this.getIdOrdenDespacho());
		ordenDespachoDTO.setOrdenVenta(this.getOrdenVenta().getDTO());
		for (ItemOrdenDespacho item : this.getItems())
		{
			ordenDespachoDTO.getItems().add(item.getDTO());
		}
		
		return ordenDespachoDTO;
	}	
	
}
