<%-- 
    Document   : index
    Created on : 2/11/2021, 11:27:33 a. m.
    Author     : Juan Pablo - Roverin Technologics
--%>

<%@ page import="java.util.List, com.cusezar.component.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% pageContext.setAttribute("clientes", (List<Cliente>) request.getAttribute("LISTA-CLIENTES"));
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>

    <body>
        Página Index
        <table border="1">
            <thead>
                <tr>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="cliente" items="${clientes}">
                <tr>
                    <td>${cliente.diaDeCreacion}</td>
                    <td>${cliente.mesDeCreacion}</td>
                    <td>${cliente.agnoDeCreacion}</td>
                    <td>${cliente.nombre}</td>
                    <td>${cliente.Celular}</td>
                    <td>${cliente.Correo}</td>
                    <td>${cliente.medioPublicitario}</td>
                </tr>
                </c:forEach>
            </tbody>
        </table>


    </body>
</html>
