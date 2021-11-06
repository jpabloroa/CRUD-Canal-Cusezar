/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cusezar.controlador;

import com.cusezar.component.Cliente;
import com.cusezar.modelo.Modelo;
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
@WebServlet(name = "Servidor", urlPatterns = {"/datos"})
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
        public static final String CONSULTA = "consulta";
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
        String proceso = (request.getParameter("proceso") == null) ? "-" : request.getParameter("proceso").toLowerCase();

        modelo = new Modelo(conexion);
        reader = new CSVReader();
        switch (proceso) {
        }
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
    private void jsonResponse(HttpServletResponse response, String mensaje) throws IOException {
        printer = response.getWriter();
        response.setContentType("application/json");
        Map<String, String> json = new LinkedHashMap();
        json.put("respuesta", mensaje);
        printer.write(new Gson().toJson(json));
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
    private void send406(Exception ex, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("error", ex.getMessage());
        response.setStatus(406);
        dispatcher = request.getRequestDispatcher("/META-DATA/message-jsp-pages/406.jsp");
        dispatcher.forward(request, response);
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
