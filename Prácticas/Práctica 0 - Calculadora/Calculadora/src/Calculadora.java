/*
 *  =============================================================================
 *
 * Participantes de la Práctica:
 * Antonio J. Galán Herrera
 *
 * GitHub: 15Galan/asignatura-302
 *
 * =============================================================================
 */

import javax.management.OperationsException;

public class Calculadora {

    public static void main(String[] args) {
        Linea linea = new Linea();

        if (args.length == 0) {
            linea.leerTeclado();

        } else {
            linea.leerFichero(args[0]);
        }

        try {
            linea.arbolizar();
            linea.getArbol().operar();

        } catch (IllegalArgumentException | OperationsException e) {
            System.err.println(e.getMessage());
            System.out.println(linea.getArbol());
        }

        System.out.println(linea.getArbol().getContenido());
    }
}
