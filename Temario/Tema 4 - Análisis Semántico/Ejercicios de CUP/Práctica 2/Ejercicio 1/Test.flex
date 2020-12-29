// Este fichero no puede modificarse

import java_cup.runtime.*;

%%

%cup

%%

0 | [1-9][0-9]*     { return new Symbol(sym.NUMERO, new Integer(yytext())); }

. | \n              { }

