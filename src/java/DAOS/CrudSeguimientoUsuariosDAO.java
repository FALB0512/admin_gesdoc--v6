/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOS;

import Modelo.radicadoenviado;
import controlador.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Modelo.ConsultarSeguimientoUsuarios;

/**
 *
 * @author farud
 */
public class CrudSeguimientoUsuariosDAO {
    
    conexion cn = new conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    ConsultarSeguimientoUsuarios acciones = new ConsultarSeguimientoUsuarios();
    
    
    
    public List listar() {
        ArrayList<ConsultarSeguimientoUsuarios> list = new ArrayList<>();
        String sql = "SELECT * FROM tblacciones";
        try {
            con = cn.getConection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            
            
            while (rs.next()) {
                ConsultarSeguimientoUsuarios accusu = new ConsultarSeguimientoUsuarios();
                
                accusu.setAccId(rs.getInt("accId"));
                accusu.setAccFechaIngreso(rs.getString("accFechaIngreso"));
                accusu.setAccHoraIngreso(rs.getString("accHoraIngreso"));
                accusu.setAccIP(rs.getString("accIP"));
                accusu.setAccAcciones(rs.getString("accAcciones"));
                accusu.setAccUsuario(rs.getString("accUsuario"));
                accusu.setAccNumeroRadicado(rs.getString("accNumeroRadicado"));
                

                list.add(accusu);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public ConsultarSeguimientoUsuarios list(int id) {

        
        
        String sql = "SELECT * FROM tblacciones WHERE accId=" + id;
        try {
            con = cn.getConection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                acciones.setAccId(rs.getInt("accId"));
                acciones.setAccFechaIngreso(rs.getString("accFechaIngreso"));
                acciones.setAccHoraIngreso(rs.getString("accHoraIngreso"));
                acciones.setAccIP(rs.getString("accIP"));
                acciones.setAccAcciones(rs.getString("accAcciones"));
                acciones.setAccUsuario(rs.getString("accUsuario"));
                acciones.setAccNumeroRadicado(rs.getString("accNumeroRadicado"));
                            
            }
        } catch (SQLException e) {
        }
        return acciones;
    }
    
     public boolean agregar(ConsultarSeguimientoUsuarios consu) {
          String sql = "call gesdoc_sena.sp_AgregarAccionesUsuarios(?, ?, ?, ?, ?, ?);";

        try {
            con = cn.getConection();
            ps = con.prepareCall(sql);

            // Asigna los valores reales a los parámetros del procedimiento almacenado
            ps.setString(1, consu.getAccFechaIngreso());
            ps.setString(2, consu.getAccHoraIngreso());
            ps.setString(3, consu.getAccIP());
            ps.setString(4, consu.getAccAcciones());
            ps.setString(5, consu.getAccUsuario());
            ps.setString(6, consu.getAccNumeroRadicado());
            
            
            
            // Ejecuta el procedimiento almacenado
            ps.execute();

            return true; // Indica que la operación fue exitosa
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false; // Indica que la operación falló
        }
    }
    
    public boolean actualizar (ConsultarSeguimientoUsuarios consu){
    String sql = "call gesdoc_sena.sp_ActualizarAccionesUsuarios(?, ?, ?, ?, ?, ?, ?);";


        try {
            con = cn.getConection();
            ps = con.prepareCall(sql);

            // Asigna los valores reales a los parámetros del procedimiento almacenado  
            ps.setInt(1, consu.getAccId());
            ps.setString(2, consu.getAccFechaIngreso());
            ps.setString(3, consu.getAccHoraIngreso());
            ps.setString(4, consu.getAccIP());
            ps.setString(5, consu.getAccAcciones());
            ps.setString(6, consu.getAccUsuario());
            ps.setString(7, consu.getAccNumeroRadicado());
           
            // Ejecuta el procedimiento almacenado
            ps.execute();

            return true; // Indica que la operación fue exitosa
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false; // Indica que la operación falló
        }
       
    }
    
    public boolean eliminar(int accId) {
        String sql = "call gesdoc_sena.sp_EliminarAccionesUsuarios(?);";

        try {
            con = cn.getConection();
            ps = con.prepareCall(sql);

            // Asigna los valores reales a los parámetros del procedimiento almacenado
            ps.setInt(1, accId);
            ps.execute();

            return true; // Indica que la operación fue exitosa
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false; // Indica que la operación falló
        }
}

    
}