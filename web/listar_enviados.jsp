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
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>GESDOC</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
        <link href="css/styles.css" rel="stylesheet" />
        <link href="consultausuario.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="shortcut icon" href="#" />  
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
        <!-- CSS personalizado --> 
        <link rel="stylesheet" href="main.css">  
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
        <!--datables CSS básico-->
        <link rel="stylesheet" type="text/css" href="datatables/datatables.min.css"/>
        <!--datables estilo bootstrap 4 CSS-->  
        <link rel="stylesheet"  type="text/css" href="datatables/DataTables-1.10.18/css/dataTables.bootstrap4.min.css">
        <link href="css/estilo_modal_detalles_enviado.css" rel="stylesheet" type="text/css"/>
        <!--font awesome con CDN-->  
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay" crossorigin="anonymous">  
    
       
    </head>
    
    <!--estilo del modal-->

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


        <!--navbar-->
        <nav class="sb-topnav navbar navbar-expand " style="background-color:#ffff;">
            <!-- Contenido de tu barra de navegación -->


            <!--Navbar Brand-->
            <a class="navbar-brand ps-3" href="index.jsp" style="width: 2px;" ></a>  



            <div class="logo" style="left: 20%">
                <img src="img/logo_gesdoc.png" alt="" style="width: 200px; display: block; margin-left: -20px; margin-right: auto;"/>
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
                                Otras Dependencias
                            </a>               

                        
                        </div>
                    </div>
                </nav>
            </div>


            <div id="layoutSidenav_content">              

                <!--Ejemplo tabla con DataTables-->
                <div>
                    <h1 style="color: black; text-align: center">Correspondencia Enviada </h1>
                </div>

                <div style="height: 100%; padding: 10px"class="row">
                    <div class="col-lg-12">
                        <div class="table-responsive">        
                            <table style="text-align: center;"id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th>Id</th>
                                        <th >Num Radicado</th>
                                        <th>Dependencia</th>
                                        <th>Asunto</th>
                                        <!--                                        <th>Antecedentes</th>
                                                                                <th>Tipo documental</th>
                                                                                <th>Observaciones</th> -->
                                        <th>Archivo</th> 
                                        <th>Acciones</th>
                                    </tr>
                                </thead>
                                <tbody>

                                    <%
                                        CrudRadicadoEnviadoDAO daorecibido = new CrudRadicadoEnviadoDAO();
                                        List<radicadoenviado> list = daorecibido.listar();
                                        Iterator<radicadoenviado> iter = list.iterator();
                                        radicadoenviado act = null;
                                        while (iter.hasNext()) {
                                            act = iter.next();
                                    %>

                                    <tr>

                                        <td><%= act.getEnvId()%></td>
                                        <td><%= act.getEnvNumeroRadicado()%></td>
                                        <td><%= act.getEnvDependencia()%></td>
                                        <td><%= act.getEnvAsunto()%></td>
<!--                                        <td><%= act.getEnvAntecedentes()%></td>
                                        <td><%= act.getEnvTipoDocumental()%></td>
                                        <td><%= act.getEnvObservaciones()%></td>-->
                                        <td>
                                            <a class="btn btn-primary" href="visor.jsp?pdf=<%= act.getEnvArchivoPdf()%>" target="_blank">
                                                <i class="fas fa-file-pdf" style="text-align: center"></i>
                                            </a>
                                        </td>

                                        <td>
                                            <a class="btn btn-warning" href="EditarEnviadoServlet?accion=editar&id=<%= act.getEnvId()%>">
                                                <i class="fas fa-edit"></i>
                                            </a>

                                            <a class="btn btn-danger" href="javascript:void(0);" onclick="confirmarEliminacion(<%= act.getEnvId()%>)">
                                                <i class="fas fa-trash-alt"></i>
                                            </a>

                                            <button class="btn btn-info" onclick="mostrarModal(
                                                            '<%= act.getEnvId()%>',
                                                            '<%= act.getEnvNumeroRadicado()%>',
                                                            '<%= act.getEnvFechaRadicacion()%>',
                                                            '<%= act.getEnvDependencia()%>',
                                                            '<%= act.getEnvNombreFuncionario()%>',
                                                            '<%= act.getEnvAsunto()%>',
                                                            '<%= act.getEnvAnexos()%>',
                                                            '<%= act.getEnvAntecedentes()%>',
                                                            '<%= act.getEnvEntidadDestino()%>',
                                                            '<%= act.getEnvNombreDestinatario()%>',
                                                            '<%= act.getEnvCiudad()%>',
                                                            '<%= act.getEnvTipoDocumental()%>',
                                                            '<%= act.getEnvObservaciones()%>'
                                                            )">
                                                <i class="fas fa-info-circle"></i>
                                            </button>
                                        </td>

                                    </tr>    

                                    <%
                                        }
                                    %>

                                </tbody>        
                            </table>                  
                        </div>
                    </div>
                </div>  

                <!--este es el modal donde se muestran los detalles del documento registrado-->
                <div class="modal" id="myModal">
                    <div class="modal-dialog">
                        <div class="modal-content modal-content-gris">

                            <!-- Encabezado del modal -->
                            <div class="modal-header">
                                <h4 class="modal-title">Detalles de Radicación</h4>
                            </div>

                            <!-- Contenido del modal -->
                            <div class="modal-body">
                                <p id="detallesDocumento"></p>
                            </div>

                            

                            <!-- Botón de cierre en el pie del modal -->
                            <div class="modal-footer">
                                <button type="button" class="btn btn-cerrar" onclick="cerrarModal()">Cerrar</button>
                            </div>

                        </div>
                    </div>
                </div>


                <!--este es el script del modal para mostrar los detalles del documento registrado-->
                <script>
    function mostrarModal(
        id, numeroRadicado, fechaRadicacion, dependencia, nombreFuncionario,
        asunto, anexos, antecedentes, entidadDestino, nomredestinatario, ciudad, tipoDocumental, observaciones
    ) {
        var modal = document.getElementById("myModal");
        var detallesDocumento = document.getElementById("detallesDocumento");

        // Construye el contenido del modal con los detalles del documento
        var contenidoModal = "<strong>ID</strong>: " + id + "<br>" +
            "<strong>Número de Radicado</strong>: " + numeroRadicado + "<br>" +
            "<strong>Fecha de Radicación</strong>: " + fechaRadicacion + "<br>" +
            "<strong>Dependencia</strong>: " + dependencia + "<br>" +
            "<strong>Nombre del Funcionario</strong>: " + nombreFuncionario + "<br>" +
            "<strong>Asunto</strong>: " + asunto + "<br>" +
            "<strong>Anexos</strong>: " + anexos + "<br>" +
            "<strong>Antecedentes</strong>: " + antecedentes + "<br>" +
            "<strong>Entidad Destino</strong>: " + entidadDestino + "<br>" +
            "<strong>Nombre Destinatario</strong>: " + nomredestinatario + "<br>" +
            "<strong>Ciudad</strong>: " + ciudad + "<br>" +
            "<strong>Tipo Documental</strong>: " + tipoDocumental + "<br>" +
            "<strong>Observaciones</strong>: " + observaciones;

        detallesDocumento.innerHTML = contenidoModal;
        modal.style.display = "block";
    }

    function cerrarModal() {
        var modal = document.getElementById("myModal");
        modal.style.display = "none";
    }

    // Cierra el modal si el usuario hace clic fuera de él
    window.onclick = function (event) {
        var modal = document.getElementById("myModal");
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
</script>


                <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.8/dist/umd/popper.min.js"></script>
                <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
                <script src="https://kit.fontawesome.com/your-fontawesome-kit.js"></script>

                <!-- jQuery, Popper.js, Bootstrap JS -->
                <script src="jquery/jquery-3.3.1.min.js"></script>
                <script src="popper/popper.min.js"></script>
                <script src="bootstrap/js/bootstrap.min.js"></script>

                <!-- datatables JS -->
                <script type="text/javascript" src="datatables/datatables.min.js"></script>    

                <!-- para usar botones en datatables JS -->  
                <script src="datatables/Buttons-1.5.6/js/dataTables.buttons.min.js"></script>  
                <script src="datatables/JSZip-2.5.0/jszip.min.js"></script>    
                <script src="datatables/pdfmake-0.1.36/pdfmake.min.js"></script>    
                <script src="datatables/pdfmake-0.1.36/vfs_fonts.js"></script>
                <script src="datatables/Buttons-1.5.6/js/buttons.html5.min.js"></script>

                <!-- código JS propìo-->    
                <script type="text/javascript" src="main.js"></script>  


            </div>

        </div>


    </div>
</main>


</div>
</div>


<script>
                    function confirmarEliminacion(id) {
                        Swal.fire({
                            title: '¿Realmente desea eliminar?',
                            text: 'Esta acción no se puede deshacer',
                            icon: 'warning',
                            showCancelButton: true,
                            confirmButtonText: 'Sí, eliminar',
                            cancelButtonText: 'Cancelar'
                        }).then((result) => {
                            if (result.isConfirmed) {
                                window.location.href = 'EnviadosServlet?accion=eliminar&id=' + id;
                            }
                        });
                    }
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



<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>

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

