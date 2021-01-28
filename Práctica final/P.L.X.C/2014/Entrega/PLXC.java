import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class PLXC {

    public static PrintStream out;
    
    public static void main(String args[]) {
        try {
            Reader in = new InputStreamReader(System.in);
            out = System.out;
            
            if (args.length > 0) {
                in = new FileReader(args[0]);
            }
            
            if (args.length > 1) {
                out = new PrintStream(new FileOutputStream(args[1]));
            }
            
            parser p = new parser(new Yylex(in));
            Object result = p.parse().value;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

