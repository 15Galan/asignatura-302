import java.io.PrintStream;
import java.util.regex.Pattern;


/**
 * Representa un conjunto de métodos que hacen
 * posible la generación del código intermedio.
 */
public class Generador {

    // Variables
    protected static PrintStream out = System.out;
    private static int etq = 0;


    // Generadores de referencias
    /**
     * Genera una etiqueta con la estructura 'EX', siendo X un número.
     *
     * @return  El nombre de una etiqueta nueva
     */
    public static String crearEtiqueta(){
        return "E" + etq++;
    }
    
    // Generadores de código intermedio
    public static void funcion(String nombre) {
        out.println(".method public static " + nombre + "(I)I");
    }
    
    public static void sipush(String valor){
        TSimb.push(valor);
        out.println("\tsipush " + valor);
    }
    
    public static void iload(String n) {
        int pos = new Integer(n);
        
        TSimb.push(TSimb.getVar(pos).getValor());
        
        out.println("\tiload " + n);
    }
    
    public static void istore(String n) {
        int pos = new Integer(n);

        TSimb.modificar(pos, new Variable(TSimb.getNombre(pos), TSimb.pop(), 1));
        
        out.println("\tistore " + n);
    }
    
    public static String iadd() {
        String x = TSimb.pop(), y = TSimb.pop();
        int res = new Integer(x) + new Integer(y);

        TSimb.push(Integer.toString(res));
        out.println("\tiadd");

        return Integer.toString(res);
    }
    
    public static String isub() {
        String x = TSimb.pop(), y = TSimb.pop();
        int res = new Integer(x) - new Integer(y);

        TSimb.push(Integer.toString(res));
        out.println("\tisub");
        
        return Integer.toString(res);
    }
    
    public static String imul() {
        String x = TSimb.pop(), y = TSimb.pop();
        int res = new Integer(x) * new Integer(y);

        TSimb.push(Integer.toString(res));
        out.println("\timul");
        
        return Integer.toString(res);
    }
    
    public static void idiv() {
        // String x = TSimb.pop(), y = TSimb.pop();
        // int res = new Integer(x) / new Integer(y);

        // TSimb.push(Integer.toString(res));
        
        out.println("\tidiv");
    }
    
    public static void pop() {
        TSimb.pop();
        out.println("\tpop");
    }
    
    public static void dup() {
        TSimb.push(TSimb.peek());
        out.println("\tdup");
    }
    
    public static void nop() {
        out.println("\tnop");
    }
    
    public static void label(String label) {
        out.println(label + ":");
    }
    
    public static void goTo(String label) {
        out.println("\tgoto " + label);
    }
    
    public static void ifeq(String l) {
        out.println("\tifeq " + l);
    }
    
    public static void ifne(String l) {
        out.println("\tifne " + l);
    }
    
    public static Etiqueta ifge() {
        Etiqueta etiqueta = new Etiqueta(crearEtiqueta(), crearEtiqueta());
        TSimb.pop();
        out.println("\tifgt " + etiqueta.getV());
        return etiqueta;
    }
    
    public static Etiqueta ifle() {
        Etiqueta etiqueta = new Etiqueta(crearEtiqueta(), crearEtiqueta());
        TSimb.pop();
        out.println("\tiflt " + etiqueta.getV());
        return etiqueta;
    }
    
    public static void invoke(String f) {
        out.println("\tinvokestatic JPL/" + f + "(I)I");
    }
    
    public static void ireturn() {
        out.println("\tireturn");
    }
    
    public static void limitStack() {
        out.println("\t.limit stack " + TSimb.getStackTam());
    }
    
    public static void limitLocal() {
        out.println("\t.limit locals " + TSimb.getVarTam());
    }
    
    public static void endMethod() {
        out.println(".end method\n");
    }
    
    public static boolean isInteger(String in) {
        Pattern p = Pattern.compile("[+-]?(0|[1-9][0-9]*)");

        return Pattern.matches(p.pattern(), in);
    }
    
    public static boolean isIdent(String in) {
        Pattern p = Pattern.compile("[_a-zA-Z][_a-zA-Z0-9]*");

        return Pattern.matches(p.pattern(), in);
    }
}

