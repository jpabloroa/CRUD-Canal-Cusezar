/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cusezar.test;

import com.cusezar.modelo.Cliente;
import com.cusezar.tools.CSVReader;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Juan Pablo - Roverin Technologics
 */
public class Testing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
//        CSVReader reader = new CSVReader();
//        String columnas = reader.getDataFromCSV("fecha=oporto,utilic=osd,sdiof=o");
////        for(String columna:columnas) {
//            System.out.println(columnas);
//        }
        StringBuilder path = new StringBuilder();
        path.append("/error/suma/");
        String[] array = path.toString().split("/");
        System.out.println(array.length);
        System.out.println((array.length > 7) ?  array[5] : "paila bro");

        StringBuilder sql = new StringBuilder();
        sql.append("`diaDeCreacion`,`mesDeCreacion`,`agnoDeCreacion`,`codigoConteo`,`viable`,")
                .append("`nombre`,`correo`,`celular`,`medioPublicitario`,`zonaBusqueda`,`proyectoDeInteres`,")
                .append("`gestionDesdeSalaDeVentas`,`habeasData`,`fechaUltimoContacto`,`contactoEfectivo`,")
                .append("`proyectoCalificado`,`diaVisita`,`mesVisita`,`agnoVisita`,`visitaEfectiva`,`estado`");
        String palabra = sql.toString();
        String[] arr = palabra.split(",()");
        System.out.println("sql.append(\"INSERT INTO clientes (\");");
        for (String s : arr) {
            System.out.println("case columna." + s.replace("`", "") + ":");
        }

//        for (String s : array) {
//            System.out.println("-> " + s);
//        }
    }

}
