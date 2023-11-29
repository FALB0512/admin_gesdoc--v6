/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author farud
 */
public class ConsultarSeguimientoUsuarios {

    int accId;
    String accFechaIngreso;
    String accHoraIngreso;
    String accIP;
    String accAcciones;
    String accUsuario;
    String accNumeroRadicado;

    public ConsultarSeguimientoUsuarios() {
    }

    public ConsultarSeguimientoUsuarios(int accId, String accFechaIngreso, String accHoraIngreso, String accIP, String accAcciones, String accUsuario, String accNumeroRadicado) {
        this.accId = accId;
        this.accFechaIngreso = accFechaIngreso;
        this.accHoraIngreso = accHoraIngreso;
        this.accIP = accIP;
        this.accAcciones = accAcciones;
        this.accUsuario = accUsuario;
        this.accNumeroRadicado = accNumeroRadicado;
    }

    public int getAccId() {
        return accId;
    }

    public void setAccId(int accId) {
        this.accId = accId;
    }

    public String getAccFechaIngreso() {
        return accFechaIngreso;
    }

    public void setAccFechaIngreso(String accFechaIngreso) {
        this.accFechaIngreso = accFechaIngreso;
    }

    public String getAccHoraIngreso() {
        return accHoraIngreso;
    }

    public void setAccHoraIngreso(String accHoraIngreso) {
        this.accHoraIngreso = accHoraIngreso;
    }

    public String getAccIP() {
        return accIP;
    }

    public void setAccIP(String accIP) {
        this.accIP = accIP;
    }

    public String getAccAcciones() {
        return accAcciones;
    }

    public void setAccAcciones(String accAcciones) {
        this.accAcciones = accAcciones;
    }

    public String getAccUsuario() {
        return accUsuario;
    }

    public void setAccUsuario(String accUsuario) {
        this.accUsuario = accUsuario;
    }

    public String getAccNumeroRadicado() {
        return accNumeroRadicado;
    }

    public void setAccNumeroRadicado(String accNumeroRadicado) {
        this.accNumeroRadicado = accNumeroRadicado;
    }
    
    
    
    

}
