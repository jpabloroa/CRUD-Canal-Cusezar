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
import java.sql.Timestamp;
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
        public String fechaDeCreacion = "fechaDeCreacion";
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
        public String fechaDeContacto = "fechaDeContacto";
        public String contactoEfectivo = "contactoEfectivo";
        public String fechaDeContactoEfectivo = "fechaDeContactoEfectivo";
        public String proyectoCalificado = "proyectoCalificado";
        public String fechaVisitaAgendada = "fechaVisitaAgendada";
        public String visitaEfectiva = "isitaEfectiva";
        public String fechaVisitaEfectiva = "fechaVisitaEfectiva";
        public String estado = "estado";
        public String fechaModificacionEstado = "fechaModificacionEstado";
        public String asignadoA = "asignadoA";
    }

    /**
     * Método getClientes()
     *
     * Genera una lista de clientes en base a una consulta
     *
     * @param sql
     * @param columna
     * @param valor
     * @param maxFilas
     * @return Clientes - Objeto tipo List
     * @throws SQLException
     */
    public List<Cliente> getClientes(String sql, String columna, String valor, int maxFilas) throws SQLException {

        //
        StringBuilder sqlCode = new StringBuilder();
        sqlCode.append(getQuery(sql)).append(getQueryFromColumn(columna, valor)).append(" LIMIT ").append(maxFilas);
        statement = conection.prepareStatement(sqlCode.toString());

        //
        resultSet = statement.executeQuery();

        //
        List<Cliente> lista = new ArrayList<>();
        while (resultSet.next()) {
            Cliente cliente = new Cliente();
            cliente.setFechaDeCreacion(resultSet.getTimestamp("fechaDeCreacion"));
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
            cliente.setFechaDeContacto(resultSet.getTimestamp("fechaDeContacto"));
            //cliente.setContactoEfectivo(resultSet.getBoolean("contactoEfectivo"));
            cliente.setFechaDeContactoEfectivo(resultSet.getTimestamp("fechaDeContactoEfectivo"));
            cliente.setProyectoCalificado(resultSet.getString("proyectoCalificado"));
            cliente.setFechaVisitaAdendada(resultSet.getTimestamp("fechaVisitaAgendada"));
            cliente.setFechaVisitaEfectiva(resultSet.getTimestamp("fechaVisitaEfectiva"));
            cliente.setEstado(resultSet.getString("estado"));
            cliente.setFechaModificacionEstado(resultSet.getTimestamp("fechaModificacionEstado"));
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
        sql.append(columna.fechaDeCreacion).append(",");
        sql.append(columna.viable).append(",");
        sql.append(columna.nombre).append(",");
        sql.append(columna.correo).append(",");
        sql.append(columna.celular).append(",");
        sql.append(columna.medioPublicitario).append(",");
        sql.append(columna.zonaBusqueda).append(",");
        sql.append(columna.proyectoDeInteres).append(",");
        sql.append(columna.gestionDesdeSalaDeVentas).append(",");
        sql.append(columna.habeasData).append(",");
        sql.append(columna.fechaDeContacto).append(",");
        //sql.append(columna.contactoEfectivo).append(",");
        sql.append(columna.fechaDeContactoEfectivo).append(",");
        sql.append(columna.proyectoCalificado).append(",");
        sql.append(columna.fechaVisitaAgendada).append(",");
        sql.append(columna.fechaVisitaEfectiva).append(",");
        sql.append(columna.estado).append(",");
        sql.append(columna.fechaModificacionEstado).append(",");
        sql.append(columna.asignadoA).append(")");
        sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        statement = conection.prepareStatement(sql.toString());

        //
        for (Cliente cliente : Clientes) {
            int i = 1;
            statement.setTimestamp(i++, new Timestamp(cliente.getFechaDeCreacion().getTime()));
            statement.setBoolean(i++, cliente.isViable());
            statement.setString(i++, cliente.getNombre());
            statement.setString(i++, (cliente.getCorreo() == null) ? null : cliente.getCorreo().trim());
            statement.setString(i++, (cliente.getCelular() == null) ? null : cliente.getCelular().trim());
            statement.setString(i++, (cliente.getMedioPublicitario() == null) ? null : cliente.getMedioPublicitario().toUpperCase());
            statement.setString(i++, (cliente.getZonaBusqueda() == null) ? null : cliente.getZonaBusqueda().toUpperCase());
            statement.setString(i++, (cliente.getProyectoDeInteres() == null) ? null : cliente.getProyectoDeInteres().toUpperCase());
            statement.setBoolean(i++, cliente.isGestionDesdeSalaDeVentas());
            statement.setBoolean(i++, cliente.isHabeasData());
            statement.setTimestamp(i++, (cliente.getFechaDeContacto() == null) ? null : new Timestamp(cliente.getFechaDeContacto().getTime()));
            //statement.setBoolean(i++, cliente.isContactoEfectivo());
            statement.setTimestamp(i++, (cliente.getFechaDeContactoEfectivo() == null) ? null : new Timestamp(cliente.getFechaDeContactoEfectivo().getTime()));
            statement.setString(i++, (cliente.getProyectoCalificado() == null) ? null : cliente.getProyectoCalificado().toUpperCase());
            statement.setTimestamp(i++, (cliente.getFechaVisitaAdendada() == null) ? null : new Timestamp(cliente.getFechaVisitaAdendada().getTime()));
            statement.setTimestamp(i++, (cliente.getFechaVisitaEfectiva() == null) ? null : new Timestamp(cliente.getFechaVisitaEfectiva().getTime()));
            statement.setString(i++, cliente.getEstado());
            statement.setTimestamp(i++, (cliente.getFechaModificacionEstado() == null) ? null : new Timestamp(cliente.getFechaModificacionEstado().getTime()));
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
     * @param parametro
     * @param valor
     * @return
     * @throws SQLException
     */
    public int updateClientes(String column, Object parametro, Object valor) throws SQLException {

        //
        StringBuilder sql = new StringBuilder();

        if (valor instanceof String) {

            //
            sql.append("UPDATE clientes SET ").append(column).append(" = ? WHERE ").append(this.columna.codigoConteo).append(" = ").append(parametro);
            statement = conection.prepareStatement(sql.toString());

            //
            statement.setObject(1, valor);

        } else {

            //
            Cliente cliente = (Cliente) valor;
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
            sql.append(columna.fechaDeContacto).append(" = ? ,");
            //sql.append(columna.contactoEfectivo).append(" = ? ,");
            sql.append(columna.fechaDeContactoEfectivo).append(" = ? ,");
            sql.append(columna.proyectoCalificado).append(" = ? ,");
            sql.append(columna.fechaVisitaAgendada).append(" = ? ,");
            sql.append(columna.fechaVisitaEfectiva).append(" = ? ,");
            sql.append(columna.estado).append(" = ? ,");
            sql.append(columna.fechaModificacionEstado).append(" = ? ,");
            sql.append(columna.asignadoA).append(" = ? ");
            sql.append(" WHERE ");
            sql.append(("".equals(column)) ? this.columna.codigoConteo : column).append(" = ?");
            statement = conection.prepareStatement(sql.toString());
            int i = 1;

            statement.setBoolean(i++, cliente.isViable());
            statement.setString(i++, cliente.getNombre());
            statement.setString(i++, (cliente.getCorreo() == null) ? null : cliente.getCorreo().trim());
            statement.setString(i++, (cliente.getCelular() == null) ? null : cliente.getCelular().trim());
            statement.setString(i++, (cliente.getMedioPublicitario() == null) ? null : cliente.getMedioPublicitario().toUpperCase());
            statement.setString(i++, (cliente.getZonaBusqueda() == null) ? null : cliente.getZonaBusqueda().toUpperCase());
            statement.setString(i++, (cliente.getProyectoDeInteres() == null) ? null : cliente.getProyectoDeInteres().toUpperCase());
            statement.setBoolean(i++, cliente.isGestionDesdeSalaDeVentas());
            statement.setBoolean(i++, cliente.isHabeasData());
            statement.setTimestamp(i++, (cliente.getFechaDeContacto() == null) ? null : new Timestamp(cliente.getFechaDeContacto().getTime()));
            //statement.setBoolean(i++, cliente.isContactoEfectivo());
            statement.setTimestamp(i++, (cliente.getFechaDeContactoEfectivo() == null) ? null : new Timestamp(cliente.getFechaDeContactoEfectivo().getTime()));
            statement.setString(i++, (cliente.getProyectoCalificado() == null) ? null : cliente.getProyectoCalificado().toUpperCase());
            statement.setTimestamp(i++, (cliente.getFechaVisitaAdendada() == null) ? null : new Timestamp(cliente.getFechaVisitaAdendada().getTime()));
            statement.setTimestamp(i++, (cliente.getFechaVisitaEfectiva() == null) ? null : new Timestamp(cliente.getFechaVisitaEfectiva().getTime()));
            statement.setString(i++, cliente.getEstado());
            statement.setTimestamp(i++, (cliente.getFechaModificacionEstado() == null) ? null : new Timestamp(cliente.getFechaModificacionEstado().getTime()));
            statement.setString(i++, cliente.getAsignadoA());
            statement.setObject(i++, parametro);
        }

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
            /*case "fecha":
                fecha = valor.split("-");
                dia = (fecha.length < 1) ? java.util.Calendar.getInstance().get(java.util.Calendar.DAY_OF_MONTH) : Integer.parseInt(fecha[0]);
                mes = (fecha.length < 2) ? java.util.Calendar.getInstance().get(java.util.Calendar.MONTH) : Integer.parseInt(fecha[1]);
                agno = (fecha.length < 3) ? java.util.Calendar.getInstance().get(java.util.Calendar.YEAR) : Integer.parseInt(fecha[2]);
                //respuesta.append(" WHERE ").append(this.columna.diaDeCreacion).append(" = ").append(dia).append(" AND ").append(this.columna.mesDeCreacion).append(" = ").append(mes).append(" AND ").append(this.columna.agnoDeCreacion).append(" = ").append(agno);
                return respuesta.toString();*/
            case "":
                respuesta.append(" WHERE ").append(this.columna.celular).append(" = ").append(valor);
                return respuesta.toString();
            /*case "dias":
                fecha = valor.split("-");
                dia = (fecha.length < 1) ? java.util.Calendar.getInstance().get(java.util.Calendar.DAY_OF_MONTH) : Integer.parseInt(fecha[0]);
                mes = (fecha.length < 2) ? java.util.Calendar.getInstance().get(java.util.Calendar.MONTH) : Integer.parseInt(fecha[1]);
                agno = (fecha.length < 3) ? java.util.Calendar.getInstance().get(java.util.Calendar.YEAR) : Integer.parseInt(fecha[2]);
                respuesta.append(" WHERE ").append(this.columna.diaUltimoContacto).append(" = ").append(dia).append(" AND ").append(this.columna.mesUltimoContacto).append(" = ").append(mes).append(" AND ").append(this.columna.agnoUltimoContacto).append(" = ").append(agno);
                return respuesta.toString();*/
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
    private String getQuery(String columnas) {
        StringBuilder respuesta = new StringBuilder();
        if (columnas == null || "".equals(columnas)) {
            respuesta.append("SELECT * FROM clientes");
            return respuesta.toString();
        } else if ("-".equals(columnas.substring(0, 1))) {
            String auxiliar = columnas.substring(1);
            switch (auxiliar) {
                case "":
                    respuesta.append("SELECT * FROM clientes");
                    return respuesta.toString();
                /*case "informe":
                    respuesta.append("SELECT * FROM clientes WHERE ").append(this.columna.mesDeCreacion).append(" = ").append("").append(" AND ").append(this.columna.agnoDeCreacion).append(" = ").append("").append(" AND ").append(this.columna.contactoEfectivo).append(" = ").append("").append(" AND ").append("").append(" = ").append("").append(" AND ").append("").append(" = ").append("").append(" AND ").append("").append(" = ").append("").append(" AND ");
                    return respuesta.toString();*/
                case "sin-gestion":
                    respuesta.append("SELECT * FROM clientes");
                    return respuesta.toString();
                case "hoy":
                default:
                    return columnas;
            }
        } else {
            return columnas;
        }
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
