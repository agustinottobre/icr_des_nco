package dto;




public class ItemOrdenDespachoDTO {

		private int idItemOD;
		private ArticuloDTO articulo;
		private int cantidad;
		private String estadoItems;
		public int getIdItemOD() {
			return idItemOD;
		}
		public void setIdItemOD(int idItemOD) {
			this.idItemOD = idItemOD;
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
		public String getEstadoItems() {
			return estadoItems;
		}
		public void setEstadoItems(String estadoItems) {
			this.estadoItems = estadoItems;
		}
		
}
