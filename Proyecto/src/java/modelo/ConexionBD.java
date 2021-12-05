package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Alizr
 */
public class ConexionBD {
    Connection con;
    public Connection Conectar(){
        String url="jdbc:mysql://localhost:3006/dbmarketplace?useTimezone=true&serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8";
        String user="root";
        String pass="admin";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con= (Connection) DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No conectado "+e.getMessage());
            con=null;
        }
        finally{
            return  con;
        }
    }
}
