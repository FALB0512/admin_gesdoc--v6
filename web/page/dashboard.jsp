
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.ConsultarSeguimientoUsuarios"%>
<%@page import="Modelo.ConsultarSeguimientoUsuarios"%>
<%@page import="DAOS.CrudSeguimientoUsuariosDAO"%>
<%@page import="Modelo.consultausuario"%>
<%@page import="controlador.conexion"%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="Expires" content="0">

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


<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<div id="layoutSidenav">
    <!--debo copiar linea 10-->
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

    <style>

        .col-xl-3 col-md-6{
            text-align: center;
        }

    </style>

    <!--contenido del dashboard-->
    <div id="layoutSidenav_content">
        <main>
            <div class="container-fluid px-4">
                <h1 class="mt-4">Bienvenidos</h1>

                <ol class="breadcrumb mb-4">
                    <li class="breadcrumb-item active">Esta es una herramienta tecnologica didactica que emula un sistema de Gestion Documental </li>
                </ol>
                <%
                    try {
                        // Establecer la conexión a la base de datos utilizando tu clase 'conexion'
                        conexion cn = new conexion();
                        Connection con = cn.getConection();

                        // Consulta SQL para contar los elementos en la tabla
                        String sql = "SELECT COUNT(*) AS total FROM tblusuarios"; // Reemplaza 'nombre_de_la_tabla' con el nombre real de tu tabla

                        PreparedStatement ps = con.prepareStatement(sql);
                        ResultSet rs = ps.executeQuery();

                        int totalElementos = 0;

                        if (rs.next()) {
                            totalElementos = rs.getInt("total");
                        }

                        // Cerrar la conexión a la base de datos
                        rs.close();
                        ps.close();
                        con.close();

                        // Mostrar el resultado
%>
                <div class="row">
                    <div class="col-xl-3 col-md-6">
                        <div class="card bg-primary text-white mb-4">
                            <div style="text-align: center; font-size: 36px;">
                                <i class="fas fa-users"></i> <!-- Icono de FontAwesome -->
                            </div>
                            <div style="text-align: center; font-size: 15px; font-family: 'Roboto', sans-serif;">Usuarios Registrados</div>
                            <div style="text-align: center; font-size: 36px; font-family: 'Roboto', sans-serif;"><%= totalElementos%></div>

                        </div>
                    </div>


                    <%
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    %>



                    <%
                        try {
                            // Establecer la conexión a la base de datos utilizando tu clase 'conexion'
                            conexion cn = new conexion();
                            Connection con = cn.getConection();

                            // Consulta SQL para contar los elementos en la tabla
                            String sql = "SELECT COUNT(*) AS total FROM tblradicadoenviados"; // Reemplaza 'nombre_de_la_tabla' con el nombre real de tu tabla

                            PreparedStatement ps = con.prepareStatement(sql);
                            ResultSet rs = ps.executeQuery();

                            int totalElementosenviados = 0;

                            if (rs.next()) {
                                totalElementosenviados = rs.getInt("total");
                            }

                            // Cerrar la conexión a la base de datos
                            rs.close();
                            ps.close();
                            con.close();

                            // Mostrar el resultado
%>
                    <div class="col-xl-3 col-md-6">

                        <div class="card bg-warning text-white mb-4">

                            <div style="text-align: center; font-size: 36px;">
                                <i class="fa fa-folder-open"></i>

                            </div>
                            <div style="text-align: center; font-size: 15px; font-family: 'Roboto', sans-serif;">Correspondencia Enviada</div>
                            <div style="text-align: center; font-size: 36px; font-family: 'Roboto', sans-serif;"><%= totalElementosenviados%></div>

                        </div>
                    </div>
                    <%
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    %>



                    <%
                        try {
                            // Establecer la conexión a la base de datos utilizando tu clase 'conexion'
                            conexion cn = new conexion();
                            Connection con = cn.getConection();

                            // Consulta SQL para contar los elementos en la tabla
                            String sql = "SELECT COUNT(*) AS total FROM tblradicadorecibido"; // Reemplaza 'nombre_de_la_tabla' con el nombre real de tu tabla

                            PreparedStatement ps = con.prepareStatement(sql);
                            ResultSet rs = ps.executeQuery();

                            int totalElementosrecibido = 0;

                            if (rs.next()) {
                                totalElementosrecibido = rs.getInt("total");
                            }

                            // Cerrar la conexión a la base de datos
                            rs.close();
                            ps.close();
                            con.close();

                            // Mostrar el resultado
%>
                    <div class="col-xl-3 col-md-6">
                        <div class="card bg-success text-white mb-4">

                            <div style="text-align: center; font-size: 36px;">
                                <i class="fa fa-inbox"></i>

                            </div>
                            <div style="text-align: center; font-size: 15px; font-family: 'Roboto', sans-serif;">Correspondencia Recibida</div>
                            <div style="text-align: center; font-size: 36px; font-family: 'Roboto', sans-serif;"><%= totalElementosrecibido%></div>


                        </div>
                    </div>
                    <%
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    %>




                    <%
                        try {
                            // Establecer la conexión a la base de datos utilizando tu clase 'conexion'
                            conexion cn = new conexion();
                            Connection con = cn.getConection();

                            // Consulta SQL para contar los elementos en la tabla
                            String sql = "SELECT COUNT(*) AS total FROM otrosenviosrecibidos"; // Reemplaza 'nombre_de_la_tabla' con el nombre real de tu tabla

                            PreparedStatement ps = con.prepareStatement(sql);
                            ResultSet rs = ps.executeQuery();

                            int totalElementosotros = 0;

                            if (rs.next()) {
                                totalElementosotros = rs.getInt("total");
                            }

                            // Cerrar la conexión a la base de datos
                            rs.close();
                            ps.close();
                            con.close();

                            // Mostrar el resultado
%>
                    <div class="col-xl-3 col-md-6">
                        <div class="card bg-danger text-white mb-4 custom-card">
                            <div style="text-align: center; font-size: 36px;">
                                <i class="fa fa-newspaper"></i>
                            </div>
                            <div style="text-align: center; font-size: 15px; font-family: 'Roboto', sans-serif;">Otras Correspondencias</div>
                            <div style="text-align: center; font-size: 36px; font-family: 'Roboto', sans-serif;"><%= totalElementosotros%></div>
                        </div>
                    </div>



                    <%
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    %>



                    <!--Ejemplo tabla con DataTables-->

                    <ol class="breadcrumb mb-4">
                        <li class="breadcrumb-item active">Seguimiento a las operaciones realizadas por los usuarios </li>
                    </ol>

                    <div style="height: 100%; padding: 8px;"class="row">
                        <div class="col-lg-12">
                            <div class="table-responsive" >        
                                <table style="text-align: center;"id="example" class="table table-striped table-bordered" cellspacing="0">
                                    <thead style="font-size: 10px">
                                        <tr>
                                            <th>Id</th>
                                            <th>Fecha de Ingreso</th>
                                            <!--                                <th>Hora de Ingreso</th>
                                                                            <th>Ip de Ingreso</th>
                                                                            <th>Operaciones Realizadas</th>-->
                                            <th>Usuario</th>
                                            <!--                                <th>Numero de Radicado</th>-->
                                            <th>Acciones</th>
                                        </tr>
                                    </thead>
                                    <tbody>


                                        <%
                                            CrudSeguimientoUsuariosDAO daosegimiento = new CrudSeguimientoUsuariosDAO();
                                            List<ConsultarSeguimientoUsuarios> list = daosegimiento.listar();
                                            Iterator<ConsultarSeguimientoUsuarios> iter = list.iterator();
                                            ConsultarSeguimientoUsuarios act = null;
                                            while (iter.hasNext()) {
                                                act = iter.next();
                                        %>

                                        <tr>

                                            <td><%= act.getAccId()%></td>
                                            <td><%= act.getAccFechaIngreso()%></td>
            <!--                            <td><%= act.getAccHoraIngreso()%></td>
                                            <td><%= act.getAccIP()%></td>
                                            <td><%= act.getAccAcciones()%></td>
                                            -->                             <td><%= act.getAccUsuario()%></td><!--
                                                                            <td><%= act.getAccNumeroRadicado()%></td>-->

                                            <td>


                                                <button class="btn btn-info" onclick="mostrarModal(
                                                    '<%= act.getAccId()%>',
                                                    '<%= act.getAccFechaIngreso()%>',
                                                    '<%= act.getAccHoraIngreso()%>',
                                                    '<%= act.getAccIP()%>',
                                                    '<%= act.getAccAcciones()%>',
                                                    '<%= act.getAccUsuario()%>',
                                                    '<%= act.getAccNumeroRadicado()%>',
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

                    <!-- Este es el modal donde se muestran los detalles del documento registrado -->
                    <div class="modal" id="myModal">
                        <div class="modal-dialog">
                            <div class="modal-content modal-content-gris">

                                <!-- Encabezado del modal -->
                                <div class="modal-header">
                                    <h4 class="modal-title">Detalles las Operaciones Realizadas</h4>

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

                    <!-- Este es el script del modal para mostrar los detalles del documento registrado -->
                    <script>
                        function mostrarModal(id, fechaIngreso, horaIngreso, ip, acciones, usuario, numeroRadicado) {
                            var modal = document.getElementById("myModal");
                            var detallesDocumento = document.getElementById("detallesDocumento");

                            // Construye el contenido del modal con los detalles del documento
                            var contenidoModal =
                                    "<strong>ID</strong>: " + id + "<br>" +
                                    "<strong>Fecha de Ingreso</strong>: " + fechaIngreso + "<br>" +
                                    "<strong>Hora de Ingreso</strong>: " + horaIngreso + "<br>" +
                                    "<strong>IP</strong>: " + ip + "<br>" +
                                    "<strong>Acciones</strong>: " + acciones + "<br>" +
                                    "<strong>Usuario</strong>: " + usuario + "<br>" +
                                    "<strong>Número de Radicado</strong>: " + numeroRadicado;

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



        </main>
        <footer class="py-4 bg-light mt-auto">
            <div class="container-fluid px-4">
                <div class="d-flex align-items-center justify-content-between small">
                    <div class="text-muted">Copyright &copy; CLPEM-SENA 2023</div>
                    <div>

                    </div>
                </div>
            </div>



        </footer>
    </div>

             
<script src="js/cierre_automatico.js"></script>
<script src="js/cerrarsesion.js"></script>
<script src="js/recargarpagina.js"></script>


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




</div>