
%%

DosVocales = [a-zA-Z]*[aeiouAEIOU][aeiouAEIOU][a-zA-Z]*

%% 

{DosVocales}[aeiouAEIOU]*					{cpv.A++;}

{DosVocales}[b-df-hj-np-tv-zB-DF-HJ-NP-TV-Z]			{cpv.C++;}

[a-zA-Z]+[aeiouAEIOU]						{cpv.B++;}

[a-zA-Z]*[b-df-hj-np-tv-zB-DF-HJ-NP-TV-Z]			{cpv.D++;}


[^]								{}
