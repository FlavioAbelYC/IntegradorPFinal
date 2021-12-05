package modelo;

import entidades.Categoria;
import entidades.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ProductoDAO {

  ConexionBD cn = new ConexionBD();
  Connection con;
  PreparedStatement ps;
  ResultSet rs;
  int r;
  public List listarCategorias(){
    String sql = "SELECT * FROM tbcategorias";
    List<Categoria> datos = new ArrayList<Categoria>();

    try {
      con = cn.Conectar();
      ps = con.prepareStatement(sql);
      rs = ps.executeQuery();
      while (rs.next()) {
        Categoria cat = new Categoria();
        cat.setIdcategoria(rs.getInt(1));
        cat.setCategoria(rs.getString(2));
        datos.add(cat);
      }
    } catch (Exception e) {
    }
    return datos;
  }
  public List allProductosTienda(int id) {
    String sql = "SELECT p.IdProducto,p.IdTienda,p.IdCategoria,p.Nombre,p.Descripcion,p.Marca, p.Presentacion,p.Precio,p.Stock,p.Estado FROM tbproductos p INNER JOIN tbtienda t ON t.IdTienda=p.IdTienda INNER JOIN tbusuarios u ON u.IdUsuario=t.IdUsuario WHERE t.IdUsuario="+id;
    List<Producto> datos = new ArrayList<Producto>();

    try {
      con = cn.Conectar();
      ps = con.prepareStatement(sql);
      rs = ps.executeQuery();
      while (rs.next()) {
        Producto pro = new Producto();
        pro.setIdproducto(rs.getInt(1));
        pro.setIdtienda(rs.getInt(2));
        pro.setIdcate(rs.getInt(3));
        pro.setNombreprod(rs.getString(4));
        pro.setDescriprod(rs.getString(5));
        pro.setMarcaprod(rs.getString(6));
        pro.setPresenprod(rs.getString(7));
        pro.setPrecioprod(rs.getFloat(8));
        pro.setStockprod(rs.getInt(9));
        pro.setEstadoprod(rs.getInt(10));
        datos.add(pro);
      }
    } catch (Exception e) {
    }
    return datos;
  }
  public List allProductos() {
    String sql = "SELECT p.IdProducto,p.IdTienda,p.IdCategoria,p.Nombre,p.Descripcion,p.Marca, p.Presentacion,p.Precio,p.Stock,p.Estado FROM tbproductos p INNER JOIN tbtienda t ON t.IdTienda=p.IdTienda INNER JOIN tbusuarios u ON u.IdUsuario=t.IdUsuario";
    List<Producto> productos = new ArrayList<>();

    try {
      con = cn.Conectar();
      ps = con.prepareStatement(sql);
      rs = ps.executeQuery();
      while (rs.next()) {
        productos.add(new Producto(rs.getInt("IdProducto"), rs.getInt("IdTienda"), rs.getInt("IdCategoria"), rs.getString("Nombre"), rs.getString("Descripcion"), rs.getString("Marca"), rs.getString("Presentacion"), rs.getFloat("Precio"), rs.getInt("Stock"), rs.getInt("Estado"), null));
      }
    } catch (Exception e) {
    }
    return productos;
  }
  public Producto getProductoID(int id) {
    Producto pro = new Producto();
    String sql = "SELECT p.*,c.Categoria FROM tbproductos p INNER JOIN tbcategorias c ON p.IdCategoria=c.IdCategoria WHERE IdProducto=?";
    try {
      con = cn.Conectar();
      ps = con.prepareStatement(sql);
      ps.setInt(1, id);
      rs = ps.executeQuery();
      if (rs.next()) {
        pro.setIdproducto(rs.getInt(1));
        pro.setIdtienda(rs.getInt(2));
        pro.setIdcate(rs.getInt(3));
        pro.setNombreprod(rs.getString(4));
        pro.setDescriprod(rs.getString(5));
        pro.setMarcaprod(rs.getString(6));
        pro.setPresenprod(rs.getString(7));
        pro.setPrecioprod(rs.getFloat(8));
        pro.setStockprod(rs.getInt(9));
        pro.setEstadoprod(rs.getInt(10));
        pro.setCategoria(rs.getString(11));
      }
    } catch (Exception e) {
      return null;
    }
    return pro;
  }
  public int addProducto(Producto p){
    int reg = 0;
    String sql = "INSERT INTO tbproductos(IdTienda,IdCategoria,Nombre,Descripcion,Marca,Presentacion,Precio,Stock,Estado) VALUES(?,?,?,?,?,?,?,?,?)";
    try {
      con = cn.Conectar();
      ps = con.prepareStatement(sql);
      ps.setInt(1, p.getIdtienda());
      ps.setInt(2, p.getIdcate());
      ps.setString(3, p.getNombreprod());
      ps.setString(4, p.getDescriprod());
      ps.setString(5, p.getMarcaprod());
      ps.setString(6, p.getPresenprod());
      ps.setFloat(7, p.getPrecioprod());
      ps.setInt(8, p.getStockprod());
      ps.setInt(9, p.getEstadoprod());
      ps.executeUpdate();
      reg = 1;
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "Error en "+e.getLocalizedMessage());
      reg = 0;
    }
    return reg;
  }
  public int updateProducto(Producto p){
    int reg = 0;
    String sql = "UPDATE tbproductos SET IdTienda=?,IdCategoria=?,Nombre=?,Descripcion=?,Marca=?,Presentacion=?,Precio=?,Stock=?,Estado=? WHERE IdProducto=?";
    try {
      con = cn.Conectar();
      ps = con.prepareStatement(sql);
      ps.setInt(1, p.getIdtienda());
      ps.setInt(2, p.getIdcate());
      ps.setString(3, p.getNombreprod());
      ps.setString(4, p.getDescriprod());
      ps.setString(5, p.getMarcaprod());
      ps.setString(6, p.getPresenprod());
      ps.setFloat(7, p.getPrecioprod());
      ps.setInt(8, p.getStockprod());
      ps.setInt(9, p.getEstadoprod());
      ps.setInt(10, p.getIdproducto());
      ps.executeUpdate();
      reg = 1;
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "Error en "+e.getLocalizedMessage());
      reg = 0;
    }
    return reg;
  }
  public boolean deleteProducto(int id){
    String sql = "DELETE FROM tbproductos WHERE IdProducto=?";
    try {
      con=cn.Conectar();
      ps = con.prepareStatement(sql);
      ps.setInt(1, id);
      ps.executeUpdate();
      return true;
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "Error en "+e.getLocalizedMessage());
    }
    return false;
  }
}
