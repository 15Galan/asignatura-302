import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class Calculadora {
    public static PrintStream out;
    public static void main(String argv[]) {
        try {
            Reader in = new InputStreamReader(System.in);    
            out = System.out;
            
            if (argv.length > 0) {
                in = new FileReader(argv[0]);
            }
            
            if (argv.length > 1) {
                out = new PrintStream(new FileOutputStream(argv[1]));
            }
            
            parser p = new parser(new Yylex(in));
            Object result = p.parse().value;
            
        } catch (Exception e) {
          e.printStackTrace();
        }
    }
}

