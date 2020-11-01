import java.io.FileReader;
import java.io.IOException;

public class wc {
    public static int lineas = 0;
    public static int palabras = 0;
    public static int caracteres = 0;    
    
    public static void main(String arg[]) {
    
        if (arg.length>0) {
            try {
                Yylex lex = new Yylex(new FileReader(arg[0]));
                
				while (  lex.yylex() != null  ) {
                    
                    
                }
                
		System.out.println(" "+lineas+ "  " + palabras+ " " + caracteres+ " " + arg[0]);
	    	} catch (IOException e) {
	    	
	    	}
        }
    }

}
