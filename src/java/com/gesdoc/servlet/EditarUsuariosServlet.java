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
 * @author ADSO
 */
public class EditarUsuariosServlet extends HttpServlet {
    
    String listar = "listarusuario_1.jsp";
    String edit = "vistas/edit.jsp";
    String actualizar = "vistas/usuarios.jsp";
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditarUsuariosServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditarUsuariosServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        
        String acceso = "";
        String action = request.getParameter("accion");

        if (action.equalsIgnoreCase("listar")) {
            acceso = listar;

        } 
        
         else if (action.equalsIgnoreCase("editar")) {
            request.setAttribute("usuId", request.getParameter("id"));
            acceso = edit;
        } else if (action.equalsIgnoreCase("Actualizar")) {
            String usuId = request.getParameter("txtusuId");
            String usuPrimerNombre = request.getParameter("txtprimernombre");
            String usuSegundoNombre = request.getParameter("txtsegundonombre");
            String usuPrimerApellido = request.getParameter("txtprimerapellido");
            String usuSegundoApellido = request.getParameter("txtsegundopellido");
            String usuNombreUsuario = request.getParameter("txtnombreusuario");
            String usuContrasena = request.getParameter("txtcontrasena");
            String usuCorreo = request.getParameter("txtcorreo");
            String usuFechaRegistro = request.getParameter("txtfecharegistro");
            String usuNivelAcceso = request.getParameter("txttipoUsuario");
            String usuObservaciones = request.getParameter("txtobservaciones");

            cus.setUsuId(Integer.parseInt(usuId));
            cus.setUsuPrimerNombre(usuPrimerNombre);
            cus.setUsuSegundoNombre(usuSegundoNombre);
            cus.setUsuPrimerApellido(usuPrimerApellido);
            cus.setUsuSegundoApellido(usuSegundoApellido);
            cus.setUsuNombreUsuario(usuNombreUsuario);
            cus.setUsuContrasena(usuContrasena);
            cus.setUsuCorreo(usuCorreo);
            cus.setUsuFechaRegistro(usuFechaRegistro);
            cus.setUsuFechaRegistro(usuFechaRegistro);
            cus.setUsuNivelAcceso(usuNivelAcceso);
            cus.setUsuObservaciones(usuObservaciones);

            usd.edit(cus);
            acceso = listar;
        }
        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
