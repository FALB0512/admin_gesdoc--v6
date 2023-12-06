<%-- 
    Document   : error
    Created on : 20/10/2023, 8:51:22 a. m.
    Author     : farud
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="Expires" content="0">
    <title>Error</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.0/css/all.min.css" rel="stylesheet" integrity="sha384-1a50d3c6d98be3a7a20a8597a2144b27b32e356b9361291843d62e8683c954cdd" crossorigin="anonymous">
<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <style>
        .error-container {
            text-align: center;
            margin-top: 50px;
        }
        .error-icon {
            font-size: 100px;
            color: #FF0000; /* Color rojo */
        }
        .error-message {
            font-size: 24px;
        }
    </style>
</head>
<body>
    <div class="container error-container">
        <i class="fas fa-exclamation-triangle error-icon"></i>
        <h1 class="error-message">Error: Está intentando subir un archivo que no está en formato PDF.</h1>
        <div>
            <button class="btn btn-primary" onclick="window.location.href='listar_enviados.jsp'">Regresar</button>
        </div>
    </div>
</body>
</html>
<script src="js/cierre_automatico.js"></script>
<script src="js/cerrarsesion.js"></script>
