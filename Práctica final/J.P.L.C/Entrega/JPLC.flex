import java_cup.runtime.*;

%%

%cup
%int


// Terminadores de línea
FinLinea    = \r|\n|\r\n
Caracter    = [^\r\n]
LineaVacia  = {FinLinea} | [ \t\f]


// Comentarios
Linea1 	= "//" {Caracter}* {FinLinea}?
Bloque 	= "/*" [^*] ~"*/" | "/*" "*"+ "/"
Javadoc = "/*" "*"+ [^/*] ~"*/"
Linea2	= "#" {Caracter}* {FinLinea}?

Comentario  = {Bloque} | {Linea1} | {Javadoc} | {Linea2}


// Número Integer (entero)
NumEntero   = 0 | [1-9][0-9]*


// Número Double (punto flotante)
Literal1    = [0-9]+
Literal2    = \. [0-9]+
Literal3    = [0-9]+ \. [0-9]*
Exponente   = [eE] [+-]? [0-9]+

NumDouble   = ({Literal3} | {Literal2} | {Literal1}) {Exponente}?


// Variable
Variable    = [_a-zA-Z][_a-zA-Z0-9]*

%%

// Palabras reservadas
"int"       { return new Symbol(sym.INT);                               }
"if"        { return new Symbol(sym.IF, Generador.crearEtiqueta());     }
"else"      { return new Symbol(sym.ELSE);                              }
"while"     { return new Symbol(sym.WHILE, Generador.crearEtiqueta());  }
"main"	    { return new Symbol(sym.MAIN);                              }
"return"    { return new Symbol(sym.RETURN);                            }


// Aperturas y cierres
"("     { return new Symbol(sym.AP);    }
")"     { return new Symbol(sym.CP);    }

"{"     { return new Symbol(sym.AL);    }
"}"     { return new Symbol(sym.CL);    }


// Separadores
","	    { return new Symbol(sym.COMA);  }
";"     { return new Symbol(sym.PYC);   }


/* Operadores lógicos */
"<"     { return new Symbol(sym.LT);    }
">"     { return new Symbol(sym.GT);    }
"<="    { return new Symbol(sym.LE);    }
">="    { return new Symbol(sym.GE);    }
"=="    { return new Symbol(sym.EQ);    }
"!="    { return new Symbol(sym.NEQ);   }


/* Operadores matemáticos */
"+"     { return new Symbol(sym.SUM);   }
"-"     { return new Symbol(sym.RES);   }
"*"     { return new Symbol(sym.MUL);   }
"/"     { return new Symbol(sym.DIV);   }

"="     { return new Symbol(sym.IGUAL); }


// Valores
{NumEntero}     { return new Symbol(sym.NUMERO, new Integer(yytext())); }
{NumDouble}     { return new Symbol(sym.NUMEROF, new Double(yytext())); }

{Variable}      { return new Symbol(sym.VAR, yytext());                 }


// No-Código
{Comentario}    { }
{LineaVacia}    { }



// Para todo lo demás
[^]     { throw new Error("Carácter '"+yytext()+"' no reconocido.");    }

