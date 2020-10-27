
/* Vacío */

%%

Variable	= [a-zA-Z_][a-zA-Z0-9_]*
Comando		= [a-zA-Z][a-zA-Z0-9_]*
Especial	= (\\).

%{
	String variable, valor;
%}

%int

%xstate VARIABLE
%xstate TEXTO
%xstate ARGUMENTOS

%%

<YYINITIAL> {
	
	/* Se reconoce un comando */
	{Comando}" "	{System.out.print(yytext());
					 yybegin(ARGUMENTOS);}

	/* Se reconoce una variable */
	{Variable}=		{variable = yytext().substring(0, yytext().length()-1);
					 valor = "";
					 yybegin(VARIABLE);}
	
	
	/* Para todo lo demás */
	[^] 			{ /* Ignorar */ }
}


<VARIABLE>	{
	
	/* Inicio de un texto (valor) */
	\"							{yybegin(TEXTO);}
	
	/* El valor es de otra variable */
	\${Variable}				{valor += TablaSimbolos.get(yytext());}
	
	/* Se reconoce un texto */
	{Especial} | [^\t\n\ ;|]	{valor += yytext();}
	
	/* Se reconoce un final de línea */
	[\t\n;|] 					{TablaSimbolos.put(variable, valor);
			 					 yybegin(YYINITIAL);}
}


<TEXTO> {

	/* El valor es de otra variable */
	\${Variable}			{valor += TablaSimbolos.get(yytext());}
	
	/* Se reconoce un texto */
	{Especial} | [^\"]		{valor += yytext();}
	
	/* Final de un texto (valor)*/
	\"  					{TablaSimbolos.put(variable, valor);yybegin(YYINITIAL);}
}


<ARGUMENTOS> {

	/* Se reconoce una variable */
	\${Variable} 	{System.out.print(TablaSimbolos.get(yytext()));}
	
	/* Para todo lo demás */
	[^]				{System.out.print(yytext());}
}

