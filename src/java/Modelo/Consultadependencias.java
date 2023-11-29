/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author farud
 */
public class Consultadependencias {
    
    int depId;
String depNombre;
String depDescripcion;
String depCorreoElectronico;
String depTelefono; 

    public Consultadependencias(int depId, String depNombre, String depDescripcion, String depCorreoElectronico, String depTelefono) {
        this.depId = depId;
        this.depNombre = depNombre;
        this.depDescripcion = depDescripcion;
        this.depCorreoElectronico = depCorreoElectronico;
        this.depTelefono = depTelefono;
    }


    public Consultadependencias() {
    }

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    public String getDepNombre() {
        return depNombre;
    }

    public void setDepNombre(String depNombre) {
        this.depNombre = depNombre;
    }

    public String getDepDescripcion() {
        return depDescripcion;
    }

    public void setDepDescripcion(String depDescripcion) {
        this.depDescripcion = depDescripcion;
    }

    public String getDepCorreoElectronico() {
        return depCorreoElectronico;
    }

    public void setDepCorreoElectronico(String depCorreoElectronico) {
        this.depCorreoElectronico = depCorreoElectronico;
    }

    public String getDepTelefono() {
        return depTelefono;
    }

    public void setDepTelefono(String depTelefono) {
        this.depTelefono = depTelefono;
    }


    
    
    
    
    
    
    
}
