
/* Vacío */

%%

Entero 		= [0-9] | [1-9][0-9]*
Octal 		= 0[0-7]+
Hexadecimal = 0x[0-9a-fA-F]+

Decimal 	= [0-9]+\.[0-9]* | [0-9]*\.[0-9]+
Exponente 	= [eE][-+]?[0-9]+

%%

/* Números enteros de dos o más cifras */
{Entero}									{return new Yytoken(Yytoken.TOKEN_CTE_ENTERO, yytext());}

/* Números enteros en octal */
{Octal}										{return new Yytoken(Yytoken.TOKEN_CTE_ENTERO, yytext());}

/* Números enteros en hexadecimal */
{Hexadecimal}								{return new Yytoken(Yytoken.TOKEN_CTE_ENTERO, yytext());}

/* Números enteros largos en base 10, octal y hexadecimal */
({Entero} | {Octal} | {Hexadecimal})[lL]	{return new Yytoken(Yytoken.TOKEN_CTE_ENTERO_LARGO, yytext());}

/* Números enteros incorrectos en octal */
0[0-9]+[lL]?								{return new Yytoken(Yytoken.TOKEN_ERROR, yytext());}

/* Números enteros incorrectos en hexadecimal */
(00+ | [0-9]*)x+[0-9a-zA-Z]*[lL]?			{return new Yytoken(Yytoken.TOKEN_ERROR, yytext());}


/* Números decimales sin exponente */
({Entero} | {Octal} | {Decimal})[dD]?		{return new Yytoken(Yytoken.TOKEN_CTE_REAL_LARGO, yytext());}
({Entero} | {Octal} | {Decimal})[fF]		{return new Yytoken(Yytoken.TOKEN_CTE_REAL_CORTO, yytext());}

/* Números decimales sin exponente, omitiendo dígitos antes o detrás del punto */
(\.[0-9]+ | [0-9]+\.)[dD]?					{return new Yytoken(Yytoken.TOKEN_CTE_REAL_LARGO, yytext());}
(\.[0-9]+ | [0-9]+\.)[fF]					{return new Yytoken(Yytoken.TOKEN_CTE_REAL_CORTO, yytext());}

/* Decimales con exponente */
{Decimal}{Exponente}[dD]?					{return new Yytoken(Yytoken.TOKEN_CTE_REAL_LARGO, yytext());}
{Decimal}{Exponente}[fF]					{return new Yytoken(Yytoken.TOKEN_CTE_REAL_CORTO, yytext());}

/* Números con mantisa entera y exponente */
[0-9]+{Exponente}[dD]?						{return new Yytoken(Yytoken.TOKEN_CTE_REAL_LARGO, yytext());}
[0-9]+{Exponente}[fF]						{return new Yytoken(Yytoken.TOKEN_CTE_REAL_CORTO, yytext());}

/* Números reales incorrectos */
(['.'0-9eE+-]* | [0-9]*\.?{Exponente}{Decimal}?)[dDfF]?		{return new Yytoken(Yytoken.TOKEN_ERROR, yytext());}


/* Números con letras o solo letras */
[0-1a-zA-Z]+								{return new Yytoken(Yytoken.TOKEN_ERROR, yytext());}


/* Saltos y espacios */
[\ \b\t\r\n]								{/* Ignorar */}

/* Salida por defecto (nada de lo anterior) */
[^]											{return new Yytoken(Yytoken.TOKEN_ERROR, yytext());}

