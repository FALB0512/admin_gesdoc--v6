<%-- 
    Document   : add
    Created on : 21/09/2023, 7:13:38 a. m.
    Author     : farud
--%>

<%@page import="controlador.conexion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="DAOS.CrudRadicadoEnviadoDAO"%>
<%@page import="Modelo.radicadoenviado"%>
<%@page import="controlador.conexion"%>
<%@page import="Modelo.consultausuario"%>
<%@page import="java.sql.*"%>
<%@page import="DAOS.UsuarioDAO"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>GESDOC</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10/dist/sweetalert2.min.css">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10/dist/sweetalert2.all.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
        <link href="css/styles.css" rel="stylesheet" />
        <link href="consultausuario.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        
        
    </head>
    <body class="sb-nav-fixed">


         <%
            String nom = (String) session.getAttribute("nom");

           if (nom != null && !nom.isEmpty()) {
           
            conexion cn = new conexion();
            Connection con;
            PreparedStatement ps;
            ResultSet rs;
            consultausuario usuario = new consultausuario();


            if (nom != null && !nom.isEmpty()) {
                // Realizar la consulta SQL solo si 'nom' es un valor válido
                String sql = "SELECT usuPrimerNombre, usuPrimerApellido FROM tblusuarios WHERE usuNombreUsuario = ?";

                try {
                    con = cn.getConection();
                    ps = con.prepareStatement(sql);
                    ps.setString(1, nom); // Establece 'nom' como un parámetro en la consulta SQL
                    rs = ps.executeQuery();

                    if (rs.next()) {
                        // Asignar los valores a 'usuario' solo si se encontraron resultados
                        usuario.setUsuPrimerNombre(rs.getString("usuPrimerNombre"));
                        usuario.setUsuPrimerApellido(rs.getString("usuPrimerApellido"));

                        // Puedes imprimir los valores recuperados si es necesario
                        String primerNombre = usuario.getUsuPrimerNombre();
                        String primerApellido = usuario.getUsuPrimerApellido();
                        out.println("Primer Nombre: " + primerNombre);
                        out.println("Primer Apellido: " + primerApellido);
                    } else {
                        // Si no se encontraron resultados, puedes imprimir un mensaje de error o realizar alguna otra acción.
                        out.println("No se encontraron resultados para el nombre de usuario: " + nom);
                    }
                } catch (SQLException e) {
                    // Manejo de excepciones
                    e.printStackTrace();
                }
            } else {
                // Si 'nom' es null o una cadena vacía, puedes imprimir un mensaje de error o realizar alguna otra acción.
                out.println("El valor de 'nom' no es válido.");
            }
        %>




        <!--navbar-->
        <nav class="sb-topnav navbar navbar-expand " style="background-color:#ffff;">
            <!-- Contenido de tu barra de navegación -->


            <!--Navbar Brand-->
            <a class="navbar-brand ps-3" href="index.jsp" style="width: 2px;" ></a>  



            <div class="logo" style="left: 20%">
                <img src="img/logo_gesdoc.png" alt="" style="width: 200px; display: block; margin-left: auto; margin-right: auto;"/>
            </div>
            <!-- Sidebar Toggle-->
            <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" style="color: black" href="#!"><i class="fas fa-bars" style="height: 20px"></i></button>
            <!-- Navbar Search-->
            <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
                <div class="input-group" style="font-size: 18px; font-weight: bold; ">                 
                    <%= usuario.getUsuPrimerNombre()%> <%= usuario.getUsuPrimerApellido()%>              
                </div>
            </form>
            <!-- Navbar-->
            <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw" style="height: 30px"></i></a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">

                        <!--                        <li><hr class="dropdown-divider" /></li>-->
                        <li><a class="dropdown-item" href="#" onclick="cerrarSesion()" style="font-weight: bold;">Salir</a></li>

                        </li>
                    </ul>
                </li>
            </ul>
        </nav>
        <form class="jotform-form" action="UserServlets" method="get" enctype="multipart/form-data" name="form_232663844244661" id="232663844244661" accept-charset="utf-8" autocomplete="on"><input type="hidden" name="formID" value="232663844244661" /><input type="hidden" id="JWTContainer" value="" /><input type="hidden" id="cardinalOrderNumber" value="" />

        <style>

            .form-required{

                font-size: 20px;
                color: red;
            }

            .form-group{
                margin-bottom: 10px;
                padding: 10px;
                font-weight: bold;

            }

            .titulos {
                padding: 10px 0px;
                background-color: gray;
                border: 2px solid gray;
                border-radius: 10px;
            }

            /* Estilos para el botón */
            .btn-success {
                background-color: #0AAA0A; /* Fondo verde */
                color: white; /* Texto blanco */
                border: none; /* Sin borde */
                padding: 10px 20px; /* Espaciado interno */
                border-radius: 5px; /* Esquinas redondeadas */
                cursor: pointer; /* Cambia el cursor al pasar el ratón */
            }

            /* Estilo de hover para el botón */
            .btn-success:hover {
                background-color:

                </style>

                <div id="layoutSidenav">
                    <div id="layoutSidenav_nav">
                        <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">

                            <!--menulateral-->

                            <div class="sb-sidenav-menu">
                                <div class="nav">
                                    <div class="sb-sidenav-menu-heading">Gestion Documental</div>
                                    <a class="nav-link" href="indexpanel.jsp?page=dashboard">
                                        <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                        Panel de Control
                                    </a>

                                    <div class="collapse" id="collapsePages" aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">

                                    </div>
                                    <div class="sb-sidenav-menu-heading">Menu</div>


                                    <a class="nav-link" href="listarusuario_1.jsp">
                                        <div class="sb-nav-link-icon"><i class="fa fa-user"></i></div>
                                        Usuarios
                                    </a>

                                    <a class="nav-link" href="listarusuario_1.jsp">
                                        <div class="sb-nav-link-icon"><i class="fa fa-tags"></i></div>
                                        Tipo Documental
                                    </a>

                                    <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                                        <div class="sb-nav-link-icon"><i class="fa fa-magnifying-glass"></i></div>
                                        Documentos
                                        <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                                    </a>
                                    <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                        <nav class="sb-sidenav-menu-nested nav">
                                            <a class="nav-link" href="listar_enviados.jsp">Enviados</a>
                                            <a class="nav-link" href="listar_recibidos.jsp">Recibidos</a>
                                            <a class="nav-link" href="listar_otros.jsp">Otros</a>
                                        </nav>
                                    </div>    

                                    <div class="sb-sidenav-menu-heading">Radicaciones</div>

                                    <a class="nav-link" href="resgiscorresenviada.jsp">
                                        <div class="sb-nav-link-icon"><i class="fa-solid fa-folder-open"></i></div>
                                        Correspondencia Enviada
                                    </a>

                                    <a class="nav-link" href="regiscorresrecibida1.jsp">
                                        <div class="sb-nav-link-icon"><i class="fa-solid fa-inbox"></i></div>
                                        Correspondencia Recibidas
                                    </a>

                                    <a class="nav-link" href="regisotros.jsp">
                                        <div class="sb-nav-link-icon"><i class="fa fa-newspaper"></i></div>
                                        Otros Envios Recibidos
                                    </a>


                                </div>
                            </div>
                        </nav>
                    </div>
                    <div id="layoutSidenav_content">
                        <main>
                            <div class="container-fluid px-4">

                                <div class="container">
                                    <div class="table-container">
                                        <table class="table table-bordered">                                 
                                                <div class="container">
                                                    <h1 class="text-center" style=" margin-top: -15px;
                                                    padding: 10px">Registro de Usuario</h1>

                                                    <div class="row">
                                                        <div class="col-md-6">
                                                            <!-- Datos del remitente -->

                                                            <div class="form-group">
                                                                <label for="txtprimernombre">Primer Nombre:</label>
                                                                <input class="form-control" type="text" id="txtprimernombre" name="txtprimernombre" required maxlength="45">
                                                            </div>

                                                            <div class="form-group">
                                                                <label for="txtprimerapellido">Primer Apellido:</label>
                                                                <input class="form-control" type="text" id="txtprimerapellido" name="txtprimerapellido" required maxlength="45">
                                                            </div>

                                                            <div class="form-group">
                                                                <label for="txtcorreo">Correo Electrónico:</label>
                                                                <input class="form-control" type="text" id="txtcorreo" name="txtcorreo" required maxlength="150">
                                                            </div>

                                                        </div>

                                                        <div class="col-md-6">

                                                            <div class="form-group">
                                                                <label for="txtsegundonombre">Segundo Nombre:</label>
                                                                <input class="form-control" type="text" id="txtsegundonombre" name="txtsegundonombre" required maxlength="45">
                                                            </div>

                                                            <div class="form-group">
                                                                <label for="txtsegundopellido">Segundo Apellido:</label>
                                                                <input class="form-control" type="text" id="txtsegundopellido" name="txtsegundopellido" required maxlength="45">
                                                            </div>

                                                            <div class="form-group">
                                                                <label for="txtfecharegistro">Fecha de Registro:</label>
                                                                <input class="form-control" type="date" id="txtfecharegistro" name="txtfecharegistro" required>
                                                            </div>


                                                        </div>

                                                        <div class="col-md-6">
                                                            <!-- Datos del remitente -->
                                                            <div class="form-group">
                                                                <label for="txtnombreusuario">Nombre Usuario:</label>
                                                                <input class="form-control" type="text" id="txtnombreusuario" name="txtnombreusuario" required maxlength="45">
                                                            </div>

                                                            <div class="form-group">
                                                                <label for="txtcontrasena">Contraseña:</label>
                                                                <input class="form-control" type="password" id="txtcontrasena" name="txtcontrasena" required maxlength="45">
                                                            </div>




                                                        </div>

                                                        <div class="col-md-6">
                                                            <!-- Datos del remitente -->


                                                            <div class="form-group">
                                                                <label for="txttipoUsuario">Nivel de Acceso:</label>
                                                                <input class="form-control" type="text" id="txttipoUsuario" name="txttipoUsuario" required maxlength="45">
                                                            </div>

                                                        </div>

                                                        <div class="col-md-6">


                                                        </div>
                                                    </div>

                                                    <!-- Otros campos -->
                                                    <div class="form-group">
                                                        <label for="txtobservaciones">Observaciones:</label>
                                                        <input class="form-control" type="text" id="txtobservaciones" name="txtobservaciones" maxlength="200">
                                                    </div>   

                                                    <input type="hidden" name="txtusuId">

                                                    <div class="text-center">
                                                        <button type="submit" class="btn btn-success" name="accion" value="Agregar">Agregar</button>
                                                    </div>

                                                </div>
                                          
                                        </table>

                                    </div>

                                </div>
                            </div>
                    </div>
                </div>
            </main>



        </div>
    </div>
<script src="js/alertas_usuario.js" type="text/javascript"></script>
<script>
    function cerrarSesion() {
        // Realizar una solicitud al controlador para cerrar la sesión
        var xhr = new XMLHttpRequest();
        xhr.open('GET', 'LoginController', true);

        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                // La solicitud fue exitosa, redirigir o realizar otras acciones según sea necesario
                window.location.href = 'index.jsp'; // Redirigir a la página de inicio, por ejemplo
            }
        };

        xhr.send();
    }
</script>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    <script src="js/scripts.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
    <script src="js/datatables-simple-demo.js"></script>
</body>
</html>
<%
} else {
    // Realiza alguna acción si 'nom' es nulo o una cadena vacía
%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Redirección </title>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
    </head>
    <body>
        <script>
    // Agrega una alerta de "Iniciar Sesión"
    Swal.fire({
        title: 'Iniciar Sesión',
        text: 'Necesitas iniciar sesión para acceder a esta página.',
        icon: 'info',
        confirmButtonText: 'Iniciar sesíon'
    }).then(() => {
        // Redirige a "index.jsp" después de hacer clic en "OK"
        window.location.href = 'index.jsp';
    });
        </script>
    </body>
</html>
<%
    }
%>