/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.gesdoc.servlet;

import DAOS.CrudRadicadoEnviadoDAO;
import Modelo.radicadoenviado;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author wiwi_
 */
public class EditarEnviadoServlet extends HttpServlet {

    String listar = "listar_enviados.jsp";
    String edit = "editar_enviados.jsp";
    radicadoenviado enviado = new radicadoenviado();
    CrudRadicadoEnviadoDAO daoenviado = new CrudRadicadoEnviadoDAO();
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
            out.println("<title>Servlet EnviadosServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EnviadosServlet at " + request.getContextPath() + "</h1>");
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

        String acceso = "";
        String action = request.getParameter("accion");

        if (action.equalsIgnoreCase("listar")) {
            acceso = listar; // Ajusta la URL según tu estructura
        } else if (action.equalsIgnoreCase("editar")) {
            request.setAttribute("envId", request.getParameter("id"));
            acceso = edit; // Ajusta la URL según tu estructura
        } else if (action.equalsIgnoreCase("Actualizar")) {
            try {

                String envId = request.getParameter("envId");
                String envNumeroRadicado = request.getParameter("numero_radicado");
                String envFechaRadicacion = request.getParameter("fecha_recepcion");
                String envDependencia = request.getParameter("Dependencia");
                String envNombreFuncionario = request.getParameter("NombreFuncionario");
                String envAsunto = request.getParameter("Asunto");
                String envAnexos = request.getParameter("anexos");
                String envAntecedentes = request.getParameter("Antecedentes");
                String envEntidadDestino = request.getParameter("Entidad");
                String envNombreDestinatario = request.getParameter("nombre_destinatario");
                String envCiudad = request.getParameter("ciudad");
                String envTipoDocumental = request.getParameter("TipoDocumental");
                String envObservaciones = request.getParameter("Observaciones");

                enviado.setEnvId(Integer.parseInt(envId));
                enviado.setEnvNumeroRadicado(envNumeroRadicado);
                enviado.setEnvFechaRadicacion(envFechaRadicacion);
                enviado.setEnvDependencia(envDependencia);
                enviado.setEnvNombreFuncionario(envNombreFuncionario);
                enviado.setEnvAsunto(envAsunto);
                enviado.setEnvAnexos(Integer.parseInt(envAnexos));
                enviado.setEnvAntecedentes(envAntecedentes);
                enviado.setEnvEntidadDestino(envEntidadDestino);
                enviado.setEnvNombreDestinatario(envNombreDestinatario);
                enviado.setEnvCiudad(envCiudad);
                enviado.setEnvTipoDocumental(envTipoDocumental);
                enviado.setEnvObservaciones(envObservaciones);

                daoenviado.actualizar(enviado);
                acceso = listar; // Puedes ajustar esto según tus necesidades

                HttpSession session = request.getSession();
                session.setAttribute("envNumeroRadicado", envNumeroRadicado);

                // Redirige al segundo servlet
                response.sendRedirect("seguimiento_editarenviados"); // Ajusta la URL según tu estructura

                return; // Importante agregar esto para evitar que se ejecute el resto del código

            } catch (Exception e) {
                e.printStackTrace();
            }
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