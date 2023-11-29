/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOS;

import controlador.conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import Modelo.consultausuario;
import java.util.List;
import java.sql.SQLException;

/**
 *
 * @author farud
 */
public class UsuarioDAO {

    conexion cn = new conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    consultausuario usuario = new consultausuario();

    public List listar() {
        ArrayList<consultausuario> list = new ArrayList<>();
        String sql = "SELECT * FROM tblusuarios";
        try {
            con = cn.getConection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                consultausuario consu = new consultausuario();
                consu.setUsuId(rs.getInt("usuId"));
                consu.setUsuPrimerNombre(rs.getString("usuPrimerNombre"));
                consu.setUsuSegundoNombre(rs.getString("usuSegundoNombre"));
                consu.setUsuPrimerApellido(rs.getString("usuPrimerApellido"));
                consu.setUsuSegundoApellido(rs.getString("usuSegundoApellido"));
                consu.setUsuNombreUsuario(rs.getString("usuNombreUsuario"));
                consu.setUsuContrasena(rs.getString("usuContrasena"));
                consu.setUsuCorreo(rs.getString("usuCorreo"));
                consu.setUsuFechaRegistro(rs.getString("usuFechaRegistro"));
                consu.setUsuNivelAcceso(rs.getString("usuNivelAcceso"));
                consu.setUsuObservaciones(rs.getString("usuObservaciones"));
                list.add(consu);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public consultausuario list(int id) {

        String sql = "SELECT * FROM tblusuarios WHERE usuId=" + id;
        try {
            con = cn.getConection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                usuario.setUsuId(rs.getInt("usuId"));
                usuario.setUsuPrimerNombre(rs.getString("usuPrimerNombre"));
                usuario.setUsuSegundoNombre(rs.getString("usuSegundoNombre"));
                usuario.setUsuPrimerApellido(rs.getString("usuPrimerApellido"));
                usuario.setUsuSegundoApellido(rs.getString("usuSegundoApellido"));
                usuario.setUsuNombreUsuario(rs.getString("usuNombreUsuario"));
                usuario.setUsuContrasena(rs.getString("usuContrasena"));
                usuario.setUsuCorreo(rs.getString("usuCorreo"));
                usuario.setUsuFechaRegistro(rs.getString("usuFechaRegistro"));
                usuario.setUsuNivelAcceso(rs.getString("usuNivelAcceso"));
                usuario.setUsuObservaciones(rs.getString("usuObservaciones"));
            }
        } catch (SQLException e) {
        }
        return usuario;
    }

    public boolean add(consultausuario usu) {
        String sql = "CALL gesdoc_sena.sp_insertarusuario(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            con = cn.getConection();
            ps = con.prepareCall(sql);

            // Asigna los valores reales a los parámetros del procedimiento almacenado
//            ps.setInt(1, usu.getUsuId());
            ps.setString(1, usu.getUsuPrimerNombre());
            ps.setString(2, usu.getUsuSegundoNombre());
            ps.setString(3, usu.getUsuPrimerApellido());
            ps.setString(4, usu.getUsuSegundoApellido());
            ps.setString(5, usu.getUsuNombreUsuario());
            ps.setString(6, usu.getUsuContrasena());
            ps.setString(7, usu.getUsuCorreo());
            ps.setString(8, usu.getUsuFechaRegistro());
            ps.setString(9, usu.getUsuNivelAcceso());
            ps.setString(10, usu.getUsuObservaciones());

            // Ejecuta el procedimiento almacenado
             ps.execute();

            return true; // Indica que la operación fue exitosa
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false; // Indica que la operación falló
        }
    }
    
    public boolean edit(consultausuario usu) {
        String sql = "CALL gesdoc_sena.sp_actualizarusuario(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            con = cn.getConection();
            ps = con.prepareCall(sql);

            // Asigna los valores reales a los parámetros del procedimiento almacenado
            ps.setInt(1, usu.getUsuId());
            ps.setString(2, usu.getUsuPrimerNombre());
            ps.setString(3, usu.getUsuSegundoNombre());
            ps.setString(4, usu.getUsuPrimerApellido());
            ps.setString(5, usu.getUsuSegundoApellido());
            ps.setString(6, usu.getUsuNombreUsuario());
            ps.setString(7, usu.getUsuContrasena());
            ps.setString(8, usu.getUsuCorreo());
            ps.setString(9, usu.getUsuFechaRegistro());
            ps.setString(10, usu.getUsuNivelAcceso());
            ps.setString(11, usu.getUsuObservaciones());

            // Ejecuta el procedimiento almacenado
            ps.execute();

            return true; // Indica que la operación fue exitosa
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false; // Indica que la operación falló
        }
    }

    public boolean eliminar(int usuId) {
        String sql = "CALL gesdoc_sena.sp_eliminarusuario(?)"; // Usamos ? como marcador de posición para el parámetro usuId

        try {
            con = cn.getConection();
            ps = con.prepareCall(sql);

            // Asigna el valor del parámetro usuId
            ps.setInt(1, usuId);

            // Ejecuta el procedimiento almacenado
            ps.execute();

            return true; // Indica que la operación fue exitosa
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false; // Indica que la operación falló
        }
    }

    public int validar(consultausuario consu) {
        int r = 0;
        String sql = "SELECT * FROM tblusuarios WHERE usuNombreUsuario = ? AND usuContrasena = ?";
        try {
            con = cn.getConection();
            ps = con.prepareStatement(sql);
            ps.setString(1, consu.getUsuNombreUsuario());
            ps.setString(2, consu.getUsuContrasena());
            rs = ps.executeQuery();
            while (rs.next()) {
                r = r + 1;
                consu.setUsuNombreUsuario(rs.getString("usuNombreUsuario"));
                consu.setUsuContrasena(rs.getString("usuContrasena"));
            }
            if (r == 1) {
                return 1;
            } else {
                return 0;
            }

        } catch (SQLException e) {
            return 0;
        }
    }

    public consultausuario buscar(String usuNombreUsuario) {
        consultausuario usuario = null; // Declara la variable para almacenar el resultado de la búsqueda

        String sql = "SELECT * FROM tblusuarios WHERE usuNombreUsuario = ?";

        try {
            con = cn.getConection();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuNombreUsuario); // Establece el valor del parámetro en el SQL
            rs = ps.executeQuery();

            if (rs.next()) {
                // Si se encontró un usuario, crea un objeto consultausuario y configura sus atributos
                usuario = new consultausuario();
                usuario.setUsuId(rs.getInt("usuId"));
                usuario.setUsuPrimerNombre(rs.getString("usuPrimerNombre"));
                usuario.setUsuSegundoNombre(rs.getString("usuSegundoNombre"));
                usuario.setUsuPrimerApellido(rs.getString("usuPrimerApellido"));
                usuario.setUsuSegundoApellido(rs.getString("usuSegundoApellido"));
                usuario.setUsuNombreUsuario(rs.getString("usuNombreUsuario"));
                usuario.setUsuContrasena(rs.getString("usuContrasena"));
                usuario.setUsuCorreo(rs.getString("usuCorreo"));
                usuario.setUsuFechaRegistro(rs.getString("usuFechaRegistro"));
                usuario.setUsuNivelAcceso(rs.getString("usuNivelAcceso"));
                usuario.setUsuObservaciones(rs.getString("usuObservaciones"));
            }
        } catch (SQLException e) {
            // Manejo de excepciones, por ejemplo, imprimir un mensaje de error
            e.printStackTrace();
        } finally {
            // Asegúrate de cerrar la conexión, el PreparedStatement y el ResultSet
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                // Manejo de excepciones al cerrar los recursos
                e.printStackTrace();
            }
        }

        return usuario; // Retorna el usuario encontrado o null si no se encontró ninguno
    }

}
