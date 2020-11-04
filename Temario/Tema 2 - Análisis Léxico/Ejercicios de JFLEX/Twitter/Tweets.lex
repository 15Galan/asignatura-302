import java.util.HashSet;
import java.util.Set;

%%

%int

Palabra		= [a-zA-Z0-9]+
Hashtags	= \#{Palabra}
Mencion		= \@[a-zA-Z0-9_]+
MencionErr	= \@[0-9_]+
Enlace		= https?:"//"{Palabra}(("." | "/"){Palabra})+

%{
	Set<String> tablaHashtags = new HashSet<>();
%}

%%

<YYINITIAL> {
	
	/* Palabra */
	{Palabra}			{Tweets.longitud += yylength();}
	
	/* Hashtags */
	{Hashtags}			{Tweets.longitud += yylength();
						 if (!tablaHashtags.contains(yytext().toLowerCase()) || Tweets.hashtags == 0) {
						 	tablaHashtags.add(yytext().toLowerCase());
						 							 	
						 } else {
						 	Tweets.repeticiones++;
						 };
						 
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
}

