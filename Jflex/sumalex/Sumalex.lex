

%%

%%
[0-9]+     { 
                return new Yytoken(Yytoken.NUMERO, yytext());
           }
\+         { 
                return new Yytoken(Yytoken.MAS, "0");
           }  
\n         { 
                return new Yytoken(Yytoken.EOLN, "0");
           }  
.          {} 
