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
import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.List;

public class Calculadora {

    private static List<Linea> lineas;

    public static void main(String[] args) {
        lineas = new LinkedList<>();

        if (args.length == 0) {
            leerTeclado();

        } else {
            leerFichero(args[0]);
        }

        try {
            calcular();

            if (args.length == 2) {
                escribirFichero(args[1]);

            } else {
                for (Linea linea : lineas) {
                    System.out.println(linea.resultado);
                }
            }

        } catch (IllegalArgumentException | OperationsException e) {
            System.err.println(e.getMessage());
        }
    }


    /**
     * Lee una línea por teclado y la asigna a la lista de líneas (que solo tendrá esa).
     */
    public static void leerTeclado() {
        try(Scanner sc = new Scanner(System.in)) {
            System.out.println("No se detectó un archivo de entrada de datos.");
            System.out.print("Escribe la operación: ");

            lineas.add(new Linea(sc.nextLine()));

        } catch (Exception e) {
            System.err.println("Error al leer la linea");
        }
    }

    /**
     * Almacena cada línea del fichero en una lista.
     *
     * @param fichero   Archivo del que lee
     */
    private static void leerFichero(String fichero) {
        try (Scanner sc = new Scanner(new File(fichero))) {
            while (sc.hasNext()) {
                lineas.add(new Linea(sc.nextLine()));
            }

        } catch (Exception e) {
            System.err.println("Error al leer " + fichero);
        }
    }

    /**
     * Almacena el resultado de cada operación (línea) en un fichero
     *
     * @param fichero   Archivo en el que escribe
     */
    public static void escribirFichero(String fichero) {
        try (PrintWriter pw = new PrintWriter(new File(fichero))) {
            for (Linea linea : lineas) {
                pw.println(linea.resultado);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Resuelve todas las operaciones descritas como líneas de la Calculadora.
     */
    private static void calcular() throws IllegalArgumentException, OperationsException {
        for (Linea linea : lineas) {
            linea.arbolizar();
            linea.calcular();
        }
    }
}
