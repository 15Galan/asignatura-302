import java.io.IOException;
import java.io.FileReader;


public class JCom {

    static int linea, bloque, documentacion;

    public static void main(String[] args) {
        if (args.length > 0) {

            try {
                Yylex lex = new Yylex(new FileReader(args[0]));
		            
	            while (lex.yylex() != -1) {
	                
	            }

                System.out.println("//  " + linea);
                System.out.println("/*  " + bloque);
                System.out.println("/** " + documentacion);

            } catch (IOException e) {
                System.err.println("Error: '" + e.getMessage() + "'.");
            }
            
        } else {
        	System.err.println("Se necesita un archivo de texto como argumento.");
        }
    }
}

