/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cusezar.controlador;

import com.cusezar.modelo.Cliente;
import com.cusezar.modelo.Modelo;
import com.cusezar.tools.CSVReader;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author Juan Pablo - Roverin Technologics
 */
@WebServlet(name = "Servidor", urlPatterns = {"/clientes/*"})
public class Servidor extends HttpServlet {

    /**
     * Mesnaje que arroja la clase
     */
    public class RESPUESTA {

        //
        public static final String HTTP_200 = " ¡ OK ! ";
        public static final String HTTP_201 = " ¡ El recurso ha sido creado exitosamente ! ";
        public static final String HTTP_406 = " ¡ Error de solicitud HTTP, verifique la sintaxis de la solicitud ! ";
    }

    //-------- Controlador
    public class CONTROLADOR {

        //
        public static final String INICIO = "inicio";
        public static final String CONSULTA = "ver";
        public static final String INSERTAR = "insertar";
    }

    //-------- Contenido
    public class CONTENT {

        //
        public static final String HTML = "html";
        public static final String JSON = "json";
        public static final String XML = "xml";
        public static final String CSV = "csv";
    }

    //-------- Mensaje
    class Mensaje {

        //
        public Mensaje(String respuesta, int estado, Object datos) {
            this.respuesta = respuesta;
            this.estado = estado;
            this.datos = datos;
        }

        //
        private String respuesta;
        private int estado;
        private Object datos;
    }

    /**
     * Métodos
     */
    /**
     *
     * @param response
     * @param mensaje
     * @throws IOException
     */
    private void jsonResponse(HttpServletResponse response, List<Cliente> listaClientes) throws IOException {
        printer = response.getWriter();
        response.setContentType("application/json");
        printer.write(new Gson().toJson(new Mensaje(" ¡ Proceso realizado exitosamente ! ", response.getStatus(), listaClientes)));
        printer.flush();
    }

    /**
     *
     * @param ex
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void sendError(Exception ex, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        printer = response.getWriter();
        response.setContentType("application/json");
        printer.write(new Gson().toJson(new Mensaje(new StringBuilder().append(" ¡ Error al ejecutar la solicitud ! ").append(request.getMethod()).append(" en el recurso ").append(request.getPathInfo()).append(" ! - Detalles : ").append(ex.getMessage()).toString(), response.getStatus(), "")));
        printer.flush();
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //
        String path = (request.getPathInfo() == null || request.getPathInfo().length() < 5) ? "ver/" : request.getPathInfo().substring(1);
        String[] paths = path.split("/");
        int arraySize = paths.length;

        //
        switch (paths[0]) {
            case CONTROLADOR.INICIO: {
                try {
                    //
                    modelo = new Modelo(conexion);
                    reader = new CSVReader();

                    //
                    String columna = (arraySize > 1) ? paths[1] : "!¡";
                    String valor = (arraySize > 2 || !"!¡".equals(columna)) ? paths[2] : columna;
                    String formato = (request.getParameter("formato") == null) ? CONTENT.JSON : request.getParameter("formato").toLowerCase();
                    String columnas = (request.getParameter("columnas") == null) ? "" : request.getParameter("columnas");
                    int maxFilas = (request.getParameter("maxFilas") == null) ? 100 : Integer.parseInt(request.getParameter("maxFilas"));
                    dispatcher = request.getRequestDispatcher("/inicio.jsp");
                    request.setAttribute("LISTA-CLIENTES", modelo.getClientes(columnas, columna, valor, maxFilas));
                    dispatcher.forward(request, response);
                } catch (SQLException ex) {
                    sendError(ex, request, response);
                }
            }
            break;
            case CONTROLADOR.CONSULTA: {
                try {
                    //
                    modelo = new Modelo(conexion);
                    reader = new CSVReader();

                    //
                    String columna = (arraySize > 1) ? paths[1] : "!¡";
                    String valor = (arraySize > 2 || !"!¡".equals(columna)) ? paths[2] : columna;
                    String formato = (request.getParameter("formato") == null) ? CONTENT.JSON : request.getParameter("formato").toLowerCase();
                    String columnas = (request.getParameter("columnas") == null) ? "" : request.getParameter("columnas");
                    int maxFilas = (request.getParameter("maxFilas") == null) ? 100 : Integer.parseInt(request.getParameter("maxFilas"));
                    jsonResponse(response, modelo.getClientes(columnas, columna, valor, maxFilas));
                } catch (SQLException ex) {
                    sendError(ex, request, response);
                }
            }
            break;

            default:
                break;
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Definición de variables de clase
     */
    @Resource(name = "jdbc/BaseCanalDigital")
    private DataSource conexion;
    private Modelo modelo;
    private RequestDispatcher dispatcher;
    private PrintWriter printer;
    private CSVReader reader;

}
