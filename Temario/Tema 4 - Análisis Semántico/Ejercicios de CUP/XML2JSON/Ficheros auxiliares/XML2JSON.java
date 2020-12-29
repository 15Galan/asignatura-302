import java.io.*;
   
public class XML2JSON {
  static public void main(String argv[]) {    
    /* Start the parser */
    try {
      parser p = new parser(new Yylex(new FileReader(argv[0])));
      Object result = p.parse().value;      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}



