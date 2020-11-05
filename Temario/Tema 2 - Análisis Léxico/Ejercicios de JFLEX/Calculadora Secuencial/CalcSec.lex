
import java.util.List;
import java.util.LinkedList;

%%

%int

Numero	= [0-9]+

%{
	List<Integer> lista = new LinkedList<>();
	int ida, idb, a, b;
	
	public void operar(List lista, String op) {
		int res = 0;

		a = (int) lista.get(lista.size()-2);
		b = (int) lista.get(lista.size()-1);
		
		ida = lista.size()-2;
		idb = lista.size()-1;
		
		switch (op) {
            case "+":
            	res = a + b;
                break;
                
            case "-":
            	res = a - b;
                break;
                
            case "*":
            	res = a * b;
                break;
                
            case "/":
            	res = a / b;
                break;
                
            default:
            	
                break;
        }
		
		lista.set(ida, res);
		lista.remove(idb);
	}
%}

%xstate SUMA
%xstate RESTA
%xstate MULTIPLICACION
%xstate DIVISION
%xstate PARENTESIS

%%

<YYINITIAL> {
	
	/* Reconoce un número */
	{Numero}					{lista.add(Integer.parseInt(yytext()));}
	
	/* Reconoce un operador */
	"+"							{yybegin(SUMA);}
	"-"							{yybegin(RESTA);}
	"*"							{yybegin(MULTIPLICACION);}
	"/"							{yybegin(DIVISION);}
	
	/* Reconoce un paréntesis inicial */
	"("							{yybegin(PARENTESIS);}
	
	/* Reconoce un paréntesis final */
	")"							{operar(lista, "*");
								 yybegin(YYINITIAL);}
	/* Final de la operación */
	";"							{System.out.println(lista.get((int) lista.size()-1));}
	
	
	/* Cualquier otro lexema */
	[^]							{ }
}


<SUMA> {
	
	/* Operar */
	{Numero}					{lista.add(Integer.parseInt(yytext()));
								 operar(lista, "+");
								 yybegin(YYINITIAL);}
}


<RESTA> {
	
	/* Operar */
	{Numero}					{lista.add(Integer.parseInt(yytext()));
								 operar(lista, "-");
								 yybegin(YYINITIAL);}
}


<MULTIPLICACION> {
	
	/* Operar */
	{Numero}					{lista.add(Integer.parseInt(yytext()));
								 operar(lista, "*");
								 yybegin(YYINITIAL);}
}


<DIVISION> {
	
	/* Operar */
	{Numero}					{lista.add(Integer.parseInt(yytext()));
								 operar(lista, "/");
								 yybegin(YYINITIAL);}
}


<PARENTESIS> {
	
	/* Operar */
	{Numero}					{lista.add(Integer.parseInt(yytext()));}
	
	/* Reconoce un operador */
	"+"							{yybegin(SUMA);}
	"-"							{yybegin(RESTA);}
	"*"							{yybegin(MULTIPLICACION);}
	"/"							{yybegin(DIVISION);}
	
	
	/* Cualquier otro lexema */
	[^]							{ }
}

