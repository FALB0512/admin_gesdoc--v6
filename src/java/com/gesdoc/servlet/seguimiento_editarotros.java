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
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 *
 * @author jorge
 */
public class seguimiento_editarotros extends HttpServlet {
    
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
            out.println("<title>Servlet seguimiento_editarotros</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet seguimiento_editarotros at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            LocalDate fechaActual = LocalDate.now();
            DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String accFechaIngreso1 = fechaActual.format(formatoFecha);

            // Obtener la hora de ingreso (en este ejemplo, se usa la hora actual del sistema)
            String accHoraIngreso2 = new SimpleDateFormat("HH:mm:ss").format(new Date());

            // Obtener la dirección IP del cliente
            String accIP3 = InetAddress.getLocalHost().getHostAddress();

            HttpSession session = request.getSession();
                    String nom = (String) session.getAttribute("nom");
                    String otrNumeroRadicado = (String) session.getAttribute("otrNumeroRadicado");

                    // Configurar el objeto de seguimiento
                    seguimiento.setAccFechaIngreso(accFechaIngreso1);
                    seguimiento.setAccHoraIngreso(accHoraIngreso2);
                    seguimiento.setAccIP(accIP3);
                    seguimiento.setAccAcciones("El usuario editò un campo en otros");
                    seguimiento.setAccUsuario(nom);
                    seguimiento.setAccNumeroRadicado(otrNumeroRadicado);

                    // Agregar seguimiento a la base de datos
                    daoseguimiento.agregar(seguimiento);

            // Agregar seguimiento a la base de datos (ajusta según tu implementación)
            // Daoseguimiento daoseguimiento = new Daoseguimiento();
            // daoseguimiento.agregar(seguimiento);

        } catch (Exception e) {
            // Manejar la excepción, por ejemplo, registrar en el registro de errores
            e.printStackTrace();
        }
        response.sendRedirect("listar_otros.jsp");
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
