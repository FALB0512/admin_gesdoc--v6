/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOS;

import controlador.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Modelo.Consultadependencias;
import Modelo.consultausuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author farud
 */
public class DependenciaDAO {
    
    conexion cn = new conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Consultadependencias cond = new Consultadependencias();
    
     public List listar() {
        ArrayList<Consultadependencias> list = new ArrayList<>();
        String sql = "SELECT * FROM tbldependencias";
        try {
            con = cn.getConection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Consultadependencias depen = new Consultadependencias();
                depen.setDepId(rs.getInt("depId"));
                depen.setDepNombre(rs.getString("depNombre"));
                depen.setDepDescripcion(rs.getString("depDescripcion"));
                depen.setDepCorreoElectronico(rs.getString("depCorreoElectronico"));
                depen.setDepTelefono(rs.getString("depTelefono"));               
                list.add(depen);
            }
        } catch (SQLException e) {
        }
        return list;
    }
     
     public Consultadependencias list(int id) {

        String sql = "SELECT * FROM tbldependencias WHERE depId=" + id;
        try {
            con = cn.getConection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                cond.setDepId(rs.getInt("depId"));
                cond.setDepNombre(rs.getString("depNombre"));
                cond.setDepDescripcion(rs.getString("depDescripcion"));
                cond.setDepCorreoElectronico(rs.getString("depCorreoElectronico"));
                cond.setDepTelefono(rs.getString("depTelefono"));
                
            }
        } catch (SQLException e) {
        }
        return cond;
    }
    
    public boolean add(Consultadependencias cdp) {
        String sql = "call gesdoc_sena.sp_InsertarDependencia(?, ?, ?, ?)";

        try {
            con = cn.getConection();
            ps = con.prepareCall(sql);

            // Asigna los valores reales a los parámetros del procedimiento almacenado
//            ps.setInt(1, usu.getUsuId());
            ps.setString(1, cdp.getDepNombre());
            ps.setString(2, cdp.getDepDescripcion());
            ps.setString(3, cdp.getDepCorreoElectronico());
            ps.setString(4, cdp.getDepTelefono());
            
            // Ejecuta el procedimiento almacenado
            ps.execute();

            return true; // Indica que la operación fue exitosa
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false; // Indica que la operación falló
        }
    }
    
    
    public boolean edit(Consultadependencias editdep) {
        String sql = "call gesdoc_sena.sp_ActualizarDependencia(?, ?, ?, ?, ?)";

        try {
            con = cn.getConection();
            ps = con.prepareCall(sql);

            // Asigna los valores reales a los parámetros del procedimiento almacenado
            ps.setInt(1, editdep.getDepId());
            ps.setString(2, editdep.getDepNombre());
            ps.setString(3, editdep.getDepDescripcion());
            ps.setString(4, editdep.getDepCorreoElectronico());
            ps.setString(5, editdep.getDepTelefono());

            // Ejecuta el procedimiento almacenado
            ps.execute();

            return true; // Indica que la operación fue exitosa
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false; // Indica que la operación falló
        }
    }
    
    
    public boolean eliminar(int depId) {
        String sql = "call gesdoc_sena.sp_EliminarDependencia(?)"; 

        try {
            con = cn.getConection();
            ps = con.prepareCall(sql);

            // Asigna el valor del parámetro usuId
            ps.setInt(1, depId);

            // Ejecuta el procedimiento almacenado
            ps.execute();

            return true; // Indica que la operación fue exitosa
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false; // Indica que la operación falló
        }
    }
    
    
    
    
    
    
    
    
    
}
