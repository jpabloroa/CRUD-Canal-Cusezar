/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cusezar.test;

import com.cusezar.component.Cliente;
import com.cusezar.utils.CSVReader;
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
        CSVReader reader = new CSVReader();
        String columnas = reader.getDataFromCSV("fecha=oporto,utilic=osd,sdiof=o");
//        for(String columna:columnas) {
            System.out.println(columnas);
//        }
    }

}
