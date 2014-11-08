package despacho.dominio;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import dto.ItemSolicitudArticuloDTO;
import dto.OrdenDespachoDTO;
import dto.SolicitudArticuloDTO;

@Entity
@Table(name = "SolicitudesArticulo")
public class SolicitudArticulo {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "idSolicitudArticulo")
	private int idSolicitud;
	
	@Column (columnDefinition="nvarchar")
	private String estadoSolicitud;
	
	private int idDeposito;
	
	@ManyToOne (fetch=FetchType.EAGER)
	@JoinColumn (name = "idOrdenDespacho")
	private OrdenDespacho ordenDespacho;
	
	@OneToMany (cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "idSolicitudArticulo")
	private Set<ItemSolicitudArticulo> items;


	public int getIdSolicitud() {
		return idSolicitud;
	}

	public void setIdSolicitud(int idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	public String getEstadoSolicitud() {
		return estadoSolicitud;
	}

	public void setEstadoSolicitud(String estadoSolicitud) {
		this.estadoSolicitud = estadoSolicitud;
	}

	public int getidDeposito() {
		return idDeposito;
	}

	public void setidDeposito(int idDeposito) {
		this.idDeposito = idDeposito;
	}

	public Set<ItemSolicitudArticulo> getItems() {
		return items;
	}

	public void setItems(Set<ItemSolicitudArticulo> items) {
		this.items = items;
	}
	
	public SolicitudArticuloDTO getDTO() {
		SolicitudArticuloDTO solicitudArticuloDTO = new SolicitudArticuloDTO();
		
		solicitudArticuloDTO.setEstadoSolicitud(this.getEstadoSolicitud());
		solicitudArticuloDTO.setidDeposito(this.getidDeposito());
		solicitudArticuloDTO.setIdSolicitud(this.getIdSolicitud());
		solicitudArticuloDTO.setIdOrdenDespacho(this.getOrdenDespacho().getIdOrdenDespacho());
		Set<ItemSolicitudArticuloDTO> items = new HashSet<ItemSolicitudArticuloDTO>();
		solicitudArticuloDTO.setItems(items);
		for (ItemSolicitudArticulo item : this.getItems())
		{
			solicitudArticuloDTO.getItems().add(item.getDTO());
		}
		
		return solicitudArticuloDTO;
	}

	public OrdenDespacho getOrdenDespacho() {
		return ordenDespacho;
	}

	public void setOrdenDespacho(OrdenDespacho ordenDespacho) {
		this.ordenDespacho = ordenDespacho;
	}


}
