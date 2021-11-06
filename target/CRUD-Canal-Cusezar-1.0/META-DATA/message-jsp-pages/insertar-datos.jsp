<%-- Document : insertar-datos Created on : 4/11/2021, 8:20:48 a. m. Author : Juan Pablo - Roverin Technologics --%>

    <%@page contentType="text/html" pageEncoding="UTF-8" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Insertar</title>
            <script>

                window.onload = function () {
                    document.getElementById("filename").addEventListener("change", function () {
                        document.getElementById("");
                    });
                };

                function insertarDatos() {

                    try {
                        //
                        var formData = new FormData(document.getElementById("formulario-insertar"));

                        //
                        var reader = new FileReader();
                        var file = document.getElementById("filename").files[0];
                        var fileType = file.type;
                        if (file != null) {
                            //if (file.type.split("/")[1].toUpperCase() !== formData.get("formato")) throw " ¡ El archivo debe estan en formato " + formData.get("formato") + " ! ";
                            reader.readAsText(file, "UTF-8");
                            reader.onload = function (evt) {
                                formData.append("datos", evt.target.result);
                                sendAjaxRequest(formData);
                            };
                            reader.onerror = function (evt) {
                                throw " ¡ Error en la creación de registros, intente nuevamente ! \n Detalles: " + evt;
                            };
                        } else {
                            throw " ¡ No se ha introducido texto ¡ ";
                        }
                    } catch (err) {
                        alert(err);
                    }
                }

                function sendAjaxRequest(data) {
                    try {

                        //
                        var params = "proceso=insertar&formato=" + data.get("formato") + "&encabezados=" + document.getElementById("encabezados").checked + "&datos=" + data.get("datos");
                        console.log("URL: " + params);

                        //       
                        var url = "datos";
                        const ajax_request = new XMLHttpRequest();
                        ajax_request.open("POST", url, true);

                        //
                        ajax_request.onreadystatechange = function () {
                            if (ajax_request.readyState == 4) {
                                //
                                var res = ajax_request.responseText;

                                //
                                let json = JSON.parse(res);
                                console.log("Respuesta recibida: " + json.respuesta);
                                document.getElementById("texto-respuesta").innerHTML = json.respuesta;
                            }
                        };

                        //
                        ajax_request.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
                        ajax_request.send(params);
                        console.log("Respuesta enviada");
                    } catch (err) {
                        alert(err);
                    }
                }

            </script>
        </head>

        <body>
            <h1>Insertar datos</h1>
            <div id="formulario">
                <form id="formulario-insertar" action="insertar-datos.jsp" method="POST" enctype="multipart/form-data">
                    <p>
                        Seleccione el tipo de archivo<select name="formato">
                            <option>HTML</option>
                            <option>CSV</option>
                            <option>JSON</option>
                            <option>XML</option>
                        </select>
                    </p>
                    <p>
                        ¿ Tiene encabezados ?<input id="encabezados" type="checkbox" name="encabezados" value="true" />
                    </p>
                    <p>
                        Subir Archivo<input id="filename" type="file" name="filename" />
                    </p>
                    <p>
                        <input type="button" value="Enviar" name="btn-enviar" onclick="insertarDatos()" />
                    </p>
                    <p>
                        <input id="input-text" type="text" name="datos" />
                    </p>
                </form>
            </div>
            <div>
                <p>
                    Subir Archivo (JAVA) <input id="filename" type="file" name="filename" />
                </p>
            </div>
            <div id="texto-respuesta"></div>
        </body>

        </html>