import java.io.FileReader;
import java.io.IOException;

public class rmv {
    public static void main(String arg[]) {
        if (arg.length>0) {
            Yylex lex = null;
            try {
                lex = new Yylex(new FileReader(arg[0]));
	            lex.yylex();
	            if (arg.length>1 && arg[1].equals("-debug")) {
	            	TablaSimbolos.dump();
	            }
	        } catch (IOException e) {
	        } 
        }
    }

}
