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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author wiwi_
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class EnviadosServlet extends HttpServlet {

    String listar = "listar_enviados.jsp";
    String agregar = "listar_enviados.jsp.jsp";
//    String edit = "editar_enviados.jsp";
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

        String acceso = "";
        String action = request.getParameter("accion");

        if (action.equalsIgnoreCase("listar")) {
            acceso = listar;

        } else if (action.equalsIgnoreCase("eliminar")) {
            id = Integer.parseInt(request.getParameter("id"));
            enviado.setEnvId(id);
            daoenviado.eliminar(id);
            acceso = listar;
        }
        response.sendRedirect(acceso);

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        processRequest(request, response);

        String acceso = "";
        String action = request.getParameter("accion");

        if (action.equalsIgnoreCase("Insertar")) {
            String path_project = "C:\\Users\\farud\\Desktop\\admin_gesdoc--v4-master\\web\\Arc_Env\\";  // Ruta donde se guardarán los archivos PDF

            Part filePart = request.getPart("file");
            String fileName = filePart.getSubmittedFileName();

            if (!fileName.toLowerCase().endsWith(".pdf")) {
                // Redirigir a la página de error
                response.sendRedirect(request.getContextPath() + "/error.jsp");
                return; // Termina la ejecución del servlet
            } else {

                // Generar un nombre de archivo único para evitar conflictos
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
                LocalDateTime now = LocalDateTime.now();
                String ahora = dtf.format(now);

                String[] split = fileName.split("\\.");
                String ext = split[split.length - 1].toLowerCase();

                String fileName_w = ahora + "." + ext;

                filePart.write(path_project + fileName_w);

                String link = "Arc_PDF/" + fileName_w;  // Ruta relativa a la carpeta web de tu proyecto

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
//            String envArchivoPdf = request.getParameter("file");

                //enviado. setEnvId(Integer.parseInt(envId));
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
                enviado.setEnvArchivoPdf(link);

                daoenviado.agregar(enviado);
//            response.sendRedirect("ListarEnviado1.jsp");
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

                    // Configurar el objeto de seguimiento
                    seguimiento.setAccFechaIngreso(accFechaIngreso1);
                    seguimiento.setAccHoraIngreso(accHoraIngreso2);
                    seguimiento.setAccIP(accIP3);
                    seguimiento.setAccAcciones("El usuario realizo un nuevo registro enviado");
                    seguimiento.setAccUsuario(nom);
                    seguimiento.setAccNumeroRadicado(envNumeroRadicado);

                    // Agregar seguimiento a la base de datos
                    daoseguimiento.agregar(seguimiento);
                } catch (Exception e) {
                    // Manejar la excepción, por ejemplo, registrar en el registro de errores
                    e.printStackTrace();
                }
                acceso = listar; // Puedes ajustar esto según tus necesidades

//            RequestDispatcher vista = request.getRequestDispatcher(acceso);
//            vista.forward(request, response);
                response.sendRedirect(acceso);

            }

        }

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
