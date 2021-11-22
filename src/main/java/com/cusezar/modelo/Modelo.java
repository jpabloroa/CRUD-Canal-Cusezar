/**
 * NO MODIFIQUE ESTE CÓDIGO
 *
 * Licencia registrada Roverin Technologics - 2021
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
 * Clase para el manejo de conexiones, realización de altas y bajas en una base
 * de datos
 *
 * @author Juan Pablo - Roverin Technologics
 */
public class Modelo {

    /**
     * Clase columna
     *
     * Define el los nombre de las columnas
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
        public String diaUltimoContacto = "diaUltimoContacto";
        public String mesUltimoContacto = "mesUltimoContacto";
        public String agnoUltimoContacto = "agnoUltimoContacto";
        public String contactoEfectivo = "contactoEfectivo";
        public String proyectoCalificado = "proyectoCalificado";
        public String diaVisita = "diaVisita";
        public String mesVisita = "mesVisita";
        public String agnoVisita = "agnoVisita";
        public String visitaEfectiva = "visitaEfectiva";
        public String estado = "estado";
        public String asignadoA = "asignadoA";
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
            cliente.setDiaUltimoContacto(resultSet.getInt(this.columna.diaUltimoContacto));
            cliente.setMesUltimoContacto(resultSet.getInt(this.columna.mesUltimoContacto));
            cliente.setAgnoUltimoContacto(resultSet.getInt(this.columna.agnoUltimoContacto));
            cliente.setContactoEfectivo(resultSet.getBoolean("contactoEfectivo"));
            cliente.setProyectoCalificado(resultSet.getString("proyectoCalificado"));
            cliente.setDiaVisita(resultSet.getInt("diaVisita"));
            cliente.setMesVisita(resultSet.getInt("mesVisita"));
            cliente.setAgnoVisita(resultSet.getInt("agnoVisita"));
            cliente.setVisitaEfectiva(resultSet.getBoolean("visitaEfectiva"));
            cliente.setEstado(resultSet.getString("estado"));
            cliente.setAsignadoA(resultSet.getString(this.columna.asignadoA));

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
        if (Clientes == null) {
            throw new SQLException(" ¡ El objeto lista es nulo ! ");
        }

        //
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO clientes (");
        sql.append(columna.diaDeCreacion).append(",");
        sql.append(columna.mesDeCreacion).append(",");
        sql.append(columna.agnoDeCreacion).append(",");
        sql.append(columna.viable).append(",");
        sql.append(columna.nombre).append(",");
        sql.append(columna.correo).append(",");
        sql.append(columna.celular).append(",");
        sql.append(columna.medioPublicitario).append(",");
        sql.append(columna.zonaBusqueda).append(",");
        sql.append(columna.proyectoDeInteres).append(",");
        sql.append(columna.gestionDesdeSalaDeVentas).append(",");
        sql.append(columna.habeasData).append(",");
        sql.append(columna.diaUltimoContacto).append(",");
        sql.append(columna.mesUltimoContacto).append(",");
        sql.append(columna.agnoUltimoContacto).append(",");
        sql.append(columna.contactoEfectivo).append(",");
        sql.append(columna.proyectoCalificado).append(",");
        sql.append(columna.diaVisita).append(",");
        sql.append(columna.mesVisita).append(",");
        sql.append(columna.agnoVisita).append(",");
        sql.append(columna.visitaEfectiva).append(",");
        sql.append(columna.estado).append(",");
        sql.append(columna.asignadoA).append(")");
        sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?, ?, ?, ?)");
        statement = conection.prepareStatement(sql.toString());

        //
        for (Cliente cliente : Clientes) {
            int i = 1;
            statement.setInt(i++, cliente.getDiaDeCreacion());
            statement.setInt(i++, cliente.getMesDeCreacion());
            statement.setInt(i++, cliente.getAgnoDeCreacion());
            statement.setBoolean(i++, cliente.isViable());
            statement.setString(i++, cliente.getNombre());
            statement.setString(i++, (cliente.getCorreo() == null) ? null : cliente.getCorreo().trim());
            statement.setString(i++, (cliente.getCelular() == null) ? null : cliente.getCelular().trim());
            statement.setString(i++, cliente.getMedioPublicitario());
            statement.setString(i++, cliente.getZonaBusqueda());
            statement.setString(i++, cliente.getProyectoDeInteres());
            statement.setBoolean(i++, cliente.isGestionDesdeSalaDeVentas());
            statement.setBoolean(i++, cliente.isHabeasData());
            statement.setInt(i++, cliente.getDiaUltimoContacto());
            statement.setInt(i++, cliente.getMesUltimoContacto());
            statement.setInt(i++, cliente.getAgnoUltimoContacto());
            statement.setBoolean(i++, cliente.isContactoEfectivo());
            statement.setString(i++, cliente.getProyectoCalificado());
            statement.setInt(i++, cliente.getDiaVisita());
            statement.setInt(i++, cliente.getMesVisita());
            statement.setInt(i++, cliente.getAgnoVisita());
            statement.setBoolean(i++, cliente.isVisitaEfectiva());
            statement.setString(i++, cliente.getEstado());
            statement.setString(i++, cliente.getAsignadoA());
            statement.addBatch();
        }

        //
        statement.executeBatch();
    }

    /**
     * Método updateClientes()
     *
     * Actualizar un datos según el parámetro seleccionado
     *
     * @param column
     * @param valor
     * @param cliente
     * @return
     * @throws SQLException
     */
    public int updateClientes(String column, String valor, Cliente cliente) throws SQLException {

        //
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE clientes SET ");
        sql.append(columna.viable).append(" = ? ,");
        sql.append(columna.nombre).append(" = ? ,");
        sql.append(columna.correo).append(" = ? ,");
        sql.append(columna.celular).append(" = ? ,");
        sql.append(columna.medioPublicitario).append(" = ? ,");
        sql.append(columna.zonaBusqueda).append(" = ? ,");
        sql.append(columna.proyectoDeInteres).append(" = ? ,");
        sql.append(columna.gestionDesdeSalaDeVentas).append(" = ? ,");
        sql.append(columna.habeasData).append(" = ? ,");
        sql.append(columna.diaUltimoContacto).append(" = ? ,");
        sql.append(columna.mesUltimoContacto).append(" = ? ,");
        sql.append(columna.agnoUltimoContacto).append(" = ? ,");
        sql.append(columna.contactoEfectivo).append(" = ? ,");
        sql.append(columna.proyectoCalificado).append(" = ? ,");
        sql.append(columna.diaVisita).append(" = ? ,");
        sql.append(columna.mesVisita).append(" = ? ,");
        sql.append(columna.agnoVisita).append(" = ? ,");
        sql.append(columna.visitaEfectiva).append(" = ? ,");
        sql.append(columna.estado).append(" = ? ,");
        sql.append(columna.asignadoA).append(" = ? ");
        sql.append(" WHERE ");
        sql.append(("".equals(column)) ? this.columna.codigoConteo : column).append(" = ?");
        statement = conection.prepareStatement(sql.toString());
        int i = 1;
        statement.setBoolean(i++, cliente.isViable());
        statement.setString(i++, cliente.getNombre());
        statement.setString(i++, (cliente.getCorreo() == null) ? null : cliente.getCorreo().trim());
        statement.setString(i++, (cliente.getCelular() == null) ? null : cliente.getCelular().trim());
        statement.setString(i++, cliente.getMedioPublicitario());
        statement.setString(i++, cliente.getZonaBusqueda());
        statement.setString(i++, cliente.getProyectoDeInteres());
        statement.setBoolean(i++, cliente.isGestionDesdeSalaDeVentas());
        statement.setBoolean(i++, cliente.isHabeasData());
        statement.setInt(i++, cliente.getDiaUltimoContacto());
        statement.setInt(i++, cliente.getMesUltimoContacto());
        statement.setInt(i++, cliente.getAgnoUltimoContacto());
        statement.setBoolean(i++, cliente.isContactoEfectivo());
        statement.setString(i++, cliente.getProyectoCalificado());
        statement.setInt(i++, cliente.getDiaVisita());
        statement.setInt(i++, cliente.getMesVisita());
        statement.setInt(i++, cliente.getAgnoVisita());
        statement.setBoolean(i++, cliente.isVisitaEfectiva());
        statement.setString(i++, cliente.getEstado());
        statement.setString(i++, cliente.getAsignadoA());
        statement.setObject(i++, valor);
        //
        return statement.executeUpdate();
    }

    /**
     * Método deleteClientes()
     *
     * Elimina un datos según el parámetro seleccionado
     *
     * @param columna
     * @param valor
     * @return
     * @throws SQLException
     */
    public int deleteClientes(String columna, String valor) throws SQLException {

        //
        if ("".equals(valor) || null == valor) {
            throw new SQLException(" No hay datos ingresados ");
        }
        //
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM clientes WHERE ").append((("".equals(columna) || columna == null) ? this.columna.celular : columna)).append(" = ?");
        statement = conection.prepareStatement(sql.toString());
        statement.setString(1, valor);

        //
        return statement.executeUpdate();
    }

    /**
     * Método getQueryFromColumn()
     *
     * Devuelve combinaciones de formatos condicionales para consulta
     *
     * @param columna
     * @param valor
     * @return
     */
    public String getQueryFromColumn(String columna, String valor) {
        StringBuilder respuesta = new StringBuilder();
        String choice = "", extra = "";

        if (columna.contains("-")) {
            String[] auxArray = columna.split("-");
            choice = auxArray[0];
            extra = auxArray[1];
        } else {
            choice = columna;
        }
        String[] fecha;
        int dia, mes, agno;
        switch (choice) {
            case "fecha":
                fecha = valor.split("-");
                dia = (fecha.length < 1) ? java.util.Calendar.getInstance().get(java.util.Calendar.DAY_OF_MONTH) : Integer.parseInt(fecha[0]);
                mes = (fecha.length < 2) ? java.util.Calendar.getInstance().get(java.util.Calendar.MONTH) : Integer.parseInt(fecha[1]);
                agno = (fecha.length < 3) ? java.util.Calendar.getInstance().get(java.util.Calendar.YEAR) : Integer.parseInt(fecha[2]);
                respuesta.append(" WHERE ").append(this.columna.diaDeCreacion).append(" = ").append(dia).append(" AND ").append(this.columna.mesDeCreacion).append(" = ").append(mes).append(" AND ").append(this.columna.agnoDeCreacion).append(" = ").append(agno);
                return respuesta.toString();
            case "":
                respuesta.append(" WHERE ").append(this.columna.celular).append(" = ").append(valor);
                return respuesta.toString();
            case "dias":
                fecha = valor.split("-");
                dia = (fecha.length < 1) ? java.util.Calendar.getInstance().get(java.util.Calendar.DAY_OF_MONTH) : Integer.parseInt(fecha[0]);
                mes = (fecha.length < 2) ? java.util.Calendar.getInstance().get(java.util.Calendar.MONTH) : Integer.parseInt(fecha[1]);
                agno = (fecha.length < 3) ? java.util.Calendar.getInstance().get(java.util.Calendar.YEAR) : Integer.parseInt(fecha[2]);
                respuesta.append(" WHERE ").append(this.columna.diaUltimoContacto).append(" = ").append(dia).append(" AND ").append(this.columna.mesUltimoContacto).append(" = ").append(mes).append(" AND ").append(this.columna.agnoUltimoContacto).append(" = ").append(agno);
                return respuesta.toString();
        }
        return "";
    }

    /**
     * Método getColumns()
     *
     * Devuelve combinaciones de formatos condicionales para consulta
     *
     * @param columnas
     * @return
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
     * <code>Contructor</code>
     *
     * @param connectionPool
     * @throws java.sql.SQLException
     */
    public Modelo(DataSource connectionPool) throws SQLException {
        //
        this.connectionPool = connectionPool;
        //
        conection = this.connectionPool.getConnection();
    }

    /**
     * Definición de variables de clase
     */
    private DataSource connectionPool;
    private Connection conection;
    private PreparedStatement statement;
    private ResultSet resultSet;
    private final COLUMNA columna = new COLUMNA();
}
