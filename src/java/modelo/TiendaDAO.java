package modelo;

import entidades.Tienda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class TiendaDAO {

    ConexionBD cn = new ConexionBD();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    public List allTiendas(){
        String sql = "SELECT * FROM tbtienda";
        List<Tienda> lista = new ArrayList<>();
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Tienda ti = new Tienda();
                ti.setId(rs.getInt(1));
                ti.setIdus(rs.getInt(2));
                ti.setRuc(rs.getString(3));
                ti.setNombre(rs.getString(4));
                ti.setEstado(rs.getInt(5));
                lista.add(ti);
            }
        } catch (Exception e) {
        }
        return lista;
    }
    public List listarTiendas(int condi) {
        String sql = "SELECT t.IdTienda,t.IdUsuario, t.Ruc,t.Nombre,t.Estado,d.IdDistrito,d.Direccion,d.Latitud,d.Logitud FROM tbtienda t INNER JOIN tbdetalletienda d ON t.IdTienda = d.IdTienda WHERE t.IdUsuario="+condi;
        List<Tienda> lista = new ArrayList<>();
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Tienda ti = new Tienda();
                ti.setId(rs.getInt(1));
                ti.setIdus(rs.getInt(2));
                ti.setRuc(rs.getString(3));
                ti.setNombre(rs.getString(4));
                ti.setEstado(rs.getInt(5));
                ti.setIddistrito(rs.getInt(6));
                ti.setDireccion(rs.getString(7));
                ti.setLatitud(rs.getString(8));
                ti.setLongitud(rs.getString(9));
                lista.add(ti);
            }
        } catch (Exception e) {
        }
        return lista;
    }
     public Tienda TiendaID(int condi) {
        Tienda ti = new Tienda();
        String sql = "SELECT t.IdTienda,t.IdUsuario,t.Ruc,t.Nombre,t.Estado FROM tbtienda t INNER JOIN tbusuarios u ON u.IdUsuario=t.IdUsuario WHERE t.IdUsuario=?";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, condi);
            rs = ps.executeQuery();
            if (rs.next()) {
              ti.setId(rs.getInt(1));
              ti.setIdus(rs.getInt(2));
              ti.setRuc(rs.getString(3));
              ti.setNombre(rs.getString(4));
              ti.setEstado(rs.getInt(5));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return ti;
    }
}
