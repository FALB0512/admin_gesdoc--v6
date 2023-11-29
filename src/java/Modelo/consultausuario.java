/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author farud
 */
public class consultausuario {
    
int usuId;
String usuPrimerNombre;
String usuSegundoNombre; 
String usuPrimerApellido;
String usuSegundoApellido; 
String usuNombreUsuario;
String usuContrasena; 
String usuCorreo; 
String usuFechaRegistro; 
String usuNivelAcceso; 
String usuObservaciones; 

    public consultausuario(int usuId, String usuPrimerNombre, String usuSegundoNombre, String usuPrimerApellido, String usuSegundoApellido, String usuNombreUsuario, String usuContrasena, String usuCorreo, String usuFechaRegistro, String usuNivelAcceso, String usuObservaciones) {
        this.usuId = usuId;
        this.usuPrimerNombre = usuPrimerNombre;
        this.usuSegundoNombre = usuSegundoNombre;
        this.usuPrimerApellido = usuPrimerApellido;
        this.usuSegundoApellido = usuSegundoApellido;
        this.usuNombreUsuario = usuNombreUsuario;
        this.usuContrasena = usuContrasena;
        this.usuCorreo = usuCorreo;
        this.usuFechaRegistro = usuFechaRegistro;
        this.usuNivelAcceso = usuNivelAcceso;
        this.usuObservaciones = usuObservaciones;
    }

    public consultausuario() {
    }

    public int getUsuId() {
        return usuId;
    }

    public void setUsuId(int usuId) {
        this.usuId = usuId;
    }

    public String getUsuPrimerNombre() {
        return usuPrimerNombre;
    }

    public void setUsuPrimerNombre(String usuPrimerNombre) {
        this.usuPrimerNombre = usuPrimerNombre;
    }

    public String getUsuSegundoNombre() {
        return usuSegundoNombre;
    }

    public void setUsuSegundoNombre(String usuSegundoNombre) {
        this.usuSegundoNombre = usuSegundoNombre;
    }

    public String getUsuPrimerApellido() {
        return usuPrimerApellido;
    }

    public void setUsuPrimerApellido(String usuPrimerApellido) {
        this.usuPrimerApellido = usuPrimerApellido;
    }

    public String getUsuSegundoApellido() {
        return usuSegundoApellido;
    }

    public void setUsuSegundoApellido(String usuSegundoApellido) {
        this.usuSegundoApellido = usuSegundoApellido;
    }

    public String getUsuNombreUsuario() {
        return usuNombreUsuario;
    }

    public void setUsuNombreUsuario(String usuNombreUsuario) {
        this.usuNombreUsuario = usuNombreUsuario;
    }

    public String getUsuContrasena() {
        return usuContrasena;
    }

    public void setUsuContrasena(String usuContrasena) {
        this.usuContrasena = usuContrasena;
    }

    public String getUsuCorreo() {
        return usuCorreo;
    }

    public void setUsuCorreo(String usuCorreo) {
        this.usuCorreo = usuCorreo;
    }

    public String getUsuFechaRegistro() {
        return usuFechaRegistro;
    }

    public void setUsuFechaRegistro(String usuFechaRegistro) {
        this.usuFechaRegistro = usuFechaRegistro;
    }

    public String getUsuNivelAcceso() {
        return usuNivelAcceso;
    }

    public void setUsuNivelAcceso(String usuNivelAcceso) {
        this.usuNivelAcceso = usuNivelAcceso;
    }

    public String getUsuObservaciones() {
        return usuObservaciones;
    }

    public void setUsuObservaciones(String usuObservaciones) {
        this.usuObservaciones = usuObservaciones;
    }

    public void eliminar(int id) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   

}
