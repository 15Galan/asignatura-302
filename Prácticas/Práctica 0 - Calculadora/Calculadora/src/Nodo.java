import javax.management.OperationsException;

public class Nodo {

    private String contenido;       // Representa un valor numérico o un operador
    private Expresion expresion;    // Indica si un nodo es un número, un operador o está vacío

    private Nodo izq, der;          // Hijos izquierdo y derecho del Nodo actual


    public Nodo() {
        contenido = " ";
    }

    public Nodo(int numero) {
        contenido = String.valueOf(numero);
        expresion = Expresion.NUMERO;
    }

    public Nodo(String operador) {
        contenido = operador;
        expresion = Expresion.OPERADOR;
    }

    public Nodo(int numero, Nodo izq, Nodo der) {
        contenido = String.valueOf(numero);
        expresion = Expresion.NUMERO;
        this.izq = izq;
        this.der = der;
    }

    public Nodo(String operador, Nodo izq, Nodo der) {
        contenido = operador;
        expresion = Expresion.OPERADOR;
        this.izq = izq;
        this.der = der;
    }


    // Getters
    public String getContenido() {
        return contenido;
    }

    public Nodo getIzq() {
        return izq;
    }

    public Nodo getDer() {
        return der;
    }

    // Setters
    public void setContenido(String operador) {
        this.contenido = operador;
    }

    public void setIzq(Nodo izq) {
        this.izq = izq;
    }

    public void setDer(Nodo der) {
        this.der = der;
    }


    // Arbol
    /**
     * Inserta ordenadmente el valor recibido en el árbol,
     * diferenciando si se trata de un número o de un operdor.
     *
     * @param valor     Número u operador a insertar.
     * @param tipo      Diferenciador
     */
    public void insertar(String valor, Expresion tipo) {
        if (tipo == Expresion.NUMERO) {
            insertarNumero(valor);

        } else {
            insertarOperador(valor);
        }
    }

    /**
     * Inserta ordenadamente un número en el árbol.
     *
     * @param numero    Número a insertar
     */
    private void insertarNumero(String numero) {
        if (expresion == null) {        // Árbol vacío
            contenido = numero;
            expresion = Expresion.NUMERO;

        } else if (expresion == Expresion.OPERADOR) {
            if (izq == null) {
                izq = new Nodo(Integer.parseInt(numero));

            } else if (izq.expresion == Expresion.OPERADOR) {
                izq.insertarNumero(numero);

            } else if (der== null) {
                der = new Nodo(Integer.parseInt(numero));

            } else if (der.expresion == Expresion.OPERADOR) {
                der.insertarNumero(numero);
            }
        }
    }

    /**
     * Inserta ordenadamente un operador en el árbol.
     *
     * @param operador  Operador a insertar
     */
    private void insertarOperador(String operador) {
        if (expresion == null) {        // Árbol vacío
            contenido = operador;
            expresion = Expresion.OPERADOR;

        } else if (expresion == Expresion.OPERADOR) {
            if (izq == null) {
                izq = new Nodo(operador);

            } else if (izq.expresion == Expresion.OPERADOR) {
                izq.insertarOperador(operador);

            } else if (der == null) {
                der = new Nodo(operador);

            } else if (der.expresion == Expresion.OPERADOR) {
                der.insertarOperador(operador);
            }
        }
    }

    /**
     * Opera todos los nodos de un árbol y le asigna un
     * resultado a cada uno, según la operación que definen.
     *
     * @throws OperationsException  Error al operar
     */
    public String calcular() throws OperationsException {
        Nodo copia = copiar(this);

        copia.operar();

        return copia.contenido;
    }

    /**
     * Transforma un nodo con operador en un nodo hoja con el
     * resultado de aplicar la operación descrita a sus hijos.
     * Si el nodo contiene un número, no hace nada.
     */
    private String operar() throws OperationsException {
        int res = 0;

        if (!esHoja() && expresion == Expresion.OPERADOR) {
            int nIzq, nDer;

            izq.operar();
            nIzq = Integer.parseInt(izq.contenido);

            der.operar();
            nDer = Integer.parseInt(der.contenido);

            switch (contenido) {
                case "+" -> res = nIzq + nDer;
                case "-" -> res = nIzq - nDer;
                case "*" -> res = nIzq * nDer;
                case "/" -> res = Math.floorDiv(nIzq, nDer);
                default -> throw new OperationsException("Error al operar " + this + ": operando incorrecto");
            }

            contenido = String.valueOf(res);
            izq = null;
            der = null;

        } else if (!esHoja() && expresion == Expresion.NUMERO && (izq.expresion == Expresion.NUMERO || der.expresion == Expresion.NUMERO)) {
            throw new OperationsException("Error al operar " + this + ": no hay operando");
        }

        return String.valueOf(res);
    }

    /**
     * Copia un árbol.
     *
     * @param original  Árbol que se quiere copiar.
     * @return          Copia del árbol.
     */
    public Nodo copiar(Nodo original) {
        if (original.esHoja()) {
            return new Nodo(original.contenido);

        } else {
            return new Nodo(original.contenido, copiar(original.izq), copiar(original.der));
        }
    }

    /**
     * Indica si un Nodo es una hoja.
     *
     * @return  TRUE si es hoja, FALSE en caso contrario
     */
    public boolean esHoja() {
        return (izq == null) && (der == null);
    }


    @Override
    public String toString() {
        StringBuilder mensaje = new StringBuilder();

        if (expresion == null) {
            mensaje.append("| |");

        } else if (esHoja()) {
            mensaje.append("(");
            mensaje.append(contenido);
            mensaje.append(")");

        } else {
            mensaje.append("(");
            mensaje.append(izq);
            mensaje.append("-[");
            mensaje.append(contenido);
            mensaje.append("]-");
            mensaje.append(der);
            mensaje.append(")");
        }

        return mensaje.toString();
    }
}
