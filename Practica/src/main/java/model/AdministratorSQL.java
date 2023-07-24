package model;
import java.util.List;
import java.sql.*;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AdministratorSQL {

    //OBJETO DE LA CLASE CONECTION
    private Connection objConnection;
    private String user = "root";
    private String passw = "1234";
    private String dataBase = "db_practicas1";
    private String url = "jdbc:mysql://localhost:3306";
    ModelUser p = new ModelUser();

    //confirmacion de conexion de bd
    public boolean connectSQL() {
        try {
            objConnection = DriverManager.getConnection(url + "/" + dataBase, user, passw);
            //JOptionPane.showMessageDialog(null, "CONEXION EXITOSA");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "CONEXION FALLIDA: " + ex);
            return false;
        }
    }

    //se crea el metodo registrar
    public void register(String name, String date, int id, String cargo) {
        try {
            String instruction = "INSERT INTO users2 (fullname, fecha, id, cargo)"
                    + " values ('" + name + "','" + date + "'," + id + ",'" + cargo + "')";
            objConnection.prepareStatement(instruction).execute();
            JOptionPane.showMessageDialog(null, "USUARIO REGISTRADO");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "USUARIO FALLIDO" + ex);
        }
    }

    //se crea el metodo consulta
    public List consult(String cargo) {
        List<ModelUser> datos = new ArrayList<>();
        String instruction = "SELECT * FROM users2 WHERE cargo = '" + cargo + "' ";
        try {

            Statement statement = objConnection.createStatement();
            ResultSet rs = statement.executeQuery(instruction);
            while (rs.next()) {
                ModelUser p = new ModelUser();
                p.setName(rs.getString(1));
                p.setBirthday(rs.getString(2));
                p.setId(rs.getInt(3));
                p.setCargo(rs.getString(4));
                datos.add(p);
                //JOptionPane.showMessageDialog(null, p.getId());
            }
            return datos;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "CONSULTA FALLIDA" + ex);
            return null;
        } finally {
            try {
                objConnection.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "cerrado FALLIDA" + ex);
            }
        }
    }
}
//}
