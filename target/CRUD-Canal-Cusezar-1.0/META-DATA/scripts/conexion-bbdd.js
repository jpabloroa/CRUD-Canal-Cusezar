window.onload = function () {
    cargarClientes();
}

/**
 * Método <code>GET</code>
 */
function cargarClientes() {
    let response = {};
    const ajaxrequest = new XMLHttpRequest();
    var url = "clientes/ver/?formato=json";
    ajaxrequest.open("GET", url, true);
    ajaxrequest.onreadystatechange = function () {
        if (ajaxrequest.readyState == 4) {
            if (ajaxrequest.status == 200) {
                //
                response = JSON.parse(ajaxrequest.responseText);
                var clientes = new Array();
                clientes = response.datos;
                //
                var tbody = document.getElementById("tabla-clientes").getElementsByTagName('tbody')[0];
                //
                for (var i = 0; i < clientes.length; i++) {
                    var tabla = tbody.insertRow(0);
                    let cliente = clientes[i];
                    tabla.insertCell(0).innerHTML += cliente.diaDeCreacion + "/" + cliente.mesDeCreacion + "/" + cliente.agnoDeCreacion;
                    tabla.insertCell(1).innerHTML += (cliente.viable) ? "SI" : "NO";
                    tabla.insertCell(2).innerHTML += (cliente.nombre == null) ? "-" : cliente.nombre;
                    tabla.insertCell(3).innerHTML += (cliente.correo == null) ? "-" : cliente.correo;
                    tabla.insertCell(4).innerHTML += (cliente.celular == null) ? "-" : cliente.celular;
                    tabla.insertCell(5).innerHTML += (cliente.medioPublicitario == null) ? "-" : cliente.medioPublicitario;
                    tabla.insertCell(6).innerHTML += (cliente.zonaBusqueda == null) ? "-" : cliente.zonaBusqueda;
                    tabla.insertCell(7).innerHTML += (cliente.proyectoDeInteres == null) ? "-" : cliente.proyectoDeInteres;
                    tabla.insertCell(8).innerHTML += (cliente.gestionDesdeSalaDeVentas) ? "SI" : "NO";
                    tabla.insertCell(9).innerHTML += (cliente.habeasData) ? "SI" : "NO";
                    tabla.insertCell(10).innerHTML += (cliente.contactado) ? "SI" : "NO";
                    tabla.insertCell(11).innerHTML += (cliente.contactoEfectivo) ? "SI" : "NO";
                    tabla.insertCell(12).innerHTML += (cliente.diaUltimoContacto == -1) ? "-" : cliente.diaUltimoContacto + "/" + (cliente.mesUltimoContacto == -1) ? "-" : cliente.mesUltimoContacto + "/" + (cliente.agnoUltimoContacto == -1) ? "-" : cliente.agnoUltimoContacto;
                    tabla.insertCell(13).innerHTML += (cliente.calificado) ? "SI" : "NO";
                    tabla.insertCell(14).innerHTML += (cliente.proyectoCalificado == null) ? "-" : cliente.proyectoCalificado;
                    tabla.insertCell(15).innerHTML += (cliente.visitaAgendada) ? "SI" : "NO";
                    tabla.insertCell(16).innerHTML += (cliente.diaVisita == -1) ? "-" : cliente.diaVisita + "/" + (cliente.mesVisita == -1) ? "-" : cliente.mesVisita + "/" + (cliente.agnoVisita == -1) ? "-" : cliente.agnoVisita;
                    tabla.insertCell(17).innerHTML += (cliente.visitaEfectiva) ? "SI" : "NO";
                    tabla.insertCell(18).innerHTML += (cliente.estado == null) ? "-" : cliente.estado;
                    tabla.insertCell(19).innerHTML += (cliente.asignadoA == null) ? "-" : cliente.asignadoA;
                }
                console.log("Procesado!");
            } else {
                response = JSON.parse(ajaxrequest.responseText);
                console.log("Error al procesar la solicitud \n Estado de la solicitud: " + response.estado + "\n Detalles: " + response.respuesta);
            }
        }
    };
    ajaxrequest.send();
}

/**
 * Método <code>POST</code>
 * 
 * @param {string} formato
 */
function insertarCliente(formato) {
    var array = [];
    switch (formato) {
        case "json":
            break;
        case "csv":
            break;
        default:
            break;
    }
    let object = {};
    object.datos = array;
    const ajaxrequest = new XMLHttpRequest();
    var url = "clientes/";
    ajaxrequest.open("POST", url, true);
    ajaxrequest.onreadystatechange = function () {
        if (ajaxrequest.readyState == 4) {
            let response = {};
            if (ajaxrequest.status == 200) {
                //
                alert(ajaxrequest.responseText);
                response = JSON.parse(ajaxrequest.responseText);
                console.log("Respuesta servidor: " + response.respuesta + "\n Estado de la solicitud: " + response.estado + "\n Información solicitud: " + response.info);
            } else {
                response = JSON.parse(ajaxrequest.responseText);
                console.log("Respuesta servidor: " + response.respuesta + "\n Estado de la solicitud: " + response.estado + "\n Información solicitud: " + response.info);
            }
        }
    };
    ajaxrequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    ajaxrequest.send(object.toJSON());
}

function actualizarCliente(){

}