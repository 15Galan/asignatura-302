import javax.management.OperationsException;

public class Linea {

    private final char[] digitos = {'0','1','2','3','4','5','6','7','8','9'};
    private final char[] operadores = {'+', '-', '*', '/'};

    private String linea;
    private Nodo arbol;

    public String resultado;    // Si la cadena es vacía, valdrá ""


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
     * Lee una línea e inserta lo leído en un árbol de operaciones.
     */
    public void arbolizar() {
        int n = -1;     // Debe apuntar al primer digito de un numero

        for (int i = 0; i < linea.length(); i++) {
            char c = linea.charAt(i);

            if (detectar(c) == Expresion.OPERADOR) {
                arbol.insertar(String.valueOf(c), Expresion.OPERADOR);
                arbol.insertar(numero(n), Expresion.NUMERO);

                n = -1;     // Reinicio de la variable

            } else if (detectar(c) == Expresion.NUMERO && n == -1) {
                n = i;      // Primer digito
            }

            if (detectar(c) == Expresion.NUMERO && i == linea.length() - 1) {
                arbol.insertar(numero(n), Expresion.NUMERO);
            }
        }
    }

    /**
     * Devuelve el número completo al que pertenece el dígito
     * en la posición indicada de la línea.
     *
     * @param posicion  Posicion de la línea con el primer digito
     *
     * @return          Número al que pertenece el dígito
     */
    public String numero(int posicion) {
        StringBuilder numero = new StringBuilder();    // Cadena de digitos (un numero)
        char digito = linea.charAt(posicion);

        while (detectar(digito) == Expresion.NUMERO && posicion < linea.length()-1) {
            numero.append(digito);

            posicion++;
            digito = linea.charAt(posicion);
        }

        // Caso para el último dígito de toda la línea
        if (posicion == linea.length()-1) {
            numero.append(digito);     // Último dígito
        }

        return numero.toString();
    }

    /**
     * Comprueba si un carácter es un número o un operador.
     *
     * @param c     Carácter
     * @return      NUMERO si es un numero, OPERADOR si es un operador, NULL si es un espacio
     */
    private Expresion detectar(char c) {
        Expresion res = null;

        if (!Character.isWhitespace(c)) {
            boolean encontrado = false;
            int i = 0;

            // Buscar entre los operadores
            while (!encontrado && i < operadores.length) {
                encontrado = operadores[i] == c;
                i++;
            }

            if (encontrado) {
                res = Expresion.OPERADOR;
            }

            // Buscar entre los digitos
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

            // No es ni un operador, ni un digito, ni un espacio
            if (!encontrado) {
                throw new IllegalArgumentException("Carácter incorrecto: \"" + c + "\"");
            }
        }

        return res;
    }

    /**
     * Ejecuta las operaciones del árbol.
     *
     * @throws OperationsException
     */
    public void calcular() throws OperationsException {
        resultado = arbol.calcular();
    }


    @Override
    public String toString() {
        return linea;
    }
}
