<%-- 
    Document   : 200
    Created on : 3/11/2021, 2:34:03 a. m.
    Author     : Juan Pablo - Roverin Technologics
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> ¡ Exito !</title>
    </head>
    <body>
        <h1>¡ la solicitud se ha procesado correctamente !</h1>
        <p>Detalles: <%= request.getAttribute("mensaje") %></p>
        <h6>Volver al inicio <a href="/index.html">Click aquí</a></h6>
    </body>
</html>
