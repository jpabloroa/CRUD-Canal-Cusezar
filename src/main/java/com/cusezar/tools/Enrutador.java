/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cusezar.tools;

import com.cusezar.component.Constantes;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Juan Pablo - Roverin Technologics
 */
@WebServlet(name = "Enrutador", urlPatterns = {"/r/*"})
public class Enrutador extends HttpServlet implements Constantes {

    private RequestDispatcher rd;

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
        ArrayList<String> urlParams = getURLParams(request.getPathInfo().substring(1));

        //
        String ruta = urlParams.get(0).toLowerCase();

        //
        switch (ruta) {
            case ENRUTADOR.INICIO:
                rd = request.getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
                break;
            case ENRUTADOR.INSERTAR:
                rd = request.getRequestDispatcher("/META-DATA/message-jsp-pages/insertar-datos.jsp");
                rd.forward(request, response);
                break;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Funciones">
    /**
     *
     */
    private ArrayList<String> getURLParams(String URLPath) {
        String path = URLPath;
        String auxiliar = "";
        ArrayList<String> respuesta = new ArrayList<>();
        int length = path.length();
        for (int i = 0; i < length; i++) {
            if ("/".equals(path.substring(i, i + 1))) {
                respuesta.add(auxiliar);
                auxiliar = "";
            } else {
                auxiliar += path.substring(i, i + 1);
            }
        }
        respuesta.add(auxiliar);
        return respuesta;
    }
    // </editor-fold>

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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
