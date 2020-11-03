
/* Vacío */

%%

Variable	= [a-zA-Z_][a-zA-Z0-9_]*

Igual		= " "*\=" "*
Union		= " "*\+" "*
Especial	= (\\).

Salida		= System.out.print"ln"?" "*"("


%{
	String variable = "", valor = "";
	boolean salida = false;
%}

%int

%xstate METODO
%xstate DECLARACION
%xstate TEXTO
%xstate	SALIDA
%xstate SUSTITUCION

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
	String" "*		{ }
	
	/* Variable */
	{Variable}		{variable = yytext();
					 yybegin(DECLARACION);}
	
	/* Mostrar por consola */
	{Salida}		{System.out.print(yytext() + "\"");
					 yybegin(SALIDA);}
	
	/* Final del método */
	"}"				{System.out.print(yytext());
					 yybegin(YYINITIAL);}
	
	
	/* Para todo lo demás */
	[^]				{System.out.print(yytext());}
}


<DECLARACION> {
	/* Poner otra variable */
	/* Inicio de un texto (valor) */
	\"					{yybegin(TEXTO);}
	
	/* Nombre de la variable */
	{Variable}			{valor += TablaSimbolos.get(yytext());}
	
	/* Asignación (= y espacios) */
	{Igual}	| {Union}	{ }
	
	/* Final de la declaración */
	[,;\n] 				{TablaSimbolos.put(variable, valor);
						 valor = "";
			 			 yybegin(METODO);}
	
	
	/* Para todo lo demás */
	[^]					{System.out.print(yytext());}
}


<TEXTO> {
	
	/* Texto */
	[^\\\"]* | {Especial}		{valor += yytext();}
	
	/* Asignación (= y espacios) */
	{Union}					{ }
	
	/* Final de un texto (valor)*/
	\"  					{yybegin(DECLARACION);}
}


<SALIDA> {

	/* Inicio de un texto */
	\"						{yybegin(SUSTITUCION);}
	
	/* Unión de Strings */
	{Union} 				{ }
	
	/* Se reconoce una variable */
	{Variable} 				{System.out.print(TablaSimbolos.get(yytext()));}
	
	\\\"					{System.out.print(yytext()); yybegin(SUSTITUCION);}
	
	/* Texto */
	[^;\\\"+)]* | {Especial}	{System.out.print(yytext()); }
		
	/* Final de la salida */
	\);						{System.out.print("\"" + yytext());
							 yybegin(METODO);}
	
	
	/* Para todo lo demás */
	[^]						{System.out.print(yytext());}
}


<SUSTITUCION> {

	/* Final de un texto (valor)*/
	\"  					{yybegin(SALIDA);}
	
	/* " escapada */
	\\\"					{System.out.print(yytext());}
	
	/* Texto */
	[^\\\"]* | {Especial}		{System.out.print(yytext());}
	
	
	/* Para todo lo demás */
	[^]						{System.out.print(yytext());}
}

