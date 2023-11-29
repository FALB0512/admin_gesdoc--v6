/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.gesdoc.servlet;

import DAOS.DependenciaDAO;
import Modelo.Consultadependencias;
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
public class DependenciaServlets extends HttpServlet {

    String listar = "listardependencias.jsp";
    String add = "vistas/add.jsp";
    String edit = "vistas/edit.jsp";
    Consultadependencias dependencias = new Consultadependencias();
    DependenciaDAO dependao = new DependenciaDAO();
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
            out.println("<title>Servlet DependenciaServlets</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DependenciaServlets at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //processRequest(request, response);

        String acceso = "";
        String action = request.getParameter("accion");

        if (action.equalsIgnoreCase("listar")) {
            acceso = listar;

        } else if (action.equalsIgnoreCase("add")) {
            acceso = add;
        } else if (action.equalsIgnoreCase("Agregar")) {

//            String usuId = request.getParameter("txtusuId");
            String depNombre = request.getParameter("txtnombre");
            String depDescripcion = request.getParameter("txtdescripcion");
            String depCorreoElectronico = request.getParameter("txtcorreo");
            String depTelefono = request.getParameter("txttelefono");

//            cus.setUsuId(Integer.parseInt(usuId));
            dependencias.setDepNombre(depNombre);
            dependencias.setDepDescripcion(depDescripcion);
            dependencias.setDepDescripcion(depCorreoElectronico);
            dependencias.setDepTelefono(depTelefono);
            dependao.add(dependencias);
            acceso = listar;

        } else if (action.equalsIgnoreCase("editar")) {
            request.setAttribute("depId", request.getParameter("id"));
            acceso = edit;
        } else if (action.equalsIgnoreCase("Actualizar")) {
            String depNombre = request.getParameter("txtnombre");
            String depDescripcion = request.getParameter("txtdescripcion");
            String depCorreoElectronico = request.getParameter("txtcorreo");
            String depTelefono = request.getParameter("txttelefono");

            dependencias.setDepNombre(depNombre);
            dependencias.setDepDescripcion(depDescripcion);
            dependencias.setDepDescripcion(depCorreoElectronico);
            dependencias.setDepTelefono(depTelefono);
            dependao.add(dependencias);
            acceso = listar;

        } else if (action.equalsIgnoreCase("eliminar")) {
            id = Integer.parseInt(request.getParameter("id"));
            dependencias.setDepId(id);
            dependao.eliminar(id);
            acceso = listar;
        }

        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);

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
