/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author farud
 */
public class ConsultaEntidades {
    
    int entId; 
    String entNombre;
    String entTipoEntidad; 
    String entDireccion; 
    String entTelefono; 
    String entCorreo;

    public ConsultaEntidades(int entId, String entNombre, String entTipoEntidad, String entDireccion, String entTelefono, String entCorreo) {
        this.entId = entId;
        this.entNombre = entNombre;
        this.entTipoEntidad = entTipoEntidad;
        this.entDireccion = entDireccion;
        this.entTelefono = entTelefono;
        this.entCorreo = entCorreo;
    }

    
    
    public ConsultaEntidades() {
    }

    public int getEntId() {
        return entId;
    }

    public void setEntId(int entId) {
        this.entId = entId;
    }

    public String getEntNombre() {
        return entNombre;
    }

    public void setEntNombre(String entNombre) {
        this.entNombre = entNombre;
    }

    public String getEntTipoEntidad() {
        return entTipoEntidad;
    }

    public void setEntTipoEntidad(String entTipoEntidad) {
        this.entTipoEntidad = entTipoEntidad;
    }

    public String getEntDireccion() {
        return entDireccion;
    }

    public void setEntDireccion(String entDireccion) {
        this.entDireccion = entDireccion;
    }

    public String getEntTelefono() {
        return entTelefono;
    }

    public void setEntTelefono(String entTelefono) {
        this.entTelefono = entTelefono;
    }

    public String getEntCorreo() {
        return entCorreo;
    }

    public void setEntCorreo(String entCorreo) {
        this.entCorreo = entCorreo;
    }

    
    
}
