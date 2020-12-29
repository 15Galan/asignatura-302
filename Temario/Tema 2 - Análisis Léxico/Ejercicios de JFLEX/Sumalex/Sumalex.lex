
/* Vacío */

%%

%%

/* Número */
[0-9]+     {return new Yytoken(Yytoken.NUMERO, yytext());}

/* Operador de suma */
\+         {return new Yytoken(Yytoken.MAS, "0");}

/* Salto de línea */
\n         {return new Yytoken(Yytoken.EOLN, "0");}  

/* Todo lo demás */
.          {}

