<%@page import="Modelo.radicadoenviado"%>
<%@page import="DAOS.CrudRadicadoEnviadoDAO"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.ConsultaEntidades"%>
<%@page import="DAOS.EntidadesDAO"%>
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
                    // Realizar la consulta SQL solo si 'nom' es un valor v�lido
                    String sql = "SELECT usuPrimerNombre, usuPrimerApellido FROM tblusuarios WHERE usuNombreUsuario = ?";

                    try {
                        con = cn.getConection();
                        ps = con.prepareStatement(sql);
                        ps.setString(1, nom); // Establece 'nom' como un par�metro en la consulta SQL
                        rs = ps.executeQuery();

                        if (rs.next()) {
                            // Asignar los valores a 'usuario' solo si se encontraron resultados
                            usuario.setUsuPrimerNombre(rs.getString("usuPrimerNombre"));
                            usuario.setUsuPrimerApellido(rs.getString("usuPrimerApellido"));

                            // Puedes imprimir los valores recuperados si es necesario
                            String primerNombre = usuario.getUsuPrimerNombre();
                            String primerApellido = usuario.getUsuPrimerApellido();

                        } else {
                            // Si no se encontraron resultados, puedes imprimir un mensaje de error o realizar alguna otra acci�n.
                            out.println("No se encontraron resultados para el nombre de usuario: " + nom);
                        }
                    } catch (SQLException e) {
                        // Manejo de excepciones
                        e.printStackTrace();
                    }
                } else {
                    // Si 'nom' es null o una cadena vac�a, puedes imprimir un mensaje de error o realizar alguna otra acci�n.
                    out.println("El valor de 'nom' no es v�lido.");
                }
        %>

        <nav class="sb-topnav navbar navbar-expand " style="background-color:#ffff; width: 215vh;">
            <!-- Contenido de tu barra de navegaci�n -->


            <!--Navbar Brand-->
            <a class="navbar-brand ps-3" href="index.jsp" style="width: 2px;" ></a>  

            <div class="logo" style="padding: -10vh;">
                <img src="img/logo_gesdoc.png" alt="" style="width: 200px; display: block; margin-right: auto;"/>
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
        

        <form class="jotform-form" action="EditarEnviadoServlet" method="get" enctype="multipart/form-data"/>

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
                                <h1 class="text-center" style=" margin-top: -15px; padding: 20px">Editar Correspondencia Enviada </h1>

                                <div>
                                    <h1 class="titulos" style="font-size: 20px; padding: 20px" > Datos del Remitente </h1>
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

                                    /* Estilos para el bot�n */
                                    .btn-success {
                                        background-color: #0AAA0A; /* Fondo verde */
                                        color: white; /* Texto blanco */
                                        border: none; /* Sin borde */
                                        padding: 10px 20px; /* Espaciado interno */
                                        border-radius: 5px; /* Esquinas redondeadas */
                                        cursor: pointer; /* Cambia el cursor al pasar el rat�n */
                                    }

                                    /* Estilo de hover para el bot�n */
                                    .btn-success:hover {
                                        background-color:


                                        </style>

                                        <div class="row" style="padding-bottom: 10px">
                                            <div class="col-md-6">
                                                <!-- Datos del remitente -->
                                                <%
                                                    CrudRadicadoEnviadoDAO dao = new CrudRadicadoEnviadoDAO();
                                                    int id = Integer.parseInt((String) request.getAttribute("envId"));
                                                    radicadoenviado p = (radicadoenviado) dao.list(id);
                                                %>
                                                <div class="form-group">
                                                    <label for="envId">Id<span class="form-required">*</span></label>
                                                    <input type="text" name="envId" class="form-control" id="envId" value="<%= p.getEnvId()%>" required readonly>
                                                </div>

                                                <div class="form-group" style="">
                                                    <label for="numero_radicado">Numero de Radicado<span class="form-required">*</span></label>
                                                    <input type="text"  name="numero_radicado" class="form-control" id="numeroRadicado" stylename="numeroRadicado" value="<%= p.getEnvNumeroRadicado()%>" required >

                                                </div>
                                                <div class="form-group">
                                                    <label for="Dependencia">Dependencia<span class="form-required">*</span></label>
                                                    <input type="text" name="Dependencia" class="form-control" id="dependencia" value="<%= p.getEnvDependencia()%>" required>
                                                </div>

                                                <div class="form-group">
                                                    <label for="Asunto">Asunto<span class="form-required">*</span></label>
                                                    <input type="text" name="Asunto" class="form-control" id="Asunto" value="<%= p.getEnvAsunto()%>" required>
                                                </div>

                                            </div>

                                            <div class="col-md-6" >

                                                <div class="form-group" >
                                                    <label for="fecha_recepcion">Fecha de Recepci�n<span class="form-required">*</span></label>
                                                    <input type="date" name="fecha_recepcion" class="form-control" id="fecha_recepcion" value="<%= p.getEnvFechaRadicacion()%>" required>
                                                </div>

                                                <div class="form-group">
                                                    <label for="NombreFuncionario">Nombre del Funcionario<span class="form-required">*</span></label>
                                                    <input type="text" name="NombreFuncionario" class="form-control" id="NombreFuncionario" value="<%= p.getEnvNombreFuncionario()%>" required>
                                                </div>

                                                <div class="form-group">
                                                    <label for="anexos">Anexos<span class="form-required">*</span></label>
                                                    <input type="text" name="anexos" class="form-control" id="anexos" value="<%= p.getEnvAnexos()%>" required>
                                                </div>

                                                <div class="form-group">
                                                    <label for="TipoDocumental">Tipo Documental<span class="form-required">*</span></label>
                                                    <input type="text" name="TipoDocumental" class="form-control" id="TipoDocumental" value="<%= p.getEnvTipoDocumental()%>" required>
                                                </div>  

                                            </div>

                                            <div class="col-md-6">
                                                <!-- Datos del remitente -->

                                                <div class="form-group">
                                                    <label for="Antecedentes">Antecedentes<span class="form-required">*</span></label>
                                                    <input type="text" name="Antecedentes" class="form-control" id="Antecedentes" value="<%= p.getEnvAntecedentes()%>" required>
                                                </div>  

                                            </div>                                                                                               

                                            <div class="col-md-6">


                                                <!--  <div class="form-group" style="padding: 10px">
                                                      <label for="archivo">Archivo<span class="form-required">*</span></label>
                                                      <input type="file" class="form-control-file" id="archivo" name="archivo" required>
                                                      </div>-->

                                            </div>

                                            <div>
                                                <h1 class="titulos" style="font-size: 20px;
                                                padding: 20px"> Datos del Destinatario </h1>
                                            </div>

                                            <div class="col-md-6">


                                                <div class="form-group">
                                                    <label for="Entidad">Entidad<span class="form-required">*</span></label>
                                                    <input type="text" name="Entidad" class="form-control" id="Entidad" value="<%= p.getEnvEntidadDestino()%>" required>
                                                </div>               

                                                <div class="form-group">
                                                    <label for="ciudad">Ciudad<span class="form-required">*</span></label>
                                                    <input type="text" name="ciudad" class="form-control" id="ciudad" value="<%= p.getEnvCiudad()%>" required>
                                                </div>   

                                            </div>


                                            <div class="col-md-6">

                                                <div class="form-group">
                                                    <label for="nombre_destinatario">Nombre del Destinatario<span class="form-required">*</span></label>
                                                    <input type="text" name="nombre_destinatario" class="form-control" id="nombre_destinatario" value="<%= p.getEnvNombreDestinatario()%>" required>
                                                </div>      

                                                <div class="form-group">
                                                    <label for="Observaciones">Observaciones<span class="form-required">*</span></label>
                                                    <input type="text" name="Observaciones" class="form-control" id="Observaciones" value="<%= p.getEnvObservaciones()%>"required>
                                                </div>   

                                            </div>

                                            <div class="col-md-6">

                                            </div>
                                        </div>

                                        <div class="text-center" style="padding: 10px;">
                                        <button type="submit" class="btn btn-success" name="accion" value="Actualizar">Actualizar</button>

                                    </div>

                                </div>
                            </div>
                    </div>
                </div>
            </main>

        </div>
    </div>
    
    <script src="js/RegistrarEnviados.js" type="text/javascript"></script>
    <script src="js/alertas_enviado.js" type="text/javascript"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

    <script>
        // Funci�n para obtener el a�o actual
        function getCurrentYear() {
            return new Date().getFullYear();
        }

        // Funci�n para obtener el �ltimo a�o registrado en el almacenamiento local
        function getLastYear() {
            return parseInt(localStorage.getItem("ultimoYear")) || getCurrentYear();
        }

        // Funci�n para obtener el �ltimo n�mero generado en el a�o actual
        function getLastNumberForCurrentYear() {
            var currentYear = getCurrentYear();
            return parseInt(localStorage.getItem("ultimoNumero" + currentYear)) || 0;
        }

        // Funci�n para reiniciar el contador cuando cambia el a�o
        function resetCounterIfNeeded() {
            var currentYear = getCurrentYear();
            var lastYear = getLastYear();

            if (currentYear !== lastYear) {
                localStorage.setItem("ultimoYear", currentYear);
                localStorage.removeItem("ultimoNumero" + currentYear); // Reinicia el contador para el nuevo a�o
            }
        }

        document.getElementById("generarBtn").addEventListener("click", function () {
            resetCounterIfNeeded();

            var confirmacion = confirm("�Desea generar un n�mero de radicado?");

            if (confirmacion) {
                var numero = getLastNumberForCurrentYear() + 1;
                var currentYear = getCurrentYear();
                localStorage.setItem("ultimoNumero" + currentYear, numero);

                var numeroFormateado = numero.toString().padStart(4, "0"); // Formatea como "0001", "0002", ...
                alert("Tu n�mero de radicado es: 2-" + numeroFormateado);
            }
        });

        // Llamamos a resetCounterIfNeeded al cargar la p�gina para asegurarnos de que el contador est� configurado correctamente
        resetCounterIfNeeded();

    </script>
<script>
    function cerrarSesion() {
        // Realizar una solicitud al controlador para cerrar la sesi�n
        var xhr = new XMLHttpRequest();
        xhr.open('GET', 'LoginController', true);

        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                // La solicitud fue exitosa, redirigir o realizar otras acciones seg�n sea necesario
                window.location.href = 'index.jsp'; // Redirigir a la p�gina de inicio, por ejemplo
            }
        };

        xhr.send();
    }
</script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    <script src="js/scripts.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
    <script src="js/datatables-simple-demo.js"></script>

<%
} else {
    // Realiza alguna acci�n si 'nom' es nulo o una cadena vac�a
%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Redirecci�n </title>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
    </head>
    <body>
        <script>
    // Agrega una alerta de "Iniciar Sesi�n"
    Swal.fire({
        title: 'Iniciar Sesi�n',
        text: 'Necesitas iniciar sesi�n para acceder a esta p�gina.',
        icon: 'info',
        confirmButtonText: 'Iniciar ses�on'
    }).then(() => {
        // Redirige a "index.jsp" despu�s de hacer clic en "OK"
        window.location.href = 'index.jsp';
    });
        </script>
    </body>
</html>
<%
    }
%>