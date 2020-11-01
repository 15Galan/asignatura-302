import java.io.IOException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.BufferedReader;


public class MainNumJava {
    /**
     * Llama al analizador sintactico, usando el nombre del fichero que se le pasa como
     * primer argumento, si no se le pasa ningun argumento usa la entrada estandar.
     */
    public static void main(String arg[]) {
        Yylex lex = null; 
        try {
            if (arg.length>0) {
                lex = new Yylex(new FileReader(arg[0]));
            } else {
              lex = new Yylex(new BufferedReader( new InputStreamReader( System.in )));
            }
            if (lex!=null) {
                Yytoken yytoken = null;
                while ((yytoken = lex.yylex()) != null  ) {
                    System.out.println("token="+yytoken.getToken()+" lexema="+yytoken.getLexema());
                }
            }
        } catch (IOException e) {
            System.out.println("Error al abrir el fichero de entrada");            
        }
    }
}


