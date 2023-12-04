/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.gesdoc.servlet;

import DAOS.CrudRadicadoRecibidoDAO;
import DAOS.CrudRadicadoRecibidoDAO;
import DAOS.CrudSeguimientoUsuariosDAO;
import Modelo.ConsultarSeguimientoUsuarios;
import Modelo.radicadorecibido;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author farud
 */

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class recibidoservlet extends HttpServlet {

    String listar = "listar_recibidos.jsp";
    String agregarrecibido = "registrarcorresrecibida.jsp";
    String editar_radicado = "editar_recibidos.jsp";
    radicadorecibido radicadorecibido = new radicadorecibido();
    CrudRadicadoRecibidoDAO daorecibido = new CrudRadicadoRecibidoDAO();
    
    ConsultarSeguimientoUsuarios seguimiento = new ConsultarSeguimientoUsuarios();
    CrudSeguimientoUsuariosDAO daoseguimiento = new CrudSeguimientoUsuariosDAO();
    String accFechaIngreso1;
    String accHoraIngreso2;
    String accIP3;

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
            out.println("<title>Servlet recibidoservlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet recibidoservlet at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { response.setCharacterEncoding("UTF-8");
        String acceso = "";
        String action = request.getParameter("accion");

        if (action.equalsIgnoreCase("listar")) {
            acceso = listar;

        } else if (action.equalsIgnoreCase("eliminar")) {
            id = Integer.parseInt(request.getParameter("id"));
            radicadorecibido.setRadId(id);
            daorecibido.eliminarrecibido(id);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { response.setCharacterEncoding("UTF-8");

        String acceso = "";
        String action = request.getParameter("accion");

        if (action.equalsIgnoreCase("Registrar")) {
            System.out.println("entro a registrar");
            acceso = agregarrecibido;
            

            String path_project = "C:\\Users\\ADSO\\Documents\\GitHub\\admin_gesdoc--v6\\web\\Arc_PDF\\";  // Ruta donde se guardarán los archivos PDF

            String path_project = "C:\\Users\\ADSO\\Documents\\GitHub\\admin_gesdoc--v6\\web\\Arc_Rec\\";  // Ruta donde se guardarán los archivos PDF


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

                String link = "Rec_PDF/" + fileName_w;  // Ruta relativa a la carpeta web de tu proyecto

                String radNumeroRadicado = request.getParameter("numero_radicado");
                String radFechaRespuesta = request.getParameter("fecha_respuesta");
                String radFechaRecepcion = request.getParameter("fecha_recepcion");
                String radNombreEntidadPersona = request.getParameter("nombre_entidad");
                String radAsunto = request.getParameter("asunto");
                String radRadicadoOrigen = request.getParameter("numero_origen");
                String radFechaCreacionDocumento = request.getParameter("fecha_creacion");
                String radAnexos = request.getParameter("anexos");
                String radCiudad = request.getParameter("ciudad");
                String radNumeroRadRespuesta = request.getParameter("NumeroRadRespuesta");
                String radDependencias = request.getParameter("Dependencia");
                String radNombreDestinatario = request.getParameter("nombre_destinatario");
                String radCorreoFuncionarioDestinatario = request.getParameter("Correo_Funcionario_Destinatario");

                radicadorecibido.setRadNumeroRadicado(radNumeroRadicado);
                radicadorecibido.setRadFechaRespuesta(radFechaRespuesta);
                radicadorecibido.setRadFechaRecepcion(radFechaRecepcion);
                radicadorecibido.setRadNombreEntidadPersona(radNombreEntidadPersona);
                radicadorecibido.setRadAsunto(radAsunto);
                radicadorecibido.setRadRadicadoOrigen(radRadicadoOrigen);
                radicadorecibido.setRadFechaCreacionDocumento(radFechaCreacionDocumento);
                radicadorecibido.setRadAnexos(Integer.parseInt(radAnexos));
                radicadorecibido.setRadCiudad(radCiudad);
                radicadorecibido.setRadNumeroRadRespuesta(radNumeroRadRespuesta);
                radicadorecibido.setRadDependencias(radDependencias);
                radicadorecibido.setRadNombreDestinatario(radNombreDestinatario);
                radicadorecibido.setRadArchivoPdf(link);
                radicadorecibido.setRadCorreoFuncionarioDestinatario(radCorreoFuncionarioDestinatario);

                daorecibido.agregarrecibido(radicadorecibido);
                
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
                    seguimiento.setAccAcciones("El usuario realizo un nuevo registro recibido");
                    seguimiento.setAccUsuario(nom);
                    seguimiento.setAccNumeroRadicado(radNumeroRadicado);

                    // Agregar seguimiento a la base de datos
                    daoseguimiento.agregar(seguimiento);
                } catch (Exception e) {
                    // Manejar la excepción, por ejemplo, registrar en el registro de errores
                    e.printStackTrace();
                }
                
                
                // Configuración de las propiedades del correo
                Properties props = new Properties();
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.port", "587");

                // Dirección de correo y contraseña de aplicaciones en gmail
                String correo = "gesdocsena2023@gmail.com";
                String contrasena = "eerzwiqrbvyspdol";

                // Crear una sesión de correo
                Session session = Session.getInstance(props, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(correo, contrasena);
                    }
                });

                try {
                    // Crear un mensaje de correo
                    Message mensaje = new MimeMessage(session);
                    mensaje.setFrom(new InternetAddress(correo));

                    // Direcciones de correo
                    String Destinatario1 = (radCorreoFuncionarioDestinatario);
//
                    // Guardamos la dirección
                    InternetAddress[] Correos = {
                        new InternetAddress(Destinatario1)

                    };
                    mensaje.setRecipients(Message.RecipientType.TO, Correos);

                    // asunto establecido
                    String asuntoPredeterminado = "Se le ha asignado el radicado con número: " + radNumeroRadicado;
                    mensaje.setSubject(asuntoPredeterminado);

                    // mensaje establecido
                    String mensajePredeterminado = "Hola "+radNombreDestinatario +",\n\n"+"Se te ha asignado un radicado para que le de respuestas según los tiempos establecidos";
                    
                    mensaje.setText(mensajePredeterminado);

                    // Enviamos el mensaje
                    Transport.send(mensaje);

                } catch (MessagingException e) {

                }
                
                
                  try {
                CrudRadicadoRecibidoDAO dao = new CrudRadicadoRecibidoDAO();

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(correo));

                String destino = dao.obtenerCorreo(contrasena);

                if (destino != null) {
                    InternetAddress[] correos = {
                        new InternetAddress(destino)
                    };
                    
                    

                    // Crear mensaje de correo
                   
                    String cuerpoMensaje = "Has asignado un nuevo radicado ";

                    message.setRecipients(Message.RecipientType.TO, correos);
                    message.setSubject("Inicio de Sesión Exitoso");
                    message.setText(cuerpoMensaje);

                    // Enviar el mensaje
                    Transport.send(message);

                    System.out.println("Correo electrónico enviado con éxito.");
                } else {
                    System.err.println("La dirección de correo electrónico de destino es nula. No se puede enviar el correo.");
                }

            } catch (MessagingException e) {
                System.err.println("Error al enviar el correo electrónico: " + e.getMessage());
                // Manejar la excepción adecuadamente, según tus necesidades.
            }



                acceso = listar; // Puedes ajustar esto según tus necesidades

                response.sendRedirect(acceso);

            }

            
        }

    }

}
