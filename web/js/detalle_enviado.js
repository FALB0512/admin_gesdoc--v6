/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */



                    function mostrarModal(id, numeroRadicado, dependencia, asunto, antecedentes, tipoDocumental, observaciones) {
                        var modal = document.getElementById("myModal");
                        var detallesDocumento = document.getElementById("detallesDocumento");

                        // Construye el contenido del modal con los detalles del documento
                        var contenidoModal = "ID: " + id + "<br>" +
                                "Número de Radicado: " + numeroRadicado + "<br>" +
                                "Dependencia: " + dependencia + "<br>" +
                                "Asunto: " + asunto + "<br>" +
                                "Antecedentes: " + antecedentes + "<br>" +
                                "Tipo Documental: " + tipoDocumental + "<br>" +
                                "Observaciones: " + observaciones;

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
