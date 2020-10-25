
/* Vacío */

%%

Palabra			= [a-zA-Z]*
Vocal			= [aeiou]
Consonante		= [b-df-hj-np-tv-z]
VocalesSeguidas	= {Palabra}{Vocal}{Vocal}{Palabra}

%%

/* Palabras con al menos 2 vocales seguidas
y que terminen también en vocal */
{VocalesSeguidas}					{cpv.A++;}

/* Palabras sin 2 vocales seguidas
y que terminen en consonante */
{Palabra}{Vocal}+					{cpv.B++;}

/* Palabras con al menos 2 vocales seguidas
y que terminen en consonante */
{VocalesSeguidas}{Consonante}+		{cpv.C++;}

/* Palabras sin 2 vocales seguidas
y que terminen en consonante */
{Palabra}{Consonante}+				{cpv.D++;}


/* Todo lo demás */
[^]									{/* Ignorar */}

