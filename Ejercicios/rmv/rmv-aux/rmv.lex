
%%

Variable = [a-zA-Z_][0-9a-zA-Z_]*

%{
String variable, valor;
%}

%int

%xstate VALOR
%xstate VARIABLE
%xstate COMANDO

%%


<YYINITIAL>{
	
/*Encuentra una variable a almacenar y llama al contexto variable*/
	{Variable}/=			{System.out.println("[v22"+ yytext()+"]");variable = yytext(); valor = ""; yybegin(VARIABLE);}
	
/*Este caso se da en los comandos (por ejemplo al detectar "echo ". Lo imprime sin más*/
	{Variable}/\ 				{System.out.print(yytext()); yybegin(COMANDO);}
	
	
/*Desagüe*/
	[^]							{ }

}

<VARIABLE>{      

	\"						{System.out.println("[v1"+ yytext()+"]");yybegin(VALOR);}

	\${Variable}					{System.out.println("[v2"+ yytext()+"]");valor += TablaSimbolos.get(yytext());}
	
	[^=;|\n\"]					{System.out.println("[v3"+ yytext()+"]");valor += yytext(); }
	
	[;|\n]						{System.out.println("[v4"+ yytext()+"]");TablaSimbolos.put(variable, valor); yybegin(YYINITIAL);}

	[^]						{System.out.println("[desague  "+ yytext()+"]");}

}

<VALOR>{
	
	\${Variable}					{valor += TablaSimbolos.get(yytext());}

	[^\"]+						{valor += yytext();}
	
	\"						{TablaSimbolos.put(variable, valor); yybegin(YYINITIAL);}
	
	[^]						{}

}

<COMANDO>{
	
	
	\${Variable}				{System.out.print(TablaSimbolos.get(yytext()));}
	
	[^]							{System.out.print(yytext()); }

}



