/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cusezar.component;

/**
 *
 * @author Juan Pablo - Roverin Technologics
 */
public interface Constantes {

    //-------- Respuesta
    public class RESPUESTA {
        
        //
        public static final String HTTP_200 = " ¡ OK ! ";
        public static final String HTTP_201 = " ¡ El recurso ha sido creado exitosamente ! ";
        public static final String HTTP_406 = " ¡ Error de solicitud HTTP, verifique la sintaxis de la solicitud ! ";
    }

    //-------- Error
    public class ERROR {

        //
        public static final String CONSULTA = " ¡ No se ha encontrado el recurso solicitado ! ";
        public static final String LISTA_VACIA = " ¡ La colección está vacía ! ";
        public static final String DATOS_INVALIDOS = " ¡ Error de sintaxis, verifique los datos ingrsados ! ";
        public static final String CONTENTIDO_NULO = " ¡ El contenido ingresado es nulo ! ";
    }

    //-------- Enrutador
    public class ENRUTADOR {

        //
        public static final String INICIO = "inicio";
        public static final String INSERTAR = "insertar";
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

    //-------- DataBaseConnector
    public class DATABASECONNECTOR {

        //
        public static final String DB_FECHA = "fecha";
    }

    //-------- Cliente
    public class CLIENTE {

        // Constantes de estado primario
        public static final String REGISTRADO = "REGISTRADO";
        public static final String REFERIDO = "REFERIDO";
        public static final String INVALIDO = "INVALIDO";

        // Constantes de estado secundario
        public static final String CONTACTADO = "CONTACTADO";
        public static final String REASIGNADO = "REASIGNADO";
        public static final String PERFILADO = "";
        public static final String INACTIVO = "INACTIVO";
        public static final String DESCALIFICADO = "";
    }
}
