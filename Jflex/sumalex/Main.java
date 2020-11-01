import java.io.FileReader;
import java.io.IOException;

public class Main {
    protected static int suma = 0;
    
    public static void add(int x) {
    	suma += x;
    }
    
    public static int getSuma() {
    	return suma;
    }
    
    public static void reset() {
        suma = 0;	
    }
    
    
    public static void main(String arg[]) {
    
        if (arg.length>0) {
            try {
                Yylex lex = new Yylex(new FileReader(arg[0]));
                Yytoken yytoken = null;
		while (  (yytoken = lex.yylex()) != null  ) {
                    System.out.println(yytoken);
                    if (yytoken.getToken() == Yytoken.NUMERO)  {
                       Main.add(yytoken.getValor());
                    } else if (yytoken.getToken() == Yytoken.MAS) {
                    } else if (yytoken.getToken() == Yytoken.EOLN) {
                       System.out.println("Suma="+Main.getSuma());
                       Main.reset();
                    } 
                }
	    } catch (IOException e) {
	    }
        }
    }

}
