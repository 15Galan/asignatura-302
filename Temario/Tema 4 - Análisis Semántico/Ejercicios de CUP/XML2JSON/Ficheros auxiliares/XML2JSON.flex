import java_cup.runtime.*;
%%

/*  Declaraciones */   

%standalone
%cup 

%%   
   
    \<[a-zA-Z0-9]+\>     { return new Symbol(sym.OPEN_TAG, yytext().substring(1,yytext().length()-1)); }
    \<\/[a-zA-Z0-9]+\>   { return new Symbol(sym.CLOSE_TAG, yytext().substring(2,yytext().length()-1)); }
    [a-zA-Z0-9]+         { return new Symbol(sym.TEXT, yytext() ); }
    \r|\n                { }
    \ |\t\f              { }
    [^]                  { throw new Error("Illegal character <"+yytext()+">"); }

