/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.gesdoc.servlet;

import DAOS.CrudRadicadoEnviadoDAO;
import Modelo.radicadoenviado;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author farud
 */
public class RegistarEnviadoServlets extends HttpServlet {

    String listar = "listar_enviados.jsp";
    String add = "resgiscorresenviada.jsp";
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
            out.println("<title>Servlet RegistarEnviadoServlets</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegistarEnviadoServlets at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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

        String acceso = "";
        String action = request.getParameter("accion");

        if (action == null) {
            action = "Registrar"; // Asignar un valor predeterminado si es nulo
        }

        if (action.equalsIgnoreCase("Registrar")) {
            acceso = add;
            System.out.println("El usuario realizo un nuevo registro");

            // Ruta donde se guardarán los archivos PDF
            String path_project = "C:\\Users\\farud\\Desktop\\admin_gesdoc -v4\\web\\Arc_PDF\\";

            // Obtener la parte del formulario que representa el archivo a cargar
            Part filePart = request.getPart("file");

            // Obtener el nombre del archivo seleccionado por el usuario
            String fileName = filePart.getSubmittedFileName();

            if (!fileName.toLowerCase().endsWith(".pdf")) {
                // Si el archivo no tiene extensión .pdf, redirigir a una página de error
                response.sendRedirect(request.getContextPath() + "/error.jsp");
                return; // Terminar la ejecución del servlet
            } else {
                // Continuar con la carga del archivo
                // Generar un nombre único para el archivo basado en la fecha y hora
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
                LocalDateTime now = LocalDateTime.now();
                String ahora = dtf.format(now);

                // Obtener la extensión del archivo y construir un nuevo nombre
                String[] split = fileName.split("\\.");
                String ext = split[split.length - 1].toLowerCase();
                String fileName_w = ahora + "." + ext;

                // Guardar el archivo en la ubicación especificada
                filePart.write(path_project + fileName_w);

                // Crear un enlace relativo al archivo PDF
                String link = "Arc_PDF/" + fileName_w;

                if (action.equalsIgnoreCase("Registrar")) {
                    System.out.println("el usuario realizo un nuevo registro");

                    //String envId = request.getParameter("txtenvId");
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
//                    String envArchivoPdf = request.getParameter("archivopdf");

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
                    acceso = listar;

                    RequestDispatcher vista = request.getRequestDispatcher(acceso);
                    vista.forward(request, response);

                }

            }
        }
    }
}
