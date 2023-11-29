/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOS;

import Modelo.Consultadependencias;
import controlador.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Modelo.ConsultaEntidades;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author farud
 */
public class EntidadesDAO {

    conexion cn = new conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    ConsultaEntidades conent = new ConsultaEntidades();

    public List listar() {
        ArrayList<ConsultaEntidades> list = new ArrayList<>();
        String sql = "SELECT * FROM tblentidades";
        try {
            con = cn.getConection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                ConsultaEntidades enti = new ConsultaEntidades();
                enti.setEntId(rs.getInt("entId"));
                enti.setEntNombre(rs.getString("entNombre"));
                enti.setEntTipoEntidad(rs.getString("entTipoEntidad"));
                enti.setEntDireccion(rs.getString("entDireccion"));
                enti.setEntTelefono(rs.getString("entTelefono"));
                enti.setEntCorreo(rs.getString("entCorreo"));
                list.add(enti);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public ConsultaEntidades list(int id) {

        String sql = "SELECT * FROM tblentidades WHERE entId=" + id;
        try {
            con = cn.getConection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                conent.setEntId(rs.getInt("entId"));
                conent.setEntNombre(rs.getString("entNombre"));
                conent.setEntTipoEntidad(rs.getString("entTipoEntidad"));
                conent.setEntDireccion(rs.getString("entDireccion"));
                conent.setEntTelefono(rs.getString("entTelefono"));
                conent.setEntCorreo(rs.getString("entCorreo"));
            }
        } catch (SQLException e) {
        }
        return conent;
    }

    public boolean add(ConsultaEntidades consuenti) {
        String sql = "call gesdoc_sena.sp_InsertarEntidad(?, ?, ?, ?, ?)";

        try {
            con = cn.getConection();
            ps = con.prepareCall(sql);

            // Asigna los valores reales a los parámetros del procedimiento almacenado
//            ps.setInt(1, usu.getUsuId());
            ps.setString(1, consuenti.getEntNombre());
            ps.setString(2, consuenti.getEntTipoEntidad());
            ps.setString(3, consuenti.getEntDireccion());
            ps.setString(4, consuenti.getEntTelefono());
            ps.setString(5, consuenti.getEntCorreo());

            // Ejecuta el procedimiento almacenado
            ps.execute();

            return true; // Indica que la operación fue exitosa
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false; // Indica que la operación falló
        }
    }

    public boolean edit(ConsultaEntidades consuenti) {
        String sql = "call gesdoc_sena.sp_ActualizarEntidad(?, ?, ?, ?, ?, ?)";

        try {
            con = cn.getConection();
            ps = con.prepareCall(sql);

            // Asigna los valores reales a los parámetros del procedimiento almacenado
            ps.setInt(1, consuenti.getEntId());
            ps.setString(2, consuenti.getEntNombre());
            ps.setString(3, consuenti.getEntTipoEntidad());
            ps.setString(4, consuenti.getEntDireccion());
            ps.setString(5, consuenti.getEntTelefono());
            ps.setString(6, consuenti.getEntCorreo());

            // Ejecuta el procedimiento almacenado
            ps.execute();

            return true; // Indica que la operación fue exitosa
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false; // Indica que la operación falló
        }
    }
    
    
     public boolean eliminar(int entId) {
        String sql = "call gesdoc_sena.sp_EliminarEntidad(?);"; 

        try {
            con = cn.getConection();
            ps = con.prepareCall(sql);

            // Asigna el valor del parámetro usuId
            ps.setInt(1, entId);

            // Ejecuta el procedimiento almacenado
            ps.execute();

            return true; // Indica que la operación fue exitosa
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false; // Indica que la operación falló
        }
    }

}
