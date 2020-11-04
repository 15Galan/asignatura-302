import java.io.IOException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.HashMap;


public class Tweets {
    /**
     * Llama al analizador sintactico, usando el nombre del fichero que se le pasa como
     * segundo argumento, si no se le pasa ningun argumento usa la entrada estandar.
     */
	//Damos por hecho que la primera entrada es un parametro correcto.

	public static int longitud = 0;
	public static int hashtags = 0;
	public static int citas = 0;
	public static int enlaces = 0;
	public static HashMap<String,Integer> listado = new HashMap<String,Integer>();
	
	public static void imprimirMapa(HashMap<String,Integer> hm) {
		for (String item : hm.keySet()) {
		    System.out.println(item+"="+hm.get(item));
		}	
	}
	
	public static void añadir(String variable) {
		if(!listado.containsKey(variable.toLowerCase())){
			listado.put(variable.toLowerCase(), 1);
		}else{
			listado.replace(variable.toLowerCase(), (listado.get(variable.toLowerCase())+1));
		}
	}
	
    public static void main(String arg[]) {
        Yylex lex = null; 
        try {
        	
        	String opcion = arg[0];
            if (arg.length>0) {
                lex = new Yylex(new FileReader(arg[1]));
            } else {
              lex = new Yylex(new BufferedReader( new InputStreamReader( System.in )));
            }
            if (lex!=null) {
                //Yytoken yytoken = null;
                while (lex.yylex() != -1 ) {
                    
                }
                
                switch (opcion){
                	case "-l":
                		System.out.println("Longitud del tweet: "+longitud);
                		break;
                	case "-h":
                		System.out.println("Numero de hashtags: "+hashtags);
                		break;
                	case "-c":
                		System.out.println("Numero de citas: "+citas);
                		break;
                	case "-e":
                		System.out.println("Numero de enlaces: "+enlaces);
                		break;
                	case "-hn":
                		imprimirMapa(listado);
                		break;
                	default:
                		System.out.println("aprende a escribir crack");
                		break;
                	
                }
                
            }
        } catch (IOException e) {
            System.out.println("Error al abrir el fichero de entrada");            
        }
    }
}
