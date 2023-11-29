function mostrarModal(
        id, numero_radicado, fechaRadicacion, NombreEntidadPersona, Asunto,
        RadicadoOrigen, FechaCreacionDocumento, Anexos, Ciudad, Dependencias, NombreDestinatario, NumRadRespuesta, FechaRespuesta
        ) {
    var detallesDocumento = document.getElementById("detallesDocumento");

    // Construye el contenido del modal con los detalles del documento
    var contenidoModal = "<strong>ID</strong>: " + id + "<br>" +
            "<strong>Número de Radicado</strong>: " + numero_radicado + "<br>" +
            "<strong>Fecha de Radicación</strong>: " + fechaRadicacion + "<br>" +
            "<strong>Nombre Entidad o Persona</strong>: " + NombreEntidadPersona + "<br>" +
            "<strong>Asunto</strong>: " + Asunto + "<br>" +
            "<strong>Radicado Origen</strong>: " + RadicadoOrigen + "<br>" +
            "<strong>Fecha del Documento</strong>: " + FechaCreacionDocumento + "<br>" +
            "<strong>Anexos</strong>: " + Anexos + "<br>" +
            "<strong>Ciudad</strong>: " + Ciudad + "<br>" +
            "<strong>Entidad Destino</strong>: " + Dependencias + "<br>" +
            "<strong>Nombre Destinatario</strong>: " + NombreDestinatario + "<br>" +
            "<strong>Radicado Respuesta</strong>: " + NumRadRespuesta + "<br>" +
            "<strong>Fecha de Respuesta</strong>: " + FechaRespuesta;

    detallesDocumento.innerHTML = contenidoModal;

    $('#myModal').modal('show'); // Muestra el modal usando jQuery
}

function cerrarModal() {
    $('#myModal').modal('hide'); // Oculta el modal usando jQuery
}

//funcion para eliminar un archo de la vista listar

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
            window.location.href = 'recibidoservlet?accion=eliminar&id=' + id;
        }
    });
}


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


//Funcion para cerrar cesion

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
