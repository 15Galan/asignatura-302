import java.io.FileReader;
import java.io.IOException;

public class wc {
 
    public static int contpal;
    public static int contlin;
    public static int contcar;
    
    public static void main(String arg[]) {
    
        if (arg.length>0) {
        
            try {
                Yylex lex = new Yylex(new FileReader(arg[0]));
                
		while (lex.yylex() != null) {
		

                }
                
	    } catch (IOException e) {
	    }
        }
        
        System.out.println(contlin+"\t"+contpal+"\t"+contcar+"\t"+arg[0]);
    }

}
