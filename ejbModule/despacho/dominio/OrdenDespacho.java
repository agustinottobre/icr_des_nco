package despacho.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import dto.HistorialOrdenDespachoDTO;
import dto.ItemOrdenDespachoDTO;
import dto.OrdenDespachoDTO;

@Entity
@Table(name = "OrdenesDespacho")
public class OrdenDespacho implements Serializable{

	@Id
	//@GeneratedValue (strategy = GenerationType.AUTO)
	private int idOrdenDespacho;
	
	@OneToOne (cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "idOrdenVenta")
	private OrdenVenta ordenVenta;
	
	@Column (columnDefinition="nvarchar")
	private String estadoOrden;
	
	private Date fechaRecepcion;
	
	private int idLogistica; 
	
	@OneToMany (cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "idOrdenDespacho")
//	@OneToMany(mappedBy="ordenDespacho", targetEntity=ItemOrdenDespacho.class, cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<ItemOrdenDespacho> items;

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

	public Set<ItemOrdenDespacho> getItems() {
		return items;
	}

	public void setItems(Set<ItemOrdenDespacho> items) {
		this.items = items;
	}
	
	public OrdenDespachoDTO getDTO() {
		OrdenDespachoDTO ordenDespachoDTO = new OrdenDespachoDTO();
		
		ordenDespachoDTO.setEstadoOrden(this.getEstadoOrden());
		if (this.getFechaRecepcion() == null) {
			this.setFechaRecepcion(new Date());
		}
		ordenDespachoDTO.setFechaRecepcion(this.getFechaRecepcion().toString());
		ordenDespachoDTO.setIdOrdenDespacho(this.getIdOrdenDespacho());
		ordenDespachoDTO.setOrdenVenta(this.getOrdenVenta().getDTO());
		Set<ItemOrdenDespachoDTO> items = new HashSet<ItemOrdenDespachoDTO>();
		ordenDespachoDTO.setItems(items);
		for (ItemOrdenDespacho item : this.getItems())
		{
			ordenDespachoDTO.getItems().add(item.getDTO());
		}
		
		return ordenDespachoDTO;
	}

	public int getIdLogistica() {
		return idLogistica;
	}

	public void setIdLogistica(int idLogistica) {
		this.idLogistica = idLogistica;
	}
	
}
