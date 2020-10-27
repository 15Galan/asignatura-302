
/* Vacío */

%%

SintaxisVariable	= [a-zA-Z_][0-9a-zA-Z_]*

%{
	String variable;
%}

%xstate TEXTO
%xstate VARIABLE
%xstate COMANDO

%%

<YYINITIAL> {
	
	/* Nombre de una variable */
	{SintaxisVariable}/\=		{variable = yytext(); yybegin(TEXTO);}
	
	/* Comienzo de una variable */
	\$							{yybegin(VARIABLE);}
	
	/* Comienzo de un texto */
	\"							{yybegin(TEXTO);}
	
	/* Comienza un comando */
	[a-zA-Z][0-9a-zA-Z_]*/\ 	{System.out.print("\n" + yytext()); yybegin(COMANDO);}
	
	
	/* Para todo lo demás */
	[^] 						{ /* Ignorar */ }
}

<VARIABLE> {
	
	{SintaxisVariable} 		{variable = yytext();}
	
	\=						{yybegin(YYINITIAL);}
	
	/* Para todo lo demás */
	[^] 					{ /* Ignorar */ }
}

<TEXTO> {
	
	/* Declaración de un valor */
	(\= | \=\")/[^\r\n\\]+		{ /* Ignorar '=' */ }
	
	/* Caracteres de fin de línea */
	\; | \"				{yybegin(YYINITIAL);}

	/* Caracteres normales (final)*/
	[^\r\n\"\;\\]+		{TablaSimbolos.put(variable, yytext());	yybegin(YYINITIAL);}
	
	/* Caracteres especiales */
	\\t					{System.out.println("T");}
	\\r					{System.out.println("R");}
	\\n 				{System.out.println("N");}
	\\\"				{System.out.println("C");}
	\\ 					{System.out.println("B");}
	
	
	/* Para todo lo demás */
	[^] 				{ /* Ignorar */ }
}

<COMANDO> {
	
	/* Texto */
	[^\r\n\"\;\$\\]+				{System.out.print(yytext());}
	
	/* Fin del comando */
	\ 								{System.out.print(yytext());}
	
	/* Comienzo de los argumentos */
	\${SintaxisVariable}			{System.out.print(TablaSimbolos.get(yytext()));}
	
	
	/* Para todo lo demás */
	[^] 							{ /* Ignorar */ }
}

