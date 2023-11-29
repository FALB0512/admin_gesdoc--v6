/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOS;


import Modelo.otrosradicados;
import controlador.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wiwi_
 */
public class OtrosEnviosDAO {
    
    conexion cn = new conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    otrosradicados otros = new otrosradicados();
    
    
    public List listarotros() {
        ArrayList<otrosradicados> list = new ArrayList<>();
        String sql = "SELECT * FROM gesdoc_sena.otrosenviosrecibidos";
        try {
            con = cn.getConection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                otrosradicados otr = new otrosradicados();
                otr.setOtrId(rs.getInt("otrId"));
                otr.setOtrFechaRecibido(rs.getString("otrFechaRecibido"));
                otr.setOtrNombreRemitente(rs.getString("otrNombreRemitente"));
                otr.setOtrNombreFuncionario_destinatario(rs.getString("otrNombreFuncionario_destinatario"));
                otr.setOtrNumeroRadicado(rs.getString("otrNumeroRadicado"));
                otr.setOtrTipoDocumental(rs.getString("otrTipoDocumental"));
                otr.setOtrCiudadOrigen(rs.getString("otrCiudadOrigen"));
                otr.setOtrAnexos(rs.getInt("otrAnexos"));
                otr.setOtrDependencias(rs.getString("otrDependencias"));
                
                
                
                list.add(otr);
            }
        } catch (SQLException e) {
        }
        return list;
    }
    
    public otrosradicados list(int id) {

        String sql = "SELECT * FROM otrosenviosrecibidos WHERE otrId=" + id;
        try {
            con = cn.getConection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                otros.setOtrId(rs.getInt("otrId"));
                otros.setOtrFechaRecibido(rs.getString("otrFechaRecibido"));
                otros.setOtrNombreRemitente(rs.getString("otrNombreRemitente"));
                otros.setOtrNombreFuncionario_destinatario(rs.getString("otrNombreFuncionario_destinatario"));
                otros.setOtrNumeroRadicado(rs.getString("otrNumeroRadicado"));
                otros.setOtrTipoDocumental(rs.getString("otrTipoDocumental"));
                otros.setOtrCiudadOrigen(rs.getString("otrCiudadOrigen"));
                otros.setOtrAnexos(rs.getInt("otrAnexos"));
                otros.setOtrDependencias(rs.getString("otrDependencias"));
            }
        } catch (SQLException e) {
        }
        return otros;
    }
    
    public boolean agregarotros(otrosradicados otros) {
          String sql = "call gesdoc_sena.sp_AgregarOtrosEnvios(?, ?, ?, ?, ?, ?, ?, ?);";

        try {
            con = cn.getConection();
            ps = con.prepareCall(sql);

            // Asigna los valores reales a los parámetros del procedimiento almacenado
            ps.setString(1, otros.getOtrFechaRecibido());
            ps.setString(2, otros.getOtrNombreRemitente());
            ps.setString(3, otros.getOtrNombreFuncionario_destinatario());
            ps.setString(4, otros.getOtrNumeroRadicado());
            ps.setString(5, otros.getOtrTipoDocumental());
            ps.setString(6, otros.getOtrCiudadOrigen());
            ps.setInt(7, otros.getOtrAnexos());
            ps.setString(8, otros.getOtrDependencias());

            // Ejecuta el procedimiento almacenado
            ps.execute();

            return true; // Indica que la operación fue exitosa
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false; // Indica que la operación falló
        }
    }
    
    public boolean actualizarOtros(otrosradicados otros) {
          String sql = "call gesdoc_sena.sp_ActualizarOtrosEnvios(?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try {
            con = cn.getConection();
            ps = con.prepareCall(sql);

            ps.setInt(1, otros.getOtrId());
            ps.setString(2, otros.getOtrFechaRecibido());
            ps.setString(3, otros.getOtrNombreRemitente());
            ps.setString(4, otros.getOtrNombreFuncionario_destinatario());
            ps.setString(5, otros.getOtrNumeroRadicado());
            ps.setString(6, otros.getOtrTipoDocumental());
            ps.setString(7, otros.getOtrCiudadOrigen());
            ps.setInt(8, otros.getOtrAnexos());
            ps.setString(9, otros.getOtrDependencias());

            // Ejecuta el procedimiento almacenado
            ps.execute();

            return true; // Indica que la operación fue exitosa
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false; // Indica que la operación falló
        }
    }
    
    public boolean eliminarotros(int otrId) {
        String sql = "call gesdoc_sena.sp_EliminarOtrosEnvios(?)";

        try {
            con = cn.getConection();
            ps = con.prepareCall(sql);

            // Asigna los valores reales a los parámetros del procedimiento almacenado
            ps.setInt(1, otrId);
            ps.execute();

            return true; // Indica que la operación fue exitosa
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false; // Indica que la operación falló
        }
}
    
}
