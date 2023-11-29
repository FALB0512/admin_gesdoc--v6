
    
// Obtener el campo de entrada del número de radicado
const numeroRadicadoInput = document.getElementById('numero_radicado');

// Función para mostrar una alerta de SweetAlert
function mostrarAlerta() {
    Swal.fire({
        icon: 'warning',
        title: 'N\u00FAmero de radicado incorrecto',
        text: 'No corresponde con la codificacion para correspondencia recibida.',
    });
}

// Función para validar el formato del número de radicado
function validarNumeroRadicado() {
    const numeroRadicado = numeroRadicadoInput.value.trim();
    const formatoValido = /^1-?\d{0,4}$/.test(numeroRadicado);

    if (!formatoValido) {
        // Mostrar una alerta de SweetAlert si el número no sigue el formato deseado
        mostrarAlerta();
        // Borrar el contenido del campo si el número no cumple con el formato
        numeroRadicadoInput.value = '';
    }
}

// Agregar evento de entrada para verificar el número mientras se escribe
numeroRadicadoInput.addEventListener('input', validarNumeroRadicado);


    

