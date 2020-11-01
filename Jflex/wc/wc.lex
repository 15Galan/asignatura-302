

%%

%%

\n				{wc.contlin++;wc.contcar+=yylength();}

[^\t\n\r\ ]+			{wc.contpal++;wc.contcar+=yylength();}

\t|\ 				{wc.contcar++;}

[^]				{ }
