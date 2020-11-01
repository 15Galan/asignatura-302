
%%

%int

DosVocSeg = [a-zA-Z]*[aeiou][aeiou][a-zA-Z]*

%%

{DosVocSeg}[b-df-hj-np-tv-zB-DF-HJ-NP-TV-Z]+		{cpv.C++;}

{DosVocSeg}						{cpv.A++;}

[a-zA-Z]*[aeiouAEIOU]+					{cpv.B++;}

[a-zA-Z]*[b-df-hj-np-tv-zB-DF-HJ-NP-TV-Z]+		{cpv.D++;}

[^]							{ }
