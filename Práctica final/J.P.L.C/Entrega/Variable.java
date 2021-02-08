/**
 * Representa una variable, permitiendo diferenciar
 * entre su nombre, su valor (numérico) y su tipo.
 */
public class Variable {

    // Variables
    private String nombre, valor;
    private int tipo;


    // Constructor
    public Variable(String nombre, String valor, int tipo){
        this.nombre = nombre;
        this.valor = valor;
        this.tipo = tipo;
    }


    // Getters
    public String getNombre(){
        return this.nombre;
    }

    public int getTipo(){
        return this.tipo;
    }

    public String getValor(){
        return this.valor;
    }

    // Setters
    public void setNombre(String nuevo){
        this.nombre = nuevo;
    }

    public void setTipo(int n){
        this.tipo = n;
    }

    public void setValor(String valor){
        this.valor = valor;
    }
}
