
import java.util.List;
import java.util.LinkedList;

%%

%int

Numero		= [0-9]+
Operador	= [\+\-\*\/]

%{
	List<Integer> lista = new LinkedList<>();
	String operador;
	
	public void operar(List pila, String op) {
		int ida, idb, a, b, res = 0;
		
		a = (int) pila.get(pila.size()-2);
		b = (int) pila.get(pila.size()-1);
		
		ida = pila.size()-2;
		idb = pila.size()-1;
				
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
	        	System.err.println("Error: operaddor = " + op);
	            break;
	    }
		
		// System.out.print(pila + " " + operador);
		
		pila.set(ida, res);
		pila.remove(idb);
		
		// System.out.println(" -> " + pila);
	}
%}

%xstate OPERACION
%xstate PARENTESIS

%%

<YYINITIAL> {
	
	/* Reconoce un número */
	{Numero}					{lista.add(Integer.parseInt(yytext()));}
	
	/* Reconoce un operador */
	{Operador}					{operador = yytext();
								 yybegin(OPERACION);}
	
	/* Reconoce un paréntesis inicial */
	"("							{yybegin(PARENTESIS);}
	
	/* Reconoce un paréntesis final */
	")"							{if (lista.size() > 1) {
									operar(lista, "*");
								 };}
								 
	/* Reconoce el final de la línea */
	";"							{System.out.println(lista.get(0));
								 lista.clear();}
	
	
	/* Cualquier otro lexema */
	[^]							{ }
}


<OPERACION> {

	/* Reconoce un número */
	{Numero}					{lista.add(Integer.parseInt(yytext()));
								 operar(lista, operador);
								 yybegin(YYINITIAL);}
}


<PARENTESIS> {
	
	/* Operar */
	{Numero}					{lista.add(Integer.parseInt(yytext()));}
	
	/* Reconoce un operador */
	{Operador}					{operador = yytext();
								 yybegin(OPERACION);}
	
	/* Reconoce un paréntesis final */
	")"							{if (lista.size() > 1) {
									operar(lista, "*");
								 };}
	
	/* Reconoce el final de la línea */
	";"							{System.out.println(lista.get(0));}
	
	
	/* Cualquier otro lexema */
	[^]							{ }
}

