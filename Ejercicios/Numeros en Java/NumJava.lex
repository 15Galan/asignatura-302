
%%

entero = [0-9] | [1-9][0-9]* 
decimal = [0-9]* "."? [0-9]+ | [0-9]+ "." [0-9]*
octal = 0[0-7]+
hexadecimal = 0x[0-9a-fA-F]+
exponente = E[+-]?[0-9]+ 

%% 

{entero}							    {return new Yytoken(Yytoken.TOKEN_CTE_ENTERO, yytext());}

{octal}									{return new Yytoken(Yytoken.TOKEN_CTE_ENTERO, yytext());}

{hexadecimal}							{return new Yytoken(Yytoken.TOKEN_CTE_ENTERO, yytext());}

{entero}[lL]							{return new Yytoken(Yytoken.TOKEN_CTE_ENTERO_LARGO, yytext());}

{octal}[lL]								{return new Yytoken(Yytoken.TOKEN_CTE_ENTERO_LARGO, yytext());}

{hexadecimal}[lL]						{return new Yytoken(Yytoken.TOKEN_CTE_ENTERO_LARGO, yytext());}

0[0-9]+[lL]?							{return new Yytoken(Yytoken.TOKEN_ERROR, yytext());}

[0-9]+x+[0-9a-zA-Z]*					{return new Yytoken(Yytoken.TOKEN_ERROR, yytext());}

{decimal}{exponente}?[fF]				{return new Yytoken(Yytoken.TOKEN_CTE_REAL_CORTO, yytext());}

{decimal}{exponente}?[dD]?				{return new Yytoken(Yytoken.TOKEN_CTE_REAL_LARGO, yytext());}

[0-9]+{exponente}[fF]						{return new Yytoken(Yytoken.TOKEN_CTE_REAL_CORTO, yytext());}

[0-9]+{exponente}[dD]?					{return new Yytoken(Yytoken.TOKEN_CTE_REAL_LARGO, yytext());}

(['.'0-9eE+-]* | [0-9]*\.?{exponente}{decimal}?)[dDfF]?  {return new Yytoken(Yytoken.TOKEN_ERROR, yytext());}

[0-1a-zA-Z]+							{return new Yytoken(Yytoken.TOKEN_ERROR, yytext());}

[^]										{}
