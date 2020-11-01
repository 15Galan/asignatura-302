/*
Cada instrucción termina con \n ; |
Para leer el valor de una variable se utiliza $-> Se utiliza para acceder al valor, no para definirlas.
Si una variable no está definido su valor produce la cadena vacía.
Las cadenas de strings se representan con ""
A las variables se les asigna un valor con el operador = 
que debe de ir justo detrás, sin espacios en blanco.
*/

%%

variable = [a-zA-Z_][a-zA-Z0-9_]*

%xstate VAR
%xstate STR
%xstate COMMAND

%{
	String variable2;
	String valor;
%}

%%

<YYINITIAL>{

{variable}/\=		{variable2 = yytext();yybegin(STR);}   			/* variable donde guarda el valor*/
	
\"			{yybegin(STR);}   					/* String */ 

{variable}/\b		{System.out.printl("\n"+yytext());yybegin(COMMAND);} 	/*shell command */

\$			{yybegin(VAR);}						/*variable shell donde sustituir el valor */

[^]			{ }							/*Otherwise nothing */
	
}

<STR>{



[^]			{ }

}

<COMMAND>{



[^]			{ }

}

<VAR>{

{variable}		{TablaSimbolos.get(yytext());}



[^]			{ }

}


