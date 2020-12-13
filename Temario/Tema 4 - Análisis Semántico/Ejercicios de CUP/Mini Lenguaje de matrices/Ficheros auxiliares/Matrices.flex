import java_cup.runtime.*;

%%
%cup

%%
\{                                                    { return new Symbol(sym.ALL); }
\}                                                    { return new Symbol(sym.CLL); }
\(                                                    { return new Symbol(sym.AP); }
\)                                                    { return new Symbol(sym.CP); }
\[                                                    { return new Symbol(sym.AC); }
\]                                                    { return new Symbol(sym.CC); }
\,                                                    { return new Symbol(sym.COMA); }
\;                                                    { return new Symbol(sym.PYC); }
\=                                                    { return new Symbol(sym.ASIG); }
\+                                                    { return new Symbol(sym.MAS); }
\-                                                    { return new Symbol(sym.MENOS); }
\*                                                    { return new Symbol(sym.POR); }
\/                                                    { return new Symbol(sym.DIV); }
inversa                                               { return new Symbol(sym.INVERSA, yytext() ); }
transpuesta                                           { return new Symbol(sym.TRANSPUESTA, yytext() ); }
adjunta                                               { return new Symbol(sym.ADJUNTA, yytext() ); }
print                                                 { return new Symbol(sym.PRINT, yytext() ); }
[a-zA-Z][a-zA-Z0-9]*                                  { return new Symbol(sym.IDENT, yytext() ); }
(0|[1-9][0-9]*)                                       |
(([0-9]*\.[0-9]+|[0-9]+\.[0-9]*)([eE][+-]?[0-9]+)?)   |
([0-9]*)([eE][+-]?[0-9]+)                             { return new Symbol(sym.NUMERO, new Double(yytext()) ); }
\/\/.*											      {  }
\r|\n                                                 {  }
\ |\t|\f                                              {  }
[^]                                                   { throw new Error("Illegal character <"+yytext()+">"); }

