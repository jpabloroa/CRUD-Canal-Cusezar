/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cusezar.tools;

import com.cusezar.component.Cliente;
import com.cusezar.component.Constantes;
import com.cusezar.utils.DataBaseConnector;
import com.cusezar.utils.CSVReader;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
@WebServlet(name = "Controlador", urlPatterns = {"/r/datos"})
public class Controlad extends HttpServlet implements Constantes {

    

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//
        String proceso = (request.getParameter("proceso") == null) ? "-" : request.getParameter("proceso").toLowerCase();

        db = new DataBaseConnector(connectionPool);
        reader = new CSVReader();
        switch (proceso) {
            case CONTROLADOR.CONSULTA: {
                try {
                    //
                    String opcion = (request.getParameter("respuesta") == null) ? CONTENT.HTML : request.getParameter("respuesta").toLowerCase();

                    String sentencia = (request.getParameter("sentencia") == null) ? "" : request.getParameter("sentencia");
                    //
                    List<Cliente> listaClientes = db.getClientes(sentencia);

                    //
                    switch (opcion) {
                        //
                        case CONTENT.HTML:
                            //
                            request.setAttribute("LISTA-CLIENTES", listaClientes);

                            //
                            response.setContentType(CONTENT.HTML);

                            //
                            rd = request.getRequestDispatcher("/META-DATA/message-jsp-pages/vista-datos.jsp");
                            rd.forward(request, response);
                            break;

                        //
                        case CONTENT.JSON:
                            break;

                        //
                        case CONTENT.XML:
                            break;

                        //
                        case CONTENT.CSV:
                            break;

                        //
                        default:
                            send406(new Exception(" ¡ El formato de texto selecionado es inválido ! "), request, response);
                            break;
                    }
                } catch (IOException | ServletException | SQLException ex) {
                    send406(ex, request, response);
                }
            }
            break;
            case CONTROLADOR.INSERTAR: {
                try {
                    //
                    String opcion = (request.getParameter("formato") == null) ? "-" : request.getParameter("formato").toLowerCase();

                    //
                    switch (opcion) {
                        //
                        case CONTENT.HTML:

                            break;

                        //
                        case CONTENT.JSON:
                            break;

                        //
                        case CONTENT.XML:
                            break;

                        //
                        case CONTENT.CSV:
                            //
                            boolean headers = (boolean) ((request.getParameter("encabezados") == null) ? "false" : Boolean.valueOf(request.getParameter("encabezados")));

                            //
                            try {
                                db.addClientes(reader.getClienteFromCSV(request.getParameter("datos"), headers));
                                jsonResponse(response, RESPUESTA.HTTP_201);
                            } catch (Exception ex) {//
                                jsonResponse(response, ex.getMessage());
                            }

                        //
                        default:
                            jsonResponse(response, RESPUESTA.HTTP_406);
                            break;
                    }
                } catch (IOException ex) {
                    jsonResponse(response, ex.getMessage());
                }
            }
            break;

            default: {
                jsonResponse(response, RESPUESTA.HTTP_406 + " ... at --- " + proceso + "at resquest: " + request.getMethod() + request.getRequestURI() + " request URL - " + request.getQueryString() + "... length: " + request.getContentLength());
            }
            break;
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
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
        processRequest(request, response);
    }

    private void jsonResponse(HttpServletResponse response, String mensaje) throws IOException {
        out = response.getWriter();
        response.setContentType("application/json");
        Map<String, String> json = new LinkedHashMap();
        json.put("respuesta", mensaje);
        out.write(new Gson().toJson(json));
        out.flush();
    }

    private void send200(String mensaje, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("mensaje", mensaje);
        response.setStatus(200);
        rd = request.getRequestDispatcher("/META-DATA/message-jsp-pages/200.jsp");
        rd.forward(request, response);
    }

    private void send406(Exception ex, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("error", ex.getMessage());
        response.setStatus(406);
        rd = request.getRequestDispatcher("/META-DATA/message-jsp-pages/406.jsp");
        rd.forward(request, response);
    }
}
// </editor-fold>

