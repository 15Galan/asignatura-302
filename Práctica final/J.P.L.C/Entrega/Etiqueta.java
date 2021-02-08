/* Representan un par de etiquetas generadas
 * a partir de una condición.
 * 
 * Las etiquetas representan lo siguiente:
 * 1. Salto si se cumple la condición (true).
 * 2. Salto si no se cumple la condición (false).
 */
public class Etiqueta {

    // Constantes
    public static final int GT  = 1;
    public static final int LT  = 2;
    public static final int EQ  = 3;
    public static final int LE  = 4;
    public static final int GE  = 5;
    public static final int NEQ = 6;
    
    
    // Variables
    private String v;
    private String f;
    
    
    // Constructor
    public Etiqueta(String v, String f) {
        this.v = v;
        this.f = f;
    }
    
    
    // Getters
    public String getV() {
        return this.v;
    }

    public String getF() {
        return this.f;
    }
    
    // Setters
    public void setV(String v) {
        this.v = v;
    }

    public void setF(String f) {
        this.f = f;
    }
}

