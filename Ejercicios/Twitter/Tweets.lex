
%%

Enlace = "http://" | "https://"
DominioValido = [a-zA-Z0-9\-]+[a-zA-Z0-9\-\.\/]+
UsuarioValido = [a-zA-Z0-9]+[a-zA-Z_]+[a-zA-Z0-9_]* | [a-zA-Z_]+[a-zA-Z0-9]+[a-zA-Z0-9_]*

%int

%%

<YYINITIAL>{

	{Enlace}{DominioValido}					{Tweets.longitud+= yylength(); Tweets.enlaces++;}

	@{UsuarioValido}						{Tweets.longitud += yylength(); Tweets.citas++;}

	#[a-zA-Z0-9]+							{Tweets.longitud += yylength(); Tweets.añadir(yytext()); Tweets.hashtags++;}

	[^]										{Tweets.longitud += yylength();}

}
