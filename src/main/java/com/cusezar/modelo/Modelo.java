/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cusezar.modelo;

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
public class Modelo {

    /**
     * Mensajes que arroja esta clase, Segun sea el caso
     */
    final String EXITO = "";
    final String FALLO = "";

    /**
     *
     */
    class COLUMNA {

        //Nombre de columnas
        public String diaDeCreacion = "diaDeCreacion";
        public String mesDeCreacion = "mesDeCreacion";
        public String agnoDeCreacion = "agnoDeCreacion";
        public String codigoConteo = "codigoConteo";
        public String viable = "viable";
        public String nombre = "nombre";
        public String correo = "correo";
        public String celular = "celular";
        public String medioPublicitario = "medioPublicitario";
        public String zonaBusqueda = "zonaBusqueda";
        public String proyectoDeInteres = "proyectoDeInteres";
        public String gestionDesdeSalaDeVentas = "gestionDesdeSalaDeVentas";
        public String habeasData = "habeasData";
        public String fechaUltimoContacto = "fechaUltimoContacto";
        public String contactoEfectivo = "contactoEfectivo";
        public String proyectoCalificado = "proyectoCalificado";
        public String diaVisita = "diaVisita";
        public String mesVisita = "mesVisita";
        public String agnoVisita = "agnoVisita";
        public String visitaEfectiva = "visitaEfectiva";
        public String estado = "estado";
    }

    /**
     *
     * @param connectionPool
     */
    public Modelo(DataSource connectionPool) {
        this.connectionPool = connectionPool;
    }

    /**
     * Método getClientes()
     *
     * Genera una lista de clientes en base a una consulta
     *
     * @param columnas
     * @param columna
     * @param valor
     * @param maxFilas
     * @return Clientes - Objeto tipo List
     * @throws SQLException
     */
    public List<Cliente> getClientes(String columnas, String columna, String valor, int maxFilas) throws SQLException {
        //
        conection = connectionPool.getConnection();

        //
        StringBuilder sql = new StringBuilder();
        sql.append(getColumns(columnas)).append(getQueryFromColumn(columna, valor)).append(" LIMIT ").append(maxFilas);
        statement = conection.prepareStatement(sql.toString());

        //
        resultSet = statement.executeQuery();

        //
        List<Cliente> lista = new ArrayList<>();
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

            //
            lista.add(cliente);
        }
        return lista;
    }

    /**
     * Método addClientes()
     *
     * Inserta una lista proporcionada en la base de datos
     *
     * @param Clientes - Objeto tipo List
     * @throws SQLException
     */
    public void addClientes(List<Cliente> Clientes) throws SQLException {
        //
        conection = connectionPool.getConnection();

        //
        StringBuilder sql = new StringBuilder();
//        sql.append("INSERT INTO clientes (`diaDeCreacion`,`mesDeCreacion`,`agnoDeCreacion`,`codigoConteo`,`viable`,")
//                .append("`nombre`,`correo`,`celular`,`medioPublicitario`,`zonaBusqueda`,`proyectoDeInteres`,")
//                .append("`gestionDesdeSalaDeVentas`,`habeasData`,`fechaUltimoContacto`,`contactoEfectivo`,")
//                .append("`proyectoCalificado`,diaVisita`,`mesVisita`,`agnoVisita`,`visitaEfectiva`,`estado`)")
//                

        sql.append("INSERT INTO clientes (");
        sql.append(columna.diaDeCreacion).append(",");
        sql.append(columna.mesDeCreacion).append(",");
        sql.append(columna.agnoDeCreacion).append(",");
        sql.append(columna.codigoConteo).append(",");
        sql.append(columna.viable).append(",");
        sql.append(columna.nombre).append(",");
        sql.append(columna.correo).append(",");
        sql.append(columna.celular).append(",");
        sql.append(columna.medioPublicitario).append(",");
        sql.append(columna.zonaBusqueda).append(",");
        sql.append(columna.proyectoDeInteres).append(",");
        sql.append(columna.gestionDesdeSalaDeVentas).append(",");
        sql.append(columna.habeasData).append(",");
        sql.append(columna.fechaUltimoContacto).append(",");
        sql.append(columna.contactoEfectivo).append(",");
        sql.append(columna.proyectoCalificado).append(",");
        sql.append(columna.diaVisita).append(",");
        sql.append(columna.mesVisita).append(",");
        sql.append(columna.agnoVisita).append(",");
        sql.append(columna.visitaEfectiva).append(",");
        sql.append(columna.estado).append(")");
        sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?, ?)");
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

        //
        statement.executeBatch();
    }

    /**
     *
     */
    private String getQueryFromColumn(String columna, String valor) {
        StringBuilder respuesta = new StringBuilder();
        switch (columna) {
            case "fecha":
                String[] fecha = valor.split("-");
                int dia = (fecha.length < 1) ? java.util.Calendar.getInstance().get(java.util.Calendar.DAY_OF_MONTH) : Integer.parseInt(fecha[0]);
                int mes = (fecha.length < 2) ? java.util.Calendar.getInstance().get(java.util.Calendar.MONTH) : Integer.parseInt(fecha[1]);
                int agno = (fecha.length < 3) ? java.util.Calendar.getInstance().get(java.util.Calendar.YEAR) : Integer.parseInt(fecha[2]);
                respuesta.append(" WHERE ").append(this.columna.diaDeCreacion).append(" = ").append(dia).append(" AND ").append(this.columna.mesDeCreacion).append(" = ").append(mes).append(" AND ").append(this.columna.agnoDeCreacion).append(" = ").append(agno);
                return respuesta.toString();
            case "":
                respuesta.append(" WHERE ").append(this.columna.celular).append(" = ").append(valor);
                return respuesta.toString();
            case "gestionado":
                respuesta.append(" WHERE ").append(this.columna.fechaUltimoContacto).append("<>''");
                return respuesta.toString();
        }
        return "";
    }

    /**
     *
     */
    private String getColumns(String columnas) {
        StringBuilder respuesta = new StringBuilder();
        switch (columnas) {
            case "":
                respuesta.append("SELECT * FROM clientes");
                return respuesta.toString();
            case "-informe":
                respuesta.append("SELECT * FROM clientes WHERE ").append(this.columna.mesDeCreacion).append(" = ").append("").append(" AND ").append(this.columna.agnoDeCreacion).append(" = ").append("").append(" AND ").append(this.columna.contactoEfectivo).append(" = ").append("").append(" AND ").append("").append(" = ").append("").append(" AND ").append("").append(" = ").append("").append(" AND ").append("").append(" = ").append("").append(" AND ");
                return respuesta.toString();
        }
        return "";
    }

    /**
     * Definición de variables de clase
     */
    private DataSource connectionPool;
    private Connection conection;
    private PreparedStatement statement;
    private ResultSet resultSet;
    private COLUMNA columna = new COLUMNA();
}
