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

/**
 *
 * @author farud
 */
public class CrudRadicadoEnviadoDAO {
    
    
    conexion cn = new conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    radicadoenviado condoc = new radicadoenviado();

    public List listar() {
        ArrayList<radicadoenviado> list = new ArrayList<>();
        String sql = "SELECT * FROM tblradicadoenviados";
        try {
            con = cn.getConection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                radicadoenviado enviado = new radicadoenviado();
                enviado.setEnvId(rs.getInt("envId"));
                enviado.setEnvNumeroRadicado(rs.getString("envNumeroRadicado"));
                enviado.setEnvFechaRadicacion(rs.getString("envFechaRadicacion"));
                enviado.setEnvDependencia(rs.getString("envDependencia"));
                enviado.setEnvNombreFuncionario(rs.getString("envNombreFuncionario"));
                enviado.setEnvAsunto(rs.getString("envAsunto"));
                enviado.setEnvAnexos(rs.getInt("envAnexos"));
                enviado.setEnvAntecedentes(rs.getString("envAntecedentes"));
                enviado.setEnvEntidadDestino(rs.getString("envEntidadDestino"));
                enviado.setEnvNombreDestinatario(rs.getString("envNombreDestinatario"));
                enviado.setEnvCiudad(rs.getString("envCiudad"));
                enviado.setEnvTipoDocumental(rs.getString("envTipoDocumental"));
                enviado.setEnvObservaciones(rs.getString("envObservaciones"));
                enviado.setEnvArchivoPdf(rs.getString("envArchivoPdf"));


                list.add(enviado);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public radicadoenviado list(int id) {

        String sql = "SELECT * FROM tblradicadoenviados WHERE envId=" + id;
        try {
            con = cn.getConection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                condoc.setEnvId(rs.getInt("envId"));
                condoc.setEnvNumeroRadicado(rs.getString("envNumeroRadicado"));
                condoc.setEnvFechaRadicacion(rs.getString("envFechaRadicacion"));
                condoc.setEnvDependencia(rs.getString("envDependencia"));
                condoc.setEnvNombreFuncionario(rs.getString("envNombreFuncionario"));
                condoc.setEnvAsunto(rs.getString("envAsunto"));
                condoc.setEnvAnexos(rs.getInt("envAnexos"));
                condoc.setEnvAntecedentes(rs.getString("envAntecedentes"));
                condoc.setEnvEntidadDestino(rs.getString("envEntidadDestino"));
                condoc.setEnvNombreDestinatario(rs.getString("envNombreDestinatario"));
                condoc.setEnvCiudad(rs.getString("envCiudad"));
                condoc.setEnvTipoDocumental(rs.getString("envTipoDocumental"));
                condoc.setEnvObservaciones(rs.getString("envObservaciones"));
                condoc.setEnvArchivoPdf(rs.getString("envArchivoPdf"));
                            
            }
        } catch (SQLException e) {
        }
        return condoc;
    }
    
    public boolean agregar(radicadoenviado env) {
          String sql = "call gesdoc_sena.sp_AgregarRadicadoEnviado(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try {
            con = cn.getConection();
            ps = con.prepareCall(sql);

            // Asigna los valores reales a los parámetros del procedimiento almacenado
            ps.setString(1, env.getEnvNumeroRadicado());
            ps.setString(2, env.getEnvFechaRadicacion());
            ps.setString(3, env.getEnvDependencia());
            ps.setString(4, env.getEnvNombreFuncionario());
            ps.setString(5, env.getEnvAsunto());
            ps.setInt(6, env.getEnvAnexos());
            ps.setString(7, env.getEnvAntecedentes());
            ps.setString(8, env.getEnvEntidadDestino());
            ps.setString(9, env.getEnvNombreDestinatario());
            ps.setString(10, env.getEnvCiudad());
            ps.setString(11, env.getEnvTipoDocumental());
            ps.setString(12, env.getEnvObservaciones());
            ps.setString(13, env.getEnvArchivoPdf());


            // Ejecuta el procedimiento almacenado
            ps.execute();

            return true; // Indica que la operación fue exitosa
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false; // Indica que la operación falló
        }
    }
    
    public boolean actualizar (radicadoenviado env){
    String sql = "call gesdoc_sena.sp_ActualizarRadicadoEnvio(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";


        try {
            con = cn.getConection();
            ps = con.prepareCall(sql);

            // Asigna los valores reales a los parámetros del procedimiento almacenado  
            ps.setInt(1, env.getEnvId());
            ps.setString(2, env.getEnvNumeroRadicado());
            ps.setString(3, env.getEnvFechaRadicacion());
            ps.setString(4, env.getEnvDependencia());
            ps.setString(5, env.getEnvNombreFuncionario());
            ps.setString(6, env.getEnvAsunto());
            ps.setInt(7, env.getEnvAnexos());
            ps.setString(8, env.getEnvAntecedentes());
            ps.setString(9, env.getEnvEntidadDestino());
            ps.setString(10, env.getEnvNombreDestinatario());
            ps.setString(11, env.getEnvCiudad());
            ps.setString(12, env.getEnvTipoDocumental());
            ps.setString(13, env.getEnvObservaciones());

            // Ejecuta el procedimiento almacenado
            ps.execute();

            return true; // Indica que la operación fue exitosa
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false; // Indica que la operación falló
        }
    
        
    }
    
    public boolean eliminar(int envId) {
        String sql = "CALL gesdoc_sena.sp_EliminarRadicadoEnvId(?)";

        try {
            con = cn.getConection();
            ps = con.prepareCall(sql);

            // Asigna los valores reales a los parámetros del procedimiento almacenado
            ps.setInt(1, envId);
            ps.execute();

            return true; // Indica que la operación fue exitosa
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false; // Indica que la operación falló
        }
}


    
}
