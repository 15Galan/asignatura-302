
/* Vacío */

%%

%int

Palabra			= [a-zA-Z]*
Vocal			= [aeiouAEIOU]
Consonante		= [b-df-hj-np-tv-zB-DF-HJ-NP-TV-Z]
VocalesSeguidas	= {Palabra}{Vocal}{Vocal}{Palabra}

%%

/* Palabras con al menos 2 vocales seguidas
y que terminen en consonante */
{VocalesSeguidas}{Consonante}+		{cpv.C++;}

/* Palabras con al menos 2 vocales seguidas
y que terminen también en vocal */
{VocalesSeguidas}					{cpv.A++;}

/* Palabras sin 2 vocales seguidas
y que terminen en consonante */
{Palabra}{Consonante}+				{cpv.D++;}

/* Palabras sin 2 vocales seguidas
y que terminen en vocal */
{Palabra}{Vocal}+					{cpv.B++;}


/* Todo lo demás */
[^]									{/* Ignorar */}

