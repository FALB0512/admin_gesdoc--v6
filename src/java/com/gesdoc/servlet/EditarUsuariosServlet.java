/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.gesdoc.servlet;

import DAOS.CrudSeguimientoUsuariosDAO;
import DAOS.UsuarioDAO;
import Modelo.ConsultarSeguimientoUsuarios;
import Modelo.consultausuario;
import com.mysql.cj.xdevapi.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;
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
public class EditarUsuariosServlet extends HttpServlet {
    
    String listar = "listarusuario_1.jsp";
    String edit = "vistas/edit.jsp";
    String actualizar = "vistas/usuarios.jsp";
    consultausuario cus = new consultausuario();
    UsuarioDAO usd = new UsuarioDAO();
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
    String receivedToken = request.getParameter("token");
    String storedToken = (String) request.getSession().getAttribute("token");

    if (receivedToken != null && receivedToken.equals(storedToken)) {
        try {
            // Actualización del usuario aquí
            String usuId = request.getParameter("txtusuId");
            String usuPrimerNombre = request.getParameter("txtprimernombre");
            // ... (otros campos)

            cus.setUsuId(Integer.parseInt(usuId));
            cus.setUsuPrimerNombre(usuPrimerNombre);
            // ... (otros campos)

            // Realizar la actualización y verificar si fue exitosa
            boolean actualizacionExitosa = usd.edit(cus);

            if (actualizacionExitosa) {
                // El usuario se actualizó correctamente

                // Obtener detalles para el seguimiento
                LocalDate fechaActual = LocalDate.now();
                DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String accFechaIngreso1 = fechaActual.format(formatoFecha);
                String accHoraIngreso2 = new SimpleDateFormat("HH:mm:ss").format(new Date());
                accIP3 = InetAddress.getLocalHost().getHostAddress();
                HttpSession session = request.getSession();
                String nom = (String) session.getAttribute("nom");

                // Configurar el objeto de seguimiento
                seguimiento.setAccFechaIngreso(accFechaIngreso1);
                seguimiento.setAccHoraIngreso(accHoraIngreso2);
                seguimiento.setAccIP(accIP3);
                seguimiento.setAccAcciones("El usuario realizó una actualización");
                seguimiento.setAccUsuario(nom);
                seguimiento.setAccNumeroRadicado("no");

                // Agregar seguimiento a la base de datos
                daoseguimiento.agregar(seguimiento);

                // Actualizar el token en la sesión para evitar duplicaciones
                request.getSession().setAttribute("token", UUID.randomUUID().toString());

                acceso = listar;
            } else {
                // La actualización no fue exitosa, manejar según tus necesidades
                // Puedes redirigir a una página de error o mostrar un mensaje al usuario
                acceso = listar;
            }
        } catch (Exception e) {
            // Manejar la excepción, por ejemplo, registrar en el registro de errores
            e.printStackTrace();
            acceso = listar; // Puedes ajustar esto según tus necesidades
        }
    } else {
        // El token no coincide, manejar según tus necesidades
        // Puedes redirigir a una página de error o mostrar un mensaje al usuario
        acceso = listar;
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
