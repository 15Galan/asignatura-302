
/* Vacío */

%%

Variable	= [a-zA-Z_][a-zA-Z0-9_]*

Igual		= " "*\=" "*
Union		= \"" "*\+" "*\"
Especial	= (\\).

Salida		= System.out.println"("


%{
	String variable = "", valor = "";
%}

%int

%xstate METODO
%xstate DECLARACION
%xstate TEXTO
%xstate	SALIDA

%%

<YYINITIAL> {
	
	/* Declaración de una clase */
	class			{System.out.print(yytext());}

	/* Nombre de la clase */
	{Variable}		{System.out.print(yytext() + "_rmj");}
	
	/* Inicio de la clase */
	"{"				{System.out.print(yytext());
					 yybegin(METODO);}
	
	/* Final de la clase */
	"}"				{System.out.print(yytext());}
	
	
	/* Para todo lo demás */
	[^]				{System.out.print(yytext());}
}


<METODO> {
	
	/* Cabecera del método */
	[^]*"{"			{System.out.print(yytext());}
	
	/* Asignación (= y espacios) */
	{Igual}			{ }
	
	/* Declaración de variable */
	String" "*		{yybegin(DECLARACION);}
	
	/* Variable */
	{Variable}		{yybegin(DECLARACION);}
	
	/* Mostrar por consola */
	{Salida}		{System.out.print(yytext());
					 yybegin(SALIDA);}
	
	/* Final del método */
	"}"				{System.out.print(yytext());
					 yybegin(YYINITIAL);}
	
	
	/* Para todo lo demás */
	[^]				{System.out.print(yytext());}
}


<DECLARACION> {
	
	/* Inicio de un texto (valor) */
	\"				{yybegin(TEXTO);}
	
	/* Nombre de la variable */
	{Variable}		{variable = yytext();}
	
	/* Asignación (= y espacios) */
	{Igual}			{ }
	
	/* Final de línea */
	[;\n] 			{TablaSimbolos.put(variable, valor);
			 		 yybegin(METODO);}
	
	
	/* Para todo lo demás */
	[^]				{System.out.print(yytext());}
}


<TEXTO> {

	/* Unión de Strings */
	{Union} 				{ }
	
	/* Texto */
	[^\"]* | {Especial}		{valor += yytext();}
	
	/* Final de un texto (valor)*/
	\"  					{TablaSimbolos.put(variable, valor);
							 yybegin(DECLARACION);}
}


<SALIDA> {

	/* Unión de Strings */
	{Union} 				{ }
	
	/* Se reconoce una variable */
	{Variable} 				{System.out.print(TablaSimbolos.get(yytext()));}
	
	/* Texto */
	[^\"]*\" | {Especial}	{System.out.print(yytext());}
	
	/* Final de la salida */
	");"					{System.out.print(yytext());
							 yybegin(METODO);}
	
	
	/* Para todo lo demás */
	[^]						{System.out.print(yytext());}
}

