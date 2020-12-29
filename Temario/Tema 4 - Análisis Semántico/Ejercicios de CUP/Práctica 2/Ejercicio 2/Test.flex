// Este fichero no puede modificarse

import java_cup.runtime.*;

%%   

%cup 

%%

// Tipos
int         { return new Symbol(sym.INT); }
double      { return new Symbol(sym.DOUBLE); }

// Símbolos especiales
;           { return new Symbol(sym.PYC); }
,           { return new Symbol(sym.COMA); }
:           { return new Symbol(sym.DP); }

// Variables
[a-zA-Z][a-zA-Z0-9]*    { return new Symbol(sym.IDENT, yytext()); }

.|\n                    { }   

