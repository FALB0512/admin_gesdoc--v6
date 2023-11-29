/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


// Función para obtener el año actual
function getCurrentYear() {
    return new Date().getFullYear();
}

// Función para obtener el último año registrado en el almacenamiento local
function getLastYear() {
    return parseInt(localStorage.getItem("ultimoYear3")) || getCurrentYear();
}

// Función para obtener el último número generado en el año actual
function getLastNumberForCurrentYear() {
    var currentYear = getCurrentYear();
    return parseInt(localStorage.getItem("ultimoNumero3" + currentYear)) || 0;
}

// Función para reiniciar el contador cuando cambia el año
function resetCounterIfNeeded() {
    var currentYear = getCurrentYear();
    var lastYear = getLastYear();
    if (currentYear !== lastYear) {
        localStorage.setItem("ultimoYear3", currentYear);
        localStorage.removeItem("ultimoNumero3" + currentYear); // Reinicia el contador para el nuevo año
    }
}

document.getElementById("generarBtn").addEventListener("click", function () {
    resetCounterIfNeeded();
    Swal.fire({
        title: '¿Desea generar un número de radicado?',
        icon: 'question',
        showCancelButton: true,
        confirmButtonText: 'Sí',
        cancelButtonText: 'No'
    }).then((result) => {
        if (result.isConfirmed) {
            var numero = getLastNumberForCurrentYear() + 1;
            var currentYear = getCurrentYear();
            localStorage.setItem("ultimoNumero3" + currentYear, numero);
            var numeroFormateado = numero.toString().padStart(4, "0"); // Formatea como "0001", "0002", ...
            Swal.fire('Número de radicado generado', 'Tu número de radicado es: 3-' + numeroFormateado, 'success');
        }
    });
});

// Llamamos a resetCounterIfNeeded al cargar la página para asegurarnos de que el contador esté configurado correctamente
resetCounterIfNeeded();


