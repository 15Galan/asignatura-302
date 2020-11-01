import java.io.IOException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.BufferedReader;


public class cpv {
    /**
     * Llama al analizador sintactico, usando el nombre del fichero que se le pasa como
     * primer argumento, si no se le pasa ningun argumento usa la entrada estandar.
     */
     
     public static int A=0;
     public static int B=0;
     public static int C=0;
     public static int D=0;
     
    public static void main(String arg[]) {
        Yylex lex = null; 
        try {
            if (arg.length>0) {
                lex = new Yylex(new FileReader(arg[0]));
            } else {
              lex = new Yylex(new BufferedReader( new InputStreamReader( System.in )));
            }
            if (lex!=null) {
            
                while (lex.yylex() != -1) {

                }
                System.out.println("A "+A+"\n");
                System.out.println("B "+B+"\n");
                System.out.println("C "+C+"\n");
                System.out.println("D "+D+"\n");
            }
            
        } catch (IOException e) {
            System.out.println("Error al abrir el fichero de entrada");            
        }
    }
}


