<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.otrosradicados"%>
<%@page import="DAOS.OtrosEnviosDAO"%>
<%@page import="java.sql.*"%>
<%@page import="controlador.conexion"%>
<%@page import="Modelo.consultausuario"%>
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
        
        
        
         <form class="jotform-form" action="EditarOtrosServlet" method="get" enctype="multipart/form-data"/>
         
      
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
                                <h1 class="text-center" style=" margin-top: -15px; padding: 20px">Editar Otros Envio Recibidos </h1>

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

                                        <div class="row" style="padding-bottom: 10px">
                                            <div class="col-md-6">
                                                <!-- Datos del remitente -->
                                                <%
                                                    OtrosEnviosDAO dao = new OtrosEnviosDAO();
                                                    int id = Integer.parseInt((String) request.getAttribute("otrId"));
                                                    otrosradicados p = (otrosradicados) dao.list(id);
                                                %>
                                                <div class="form-group">
                                                    <label for="otrId">Id<span class="form-required">*</span></label>
                                                    <input type="text" name="otrId" class="form-control" id="otrId" value="<%= p.getOtrId()%>" required readonly>
                                                </div>  

                                                <div class="form-group" style="">
                                                    <label for="numeroRadicado">Numero de Radicado<span class="form-required">*</span></label>
                                                    <input type="text"  name="numero_radicado" class="form-control" id="numeroRadicado" stylename="numeroRadicado"  value="<%= p.getOtrNumeroRadicado()%>" required readonly>

                                                </div>
                                                <div class="form-group">
                                                    <label for="nombre_entidad">Nombre Entidad O Remitente<span class="form-required">*</span></label>
                                                    <input type="text" name="nombre_entidad" class="form-control" id="nombre_entidad" value="<%= p.getOtrNombreRemitente()%>"required>
                                                </div>

                                                <div class="form-group">
                                                    <label for="ciudad">Ciudad de Origen<span class="form-required">*</span></label>
                                                    <input type="text" name="ciudad" class="form-control" id="ciudad"  value="<%= p.getOtrCiudadOrigen()%>" required>
                                                </div>

                                            </div>

                                            <div class="col-md-6" >

                                                <div class="form-group" >
                                                    <label for="fecha_recepcion">Fecha de Recepción<span class="form-required">*</span></label>
                                                    <input type="date" name="fecha_recepcion" class="form-control" id="fecha_recepcion" value="<%= p.getOtrFechaRecibido()%>" required>
                                                </div>



                                                <div class="form-group">
                                                    <label for="tipo_documental">Tipo Documental<span class="form-required">*</span></label>
                                                    <input type="text" name="tipo_documental" class="form-control" id="tipo_documental" value="<%= p.getOtrTipoDocumental()%>" required>
                                                </div>

                                                <div class="form-group">
                                                    <label for="anexos">Anexos<span class="form-required">*</span></label>
                                                    <input type="text" name="anexos" class="form-control" id="anexos" value="<%= p.getOtrAnexos()%>" required>
                                                </div>

                                            </div>


                                            <div class="col-md-6">
                                            </div>

                                            <div>
                                                <h1 class="titulos" style="font-size: 20px;
                                                padding: 20px"> Datos del Destinatario </h1>
                                            </div>

                                            <div class="col-md-6">


                                                <div class="form-group">
                                                    <label for="dependencia">Dependencia<span class="form-required">*</span></label>
                                                    <input type="text" name="Dependencia" class="form-control" id="dependencia" value="<%= p.getOtrDependencias()%>" required>
                                                </div>               

                                            </div>


                                            <div class="col-md-6">

                                                <div class="form-group">
                                                    <label for="nombre_funcionario">Nombre del Funcionario<span class="form-required">*</span></label>
                                                    <input type="text" name="nombre_funcionario" class="form-control" id="nombre_funcionario" value="<%= p.getOtrNombreFuncionario_destinatario()%>" required>
                                                </div>      


                                            </div>

                                            <div class="col-md-6">

                                            </div>
                                        </div>

                                        <div class="text-center" style="padding: 10px;">
                                        <button type="submit" class="btn btn-success" name="accion" value="Actualizar" >Actualizar</button>
                                       


                                    </div>

                                </div>
                            </div>
                    </div>
                </div>
            </main>

        </div>
    </div>
<script src="js/alertas_editarotros.js" type="text/javascript"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>

    <script>
        // Función para obtener el año actual
        function getCurrentYear() {
            return new Date().getFullYear();
        }

        // Función para obtener el último año registrado en el almacenamiento local
        function getLastYear() {
            return parseInt(localStorage.getItem("ultimoYear3")) || getCurrentYear();
        }

        // Función para obtener el último número generado en el año actual
        function getLastNumberForCurrentYear() {
            var currentYear = getCurrentYear();
            return parseInt(localStorage.getItem("ultimoNumero3" + currentYear)) || 0;
        }

        // Función para reiniciar el contador cuando cambia el año
        function resetCounterIfNeeded() {
            var currentYear = getCurrentYear();
            var lastYear = getLastYear();

            if (currentYear !== lastYear) {
                localStorage.setItem("ultimoYear3", currentYear);
                localStorage.removeItem("ultimoNumero3" + currentYear); // Reinicia el contador para el nuevo año
            }
        }

        document.getElementById("generarBtn").addEventListener("click", function () {
            resetCounterIfNeeded();

            var confirmacion = confirm("¿Desea generar un número de radicado?");

            if (confirmacion) {
                var numero = getLastNumberForCurrentYear() + 1;
                var currentYear = getCurrentYear();
                localStorage.setItem("ultimoNumero3" + currentYear, numero);

                var numeroFormateado = numero.toString().padStart(4, "0"); // Formatea como "0001", "0002", ...
                alert("Tu número de radicado es: 3-" + numeroFormateado);
            }
        });

        // Llamamos a resetCounterIfNeeded al cargar la página para asegurarnos de que el contador esté configurado correctamente
        resetCounterIfNeeded();


    </script>
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