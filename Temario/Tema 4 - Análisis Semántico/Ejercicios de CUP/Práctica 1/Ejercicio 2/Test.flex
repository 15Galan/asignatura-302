// Este fichero no puede modificarse

import java_cup.runtime.*;

%%

%cup

%%

/* Palabras reservadas*/
int     {return new Symbol(sym.INT);}
double  {return new Symbol(sym.DOUBLE);}

/* Fin de la definición */
;       {return new Symbol(sym.PYC);}

/* Variable */
[a-zA-Z][a-zA-Z0-9]*    {return new Symbol(sym.IDENT, yytext());}

/* Para todo lo demás */
. | \n                  { }

