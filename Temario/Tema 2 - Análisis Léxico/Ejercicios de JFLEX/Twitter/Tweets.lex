
/* Vacío */

%%

%int

Palabra		= [a-zA-Z0-9]+
Hashtags	= \#{Palabra}
Mencion		= \@[a-zA-Z0-9_]+
MencionErr	= \@[0-9_]+
Enlace		= https?:"//"{Palabra}(("." | "/"){Palabra})+

%%

/* Palabra */
{Palabra}			{Tweets.longitud += yylength();}

/* Hashtags */
{Hashtags}			{Tweets.longitud += yylength();
					 TablaHashtags.put(yytext());				 
					 Tweets.hashtags++;}

/* Mención incorrecta */
{MencionErr}		{Tweets.longitud += yylength();}

/* Mención */
{Mencion}			{Tweets.longitud += yylength();
					 Tweets.citas++;}

/* Enlace */
{Enlace}			{Tweets.longitud += yylength();
					 Tweets.enlaces++;}


/* Cualquier otra cosa */
[^]					{Tweets.longitud += yylength();}

