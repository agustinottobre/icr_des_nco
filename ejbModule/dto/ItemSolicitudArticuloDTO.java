package dto;

public class ItemSolicitudArticuloDTO {

	private int idItemSA;
	private ArticuloDTO articulo;
	private int cantidad;
	public int getIdItemSA() {
		return idItemSA;
	}
	public void setIdItemSA(int idItemSA) {
		this.idItemSA = idItemSA;
	}
	public ArticuloDTO getArticulo() {
		return articulo;
	}
	public void setArticulo(ArticuloDTO articulo) {
		this.articulo = articulo;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
}
