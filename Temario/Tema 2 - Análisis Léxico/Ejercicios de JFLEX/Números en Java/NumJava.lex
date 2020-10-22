
/* Vacío */

%%

Entero = [1-9][0-9]*
Octal = 0[0-7]+
Hexadecimal = 0x[0-9a-fA-F]+
Decimal = ([0-9]+\.[0-9]* | [0-9]*\.[0-9]+)[lL]?
Exponente = E-?[0-9]+

%%

/* Números enteros de una cifra */
[0-9]										{return new Yytoken(257, yytext());}

/* Números enteros de dos o más cifras */
{Entero}									{return new Yytoken(257, yytext());}

/* Números enteros en octal */
{Octal}										{return new Yytoken(257, yytext());}

/* Números enteros en hexadecimal */
{Hexadecimal}								{return new Yytoken(257, yytext());}

/* Números enteros largos en base 10, octal y hexadecimal */
({Entero} | {Octal} | {Hexadecimal})[lL]	{return new Yytoken(258, yytext());}

/* Números enteros incorrectos en octal */
0[0-9]+										{return new Yytoken(261, yytext());}

/* Números enteros incorrectos en hexadecimal */
[0-9]x[0-9a-zA-Z]*							{return new Yytoken(261, yytext());}

/* Números decimales sin exponente */
{Decimal}									{return new Yytoken(260, yytext());}


/* Saltos y espacios */
(' '|'\b'|'\t'|'\n')						{/* Ignorar */}

/* Salida por defecto */
[^]											{return new Yytoken(261, yytext());}

