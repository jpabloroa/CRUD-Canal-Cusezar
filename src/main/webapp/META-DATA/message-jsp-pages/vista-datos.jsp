<%-- 
    Document   : vista-datos
    Created on : 3/11/2021, 1:46:42 p. m.
    Author     : Juan Pablo - Roverin Technologics
--%>
<%@ page import="java.util.List, com.cusezar.component.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <% List<Cliente> clientes = (List<Cliente>) request.getAttribute("LISTA-CLIENTES");%>
    <body>

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
                <% for (Cliente cliente : clientes) {%>
                <tr>
                    <td><%= cliente.getNombre()%></td>
                    <td><%= cliente.getCelular()%></td>
                    <td><%= cliente.getCorreo()%></td>
                    <td><%= cliente.getMesDeCreacion() %></td>
                </tr>
                <%}%>
            </tbody>
        </table>


    </body>
</html>