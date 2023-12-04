document.addEventListener('DOMContentLoaded', function () {
  const camposBloqueados = ['nombre_entidad',
        'asunto',
        'ciudad',
        'Dependencia',
        'nombre_destinatario',
        'nombre_destinatario'];
    
  const campoNumerico = ['numero_origen',
        'anexos',
        'NumeroRadRespuesta'];

  camposBloqueados.forEach(campo => {
    const inputCampo = document.getElementById(campo);

    inputCampo.addEventListener('keydown', function (event) {
      // Verificar si la tecla presionada es un número (0-9)
      if (event.key >= '0' && event.key <= '9') {
        event.preventDefault();
        // Mostrar una alerta SweetAlert indicando que los números no están permitidos
        Swal.fire({
          icon: 'warning',
          title: 'Notificaci\u00f3n',
          text: `Este campo solo puede contener letras.`,
        });
      }
    });
  });

  const inputAnexos = document.getElementById(campoNumerico);

  inputAnexos.addEventListener('keydown', function (event) {
    // Verificar si la tecla presionada no es un número (0-9)
    if (!(event.key >= '0' && event.key <= '9')) {
      event.preventDefault();
      // Mostrar una alerta SweetAlert indicando que solo se permiten números en este campo
      Swal.fire({
        icon: 'warning',
        title: 'Notificaci\u00f3n',
        text: 'Este campo solo puede contener n\u00FAmeros.'
      });
    }
  });
});