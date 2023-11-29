package com.gesdoc.servlet;

import DAOS.UsuarioDAO;
import Modelo.consultausuario;
import DAOS.CrudSeguimientoUsuariosDAO;
import Modelo.ConsultarSeguimientoUsuarios;
import java.io.IOException;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Manue
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    consultausuario cus = new consultausuario();
    UsuarioDAO usd = new UsuarioDAO();
    int r;
    
    ConsultarSeguimientoUsuarios seguimiento = new ConsultarSeguimientoUsuarios();
    CrudSeguimientoUsuariosDAO daoseguimiento = new CrudSeguimientoUsuariosDAO();
    String accFechaIngreso1;
    String accHoraIngreso2;
    String accIP3;


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");
        if (accion.equals("INGRESAR")) {
            
            
            LocalDate fechaActual = LocalDate.now();
            DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String accFechaIngreso1 = fechaActual.format(formatoFecha);

// Obtener la hora de ingreso (en este ejemplo, se usa la hora actual del sistema)
            String accHoraIngreso2 = new SimpleDateFormat("HH:mm:ss").format(new Date());

// Obtener la dirección IP del cliente
            accIP3 = InetAddress.getLocalHost().getHostAddress();
            
            
            String nom = request.getParameter("txtusuario");
            String contra = request.getParameter("txtclave");
            cus.setUsuNombreUsuario(nom);
            cus.setUsuContrasena(contra);
            r = usd.validar(cus);
            if (r == 1) {
                HttpSession session = request.getSession();
                session.setAttribute("nom", nom);
                
                String accFechaIngreso = request.getParameter("accFechaIngreso");
                String accHoraIngreso = request.getParameter("accHoraIngreso");
                String accIP = request.getParameter("accIP");
                String accAcciones = request.getParameter("El usuario ingreso al sistema");
                String accUsuario = request.getParameter("");
                String accNumeroRadicado = request.getParameter("");

                seguimiento.setAccFechaIngreso(accFechaIngreso1);
                seguimiento.setAccHoraIngreso(accHoraIngreso2);
                seguimiento.setAccIP(accIP3);
                seguimiento.setAccAcciones("el usuario ingreso al sistema");
                seguimiento.setAccUsuario(nom);
                seguimiento.setAccNumeroRadicado("No");

                daoseguimiento.agregar(seguimiento);
                request.getRequestDispatcher("indexpanel.jsp").forward(request, response);
            } else {
                // Credenciales incorrectas
                request.setAttribute("errorMensaje", "Sus credenciales son incorrectas.");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
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
    HttpSession session = request.getSession(false);

    if (session != null) {
        session.invalidate();
    }

    // Puedes redirigir a la página de inicio u otra página después de cerrar la sesión
    response.sendRedirect("index.jsp");
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
