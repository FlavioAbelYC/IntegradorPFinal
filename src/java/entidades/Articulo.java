package entidades;

public class Articulo {

  private int idProducto;
  private int cantidad;

  public Articulo(int idProducto, int cantidad) {
    this.idProducto = idProducto;
    this.cantidad = cantidad;
  }

  public Articulo() {
  }

  /**
   * @return the idProducto
   */
  public int getIdProducto() {
    return idProducto;
  }

  /**
   * @param idProducto the idProducto to set
   */
  public void setIdProducto(int idProducto) {
    this.idProducto = idProducto;
  }

  /**
   * @return the cantidad
   */
  public int getCantidad() {
    return cantidad;
  }

  /**
   * @param cantidad the cantidad to set
   */
  public void setCantidad(int cantidad) {
    this.cantidad = cantidad;
  }

}
