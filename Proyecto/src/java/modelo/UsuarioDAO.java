
package modelo;

import entidades.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class UsuarioDAO {
    ConexionBD cn = new ConexionBD();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
    public Usuario Login(String user,String pass){
       Usuario us = new Usuario();
       String sql = "SELECT u.IdUsuario,u.Dni,u.Nombres, u.Apellidos, u.Direccion,u.Telefono,u.Email,u.Password,u.IdPrivilegio,u.Estado, p.Nombre FROM tbusuarios u INNER JOIN tbprivilegios p ON u.IdPrivilegio=p.IdPrivilegio  WHERE Email=? AND Password=?";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while (rs.next()) {
                us.setIdusuario(rs.getInt(1));
                us.setDni(rs.getString(2));
                us.setNombres(rs.getString(3));
                us.setApellidos(rs.getString(4));
                us.setDireccion(rs.getString(5));
                us.setTelefono(rs.getString(6));
                us.setEmail(rs.getString(7));
                us.setPassword(rs.getString(8));
                us.setIdprivilegio(rs.getInt(9));
                us.setEstado(rs.getInt(10));
                us.setNombreprivilegio(rs.getString(11));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
       return us;
    }
    public List listarUsuarios(){
        String sql = "SELECT u.IdUsuario,u.Dni,u.Nombres, u.Apellidos, u.Direccion,u.Telefono,u.Email,u.Password,u.IdPrivilegio,u.Estado, p.Nombre FROM tbusuarios u INNER JOIN tbprivilegios p ON u.IdPrivilegio=p.IdPrivilegio";
        List<Usuario>lista = new ArrayList<>();
        try {
            con=cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                Usuario us = new Usuario();
                us.setIdusuario(rs.getInt(1));
                us.setDni(rs.getString(2));
                us.setNombres(rs.getString(3));
                us.setApellidos(rs.getString(4));
                us.setDireccion(rs.getString(5));
                us.setTelefono(rs.getString(6));
                us.setEmail(rs.getString(7));
                us.setPassword(rs.getString(8));
                us.setIdprivilegio(rs.getInt(9));
                us.setEstado(rs.getInt(10));
                us.setNombreprivilegio(rs.getString(11));
                lista.add(us);
            }
        } catch (Exception e) {
        }
        return lista;
    }
    public int agregarUsuario(Usuario u){
        String sql = "INSERT INTO tbusuarios(Dni,Nombres,Apellidos,Direccion,Telefono,Email,Password,IdPrivilegio,Estado) VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, u.getDni());
            ps.setString(2, u.getNombres());
            ps.setString(3, u.getApellidos());
            ps.setString(4, u.getDireccion());
            ps.setString(5, u.getTelefono());
            ps.setString(6, u.getEmail());
            ps.setString(7, u.getPassword());
            ps.setInt(8, u.getIdprivilegio());
            ps.setInt(9, u.getEstado());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }
}
