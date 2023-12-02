/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.gesdoc.servlet;

import DAOS.CrudRadicadoEnviadoDAO;
import DAOS.CrudSeguimientoUsuariosDAO;
import Modelo.ConsultarSeguimientoUsuarios;
import Modelo.radicadoenviado;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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

        String acceso = "listar_enviados.jsp";
        String action = request.getParameter("accion");

        if (action.equalsIgnoreCase("listar")) {
            acceso = listar;

        } else if (action.equalsIgnoreCase("editar")) {
            System.out.println("Entro a editar");
            request.setAttribute("envId", request.getParameter("id"));
            acceso = edit;
        } else if (action.equalsIgnoreCase("Actualizar")) {

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

            try {
                LocalDate fechaActual = LocalDate.now();
                DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String accFechaIngreso1 = fechaActual.format(formatoFecha);

                // Obtener la hora de ingreso (en este ejemplo, se usa la hora actual del sistema)
                String accHoraIngreso2 = new SimpleDateFormat("HH:mm:ss").format(new Date());

                // Obtener la dirección IP del cliente
                accIP3 = InetAddress.getLocalHost().getHostAddress();

                HttpSession session = request.getSession();
                String nom = (String) session.getAttribute("nom");

                seguimiento.setAccFechaIngreso(accFechaIngreso1);
                seguimiento.setAccHoraIngreso(accHoraIngreso2);
                seguimiento.setAccIP(accIP3);
                seguimiento.setAccAcciones("El usuario editó un registro en enviados");
                seguimiento.setAccUsuario(nom);
                seguimiento.setAccNumeroRadicado(envNumeroRadicado);

                // Agregar seguimiento a la base de datos
                daoseguimiento.agregar(seguimiento);
            } catch (Exception e) {
                // Manejar la excepción, por ejemplo, registrar en el registro de errores
                e.printStackTrace();
            }
        }

        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);

    }
}
