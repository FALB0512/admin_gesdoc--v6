
package controlador;



import java.sql.*;

public class conexion{
    Connection con;
    public Connection getConection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/gesdoc_sena?serverTimezone=UTC","root","2556229");   
        } catch (Exception e) {
        }
        return con;
    }
}

