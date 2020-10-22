public class Yytoken {
    public final static int TOKEN_CTE_ENTERO = 257;
    public final static int TOKEN_CTE_ENTERO_LARGO = 258;
    public final static int TOKEN_CTE_REAL_CORTO = 259;
    public final static int TOKEN_CTE_REAL_LARGO = 260;
    public final static int TOKEN_ERROR = 261;

    private int token;
    private String lexema;

    public Yytoken(int token, String lexema) {
         this.token = token;
         this.lexema = lexema;
    }

    public int getToken()  {
         return token;
    }

    public String getLexema() {
         return lexema;
    }

    public String toString() {
         return "<"+token+","+lexema+">";
    }
}
