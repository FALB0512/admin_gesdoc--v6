/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOS;


import controlador.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Modelo.radicadorecibido;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wiwi_
 */
public class CrudRadicadoRecibidoDAO {
    conexion cn = new conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    radicadorecibido radicadorecibido = new radicadorecibido();      
    
     public List listarrecibidos() {
        ArrayList<radicadorecibido> listarrecibidos = new ArrayList<>();
        String sql = "SELECT * FROM tblradicadorecibido";
        try {
            con = cn.getConection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                radicadorecibido recibido = new radicadorecibido(); 
                
                recibido.setRadId(rs.getInt("RadId"));
                recibido.setRadNumeroRadicado(rs.getString("radNumeroRadicado"));
                recibido.setRadFechaRespuesta(rs.getString("radFechaRespuesta"));
                recibido.setRadFechaRecepcion(rs.getString("radFechaRecepcion"));
                recibido.setRadNombreEntidadPersona(rs.getString("radNombreEntidadPersona"));
                recibido.setRadAsunto(rs.getString("radAsunto"));
                recibido.setRadRadicadoOrigen(rs.getString("radRadicadoOrigen"));
                recibido.setRadFechaCreacionDocumento(rs.getString("radFechaCreacionDocumento"));
                recibido.setRadAnexos(rs.getInt("radAnexos"));
                recibido.setRadCiudad(rs.getString("radCiudad"));
                recibido.setRadNumeroRadRespuesta(rs.getString("radNumeroRadRespuesta"));
                recibido.setRadDependencias(rs.getString("radDependencias"));
                recibido.setRadNombreDestinatario(rs.getString("radNombreDestinatario"));
                recibido.setRadArchivoPdf(rs.getString("radArchivoPdf"));
                
                listarrecibidos.add(recibido);
                            
            }
        } catch (SQLException e) {
        }
        return listarrecibidos;
    }
     
   
    public radicadorecibido editar(int id) {

        String sql = "SELECT * FROM tblradicadorecibido WHERE radId=" + id;
        try {
            con = cn.getConection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                radicadorecibido.setRadId(rs.getInt("RadId"));
                radicadorecibido.setRadNumeroRadicado(rs.getString("radNumeroRadicado"));
                radicadorecibido.setRadFechaRespuesta(rs.getString("radFechaRespuesta"));
                radicadorecibido.setRadFechaRecepcion(rs.getString("radFechaRecepcion"));
                radicadorecibido.setRadNombreEntidadPersona(rs.getString("radNombreEntidadPersona"));
                radicadorecibido.setRadAsunto(rs.getString("radAsunto"));
                radicadorecibido.setRadRadicadoOrigen(rs.getString("radRadicadoOrigen"));
                radicadorecibido.setRadFechaCreacionDocumento(rs.getString("radFechaCreacionDocumento"));
                radicadorecibido.setRadAnexos(rs.getInt("radAnexos"));
                radicadorecibido.setRadCiudad(rs.getString("radCiudad"));
                radicadorecibido.setRadNumeroRadRespuesta(rs.getString("radNumeroRadRespuesta"));
                radicadorecibido.setRadDependencias(rs.getString("radDependencias"));
                radicadorecibido.setRadNombreDestinatario(rs.getString("radNombreDestinatario"));
                radicadorecibido.setRadArchivoPdf(rs.getString("radArchivoPdf"));
                
                                              
            }
        } catch (SQLException e) {
        }
        return radicadorecibido;
    }
    
    
    
    public boolean agregarrecibido(radicadorecibido rec) {
          String sql = "call gesdoc_sena.sp_AgregarRadicadoRecibido(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try {
            con = cn.getConection();
            ps = con.prepareCall(sql);

            // Asigna los valores reales a los parámetros del procedimiento almacenado
            ps.setString(1, rec.getRadNumeroRadicado());
            ps.setString(2, rec.getRadFechaRespuesta());
            ps.setString(3, rec.getRadFechaRecepcion());
            ps.setString(4, rec.getRadNombreEntidadPersona());
            ps.setString(5, rec.getRadAsunto());
            ps.setString(6, rec.getRadRadicadoOrigen());
            ps.setString(7, rec.getRadFechaCreacionDocumento());
            ps.setInt(8, rec.getRadAnexos());
            ps.setString(9, rec.getRadCiudad());
            ps.setString(10, rec.getRadNumeroRadRespuesta());
            ps.setString(11, rec.getRadDependencias());
            ps.setString(12, rec.getRadNombreDestinatario());
            ps.setString(13, rec.getRadArchivoPdf());


            // Ejecuta el procedimiento almacenado
            ps.execute();

            return true; // Indica que la operación fue exitosa
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false; // Indica que la operación falló
        }
    }
    
    public boolean actualizarrecibido(radicadorecibido rec) {
          String sql = "call gesdoc_sena.sp_ActualizarRadicadoRecibido(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try {
            con = cn.getConection();
            ps = con.prepareCall(sql);

            ps.setInt(1, rec.getRadId());
            ps.setString(2, rec.getRadNumeroRadicado());
            ps.setString(3, rec.getRadFechaRespuesta());
            ps.setString(4, rec.getRadFechaRecepcion());
            ps.setString(5, rec.getRadNombreEntidadPersona());
            ps.setString(6, rec.getRadAsunto());
            ps.setString(7, rec.getRadRadicadoOrigen());
            ps.setString(8, rec.getRadFechaCreacionDocumento());
            ps.setInt(9, rec.getRadAnexos());
            ps.setString(10, rec.getRadCiudad());
            ps.setString(11, rec.getRadNumeroRadRespuesta());
            ps.setString(12, rec.getRadDependencias());
            ps.setString(13, rec.getRadNombreDestinatario());


            
            // Ejecuta el procedimiento almacenado
            ps.execute();

            return true; // Indica que la operación fue exitosa
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false; // Indica que la operación falló
        }
    }
    
    public boolean eliminarrecibido(int radId) {
        String sql = "CALL gesdoc_sena.sp_EliminarRadicadoRecibido(?)";

        try {
            con = cn.getConection();
            ps = con.prepareCall(sql);

            // Asigna los valores reales a los parámetros del procedimiento almacenado
            ps.setInt(1, radId);
            ps.execute();

            return true; // Indica que la operación fue exitosa
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false; // Indica que la operación falló
        }
}
    

    }

