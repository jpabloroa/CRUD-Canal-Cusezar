/**
 * NO MODIFIQUE ESTE CÓDIGO
 *
 * Licencia registrada Roverin Technologics - 2021
 */
package com.cusezar.controlador;

import com.cusezar.modelo.Cliente;
import com.cusezar.modelo.Modelo;
import com.cusezar.tools.CSVReader;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
     * Método <code>GET</code>
     *
     * Recibe como parámentros un objeto request y response Sintaxis:
     *
     * -> Consulta: clientes/?formato=${formato}&sql=${sql}&maxFilas=${maxFilas}
     * formato: JSON, HTML
     *
     * -> Consulta:
     * clientes/ver/?formato=${formato}&sql=${sql}&maxFilas=${maxFilas} formato:
     * JSON, HTML
     *
     * -> Consulta:
     * clientes/ver/${columna}/${parámetro}?formato=${formato}&columnas=${columnas}&maxFilas=${maxFilas}
     * formato: JSON, HTML
     *
     * -> Insertar: clientes/insertar/?datos=${datos}?formato=${formato}
     * formato: JSON, CSV
     *
     * -> Actualizar:
     * clientes/actualizar/${parámetro}?formato=${formato}&columna=${columna}&datos=${datos}
     * formato: JSON, CSV
     *
     * -> Eliminar: clientes/${parámetro}?columna=${columna}
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
        try {
            //
            String path = (request.getPathInfo() == null || request.getPathInfo().length() < 5) ? "ver/" : request.getPathInfo().substring(1);
            String[] paths = path.split("/");
            int arraySize = paths.length;

            //
            switch (paths[0]) {
                case CONTROLADOR.CONSULTA: {
                    try {
                        //
                        modelo = new Modelo(conexion);

                        //
                        String columna = (arraySize > 1) ? paths[1] : "!¡";
                        String valor = (arraySize > 2 || !"!¡".equals(columna)) ? paths[2] : columna;
                        String formato = (request.getParameter("formato") == null) ? CONTENT.JSON : request.getParameter("formato").toLowerCase();
                        String sql = (request.getParameter("sql") == null) ? "" : request.getParameter("sql");
                        int maxFilas = (request.getParameter("maxFilas") == null) ? 100 : Integer.parseInt(request.getParameter("maxFilas"));
                        List<Cliente> listaRespuesta = modelo.getClientes(sql, columna, valor, maxFilas);
                        switch (formato) {
                            case CONTENT.JSON:
                                jsonResponse(response, listaRespuesta, new StringBuilder().append("Se proyectan ").append(listaRespuesta.size()).append(" clientes").toString());
                                break;
                            case CONTENT.HTML:
                                dispatcher = request.getRequestDispatcher("/index.html");
                                dispatcher.forward(request, response);
                                break;
                            default:
                                throw new JsonSyntaxException(" Especifique un formato de envío válido ");
                        }
                    } catch (IOException | NumberFormatException | SQLException ex) {
                        sendError(ex, request, response);
                    }
                }
                break;
                case CONTROLADOR.INSERTAR: {
                    request.setAttribute(CONTROLADOR.INSERTAR, request.getParameter("datos"));
                    doPost(request, response);
                }
                break;
                case CONTROLADOR.ACTUALIZAR: {
                    request.setAttribute(CONTROLADOR.ACTUALIZAR, (arraySize > 1) ? paths[1] : null);
                    request.setAttribute("formato", request.getParameter("formato"));
                    doPut(request, response);
                }
                break;
                case CONTROLADOR.ELIMINAR: {
                    request.setAttribute(CONTROLADOR.ELIMINAR, (arraySize > 1) ? paths[1] : null);
                    doDelete(request, response);
                }
                break;
                default:
                    throw new JsonSyntaxException(" Especifique un formato de envío válido ");
            }
        } catch (IOException | ServletException ex) {
            sendError(ex, request, response);
        }
    }

    /**
     * Método <code>POST</code>
     *
     * Recibe como parámentros un objeto request y response Sintaxis:
     *
     * clientes/${datos} formato: JSON, CSV
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //
        try {
            //
            modelo = new Modelo(conexion);
            reader = new CSVReader();

            //
            List<Cliente> listaRespuesta = new ArrayList<>();
            String formato = "";
            Cliente clienteRecibido = null;

            //
            if (request.getAttribute(CONTROLADOR.INSERTAR) != null) {

                //
                formato = (request.getParameter("formato") == null) ? CONTENT.CSV : ((String) request.getParameter("formato")).toLowerCase();

                //
                switch (formato) {
                    case CONTENT.JSON:
                        //
                        clienteRecibido = new Gson().fromJson((String) request.getAttribute(CONTROLADOR.INSERTAR), Cliente.class);
                        break;
                    case CONTENT.CSV:
                        //
                        clienteRecibido = reader.getClienteFromCSV((String) request.getAttribute(CONTROLADOR.INSERTAR), false).get(0);
                        break;
                    default:
                        throw new JsonSyntaxException(" Especifique un formato de envío válido ");
                }
                listaRespuesta.add(clienteRecibido);
            } else {

                //
                formato = (request.getParameter("formato") == null) ? CONTENT.JSON : request.getParameter("formato").toLowerCase();
                //
                switch (formato) {
                    case CONTENT.CSV:
                        //
                        listaRespuesta = reader.getClienteFromCSV((request.getParameter("datos") == null) ? "" : request.getParameter("datos"), Boolean.getBoolean((request.getParameter("encabezados") == null) ? "true" : request.getParameter("encabezados")));
                        break;
                    case CONTENT.JSON:
                        //
                        Mensaje mensajeRecibido = new Gson().fromJson(new InputStreamReader(request.getInputStream()), Mensaje.class);
                        listaRespuesta = (List<Cliente>) mensajeRecibido.datos;
                        break;
                    default:
                        throw new JsonSyntaxException(" Especifique un formato de envío válido ");
                }
            }

            //
            modelo.addClientes(listaRespuesta);
            jsonResponse(response, listaRespuesta, new StringBuilder().append("Se insertaron ").append(listaRespuesta.size()).append(" nuevos clientes").toString());
        } catch (JsonSyntaxException | IOException | SQLException ex) {
            sendError(ex, request, response);
        }
    }

    /**
     * Método <code>PUT</code>
     *
     * Recibe como parámentros un objeto request y response Sintaxis:
     *
     * clientes/${parámetro}?${datos} formato: JSON, CSV
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //
        try {
            //
            modelo = new Modelo(conexion);
            reader = new CSVReader();

            //
            String path = (request.getPathInfo().length() > 1) ? request.getPathInfo().substring(1) : "-";

            //
            List<Cliente> listaRespuesta = new ArrayList<>();
            Cliente cliente = (request.getAttribute(CONTROLADOR.ACTUALIZAR) != null) ? new Gson().fromJson((String) request.getAttribute(CONTROLADOR.ACTUALIZAR), Cliente.class) : new Gson().fromJson(new InputStreamReader(request.getInputStream()), com.cusezar.modelo.Cliente.class);
            String valor = ("-".equals(path)) ? cliente.getCelular() : path;
            String columna = (request.getParameter("columna") == null) ? "" : request.getParameter("columna");
            String formato = (request.getParameter("formato") == null) ? CONTENT.JSON : request.getParameter("formato").toLowerCase();
            int listaSize = 0;

            //
            switch (formato) {
                case CONTENT.JSON:
                    //
                    listaSize = modelo.updateClientes(columna, valor, cliente);
                    listaRespuesta.add(cliente);
                    jsonResponse(response, listaRespuesta, "perra");
                    break;
                default:
                    throw new JsonSyntaxException(" Especifique un formato de envío válido ");
            }

            //
            jsonResponse(response, listaRespuesta, (listaSize == 0) ? new StringBuilder().append(" No se han efectuado cambios ").toString() : new StringBuilder().append("Se afectó(aron) ").append(listaSize).append(" cliente(s)").toString());
        } catch (JsonSyntaxException | IOException | SQLException ex) {
            sendError(ex, request, response);
        }
    }

    /**
     * Método <code>DELETE</code>
     *
     * Recibe como parámentros un objeto request y response Sintaxis:
     *
     * clientes/${parámetro}?columna=${columna}
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //
        try {
            //
            modelo = new Modelo(conexion);

            //
            List<Cliente> listaRespuesta = new ArrayList<>();
            String path = (request.getPathInfo().length() > 1) ? request.getPathInfo().substring(1) : "-";

            //
            String valor = ("-".equals(path)) ? "" : path;
            String columna = (request.getParameter("columna") == null) ? "" : request.getParameter("columna");

            //
            int listaSize = modelo.deleteClientes(columna, valor);

            //
            jsonResponse(response, listaRespuesta, (listaSize == 0) ? new StringBuilder().append(" No se han efectuado cambios ").toString() : new StringBuilder().append("Se afectó(aron) ").append(listaSize).append(" cliente(s)").toString());
        } catch (JsonSyntaxException | IOException | SQLException ex) {
            sendError(ex, request, response);

        }
    }

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
        public static final String ACTUALIZAR = "actualizar";
        public static final String ELIMINAR = "eliminar";
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
    public class Mensaje {

        //
        public Mensaje(String respuesta, int estado, List<Cliente> datos, String info) {
            this.respuesta = respuesta;
            this.estado = estado;
            this.datos = datos;
            this.info = info;
        }

        //
        private String respuesta;
        private int estado;
        private String formato;
        private List<Cliente> datos;
        private String info;
    }

    /**
     * Métodos
     */
    /**
     * Método JsonResponse
     *
     * @param response
     * @param mensaje
     * @throws IOException
     */
    private void jsonResponse(HttpServletResponse response, List<Cliente> listaClientes, String info) throws IOException {
        printer = response.getWriter();
        response.setContentType("application/json");
        printer.write(new Gson().toJson(new Mensaje(" ¡ Proceso realizado exitosamente ! ", response.getStatus(), listaClientes, info)));
        printer.flush();
    }

    /**
     * Método sendError
     *
     * @param ex
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void sendError(Exception ex, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        StringBuilder resource = new StringBuilder();
        resource.append(br.readLine());
        printer = response.getWriter();
        response.setContentType("application/json");
        printer.write(new Gson().toJson(new Mensaje(new StringBuilder().append(" ¡ Error al ejecutar la solicitud ").append(request.getMethod()).append(" en el recurso ").append(request.getPathInfo()).append(" con la URI ").append(resource.toString()).append(" ! ").toString(), response.getStatus(), new ArrayList<Cliente>(), ex.getMessage())));
        printer.flush();
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
