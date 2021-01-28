int x;
int y;
x=y=1;
if (x==y) {
   y = y+2;
   if (x<y) {
      int y;
      x = y = x+y+3;
      print(x+y);
   }
   x = x*y+4;
   print(x);
}
y = x*y+7;
print(y);

