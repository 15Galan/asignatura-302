import java.util.Stack;
import java.util.ArrayList;


/**
 * Representa una tabla que contiene variables y que
 * permite su tratamiento de diferentes formas.
 */
public class TSimb {

    // Variables
    private static ArrayList<Variable> variables = new ArrayList<>();
    private static Stack<String> pila = new Stack<String>();
    private static int maxPila = 0;

    
    // Getters
    public static ArrayList<Variable> getVars() {
        return variables;
    }
    
    public static Stack<String> getPila() {
        return pila;
    }
    
    public static int getStackTam() {
        return maxPila;
    }
    
        
    // Tratamiento de la pila
    /**
     * Añade una variable a la pila de la tabla de símbolos.
     *
     * @param nombre    Nombre de la variable a almacenar
     */
    public static void push(String nombre) {
        pila.push(nombre);
        
        if (pila.size() > maxPila) {
            maxPila = pila.size();
        }
    }
    
    /**
     * Devuelve el valor en la cabeza de la pila, sin sacarlo.
     *
     * @return  El valor en la cabeza de la pila
     */
    public static String peek() {
        return pila.peek();
    }
    
    /**
     * Devuelve el valor en la cabeza de la pila, y lo saca de esta.
     *
     * @return  El valor en la cabeza de la pila
     */
    public static String pop() {
        return pila.pop();
    }
    
    
    // Tratamiento de la lista de variables
    /**
     * Devuelve el número de variables almacenadas en la tabla de símbolos.
     *
     * @return  El número de variables
     */
    public static int getVarTam() {
        return variables.size();
    }
    
    /**
     * Añade una variable a la tabla de símbolos.
     */
    public static void add(String nombre, String valor, int tipo) {
        variables.add(new Variable(nombre, valor, tipo));
    }
    
    /**
     * Devuelve la variable que se encuentra en una posición
     * concreta de la tabla de símbolos.
     *
     * @param   indice  Posición de la tabla de símbolos donde buscar
     *
     * @return  La variable en la posición dada
     */
    public static Variable getVar(int indice) {
        return variables.get(indice);
    }
    
    /**
     * Devuelve el nombre de la variable que se encuentra
     * en una posición concreta de la tabla de símbolos.
     *
     * @param   indice  Posición de la tabla de símbolos donde buscar
     *
     * @return  El nombre de la variable en la posición dada
     */
    public static String getNombre(int indice) {
        return variables.get(indice).getNombre();
    }
    
    /**
     * Devuelve la posición de la variable que se encuentra
     * en la tabla de símbolos con un nombre concreto.
     * 
     * @param   nombre  Nombre de la variable a buscar
     *
     * @return  Posición de la variable en la tabla de símbolos
     */
    public static String indexOf(String nombre) {
        int salida = 0;
        
        for (int i = 0; i < variables.size(); i++) {
            if (getNombre(i).equals(nombre)) {
                salida = i;
            }
        }

        return Integer.toString(salida);
    }
    
    /**
     * Cambia el valor de la variable que se encuentra en una
     * posición concreta de la tabla de símbolos.
     *
     * @param   indice      Posición de la tabla de símbolos
     * @param   variable    Nueva variable
     */
    public static void modificar(int indice, Variable variable) {
        variables.set(indice, variable);
    }

    /**
     * Vacía la tabla de símbolos.
     */
    public static void clear() {
        variables.clear();
        pila.clear();
        maxPila = 0;
    }
}
