import java.io.IOException;
import java.io.FileReader;


public class cpv {

    static int A, B, C, D;

    public static void main(String[] args) {
        if (args.length > 0) {

            try {
                Yylex lex = new Yylex(new FileReader(args[0]));
		            
	            while (lex.yylex() != -1) {
	                
	            }

                System.out.println("A " + A);
                System.out.println("B " + B);
                System.out.println("C " + C);
                System.out.println("D " + D);

            } catch (IOException e) {
                System.err.println("Error: '" + e.getMessage() + "'.");
            }
            
        } else {
        	System.out.println("Se necesita un archivo de texto como argumento.");
        }
    }
}

