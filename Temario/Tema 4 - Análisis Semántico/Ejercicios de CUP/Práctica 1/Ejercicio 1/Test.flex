// Este fichero no puede modificarse

import java_cup.runtime.*;

%%

%cup

%%

[a-zA-Z][a-zA-Z0-9]*    {return new Symbol(sym.IDENT, yytext());}

0 | [1-9][0-9]*         {return new Symbol(sym.NUMERO, yytext());}

. | \n                  { }

