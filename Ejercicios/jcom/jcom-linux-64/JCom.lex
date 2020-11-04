
%%

%int

%xstate BARRAS
%xstate BARRASTERISCO
%xstate BARRASTERISCO2

%%

<YYINITIAL>{

	\/\/					{yybegin(BARRAS);}
	
	\/\*					{yybegin(BARRASTERISCO);}

	\/\*\*					{yybegin(BARRASTERISCO2);}

	[^]					{}

}

<BARRAS>{

/*Cuenta todo lo que no sea un espacio, tabulador o salto de linea*/
	[^\t\n\ ]				{JCom.barras+=yylength();}

/*Cuando detecta un salto de linea acaba el comentario*/
	[\n]					{yybegin(YYINITIAL);}

/*Desagüe*/
	[^]					{}

}

<BARRASTERISCO>{

/*Cuenta todo lo que no sea un espacio, tabulador o salto de linea*/
	[^\t\n\ ]				{JCom.barraste+=yylength();}

/*Cuando detecta un asterisco-barra acaba el comentario*/
	\*\/					{yybegin(YYINITIAL);}

/*Desagüe*/
	[^]					{}

}

<BARRASTERISCO2>{

/*Cuenta todo lo que no sea un espacio, tabulador o salto de linea*/
	[^\t\n\ ]				{JCom.barraste2+=yylength();}

/*Cuando detecta un asterisco-barra acaba el comentario*/
	\*\/					{yybegin(YYINITIAL);}

/*Desagüe*/
	[^]					{}

}


