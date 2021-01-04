import java_cup.runtime.*;

%%

%int
%cup

%%

// Funciones del lenguaje
"if"        { return new Symbol(sym.IF);    }
"else"      { return new Symbol(sym.ELSE);  }
"do"        { return new Symbol(sym.DO);    }
"while"     { return new Symbol(sym.WHILE); }
"for"       { return new Symbol(sym.FOR);   }
"print"     { return new Symbol(sym.PRINT); }


// Aperturas y cierres
"("         { return new Symbol(sym.AP);    }
")"         { return new Symbol(sym.CP);    }

"["         { return new Symbol(sym.AC);    }
"]"         { return new Symbol(sym.CC);    }

"{"         { return new Symbol(sym.AL);    }
"}"         { return new Symbol(sym.CL);    }


// Operadores lógicos
"=="        { return new Symbol(sym.EQ);    }
"!="        { return new Symbol(sym.NE);    }
"<="        { return new Symbol(sym.LE);    }
"<"         { return new Symbol(sym.LT);    }
">="        { return new Symbol(sym.GE);    }
">"         { return new Symbol(sym.GT);    }

"&&"        { return new Symbol(sym.AND);   }
"||"        { return new Symbol(sym.OR);    }
"!"         { return new Symbol(sym.NO);    }


// Operadores matemáticos
"+"         { return new Symbol(sym.SUM);   }
"-"         { return new Symbol(sym.RES);   }
"*"         { return new Symbol(sym.MUL);   }
"/"         { return new Symbol(sym.DIV);   }

"="         { return new Symbol(sym.IGUAL); }


// Fin de sentencia
";"         { return new Symbol(sym.PYC);   }


// Valores
[0-9]+                  { return new Symbol(sym.NUM, yytext()); }
[_a-zA-Z][_a-zA-Z0-9]*  { return new Symbol(sym.VAR, yytext()); }



// Para todo lo demás
[^]         { }

