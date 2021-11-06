/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cusezar.utils;

import com.cusezar.component.Cliente;
import com.cusezar.component.Constantes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author Juan Pablo - Roverin Technologics
 */
public class DataBaseConnector implements Constantes{

    //------- Definir el DataSource
    private DataSource connectionPool;
    private Connection conection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    //------- Constructor
    public DataBaseConnector(DataSource connectionPool) {
        this.connectionPool = connectionPool;
    }

    //------- Métodos para la conexión a BBDD
    public List<Cliente> getClientes(String sentencia) throws SQLException {
        conection = connectionPool.getConnection();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM clientes ").append(generadorSQL(sentencia));
        statement = conection.prepareStatement(sql.toString());
        resultSet = statement.executeQuery();
        List<Cliente> lista = new ArrayList<>();
        if (resultSet == null) {
            throw new SQLException(ERROR.CONSULTA);
        }
        while (resultSet.next()) {
            Cliente cliente = new Cliente();
            cliente.setDiaDeCreacion(resultSet.getInt("diaDeCreacion"));
            cliente.setMesDeCreacion(resultSet.getInt("mesDeCreacion"));
            cliente.setAgnoDeCreacion(resultSet.getInt("agnoDeCreacion"));
            cliente.setNombre(resultSet.getString("nombre"));
            cliente.setCorreo(resultSet.getString("correo"));
            cliente.setCelular(resultSet.getString("celular"));
            cliente.setMedioPublicitario(resultSet.getString("medioPublicitario"));
            cliente.setCodigoConteo(resultSet.getInt("codigoConteo"));
            cliente.setViable(resultSet.getBoolean("viable"));
            cliente.setZonaBusqueda(resultSet.getString("zonaBusqueda"));
            cliente.setProyectoDeInteres(resultSet.getString("proyectoDeInteres"));
            cliente.setGestionDesdeSalaDeVentas(resultSet.getBoolean("gestionDesdeSalaDeVentas"));
            cliente.setHabeasData(resultSet.getBoolean("habeasData"));
            cliente.setFechaUltimoContacto(resultSet.getString("fechaUltimoContacto"));
            cliente.setContactoEfectivo(resultSet.getBoolean("contactoEfectivo"));
            cliente.setProyectoCalificado(resultSet.getString("proyectoCalificado"));
            cliente.setDiaVisita(resultSet.getInt("diaVisita"));
            cliente.setMesVisita(resultSet.getInt("mesVisita"));
            cliente.setAgnoVisita(resultSet.getInt("agnoVisita"));
            cliente.setVisitaEfectiva(resultSet.getBoolean("visitaEfectiva"));
            cliente.setEstado(resultSet.getString("estado"));
            lista.add(cliente);
        }
        return lista;
    }

    public void addClientes(List<Cliente> Clientes) throws SQLException {
        if (Clientes == null) {
            throw new SQLException(ERROR.LISTA_VACIA);
        }
        conection = connectionPool.getConnection();
        int anchoLote = Clientes.size();
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO clientes (")
                .append("        `diaDeCreacion`,")
                .append("        `mesDeCreacion`,")
                .append("        `agnoDeCreacion`,")
                .append("        `codigoConteo`,")
                .append("        `viable`,")
                .append("        `nombre`,")
                .append("        `correo`,")
                .append("        `celular`,")
                .append("        `medioPublicitario`,")
                .append("        `zonaBusqueda`,")
                .append("        `proyectoDeInteres`,")
                .append("        `gestionDesdeSalaDeVentas`,")
                .append("        `habeasData`,")
                .append("        `fechaUltimoContacto`,")
                .append("        `contactoEfectivo`,")
                .append("        `proyectoCalificado`,")
                .append("        `diaVisita`,")
                .append("        `mesVisita`,")
                .append("        `agnoVisita`,")
                .append("        `visitaEfectiva`,")
                .append("        `estado`)")
                .append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?, ?)");
        statement = conection.prepareStatement(sql.toString());
        for (Cliente cliente : Clientes) {
            statement.setInt(1, cliente.getDiaDeCreacion());
            statement.setInt(2, cliente.getMesDeCreacion());
            statement.setInt(3, cliente.getAgnoDeCreacion());
            statement.setString(4, null);
            statement.setBoolean(5, cliente.isViable());
            statement.setString(6, cliente.getNombre());
            statement.setString(7, cliente.getCorreo());
            statement.setString(8, cliente.getCelular());
            statement.setString(9, cliente.getMedioPublicitario());
            statement.setString(10, cliente.getZonaBusqueda());
            statement.setString(11, cliente.getProyectoDeInteres());
            statement.setBoolean(12, cliente.isGestionDesdeSalaDeVentas());
            statement.setBoolean(13, cliente.isHabeasData());
            statement.setString(14, cliente.getFechaUltimoContacto());
            statement.setBoolean(15, cliente.isContactoEfectivo());
            statement.setString(16, cliente.getProyectoCalificado());
            statement.setInt(17, cliente.getDiaVisita());
            statement.setInt(18, cliente.getMesVisita());
            statement.setInt(19, cliente.getAgnoVisita());
            statement.setBoolean(20, cliente.isVisitaEfectiva());
            statement.setString(21, cliente.getEstado());
            statement.addBatch();
        }
        statement.executeBatch();
    }

    private String generadorSQL(String sentencia) {
        StringBuilder respuesta = new StringBuilder();
        String str = (sentencia == null) ? "-" : sentencia;
        switch (str) {
            case "":
                respuesta.append(" WHERE ");
                respuesta.append(str);
                return respuesta.toString();
            default:
                return "";
        }
    }
}
