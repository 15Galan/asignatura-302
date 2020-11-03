
/* Vacío */

%%

%int

%xstate LINEA
%xstate BLOQUE
%xstate DOCUMENTACION

%%

<YYINITIAL> {
	
	/* Inicio de un
	comentario de línea */
	"//"			{yybegin(LINEA);}
	
	/* Inicio de un
	bloque de comentario */
	"/*"			{yybegin(BLOQUE);}
	
	/* Inicio de un
	comentario de documentación */
	"/**"			{yybegin(DOCUMENTACION);}
	
	/* Ignorar cualquier String */
	\"[^]*\"		{ }
	
	
	/* Cualquier otro lexema */
	[^]				{ }
}


<LINEA> {
	
	/* Cualquier caracter
	menos espacios y saltos */
	[^\s\r\n\t]		{JCom.linea++;}
	
	/* Fin del comentario */
	\n				{yybegin(YYINITIAL);}
	
	
	/* Cualquier otro lexema */
	[^]				{ }
}


<BLOQUE> {
	
	/* Cualquier caracter
	menos espacios y saltos */
	[^\s\r\n\t]		{JCom.bloque++;}
	
	/* Fin del comentario */
	"*/"			{yybegin(YYINITIAL);}
	
	
	/* Cualquier otro lexema */
	[^]				{ }
}


<DOCUMENTACION> {
	
	/* Cualquier caracter
	menos espacios y saltos */
	[^\s\r\n\t]		{JCom.documentacion++;}
	
	/* Fin del comentario */
	"*/"			{yybegin(YYINITIAL);}
	
	
	/* Cualquier otro lexema */
	[^]				{ }
}

