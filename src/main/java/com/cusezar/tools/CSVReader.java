/**
 * NO MODIFIQUE ESTE CÓDIGO
 * 
 * Licencia registrada Roverin Technologics - 2021
 */
package com.cusezar.tools;

import com.cusezar.modelo.Cliente;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Clase para el manejo de datos en formato <code>text/csv</code>
 * 
 * @author Juan Pablo - Roverin Technologics
 */
public class CSVReader {

    /**
     * Método getCLienteFromCSV()
     * 
     * Parsea una cadena de texto en formato <code>text/csv</code> 
     * a un objeto Cliente con 4 parámetros básicos, nombre, correo,
     * celular y medio publicitario
     * 
     * @param CSV
     * @param gotHeaders
     * @return
     * @throws IOException 
     */
    public List<Cliente> getClienteFromCSV(String CSV, boolean gotHeaders) throws IOException {
        List<Cliente> lista = new ArrayList<>();
        String text = CSV;
        if ("".equals(text)) {
            throw new IOException(" El contenido es nulo ");
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
                throw new IOException(" Los datos ingresados son incorrectos ");
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
}
