/**
 * NO MODIFIQUE ESTE CÓDIGO
 *
 * Licencia registrada Roverin Technologics - 2021
 */
package com.cusezar.test;

import java.lang.reflect.Field;

/**
 *
 * Clase de testing
 *
 * @author Juan Pablo - Roverin Technologics
 */
public class Testing {

    public int i = 5;

    public Integer test = 5;

    public String omghi = "der";

    public static String testStatic = "THIS IS STATIC";

    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
        Testing t = new Testing();
        for (Field f : t.getClass().getFields()) {
            System.out.println(f.getGenericType() + " " + f.getName() + " = " + f.get(t));
        }
    }
}
