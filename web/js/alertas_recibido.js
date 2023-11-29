document.addEventListener('DOMContentLoaded', function () {
    const camposConValidacionLetras = [
        'nombre_entidad',
        'asunto',
        'ciudad',
        'Dependencia',
        'nombre_destinatario',
        'nombre_destinatario'
    ];


    const camposSoloNumeros = [
        'numero_origen',
        'anexos',
        'NumeroRadRespuesta'
        
        
    ];

    function agregarValidacionLetras(campoId) {
        const campoInput = document.getElementById(campoId);

        campoInput.addEventListener('input', function () {
            const valorOriginal = this.value;
            this.value = valorOriginal.replace(/[^a-zA-ZáéíóúüñÁÉÍÓÚÜÑ\s´']/ug, '');

            if (valorOriginal !== this.value && this.value.trim() === '') {
                mostrarNotificacionError('letras');
            }
        });
    }


    function agregarValidacionSoloNumeros(campoId) {
        const campoInput = document.getElementById(campoId);

        campoInput.addEventListener('input', function () {
            const valorOriginal = this.value;
            this.value = valorOriginal.replace(/\D/ug, '');

            if (valorOriginal !== this.value && this.value.trim() === '') {
                mostrarNotificacionError('numeros');
            }
        });
    }

    function mostrarNotificacionError(tipo) {
        let mensaje;
        if (tipo === 'letras') {
            mensaje = 'Este campo solo puede contener letras.';
        } else if (tipo === 'numeros') {
            mensaje = 'Este campo solo puede contener n\u00FAmeros.';
        }

        Swal.fire({
            icon: 'warning',
            title: 'Notificaci\u00f3n',
            text: mensaje,
        });
    }

    camposConValidacionLetras.forEach(agregarValidacionLetras);
    camposSoloNumeros.forEach(agregarValidacionSoloNumeros);
});
