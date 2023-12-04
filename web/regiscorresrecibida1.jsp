<%@page import="Modelo.radicadorecibido"%>
<%@page import="DAOS.CrudRadicadoRecibidoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.ConsultaEntidades"%>
<%@page import="DAOS.EntidadesDAO"%>
<%@page import="controlador.conexion"%>
<%@page import="Modelo.consultausuario"%>
<%@page import="java.sql.*"%>
<%@page import="DAOS.UsuarioDAO"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="Expires" content="0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>GESDOC</title>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
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

                                    <h1 class="text-center" style=" margin-top: -15px; padding: 20px">Registrar Correspondencia Recibida</h1>

                                    <div class="form-group" style="padding: 1px; font-weight: bold;">
                                        <button style="font-weight: bold;" id="generarBtn" class="btn btn-primary"><i class="fa fa-rotate-right"></i> Generar Radicado</button>

                                    </div>

                                    <div>
                                        <h1 class="titulos" style="font-size: 20px; padding: 20px" > Datos de la Persona o Entidad Remitente </h1>
                                    </div>

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
                                            <form class="jotform-form" action="recibidoservlet" method="post" enctype="multipart/form-data"  accept-charset="UTF-8">

                                            <div class="row" style="padding-bottom: 10px">
                                                <div class="col-md-6">
                                                    <!-- Datos del remitente -->
                                                    
                                                   <div class="form-group" style="">
                                                    <label for="numero_radicado">Numero de Radicado<span class="form-required">*</span></label>
                                                    <input type="text"  name="numero_radicado" class="form-control" id="numero_radicado" stylename="numero_radicado" required maxlength="100">

                                                </div>
                                                    

                                                    <div class="form-group">
                                                        <label for="nombre_entidad">Nombre Entidad o Persona<span class="form-required">*</span></label>
                                                        <input type="text" name="nombre_entidad" class="form-control" id="nombre_entidad" required maxlength="50">
                                                    </div>

                                                    <div class="form-group">
                                                        <label for="numero_origen">Radicado Origen<span class="form-required">*</span></label>
                                                        <input type="text" name="numero_origen" class="form-control" id="numero_origen" required maxlength="50">
                                                    </div>

                                                </div>

                                                <div class="col-md-6" >

                                                    <div class="form-group" >
                                                        <label for="fecha_recepcion">Fecha de Recepción<span class="form-required">*</span></label>
                                                        <input type="date" name="fecha_recepcion" class="form-control" id="fecha_recepcion" required >
                                                    </div>

                                                    <div class="form-group">
                                                        <label for="asunto">Asunto<span class="form-required">*</span></label>
                                                        <input type="text" name="asunto" class="form-control" id="asunto" required maxlength="60">
                                                    </div>

                                                    <div class="form-group">
                                                        <label for="fecha_creacion">Fecha del Documento<span class="form-required">*</span></label>
                                                        <input type="date" name="fecha_creacion" class="form-control" id="fecha_creacion" required >
                                                    </div>

                                                </div>

                                                <div class="col-md-6">
                                                    <!-- Datos del remitente -->
                                                    <div class="form-group">
                                                        <label for="anexos">Anexos<span class="form-required">*</span></label>
                                                        <input type="text" name="anexos" class="form-control" id="anexos" required >
                                                    </div>                                                               

                                                </div>                                                                                               


                                                <div class="col-md-6">

                                                    <div class="form-group">
                                                        <label for="ciudad">Ciudad de Origen<span class="form-required">*</span></label>
                                                        <input type="text" name="ciudad" class="form-control" id="ciudad" required maxlength="60">
                                                    </div>    

                                                    <div class="form-group" style="padding: 10px">
                                                        <label for="file">Archivo<span class="form-required">*</span></label>
                                                        <input type="file" class="form-control-file" id="file" name="file" required>
                                                    </div>

                                                </div>

                                                <div>
                                                    <h1 class="titulos" style="font-size: 20px;
                                                    padding: 20px"> Datos del Destinatario </h1>
                                                </div>

                                                <div class="col-md-6">


                                                    <div class="form-group">
                                                        <label for="Dependencia">Dependencia<span class="form-required">*</span></label>
                                                        <input type="text" name="Dependencia" class="form-control" id="Dependencia" required maxlength="45">
                                                    </div>                                                    

                                                </div>


                                                <div class="col-md-6">

                                                    <div class="form-group">
                                                        <label for="nombre_destinatario">Nombre Funcionario Responsable<span class="form-required">*</span></label>
                                                        <input type="text" name="nombre_destinatario" class="form-control" id="nombre_destinatario" required maxlength="50">
                                                    </div>                                                    

                                                </div>

                                                <div>
                                                    <h1 class="titulos" style="font-size: 20px;
                                                    padding: 20px"> Respuestas y Antecedentes </h1>
                                                </div>

                                                <div class="col-md-6">

                                                    <div class="form-group">
                                                        <label for="NumeroRadRespuesta" style="">Numero de radicado de respuesta<span class="form-required">*</span></label>
                                                        <input type="text" name="NumeroRadRespuesta" class="form-control" id="NumeroRadRespuesta"  required maxlength="70">
                                                    </div>                                                    

                                                </div>


                                                <div class="col-md-6">

                                                    <div class="form-group">
                                                        <label for="fecha_respuesta">Fecha del Documento<span class="form-required">*</span></label>
                                                        <input type="date" name="fecha_respuesta" class="form-control" id="fecha_respuesta" required >
                                                    </div>                                                 

                                                </div>


                                                <div class="text-center" style="padding: 10px">
                                                    <button type="submit" class="btn btn-success" name="accion" value="Registrar">Registrar</button>
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
<script src="js/alertas_recibido.js" type="text/javascript"></script>
<script src="js/alerta_numrecibido.js" type="text/javascript"></script>

<script>
    // Función para obtener el año actual
    function getCurrentYear() {
        return new Date().getFullYear();
    }

    // Función para obtener el último año registrado en el almacenamiento local
    function getLastYear() {
        return parseInt(localStorage.getItem("ultimoYear")) || getCurrentYear();
    }

    // Función para reiniciar el contador cuando cambia el año
    function resetCounterIfNeeded() {
        var currentYear = getCurrentYear();
        var lastYear = getLastYear();

        if (currentYear !== lastYear) {
            localStorage.setItem("ultimoYear", currentYear);
            localStorage.setItem("ultimoNumero", 1); // Reinicia el contador
        }
    }

    document.getElementById("generarBtn").addEventListener("click", function () {
        resetCounterIfNeeded();

        Swal.fire({
            title: '¿Desea generar un número de radicado?',
            icon: 'question',
            showCancelButton: true,
            confirmButtonText: 'Sí',
            cancelButtonText: 'No'
        }).then((result) => {
            if (result.isConfirmed) {
                var numero = parseInt(localStorage.getItem("ultimoNumero")) || 0;
                numero++;
                localStorage.setItem("ultimoNumero", numero);

                var numeroFormateado = numero.toString().padStart(4, "0"); // Formatea como "0001", "0002", ...
                Swal.fire('Número de radicado generado', 'Tu número de radicado es: 1-' + numeroFormateado, 'success');
            }
        });
    });

    // Llamamos a resetCounterIfNeeded al cargar la página para asegurarnos de que el contador esté configurado correctamente
    resetCounterIfNeeded();


</script>

<script>
    document.querySelector("form").addEventListener("submit", function (event) {
        const inputFields = document.querySelectorAll(".form-control");
        let camposVacios = false;

        inputFields.forEach(function (input) {
            if (input.value.trim() === "") {
                camposVacios = true;
                input.classList.add("is-invalid");
            } else {
                input.classList.remove("is-invalid");
            }
        });

        if (camposVacios) {
            event.preventDefault(); // Evita que se envíe el formulario si hay campos vacíos
            // Muestra una alerta SweetAlert2 de error
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Por favor, complete todos los campos.',
            });
        }
    });
</script>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="js/scripts.js"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
<script src="js/datatables-simple-demo.js"></script>
<script src="js/cierre_automatico.js"></script>
<script src="js/cerrarsesion.js"></script>

<%
} else {
    // Realiza alguna acción si 'nom' es nulo o una cadena vacía
%>
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
