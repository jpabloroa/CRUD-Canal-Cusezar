/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cusezar.utils;

import com.cusezar.component.Cliente;
import com.cusezar.component.Constantes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan Pablo - Roverin Technologics
 */
public class CSVReader implements Constantes {

    public List<Cliente> getClienteFromCSV(String CSV, boolean gotHeaders) throws IOException {
        List<Cliente> lista = new ArrayList<>();
        String text = CSV;
        if (text == null) {
            throw new IOException(ERROR.CONTENTIDO_NULO);
        }
        String[] filas = text.split("\n");
        int largo = filas.length;
        int filasClientes = 0;
        if (gotHeaders) {
            filasClientes++;
        }
        for (int i = filasClientes; i < largo; i++) {
            String fila = filas[i];
            String[] cliente = fila.split(",");
            if (cliente.length < 4) {
                throw new IOException(ERROR.DATOS_INVALIDOS);
            }
            Cliente nuevoCliente = new Cliente();
            nuevoCliente.setNombre(cliente[0]);
            nuevoCliente.setCorreo(cliente[1]);
            nuevoCliente.setCelular(cliente[2]);
            nuevoCliente.setMedioPublicitario(cliente[3]);
            lista.add(nuevoCliente);
        }
        return lista;
    }

    public String getDataFromCSV(String CSV) {
        String text = CSV;
        return text.replace(",", " AND ");
    }

}
