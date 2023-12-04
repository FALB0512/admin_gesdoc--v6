<%-- 
    Document   : visor
    Created on : 17/10/2023, 9:50:08 a. m.
    Author     : farud
--%>

<%@page import="java.net.URLDecoder"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Expires" content="0">
    <title>Visualizar PDF</title>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.3.0/dist/sweetalert2.all.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
</head>
<body>
    <%
        String pdfURL = request.getParameter("pdf");
        pdfURL = URLDecoder.decode(pdfURL, "UTF-8");
    %>

    <!-- Utiliza la variable "pdfURL" para mostrar el archivo PDF -->
    <div>
        <object data="<%= pdfURL %>" type="application/pdf" width="100%" height="800">
            <!-- Puedes agregar un mensaje alternativo si el navegador no puede mostrar el PDF -->
            <p>No se puede mostrar el PDF. <a href="<%= pdfURL %>">Descargar PDF</a></p>
        </object>
    </div>
    
</body>
</html>
<script src="js/cierre_automatico.js"></script>
<script src="js/cerrarsesion.js"></script>


