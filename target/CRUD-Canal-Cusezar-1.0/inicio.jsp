<%-- 
    Document   : inicio
    Created on : 7/11/2021, 1:09:35 p. m.
    Author     : Juan Pablo - Roverin Technologics
--%>

<%@page import="java.util.List"%>
<%@page import="com.cusezar.modelo.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio</title>
    </head>
    <% List<Cliente> clientes = (List<Cliente>) request.getAttribute("LISTA-CLIENTES"); %>
    <body>
        <h1>Buenas buenas<h1>
        <table border="1">
            <thead>
                <tr>
                    <th>Fecha de creación</th>
                    <th>Viable</th>
                    <th>Nombre</th>
                    <th>Correo</th>
                    <th>Celular</th>
                    <th>Medio Publicitario</th>
                    <th>Zona Búsqueda</th>
                    <th>Proyecto de Interés</th>
                    <th>Gestión desde zero S/V</th>
                    <th>Habeas Data</th>
                    <th>Contacto</th>
                    <th>Contacto Efectivo</th>
                    <th>Fecha Último Contacto</th>
                    <th>Calificado</th>
                    <th>Proyecto Calificado</th>
                    <th>Visita agendada</th>
                    <th>Fecha visita</th>
                    <th>Visita Efectiva</th>
                    <th>Estado</th>
                </tr>
            </thead>
            <tbody>
                <% for (Cliente cliente : clientes) {%>
                <tr>
                    <td><%= new StringBuilder().append(((cliente.getDiaDeCreacion() == -1) ? "-" : cliente.getDiaDeCreacion())).append("/").append((cliente.getMesDeCreacion() == -1) ? "-" : cliente.getMesDeCreacion()).append("/").append((cliente.getAgnoDeCreacion() == -1) ? "-" : cliente.getAgnoDeCreacion()).toString()%></td>
                    <td><%= (cliente.isViable()) ? "SI" : "NO"%></td>
                    <td><%= (cliente.getNombre() == null) ? "-" : cliente.getNombre()%></td>
                    <td><%= (cliente.getCorreo() == null) ? "-" : cliente.getCorreo()%></td>
                    <td><%= (cliente.getCelular() == null) ? "-" : cliente.getCelular()%></td>
                    <td><%= (cliente.getMedioPublicitario() == null) ? "-" : cliente.getMedioPublicitario()%></td>
                    <td><%= (cliente.getZonaBusqueda() == null) ? "-" : cliente.getZonaBusqueda()%></td>
                    <td><%= (cliente.getProyectoDeInteres() == null) ? "-" : cliente.getProyectoDeInteres()%></td>
                    <td><%= (cliente.isGestionDesdeSalaDeVentas()) ? "SI" : "NO"%></td>
                    <td><%= (cliente.isHabeasData()) ? "SI" : "NO"%></td>
                    <td><%= (cliente.isContactado()) ? "SI" : "NO"%></td>
                    <td><%= (cliente.isContactoEfectivo()) ? "SI" : "NO"%></td>
                    <td><%= (cliente.getFechaUltimoContacto() == null) ? "-" : cliente.getFechaUltimoContacto()%></td>
                    <td><%= (cliente.isCalificado()) ? "SI" : "NO"%></td>
                    <td><%= (cliente.getProyectoCalificado() == null) ? "-" : cliente.getProyectoCalificado()%></td>
                    <td><%= (cliente.isVisitaAgendada()) ? "SI" : "NO"%></td>
                    <td><%= new StringBuilder().append((cliente.getDiaVisita() == -1) ? "-" : cliente.getDiaVisita()).append("/").append((cliente.getMesVisita() == -1 ? "-" : cliente.getMesVisita())).append("/").append((cliente.getAgnoVisita() == -1) ? "-" : cliente.getAgnoVisita()).toString()%></td>
                    <td><%= (cliente.isVisitaEfectiva()) ? "SI" : "NO"%></td>
                    <td><%= (cliente.getEstado() == null) ? "-" : cliente.getEstado()%></td>
                </tr>
                <%}%>
            </tbody>
        </table>

    </body>
</html>
