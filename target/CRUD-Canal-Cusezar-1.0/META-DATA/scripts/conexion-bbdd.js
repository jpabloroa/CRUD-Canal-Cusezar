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
                tablaMain.innerHTML = "";
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
    tabla.innerHTML += `<div class="table-body-cell" columna="fechaDeCreacion" title="Fecha de creación" info="Creado en :${cliente.fechaDeCreacion}" tipo="fecha">${cliente.fechaDeCreacion}</div>`;
    tabla.innerHTML += `<div class="table-body-cell" onclick="setEditable(this)" onblur="nonEditable(this)" columna="viable" title="Viable" info="" tipo="opcion"><select>${(cliente.viable == 1) ? "<option selected='selected'>SI</option><option>NO</option>" : "<option selected='selected'>NO</option><option>SI</option>"}</select></div>`;
    tabla.innerHTML += `<div class="table-body-cell" onclick="setEditable(this)" onblur="nonEditable(this)" columna="nombre" title="" info="Nombre del cliente" tipo="texto">${(cliente.nombre == null) ? "-" : cliente.nombre}</div>`;
    tabla.innerHTML += `<div class="table-body-cell" onclick="setEditable(this)" onblur="nonEditable(this)" columna="correo" title="Correo electrónico" info="" tipo="texto">${(cliente.correo == null) ? "-" : cliente.correo}</div>`;
    tabla.innerHTML += `<div class="table-body-cell" onclick="setEditable(this)" onblur="nonEditable(this)" columna="celular" title="Número de celular" info="" tipo="texto">${(cliente.celular == null) ? "-" : cliente.celular}</div>`;
    tabla.innerHTML += `<div class="table-body-cell" onclick="setEditable(this)" onblur="nonEditable(this)" columna="medioPublicitario" title="Medio publicitario" info="" tipo="texto">${(cliente.medioPublicitario == null) ? "-" : cliente.medioPublicitario}</div>`;
    tabla.innerHTML += `<div class="table-body-cell" onclick="setEditable(this)" onblur="nonEditable(this)" columna="zonaBusqueda" title="Zona de búsqueda" info="" tipo="texto">${(cliente.zonaBusqueda == null) ? "-" : cliente.zonaBusqueda}</div>`;
    tabla.innerHTML += `<div class="table-body-cell" onclick="setEditable(this)" onblur="nonEditable(this)" columna="proyectoDeInteres" title="Proyecto de interés" info="" tipo="texto">${(cliente.proyectoDeInteres == null) ? "-" : cliente.proyectoDeInteres}</div>`;
    tabla.innerHTML += `<div class="table-body-cell" onclick="setEditable(this)" onblur="nonEditable(this)" columna="gestionDesdeSalaDeVentas" title="Gestionado por sala de ventas" info="" tipo="opcion"><select>${(cliente.gestionDesdeSalaDeVentas == 1) ? "<option selected='selected'>SI</option><option>NO</option>" : "<option selected='selected' >NO</option><option>SI</option>"}</select></div>`;
    tabla.innerHTML += `<div class="table-body-cell" onclick="setEditable(this)" onblur="nonEditable(this)" columna="habeasData" title="Autoriza tratamiento de datos" info="" tipo="opcion"><select>${(cliente.habeasData == 1) ? "<option selected='selected'>SI</option><option>NO</option>" : "<option selected='selected'>NO</option><option>SI</option>"}</select></div>`;
    tabla.innerHTML += `<div class="table-body-cell" onclick="setEditable(this)" onblur="nonEditable(this)" columna="contactado" title="Contactado" info="${(cliente.contactado == 1) ? "Contactado el " + cliente.fechaDeContacto : "No ha sido contactado"}" tipo="opcion"><select>${(cliente.contactado == 1) ? "<option selected='selected' >SI</option><option>NO</option>" : "<option selected='selected'>NO</option><option>SI</option>"}</select></div>`;
    tabla.innerHTML += `<div class="table-body-cell" onclick="setEditable(this)" onblur="nonEditable(this)" columna="contactoEfectivo" title="Contacto efectivo" info="${(cliente.contactoEfectivo == 1) ? "Último contacto el " + cliente.fechaDeContactoEfectivo : "No ha sido registrado como contacto efectivo"}" tipo="opcion"><select>${(cliente.contactoEfectivo == 1) ? "<option selected='selected'>SI</option><option>NO</option>" : "<option selected='selected'>NO</option><option>SI</option>"}</select></div>`;
    tabla.innerHTML += `<div class="table-body-cell" onclick="setEditable(this)" onblur="nonEditable(this)" columna="calificado" title="Calificado" info="" tipo="opcion"><select>${(cliente.calificado == 1) ? "<option selected='selected'>SI</option><option>NO</option>" : "<option selected='selected'>NO</option><option>SI</option>"}</select></div>`;
    tabla.innerHTML += `<div class="table-body-cell" onclick="setEditable(this)" onblur="nonEditable(this)" columna="proyectoCalificado" title="Proyecto calificado" info="" tipo="texto">${(cliente.proyectoCalificado == null) ? "-" : cliente.proyectoCalificado}</div>`;
    tabla.innerHTML += `<div class="table-body-cell" onclick="setEditable(this)" onblur="nonEditable(this)" columna="visitaAgendada" title="Visita agendada" info="" tipo="opcion"><select>${(cliente.visitaAgendada == 1) ? "<option selected='selected'>SI</option><option>NO</option>" : "<option selected='selected'>NO</option><option>SI</option>"}</select></div>`;
    tabla.innerHTML += `<div class="table-body-cell" onclick="setEditable(this)" onblur="nonEditable(this)" columna="fechaVisitaAgendada" title="Fecha de visita efectiva" info="" tipo="fecha"><input type="date" ${(cliente.fechaVisitaAgendada == null) ? "" : "value='" + new Date(cliente.fechaVisitaAgendada).toISOString().substring(0, 10) + "'"}></div>`;
    tabla.innerHTML += `<div class="table-body-cell" onclick="setEditable(this)" onblur="nonEditable(this)" columna="visitaEfectiva" title="Visita efectiva" info="" tipo="opcion"><select>${(cliente.visitaEfectiva == 1) ? "<option selected='selected'>SI</option><option>NO</option>" : "<option selected='selected'>NO</option><option>SI</option>"}</select></div> `;
    tabla.innerHTML += `<div class="table-body-cell" onclick="setEditable(this)" onblur="nonEditable(this)" columna="estado" title="Estado del cliente" info="" tipo="texto"> ${(cliente.estado == null) ? "-" : cliente.estado}</div>`;
    tabla.innerHTML += `<div class="table-body-cell" onclick="setEditable(this)" onblur="nonEditable(this)" columna="asignadoA" title="Gestion asignada a" info="" tipo="texto">${(cliente.asignadoA == null) ? "-" : cliente.asignadoA}</div>`;
    //tabla.innerHTML += `< div class="table-body-cell" ondblclick = "setEditable(this)" onblur = "nonEditable(this)" columna = "${cliente}" title = "" info = "" tipo = "texto" > ${ cliente }</div > `;



    /*
        tabla.innerHTML += `< div class="table-body-cell" tipo = "fecha" info = "" data = "${cliente.fechaDeCreacion}" title = "Fecha de creación" columna = "fechaCreacion" > Creado en: ${ cliente.fechaDeCreacion }</div > `;
        tabla.innerHTML += `< div class="table-body-cell" tipo = "opcion" info = "" data = "${cliente.viable}" title = "¿Es viable?" columna = "viable" onclick = "setEditable(this)" onblur = "nonEditable(this)" > ${ (cliente.viable) ? "SI" : "NO" }</div > `;
        tabla.innerHTML += `< div class="table-body-cell" tipo = "texto" info = "" data = "${cliente.nombre}" title = "Nombre del cliente" columna = "nombre" onclick = "setEditable(this)" onblur = "nonEditable(this)" > ${ (cliente.nombre == null) ? "-" : cliente.nombre }</div > `;
        tabla.innerHTML += `< div class="table-body-cell" tipo = "texto" info = "" data = "${cliente.correo}" title = "Correo del cliente" columna = "correo" onclick = "setEditable(this)" onblur = "nonEditable(this)" > ${ (cliente.correo == null) ? "-" : cliente.correo }</div > `;
        tabla.innerHTML += `< div class="table-body-cell" tipo = "texto" info = "" data = "${cliente.celular}" title = "Celular del cliente" columna = "celular" onclick = "setEditable(this)" onblur = "nonEditable(this)" > ${ (cliente.celular == null) ? "-" : cliente.celular }</div > `;
        tabla.innerHTML += `< div class="table-body-cell" tipo = "texto" info = "" data = "${cliente.medioPublicitario}" title = "Medio publicitario" columna = "medioPublicitario" onclick = "setEditable(this)" onblur = "nonEditable(this)" > ${ (cliente.medioPublicitario == null) ? "-" : cliente.medioPublicitario }</div > `;
        tabla.innerHTML += `< div class="table-body-cell" tipo = "texto" info = "" data = "${cliente.zonaBusqueda}" title = "Zona de Búsqueda" columna = "zonaBusqueda" onclick = "setEditable(this)" onblur = "nonEditable(this)" > ${ (cliente.zonaBusqueda == null) ? "-" : cliente.zonaBusqueda }</div > `;
        tabla.innerHTML += `< div class="table-body-cell" tipo = "texto" info = "" data = "${cliente.proyectoDeInteres}" title = "Proyecto de Interés" columna = "proyectoDeInteres" onclick = "setEditable(this)" onblur = "nonEditable(this)" > ${ (cliente.proyectoDeInteres == null) ? "-" : cliente.proyectoDeInteres }</div > `;
        tabla.innerHTML += `< div class="table-body-cell" tipo = "opcion" info = "" data = "${cliente.gestionDesdeSalaDeVentas}" title = "¿Gestionado por S/V?" columna = "gestionDesdeSalaDeVentas" onclick = "setEditable(this)" onblur = "nonEditable(this)" > ${ (cliente.gestionDesdeSalaDeVentas) ? "SI" : "NO" }</div > `;
        tabla.innerHTML += `< div class="table-body-cell" tipo = "opcion" info = "" data = "${cliente.habeasData}" title = "Habeas Data" columna = "habeasData" onclick = "setEditable(this)" onblur = "nonEditable(this)" > ${ (cliente.habeasData) ? "SI" : "NO" }</div > `;
        tabla.innerHTML += `< div class="table-body-cell" tipo = "opcion" info = "" data = "${cliente.contactado}" title = "Contactado" columna = "contactado" onclick = "setEditable(this)" onblur = "nonEditable(this)" > ${ (cliente.contactado) ? "SI" : "NO" }</div > `;
        tabla.innerHTML += `< div class="table-body-cell" tipo = "opcion" info = "" data = "${cliente.contactoEfectivo}" title = "Contacto Efectivo" columna = "contactoEfectivo" onclick = "setEditable(this)" onblur = "nonEditable(this)" > ${ (cliente.contactoEfectivo) ? "SI" : "NO" }</div > `;
        tabla.innerHTML += `< div class="table-body-cell" tipo = "fecha" info = "" data = "${cliente.fechaDeContacto}" title = "Fecha de último contacto" columna = "fechaUltimoContacto" onclick = "setEditable(this)" onblur = "nonEditable(this)" > ${ (cliente.fechaDeContacto == null) ? "-/-/-" : cliente.fechaDeContacto }</div > `;
        tabla.innerHTML += `< div class="table-body-cell" tipo = "opcion" info = "" data = "${cliente.calificado}" title = "¿Contacto calificado?" columna = "calificado" onclick = "setEditable(this)" onblur = "nonEditable(this)" > ${ (cliente.calificado) ? "SI" : "NO" }</div > `;
        tabla.innerHTML += `< div class="table-body-cell" tipo = "opcion" info = "" data = "${cliente.proyectoCalificado}" title = "¿Califica para el proyecto?" columna = "proyectoCalificado" onclick = "setEditable(this)" onblur = "nonEditable(this)" > ${ (cliente.proyectoCalificado == null) ? "-" : cliente.proyectoCalificado }</div > `;
        tabla.innerHTML += `< div class="table-body-cell" tipo = "opcion" info = "" data = "${cliente.visitaAgendada}" title = "¿Agenda visita?" columna = "visitaAgendada" onclick = "setEditable(this)" onblur = "nonEditable(this)" > ${ (cliente.visitaAgendada) ? "SI" : "NO" }</div > `;
        tabla.innerHTML += `< div class="table-body-cell" tipo = "fecha" info = "" data = "${cliente.fechaVisitaAgendada}" title = "Fecha de visita" columna = "fechaVisita" onclick = "setEditable(this)" onblur = "nonEditable(this)" > ${ (cliente.fechaVisitaAgendada == null) ? "-/-/-" : cliente.fechaVisitaAgendada }</div > `;
        tabla.innerHTML += `< div class="table-body-cell" tipo = "opcion" info = "" data = "${cliente.visitaEfectiva}" title = "¿La visita fue efectiva?" columna = "visitaEfectiva" onclick = "setEditable(this)" onblur = "nonEditable(this)" > ${ (cliente.visitaEfectiva) ? "SI" : "NO" }</div > `;
        tabla.innerHTML += `< div class="table-body-cell" tipo = "estado" info = "" data = "${cliente.estado}" title = "Estado del cliente" columna = "estado" onclick = "setEditable(this)" onblur = "nonEditable(this)" > ${ (cliente.estado == null) ? "-" : cliente.estado }</div > `;
        tabla.innerHTML += `< div class="table-body-cell" tipo = "asesor" info = "" data = "${cliente.asignadoA}" title = "Cliente asignado a" columna = "asignadoA" onclick = "setEditable(this)" onblur = "nonEditable(this)" > ${ (cliente.asignadoA == null) ? "-" : cliente.asignadoA }</div > `;*/
    return tabla;
}

document.addEventListener('contextmenu', function (e) {
    var context_menu = document.getElementById("context-menu-deployable");
    context_menu.style.display = "block";
    context_menu.style.top = `${e.clientY} px`;
    context_menu.style.left = `${e.clientX} px`;
    context_menu.onblur = function () {
        context_menu.style.display = "none";
    }
    var element = document.elementFromPoint(e.clientX, e.clientY);
    context_menu.innerHTML += (element.getAttribute("info") == null) ? `No hay información` : element.getAttribute("info");
    e.preventDefault();
}, false);

function setEditable(el) {
    var element = el;
    var tipoElemento = element.getAttribute("tipo");
    switch (tipoElemento) {
        case "opcion":
            element.contentEditable = "true";
            element.getElementsByTagName("select")[0].onchange = function () {
                element.contentEditable = "false";
                getClienteFromDiv(element);
            };
            break;
        case "texto":
            element.contentEditable = "true";
            element.onblur = function () {
                element.contentEditable = "false";
                getClienteFromDiv(element);
            };
            break;
        case "fecha":
            element.contentEditable = "true";
            element.getElementsByTagName("input")[0].onchange = function () {
                element.contentEditable = "false";
                getClienteFromDiv(element, true);
            };
            break;
        default:
            element.contentEditable = "true";
            element.onblur = function () {
                element.contentEditable = "false";
                getClienteFromDiv(element);
            };
            break;
    }
}

function getClienteFromDiv(element, hasChanged) {
    var array = element.parentNode.getElementsByClassName("table-body-cell");
    let obj = {};
    for (var i = 0; i < array.length; i++) {
        if (array[i].getElementsByTagName("input").length > 0) {
            console.log(`locococo : ${array[i].getElementsByTagName("input")[0].value}`);
            obj[array[i].getAttribute("columna")] = `${(hasChanged) ? new Date(array[i].getElementsByTagName("input")[0].value) : null}`;
        } else if (array[i].getElementsByTagName("select").length > 0) {
            obj[array[i].getAttribute("columna")] = (array[i].getElementsByTagName("select")[0].value == "SI");
        } else {
            obj[array[i].getAttribute("columna")] = array[i].innerHTML;
        }
    }
    obj.codigoConteo = element.parentNode.id.split("-")[1];
    console.log(obj);
    actualizarCliente(obj);
    console.log(` ¡ Actualizado el cliente ${obj.codigoConteo} !${obj.nombre} `);
}

function nonEditable(element) {

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
                objeto.proyectoDeInteres = xlsx[i].PROYECTO;
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
                console.log(` ¡ Se insertaron ${array.length} clientes! `);
                console.log(`Respuesta servidor: ${response.respuesta} \n Estado de la solicitud: ${response.estado} \n Información solicitud: ${response.info} `);
            } else {
                response = JSON.parse(ajaxrequest.responseText);
                console.log(`Respuesta servidor: ${response.respuesta} \n Estado de la solicitud: ${response.estado} \n Información solicitud: ${response.info} `);
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
    var url = `clientes/${object.codigoConteo} `;
    ajaxrequest.open("PUT", url, true);
    ajaxrequest.onreadystatechange = function () {
        if (ajaxrequest.readyState == 4) {
            let response = {};
            if (ajaxrequest.status == 200) {
                //
                response = JSON.parse(ajaxrequest.responseText);
                console.log(`Respuesta servidor: ${response.respuesta} \n Estado de la solicitud: ${response.estado} \n Información solicitud: ${response.info} `);
            } else {
                response = JSON.parse(ajaxrequest.responseText);
                console.log(`Respuesta servidor: ${response.respuesta} \n Estado de la solicitud: ${response.estado} \n Información solicitud: ${response.info} `);
            }
        }
    };
    ajaxrequest.setRequestHeader("Content-Type", "application/json");
    ajaxrequest.send(JSON.stringify(object));
}