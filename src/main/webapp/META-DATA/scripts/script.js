


function filtro() {

}

function nav_button(action) {
    var accion = action.toLowerCase();
    switch (accion) {
        case "insertar":
            insertar();
            break;
        case "whatsapp":
            break;
        case "correo":
            break;
    }
}



function buscarPorColumna() {
    var formData, column, filter, table, tr, td, i, txtValue;
    formData = new FormData(document.getElementById("formulario-buscar"));
    switch (formData.get("columna")) {
        case "Nombre":
            column = 2;
            break;
        case "Correo":
            column = 3;
            break;
        case "Celular":
            column = 4;
            break;
        case "Fecha de visita":
            column = 16;
            break;
    }
    console.log(column);
    filter = formData.get("valor").toUpperCase();
    table = document.getElementById("tabla-clientes");
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[column];
        if (td) {
            txtValue = td.textContent || td.innerText;
            console.log(txtValue);
            var letter = txtValue.toUpperCase().indexOf(filter);
            console.log(letter);
            if (letter > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}


function insertar(tipo) {
    switch (tipo.toLowerCase()) {
        case "csv":

            break;
        case "json":
            break;
        case "":
            break;
    }
}

