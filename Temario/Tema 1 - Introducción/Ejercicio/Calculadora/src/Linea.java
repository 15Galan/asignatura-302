import javax.management.OperationsException;

public class Linea {

    private final char[] digitos = {'0','1','2','3','4','5','6','7','8','9'};
    private final char[] operadores = {'+', '-', '*', '/'};

    private String linea;       // Expresión matemática
    private Nodo arbol;         // Representación de la expresión

    public String resultado;    // Si la cadena es vacía, valdrá ""


    public Linea(String texto) {
        linea = comprimir(texto);
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
     * Elimina todos los espacios de una línea.
     *
     * @param texto     Línea a modificar
     *
     * @return          La misma línea, sin espacios
     */
    private String comprimir(String texto) {
        StringBuilder linea = new StringBuilder();

        for (int i = 0; i < texto.length(); i++) {
            if (!Character.isWhitespace(texto.charAt(i))) {
                linea.append(texto.charAt(i));
            }
        }

        return linea.toString();
    }

    /**
     * Lee una línea e inserta lo leído en un árbol de operaciones.
     */
    public void arbolizar() {
        int i = 0;      // Recorre toda la expresion principal
        int n = -1;     // Apunta al primer digito de un numero
        int e = 0;      // Recorre sub-expresiones

        // Dividir expresiones de sumas (+)
        while (i < linea.length()) {
            if (linea.charAt(i) == '+') {
                Linea expIzq = crearExpresion(e, i);
                expIzq.arbolizar();

                Linea expDer = crearExpresion(i+1, linea.length()-1);
                expDer.arbolizar();

                arbol = new Nodo('+', expIzq.arbol, expDer.arbol);

                break;
            }

            i++;
        }

        // Tratamiento de otros operadores
        



//        for (int i = 0; i < linea.length(); i++) {
//            char c = linea.charAt(i);
//
//            if (c == '+') {
//                Linea exp1 = crearExpresion(e, i);
//                arbol.insertar(String.valueOf(c), Expresion.OPERADOR);
//                arbol.insertar(numero(n), Expresion.NUMERO);
//
//                n = -1;     // Reinicio de la variable
//
//            } else if (detectar(c) == Expresion.NUMERO && n == -1) {
//                n = i;      // Primer digito
//            }
//
//            if (detectar(c) == Expresion.NUMERO && i == linea.length() - 1) {
//                arbol.insertar(numero(n), Expresion.NUMERO);
//            }
//        }

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
        boolean encontrado = false;
        Expresion res = null;
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

        return res;
    }

    /**
     * Ejecuta las operaciones del árbol.
     *
     * @throws OperationsException  Error al operar
     */
    public void calcular() throws OperationsException {
        resultado = arbol.calcular();
    }

    /**
     * Extrae una expresión contenida en la línea.
     *
     * @param inicio    Posición de la línea en la que empieza
     * @param fin       Posición de la línea en la que acaba
     *
     * @return      Una línea basada en la expresión indicada
     */
    public Linea crearExpresion(int inicio, int fin) {
        StringBuilder expresion = new StringBuilder();

        while (inicio < fin) {
            expresion.append(linea.charAt(inicio));
            inicio++;
        }

        return new Linea(expresion.toString());
    }


    @Override
    public String toString() {
        return linea;
    }
}
