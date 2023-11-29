<%-- 
    Document   : login
    Created on : 26-feb-2020, 17:55:01
    Author     : Manue
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://fontawesome.com/icons/user?f=classic&s=solid">
        <link href="EstilosCSS/estilologin.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <title>GESDOC</title>
            <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
            <link rel="icon" href="img/favicon-16x16.png"/>
    </head>
   <body>
    <center>
        <div class="container" style="margin-top: 150px;">
            <form method="post" action="/admin_gesdoc/LoginController">
                <div class="card" style="width: 23rem; padding: 20px">
                    <div class="card-body" >
                        <!--<img src="img/LogoGD.jpg" alt="alt" style="width: 15rem;"/>-->
                        <img src="img/logo_gesdoc.png" alt="" style="width: 17rem;"/>
                        <div class="input-with-icon">
                            <i class="fas fa-user"></i>
                            <input type="text" name="txtusuario" class="form-control mt-4" placeholder="Usuario" required>
                        </div>
                        <div class="input-with-icon" >
                            <i class="fas fa-lock"></i>
                            <input type="password" name="txtclave" class="form-control mt-2" placeholder="Contraseña" required>
                        </div>

                        <input type="submit" class="btn btn-success mt-3" name="accion" value="INGRESAR">

                        <!-- Verifica si errorMensaje está definida y muestra el SweetAlert -->
                        <% String errorMensaje = (String) request.getAttribute("errorMensaje"); %>
                        <% if (errorMensaje != null) { %>
                            <script>
                                Swal.fire({
                                    title: 'Error',
                                    text: '<%= errorMensaje %>',
                                    icon: 'error'
                                });
                            </script>
                            
                        <% } %>
                    </div>
                </div>
            </form>
        </div>
    </center>
    <script src="https://kit.fontawesome.com/9d96efaa7a.js" crossorigin="anonymous"></script>
</body>
</html>
