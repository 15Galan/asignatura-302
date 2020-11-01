
%%

entero = [0-9]|[1-9][0-9]*
hexadecimal = 0x[0-9a-fA-F]+
octal = 0[0-7]+
exponente = E[+-]?[0-9]+
decimal = ([0-9]+\.?[0-9]*|[0-9]*\.?[0-9]+|[0-9]+\.?[0-9]+){exponente}?

%%

{entero}|{octal}|{hexadecimal}					{return new Yytoken(Yytoken.TOKEN_CTE_ENTERO,yytext());}

({entero}|{octal}|{hexadecimal})(l|L)				{return new Yytoken(Yytoken.TOKEN_CTE_ENTERO_LARGO,yytext());}

0[0-9]+(l|L)?							{return new Yytoken(Yytoken.TOKEN_ERROR,yytext());}

[0-9a-zA-z]+x[0-9a-zA-Z]*					{return new Yytoken(Yytoken.TOKEN_ERROR,yytext());}

{decimal}[fF]							{return new Yytoken(Yytoken.TOKEN_CTE_REAL_CORTO,yytext());}

{decimal}[dD]?							{return new Yytoken(Yytoken.TOKEN_CTE_REAL_LARGO,yytext());}

[0-9]+{exponente}[dD]? 			                        {return new Yytoken(Yytoken.TOKEN_CTE_REAL_LARGO, yytext());}

[0-9]+{exponente}[fF]                 			        {return new Yytoken(Yytoken.TOKEN_CTE_REAL_CORTO, yytext());}

(['.'0-9eE+-]* | [0-9]*\.?{exponente}{decimal}?)[dDfF]?         {return new Yytoken(Yytoken.TOKEN_ERROR, yytext());}

[0-1a-zA-Z]+                                			{return new Yytoken(Yytoken.TOKEN_ERROR, yytext());}

[\ \b\t\r\n]							{ }

