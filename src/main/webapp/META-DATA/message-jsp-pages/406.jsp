<%-- 
    Document   : 406
    Created on : 3/11/2021, 2:30:03 a. m.
    Author     : Juan Pablo - Roverin Technologics
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> ¡ Error ¡</title>
    </head>
    <body>
        <h1>¡ Ha ocurrido algo inesperado !</h1>
        <p>Detalles: <%= request.getAttribute("error") %></p>
        <h6>Volver al inicio <a href="/index.html">Click aquí</a></h6>
    </body>
</html>
