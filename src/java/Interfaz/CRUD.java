/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaz;

import java.util.List;
import Modelo.consultausuario;

/**
 *
 * @author farud
 */
public interface CRUD {
     public List listar();
     public consultausuario list(int id);
     public boolean add (consultausuario usu);
     public boolean edit (consultausuario usu);
    
}
