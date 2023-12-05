let temporizadorInactividad;
const tiempoInactividad = 900000; // 10 segundos (en milisegundos)

function reiniciarTemporizador() {
    // Reinicia el temporizador cada vez que hay interacción del usuario
    clearTimeout(temporizadorInactividad);
    temporizadorInactividad = setTimeout(cerrarSesion, tiempoInactividad);
}

function cerrarSesion() {
    // Coloca aquí tu lógica para cerrar la sesión y redirigir a index.jsp
    // Ejemplo: redirigir a la página de cierre de sesión (logout) y luego a index.jsp
    window.location.href = 'LoginController';  // Ajusta la ruta según tu aplicación
}

// Configurar eventos de interacción del usuario para reiniciar el temporizador
document.addEventListener('mousemove', reiniciarTemporizador);
document.addEventListener('keypress', reiniciarTemporizador);
document.addEventListener('click', reiniciarTemporizador); // Puedes agregar más eventos según sea necesario

// Iniciar el temporizador al cargar la página
document.addEventListener('DOMContentLoaded', () => {
    temporizadorInactividad = setTimeout(cerrarSesion, tiempoInactividad);
});
