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
                var tablaMain = document.getElementById("resp-table-body");

                //
                for (var i = 0; i < clientes.length; i++) {

                    //
                    let cliente = clientes[i];
                    tablaMain.appendChild(cargarClientes_agregarFila(cliente));
                }
                console.log(`Se procesaron ${clientes.length} clientes !`);
            } else {
                response = JSON.parse(ajaxrequest.responseText);
                console.log("Error al procesar la solicitud \n Estado de la solicitud: " + response.estado + "\n Detalles: " + response.respuesta);
            }
        }
    };
    ajaxrequest.send();
}

function cargarClientes_agregarFila(obj) {
    var cliente = obj;
    var tabla = document.createElement("div");
    tabla.id = `cliente-${cliente.codigoConteo}`;
    tabla.className = "resp-table-row";
    tabla
    //tabla.innerHTML += `<div class="resp-table-row" id="cliente-${cliente.codigoConteo}" onclick="accionar()" onblur="getClientData(this)">`;
    tabla.innerHTML += `<div class="table-body-cell" title="fechaCreacion" onclick="setEditable(this)" onblur="nonEditable(this)">${cliente.diaDeCreacion + "/" + cliente.mesDeCreacion + "/" + cliente.agnoDeCreacion}</div>`;
    tabla.innerHTML += `<div class="table-body-cell" title="viable" onclick="setEditable(this)" onblur="nonEditable(this)">${(cliente.viable) ? "SI" : "NO"}</div>`;
    tabla.innerHTML += `<div class="table-body-cell" title="nombre" onclick="setEditable(this)" onblur="nonEditable(this)">${(cliente.nombre == null) ? "-" : cliente.nombre}</div>`;
    tabla.innerHTML += `<div class="table-body-cell" title="correo" onclick="setEditable(this)" onblur="nonEditable(this)">${(cliente.correo == null) ? "-" : cliente.correo}</div>`;
    tabla.innerHTML += `<div class="table-body-cell" title="celular" onclick="setEditable(this)" onblur="nonEditable(this)">${(cliente.celular == null) ? "-" : cliente.celular}</div>`;
    tabla.innerHTML += `<div class="table-body-cell" title="medioPublicitario" onclick="setEditable(this)" onblur="nonEditable(this)">${(cliente.medioPublicitario == null) ? "-" : cliente.medioPublicitario}</div>`;
    tabla.innerHTML += `<div class="table-body-cell" title="zonaBusqueda" onclick="setEditable(this)" onblur="nonEditable(this)">${(cliente.zonaBusqueda == null) ? "-" : cliente.zonaBusqueda}</div>`;
    tabla.innerHTML += `<div class="table-body-cell" title="proyectoDeInteres" onclick="setEditable(this)" onblur="nonEditable(this)">${(cliente.proyectoDeInteres == null) ? "-" : cliente.proyectoDeInteres}</div>`;
    tabla.innerHTML += `<div class="table-body-cell" title="gestionDesdeSalaDeVentas" onclick="setEditable(this)" onblur="nonEditable(this)">${(cliente.gestionDesdeSalaDeVentas) ? "SI" : "NO"}</div>`;
    tabla.innerHTML += `<div class="table-body-cell" title="habeasData" onclick="setEditable(this)" onblur="nonEditable(this)">${(cliente.habeasData) ? "SI" : "NO"}</div>`;
    tabla.innerHTML += `<div class="table-body-cell" title="contactado" onclick="setEditable(this)" onblur="nonEditable(this)">${(cliente.contactado) ? "SI" : "NO"}</div>`;
    tabla.innerHTML += `<div class="table-body-cell" title="contactoEfectivo" onclick="setEditable(this)" onblur="nonEditable(this)">${(cliente.contactoEfectivo) ? "SI" : "NO"}</div>`;
    tabla.innerHTML += `<div class="table-body-cell" title="fechaUltimoContacto" onclick="setEditable(this)" onblur="nonEditable(this)">${((cliente.diaUltimoContacto == -1) ? "-" : cliente.diaUltimoContacto) + "/" + ((cliente.mesUltimoContacto == -1) ? "-" : cliente.mesUltimoContacto) + "/" + ((cliente.agnoUltimoContacto == -1) ? "-" : cliente.agnoUltimoContacto)}</div>`;
    tabla.innerHTML += `<div class="table-body-cell" title="calificado" onclick="setEditable(this)" onblur="nonEditable(this)">${(cliente.calificado) ? "SI" : "NO"}</div>`;
    tabla.innerHTML += `<div class="table-body-cell" title="proyectoCalificado" onclick="setEditable(this)" onblur="nonEditable(this)">${(cliente.proyectoCalificado == null) ? "-" : cliente.proyectoCalificado}</div>`;
    tabla.innerHTML += `<div class="table-body-cell" title="visitaAgendada" onclick="setEditable(this)" onblur="nonEditable(this)">${(cliente.visitaAgendada) ? "SI" : "NO"}</div>`;
    tabla.innerHTML += `<div class="table-body-cell" title="fechaVisita" onclick="setEditable(this)" onblur="nonEditable(this)">${((cliente.diaVisita == -1) ? "-" : cliente.diaVisita) + "/" + ((cliente.mesVisita == -1) ? "-" : cliente.mesVisita) + "/" + ((cliente.agnoVisita == -1) ? "-" : cliente.agnoVisita)}</div>`;
    tabla.innerHTML += `<div class="table-body-cell" title="visitaEfectiva" onclick="setEditable(this)" onblur="nonEditable(this)">${(cliente.visitaEfectiva) ? "SI" : "NO"}</div>`;
    tabla.innerHTML += `<div class="table-body-cell" title="estado" onclick="setEditable(this)" onblur="nonEditable(this)">${(cliente.estado == null) ? "-" : cliente.estado}</div>`;
    tabla.innerHTML += `<div class="table-body-cell" title="asignadoA" onclick="setEditable(this)" onblur="nonEditable(this)">${(cliente.asignadoA == null) ? "-" : cliente.asignadoA}</div>`;
    return tabla;
}
function accionar() {
    console.log("perico");
}
function setEditable(element) {
    element.contentEditable = "true";
    element.onblur = function () {
        var array = [];
        array = element.parentNode.getElementsByClassName("table-body-cell");
        let obj = {};
        var i = 0;
        var size = array.length;
        console.log(array.length);
        while (i < size) {
            obj[array[i].title] = array[i].innerHTML;
            i++;
        };
        obj.codigoConteo = element.parentNode.id.split("-")[1];
        actualizarCliente(obj);
        console.log(` ¡ Actualizado el cliente ${obj.codigoConteo} ! ${obj.nombre}`);
    };
}

function nonEditable(element) {
    element.contentEditable = "false";
}

/** */

/**
 * Método <code>POST</code>
 * 
 * @param {string} formato
 * @param {string} valor 
 */
function insertarCliente(formato, valor) {
    var array = [];
    switch (formato) {
        case "json":
            break;
        case "xlsx":
            var xlsx = valor;
            for (var i = 0; i < xlsx.length; i++) {
                let objeto = {};
                objeto.nombre = xlsx[i].NOMBRE;
                objeto.correo = xlsx[i].CORREO;
                objeto.celular = xlsx[i].CELULAR;
                objeto.medioPublicitario = xlsx[i].MEDIO;
                array.push(objeto);
            }
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
                response = JSON.parse(ajaxrequest.responseText);
                console.log(` ¡ Se insertaron ${array.length} clientes ! `);
                console.log(`Respuesta servidor: ${response.respuesta} \n Estado de la solicitud: ${response.estado} \n Información solicitud: ${response.info}`);
            } else {
                response = JSON.parse(ajaxrequest.responseText);
                console.log(`Respuesta servidor: ${response.respuesta} \n Estado de la solicitud: ${response.estado} \n Información solicitud: ${response.info}`);
            }
        }
    };
    ajaxrequest.setRequestHeader("Content-Type", "application/json");
    ajaxrequest.send(JSON.stringify(object));
}

/** */

/**
 * 
 * @param {Object} object 
 */
function actualizarCliente(object) {
    const ajaxrequest = new XMLHttpRequest();
    var url = `clientes/${object.codigoConteo}`;
    ajaxrequest.open("PUT", url, true);
    ajaxrequest.onreadystatechange = function () {
        if (ajaxrequest.readyState == 4) {
            let response = {};
            if (ajaxrequest.status == 200) {
                //
                response = JSON.parse(ajaxrequest.responseText);
                console.log(`Respuesta servidor: ${response.respuesta} \n Estado de la solicitud: ${response.estado} \n Información solicitud: ${response.info}`);
            } else {
                response = JSON.parse(ajaxrequest.responseText);
                console.log(`Respuesta servidor: ${response.respuesta} \n Estado de la solicitud: ${response.estado} \n Información solicitud: ${response.info}`);
            }
        }
    };
    ajaxrequest.setRequestHeader("Content-Type", "application/json");
    ajaxrequest.send(JSON.stringify(object));
}