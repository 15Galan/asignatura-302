import java_cup.runtime.*;

%%

%cup

%%

/* Operadores */
"+"         {return new Symbol(sym.SUM);}
"-"         {return new Symbol(sym.RES);}
"*"         {return new Symbol(sym.MUL);}
"/"         {return new Symbol(sym.DIV);}
"("         {return new Symbol(sym.AP);}
")"         {return new Symbol(sym.CP);}

/* Número */
[0-9]+      {return new Symbol(sym.NUM, new Integer(yytext()));}

/* Cualquier otra cosa */
[\r\n]+     {return new Symbol(sym.FIN);}

[^]         { }

