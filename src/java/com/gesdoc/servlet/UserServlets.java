/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.gesdoc.servlet;

import DAOS.UsuarioDAO;
import Modelo.consultausuario;
import com.mysql.cj.xdevapi.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author farud
 */
public class UserServlets extends HttpServlet {

    String listar = "listarusuario_1.jsp";
    String add = "add.jsp";
    String edit = "vistas/edit.jsp";
    consultausuario cus = new consultausuario();
    UsuarioDAO usd = new UsuarioDAO();
    int id;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acceso = "";
        String action = request.getParameter("accion");

        if (action.equalsIgnoreCase("listar")) {
            acceso = listar;

        } else if (action.equalsIgnoreCase("add")) {
            acceso = add;
        } else if (action.equalsIgnoreCase("Agregar")) {
              System.out.println("entro a agregar");
//            String usuId = request.getParameter("txtusuId");
            String usuPrimerNombre = request.getParameter("txtprimernombre");
            System.out.println(usuPrimerNombre);
            String usuSegundoNombre = request.getParameter("txtsegundonombre");
            String usuPrimerApellido = request.getParameter("txtprimerapellido");
            String usuSegundoApellido = request.getParameter("txtsegundopellido");
            String usuNombreUsuario = request.getParameter("txtnombreusuario");
            String usuContrasena = request.getParameter("txtcontrasena");
            String usuCorreo = request.getParameter("txtcorreo");
            String usuFechaRegistro = request.getParameter("txtfecharegistro");
            String usuNivelAcceso = request.getParameter("txttipoUsuario");
            String usuObservaciones = request.getParameter("txtobservaciones");

//            cus.setUsuId(Integer.parseInt(usuId));
            cus.setUsuPrimerNombre(usuPrimerNombre);
            cus.setUsuSegundoNombre(usuSegundoNombre);
            cus.setUsuPrimerApellido(usuPrimerApellido);
            cus.setUsuSegundoApellido(usuSegundoApellido);
            cus.setUsuNombreUsuario(usuNombreUsuario);
            cus.setUsuContrasena(usuContrasena);
            cus.setUsuCorreo(usuCorreo);
            cus.setUsuFechaRegistro(usuFechaRegistro);
            cus.setUsuNivelAcceso(usuNivelAcceso);
            cus.setUsuObservaciones(usuObservaciones);

            usd.add(cus);
            acceso = listar;

        } else if (action.equalsIgnoreCase("eliminar")) {
            id = Integer.parseInt(request.getParameter("id"));
            cus.setUsuId(id);
            usd.eliminar(id);
            acceso = listar;
        } else if (action.equalsIgnoreCase("buscar")) {
            String usuNombreUsuario = request.getParameter("txtbuscar");

            // Llama al método de búsqueda en tu DAO
            consultausuario usuario = usd.buscar(usuNombreUsuario);

            // Guarda el resultado en el request
            request.setAttribute("usuarioEncontrado", usuario);

            // Define la vista donde se mostrará el resultado de la búsqueda
            acceso = "vistas/Listarusuario.jsp"; // Cambia la vista a la que desees redirigir
        }

        response.sendRedirect(acceso);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
