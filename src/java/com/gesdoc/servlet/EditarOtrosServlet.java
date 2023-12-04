/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.gesdoc.servlet;

import DAOS.CrudSeguimientoUsuariosDAO;
import DAOS.OtrosEnviosDAO;
import Modelo.ConsultarSeguimientoUsuarios;
import Modelo.otrosradicados;
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
 * @author ADSO
 */
public class EditarOtrosServlet extends HttpServlet {
    String listar = "listar_otros.jsp";
    String editar_radicado = "editar_otros.jsp";
    otrosradicados otrosradicados = new otrosradicados();
    OtrosEnviosDAO daootros = new OtrosEnviosDAO();
    int id;
    
    ConsultarSeguimientoUsuarios seguimiento = new ConsultarSeguimientoUsuarios();
    CrudSeguimientoUsuariosDAO daoseguimiento = new CrudSeguimientoUsuariosDAO();
    String accFechaIngreso1;
    String accHoraIngreso2;
    String accIP3;

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
            out.println("<title>Servlet EditarOtrosServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditarOtrosServlet at " + request.getContextPath() + "</h1>");
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
            acceso = listar; // Ajusta la URL según tu estructura
        } else if (action.equalsIgnoreCase("editar")) {
            request.setAttribute("otrId", request.getParameter("id"));
            acceso = editar_radicado; // Ajusta la URL según tu estructura
        } else if (action.equalsIgnoreCase("Actualizar")) {
            try {
          
            String otrId = request.getParameter("otrId");
            String otrFechaRecibido = request.getParameter("fecha_recepcion");
            String otrNombreRemitente = request.getParameter("nombre_entidad");
            String otrNombreFuncionario_destinatario = request.getParameter("nombre_funcionario");
            String otrNumeroRadicado = request.getParameter("numero_radicado");
            String otrTipoDocumental = request.getParameter("tipo_documental");
            String otrCiudadOrigen = request.getParameter("ciudad");
            String otrAnexos = request.getParameter("anexos");
            String otrDependencias = request.getParameter("Dependencia");
            
            otrosradicados. setOtrId(Integer.parseInt(otrId));
            otrosradicados. setOtrFechaRecibido(otrFechaRecibido);
            otrosradicados. setOtrNombreRemitente(otrNombreRemitente);
            otrosradicados. setOtrNombreFuncionario_destinatario(otrNombreFuncionario_destinatario);
            otrosradicados. setOtrNumeroRadicado(otrNumeroRadicado);
            otrosradicados. setOtrTipoDocumental(otrTipoDocumental);
            otrosradicados. setOtrCiudadOrigen(otrCiudadOrigen);
            otrosradicados. setOtrAnexos(Integer.parseInt(otrAnexos));
            otrosradicados. setOtrDependencias(otrDependencias);
        
            daootros.actualizarOtros(otrosradicados);
            
            acceso = listar; // Puedes ajustar esto según tus necesidades
            HttpSession session = request.getSession();
                session.setAttribute("otrNumeroRadicado", otrNumeroRadicado);

                // Redirige al segundo servlet
                response.sendRedirect("seguimiento_editarotros"); // Ajusta la URL según tu estructura

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
