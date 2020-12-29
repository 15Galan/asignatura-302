import java_cup.runtime.*;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Arbol {
	
	public final static String ERROR   = "ERROR: La entrada no tiene un formato correcto";
	public final static int MAXNIVEL  = 10;
	
	/* Atributos globales que deben usarse en el parser */
	/* (Al ser publicos pueden modificarse directamente) */
	public Integer raiz;
	public Integer maximo;
	public Integer profundidad;
	public Integer elementos;
	public Integer[] sumaNivel;	
	public String arbolInverso;
	public String grafo;
    
	
	/* Constructor por defecto */
	public Arbol() {
		raiz = 0;
		maximo = 0;
		profundidad = 0;
		elementos = 0;
		sumaNivel = new Integer[MAXNIVEL];
		arbolInverso = new String();
		grafo = new String();
	}	
	
    /* metodo principal */
	public static void main(String argv[]) {    
        try {

          // Abrir ficheros de entrada y salida	
          Reader fr = new BufferedReader(new InputStreamReader(System.in));
          if (argv.length>0) {
        	  fr = new FileReader(argv[0]);
          }
          PrintStream out = System.out;
          if (argv.length>1) {
        	  out = new PrintStream(new FileOutputStream(argv[1]));
          }
          
          // Crear el parser
          parser p = new parser(new Yylex(fr));
          if (p!=null) {
              Symbol s = p.parse();
              // imprimir la salida
              if (s!=null) {
            	  out.println(s.value);
              }
          }
          
        } catch (Exception e) {
          e.printStackTrace();
        }
      }

    /* Para la salida con formato */
    public String toString() {
    	String st = "";
    	if (raiz !=null) {
    		st += "Raiz: " + raiz +"\n";
    	}
    	if (maximo !=null) {
    		st += "Maximo: " + maximo +"\n";
    	}
    	if (profundidad !=null) {
    		st += "Profundidad: "+ profundidad+ "\n";
    	}
    	if (elementos !=null) {
    		st += "Elementos: "+ elementos + "\n";
    	}
    	if (sumaNivel !=null) {
    		for(int i=0; i<MAXNIVEL; i++) {
    	    	if (sumaNivel[i] !=null) {
    	    		st += "Suma nivel "+i+": "+ sumaNivel[i] + "\n";
    	    	}
    		}
    	}
    	if (arbolInverso !=null) {
    		st += "Arbol inverso: " + arbolInverso +"\n";
    	}
    	if (arbolInverso !=null) {
    		st += "Grafo: " + grafo +"\n";
    	}
    	return st;
    }
    
}