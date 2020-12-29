
/* Vacío */

%%

%int

%%

[^\t\n\r\ ]+		{wc.palabras++; wc.caracteres += yylength();}
\t | \  			{wc.caracteres++;}
\r | \n | \r\n		{wc.lineas++; wc.caracteres += yylength();}

