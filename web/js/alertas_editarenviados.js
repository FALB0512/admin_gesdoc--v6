document.addEventListener('DOMContentLoaded', function () {
  const camposBloqueados = ['Dependencia', 'Asunto', 'NombreFuncionario', 'TipoDocumental', 'Antecedentes', 'Entidad', 'ciudad', 'nombre_destinatario', 'Observaciones'];

  const camposNumericos = ['anexos'];

  camposBloqueados.forEach(campo => {
    const inputCampo = document.getElementById(campo);

    if (inputCampo) {
      inputCampo.addEventListener('keydown', function (event) {
        // Verificar si la tecla presionada es un número (0-9)
        if (event.key >= '0' && event.key <= '9') {
          event.preventDefault();
          // Mostrar una alerta SweetAlert indicando que solo se permiten letras en este campo
          mostrarAlerta('Este campo solo puede contener letras.');
        }
      });
    }
  });

  camposNumericos.forEach(campo => {
    const inputCampoNumerico = document.getElementById(campo);

    if (inputCampoNumerico) {
      inputCampoNumerico.addEventListener('keydown', function (event) {
        // Verificar si la tecla presionada no es un número (0-9)
        if (!(event.key >= '0' && event.key <= '9')) {
          event.preventDefault();
          // Mostrar una alerta SweetAlert indicando que solo se permiten números en este campo
          mostrarAlerta('Este campo solo puede contener n\u00FAmeros.');
        }
      });
    }
  });

  function mostrarAlerta(mensaje) {
    Swal.fire({
      icon: 'warning',
      title: 'Notificaci\u00f3n',
      text: mensaje
    });
  }
});
