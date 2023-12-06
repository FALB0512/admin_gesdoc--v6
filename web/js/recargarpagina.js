// Función para recargar la página después de cerrar la sesión
        function recargarPagina() {
            location.reload(true); // Forzar la recarga desde el servidor
        }

        // Llamar a la función de recarga al cargar la página
        window.onload = function () {
            recargarPagina();
        };