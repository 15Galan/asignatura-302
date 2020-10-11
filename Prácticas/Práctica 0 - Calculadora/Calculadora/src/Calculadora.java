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

    private static String salida;
    private static List<Linea> lineas;

    public static void main(String[] args) {
        lineas = new LinkedList<>();

        if (args.length == 0) {
            Linea linea = new Linea();
            linea.leerTeclado();

            lineas.add(linea);

        } else {
            if (args.length == 2) {
                salida = args[1];
            }

            leerFichero(args[0]);
        }

        calcular();
    }


    private static void leerFichero(String fichero) {
        try (Scanner sc = new Scanner(new File(fichero))) {
            while (sc.hasNext()) {
                lineas.add(new Linea(sc.nextLine()));
            }

        } catch (Exception e) {
            System.err.println("Error al leer " + fichero);
        }
    }

    private static void calcular() {
        String resultado, arbol = null;

        try (PrintWriter pw = new PrintWriter(new File(salida))) {
            for (Linea linea : lineas) {
                linea.arbolizar();
                arbol = linea.getArbol().toString();

                linea.getArbol().operar();
                resultado = linea.getArbol().getContenido();

                if (salida != null) {
                    pw.println(resultado);

                } else {
                    System.out.println(resultado);
                }
            }

        } catch (IllegalArgumentException | OperationsException e) {
            System.err.println(e.getMessage());
            System.out.println(arbol);

        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}
