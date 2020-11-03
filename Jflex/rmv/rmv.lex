/*
Cada instrucción termina con \n ; |
Para leer el valor de una variable se utiliza $-> Se utiliza para acceder al valor, no para definirlas.
Si una variable no está definido su valor produce la cadena vacía.
Las cadenas de strings se representan con ""
A las variables se les asigna un valor con el operador = 
que debe de ir justo detrás, sin espacios en blanco.
*/

%%

sintaxis = [a-zA-Z_][a-zA-Z0-9_]*

%int

%xstate VAL
%xstate STR
%xstate COMMAND

%{
	String variable;
	String valor;
%}

%%

<YYINITIAL>{

{sintaxis}/=		{variable = yytext();valor = "";yybegin(STR);}		/* variable donde guarda el valor*/

{sintaxis}/" "		{System.out.print("\n"+yytext());yybegin(COMMAND);} 	/*shell command */

[^]			{ }							/*Otherwise nothing */
	
}

<STR>{

=				{ }

\"				{yybegin(VAL);}

{sintaxis}(\\).|[^;|\n=\"\t]+	{valor=yytext();}

[|\n\t;]			{TablaSimbolos.put(variable,valor);yybegin(YYINITIAL);}


[^]				{ }

}

<COMMAND>{

\${sintaxis}			{System.out.print(TablaSimbolos.get(yytext()));}

[^]				{System.out.print(yytext());}

}

<VAL>{

\${sintaxis}			{valor += TablaSimbolos.get(yytext());}

[^\"]|(\\).			{valor += yytext();}

\"				{TablaSimbolos.put(variable,valor);yybegin(YYINITIAL);}

[^]				{ }

}


