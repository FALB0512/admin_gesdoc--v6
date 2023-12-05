function cerrarSesion() {
    // Obtener una marca de tiempo única para evitar la caché del navegador
    var timestamp = new Date().getTime();

    // Realizar una solicitud al controlador para cerrar la sesión
    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'LoginController?timestamp=' + timestamp, true);

    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            // La solicitud fue exitosa, redirigir o realizar otras acciones según sea necesario
            window.location.href = 'index.jsp?timestamp=' + timestamp; // Redirigir a la página de inicio con la marca de tiempo
        }
    };

    xhr.send();
}
