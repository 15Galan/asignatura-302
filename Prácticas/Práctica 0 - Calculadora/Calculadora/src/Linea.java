import java.util.Scanner;

public class Linea {

    private final char[] digitos = {'0','1','2','3','4','5','6','7','8','9'};
    private final char[] operadores = {'+', '-', '*', '/'};

    private String linea;
    private Nodo arbol;


    public Linea(String texto) {
        linea = texto;
        arbol = new Nodo();
    }

    public Linea() {
        linea = "";
        arbol = new Nodo();
    }


    // Getters
    public String getLinea() {
        return linea;
    }

    public Nodo getArbol() {
        return arbol;
    }

    // Setters
    public void setLinea(String linea) {
        this.linea = linea;
    }

    public void setArbol(Nodo arbol) {
        this.arbol = arbol;
    }


    // Linea
    /**
     * Lee una línea por teclado y la asigna.
     */
    public void leerTeclado() {
        try(Scanner sc = new Scanner(System.in)) {
            System.out.println("No se detectó un archivo de entrada de datos.");
            System.out.print("Escribe la operación: ");

            linea = sc.nextLine();

        } catch (Exception e) {
            System.err.println("Error al leer la linea");
        }
    }

    /**
     * Lee una línea e inserta lo leído en un árbol de operaciones.
     */
    public void arbolizar() {
        int n = 0;

        for (int i = 0; i < linea.length(); i++) {
            char c = linea.charAt(i);

            if (detectar(c) == Expresion.OPERADOR) {
                arbol.insertar(String.valueOf(c), Expresion.OPERADOR);
                arbol.insertar(numero(n), Expresion.NUMERO);

                n = i+1;
            }

            if (i == linea.length()-1) {
                arbol.insertar(numero(n), Expresion.NUMERO);
            }
        }
    }

    /**
     * Devuelve el número completo al que pertenece el dígito indicado
     * en la posición indicada de la línea.
     *
     * @param posicion  Posicion de la línea con el primer digito
     *
     * @return          Número al que pertenece el dígito
     */
    public String numero(int posicion) {
        StringBuilder num = new StringBuilder();    // Cadena de digitos (un numero)

        while (detectar(linea.charAt(posicion)) == Expresion.NUMERO && posicion < linea.length()-1) {
            num.append(linea.charAt(posicion));
            posicion++;
        }

        // Caso para el último dígito de toda la línea
        if (posicion == linea.length()-1) {
            num.append(linea.charAt(posicion));     // Último dígito
        }

//        System.out.println("Número " + num + " detectado para el caracter " + digito);

        return num.toString();
    }

    /**
     * Comprueba si un carácter es un número o un operador.
     *
     * @param c     Carácter
     * @return      NUMERO si es un numero, OPERADOR si es un operador
     */
    private Expresion detectar(char c) {
        boolean encontrado = false;
        Expresion res = null;
        int i = 0;

        while (!encontrado && i < operadores.length) {
            encontrado = operadores[i] == c;
            i++;

            if (encontrado) {
                res = Expresion.OPERADOR;
            }
        }

        if (!encontrado) {
            i = 0;

            while (!encontrado && i < digitos.length) {
                encontrado = digitos[i] == c;
                i++;
            }

            if (encontrado) {
                res = Expresion.NUMERO;
            }
        }

        if (!encontrado) {
            throw new IllegalArgumentException("Carácter incorrecto: \"" + c +"\"");
        }

        return res;
    }


    @Override
    public String toString() {
        return linea;
    }
}
