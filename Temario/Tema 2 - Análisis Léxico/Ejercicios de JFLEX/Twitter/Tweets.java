import java.io.FileReader;
import java.io.IOException;

public class Tweets {
	
	static int longitud, hashtags, citas, enlaces;
	
	public static void main(String arg[]) {
		
        if (arg.length > 0) {
            Yylex lex = null;
            
            try {
                lex = new Yylex(new FileReader(arg[1]));
                
	            while (lex.yylex() != -1) { }
	            
	            if (arg[0].equals("-l")) {
	            	System.err.println("Longitud: " + longitud);
	            	
	            } else if (arg[0].equals("-h")) {
	            	System.err.println("Hashtags: " + hashtags);
	            	
	            } else if (arg[0].equals("-c")) {
	            	System.err.println("Citas: " + citas);
	            	
	            } else if (arg[0].equals("-e")) {
	            	System.err.println("Enlaces: " + enlaces);
	            	
	            } else if (arg[0].equals("-hn")) {
	            	TablaHashtags.dump();
	            
	            } else {
	            	System.err.println("Opción no reconocida");
	            }
	            
	        } catch (IOException e) {
	        	System.err.println(e.getMessage());
	        } 
        }
    }
}

