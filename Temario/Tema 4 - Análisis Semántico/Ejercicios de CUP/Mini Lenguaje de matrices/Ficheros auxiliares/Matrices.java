import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;

public class Matrices {
	
	public final static String ERROR_FILAS   = "ERROR1: Todas las filas de la matriz deben tener la misma dimension";
	public final static String ERROR_INVERSA = "ERROR2: La operacion inversa() requiere una matriz cuadrada";
	public final static String ERROR_ADJUNTA = "ERROR3: La operacion adjunta() requiere una matriz cuadrada";
	public final static String ERROR_PROD    = "ERROR4: La operacion producto() requiere coincidencia de filas y columnas";
	public final static String ERROR_SUMA    = "ERROR5: La operacion suma() requiere matrices de iguales dimensiones";
	
	/** 
	 * Devuelve la inversa de una matriz
	 * @param matriz
	 * @return
	 */
    public static double[][] inversa(double[][] matriz) {
        double det=1/determinante(matriz);
        double[][] nmatriz=adjunta(matriz);
        nmatriz = producto(det,nmatriz);
        return nmatriz;
    }

    /**
     * Producto de un escalar por una matriz
     * @param n
     * @param matriz
     */
    public static double[][] producto(double n, double[][] matriz) {
    	double[][]  salida = new double[matriz.length][matriz[0].length];
        for(int i=0;i<matriz.length;i++)
            for(int j=0;j<matriz[0].length;j++)
                salida[i][j] = n*matriz[i][j];
        return salida;
    }

    /**
     * Producto de dos matrices
     * @param matriz1
     * @param matriz2
     */
    public static double[][] producto(double[][] matriz1, double[][] matriz2) {
    	double[][] salida = new double[matriz1.length][matriz2[0].length];
        for(int i=0;i<matriz1.length;i++)
            for(int j=0;j<matriz2[0].length;j++) {
            	salida[i][j] = 0;
        		for(int k=0;k<matriz1[0].length; k++) 
        			salida[i][j] += matriz1[i][k] * matriz2[k][j];
            }
        return salida;			
    }

    /**
     * Suma de un escalar por una matriz
     * @param n
     * @param matriz
     */
    public static double[][] suma(double n, double[][] matriz) {
    	double[][] salida = new double[matriz.length][matriz[0].length];
        for(int i=0;i<matriz.length;i++)
            for(int j=0;j<matriz[0].length;j++)
                salida[i][j] = n+matriz[i][j];
        return salida;
    }

    /**
     * Suma de dos matrices
     * @param matriz1
     * @param matriz2
     */
    public static double[][] suma(double[][] matriz1, double[][] matriz2) {
    	double[][] salida = new double[matriz1.length][matriz1[0].length];
        for(int i=0;i<matriz1.length;i++)
            for(int j=0;j<matriz2[0].length;j++) 
            	salida[i][j] = matriz1[i][j] + matriz2[i][j];
        return salida;			
    }

    /** Matriz adjunta de otra matriz
     * @param matriz
     * @return
     */
    public static double[][] adjunta(double [][] matriz){
        return transpuesta(cofactores(matriz));
    }

    /**
     * Matriz de cofactores
     * @param matriz
     * @return
     */
    public static double[][] cofactores(double[][] matriz){
        double[][] nm=new double[matriz.length][matriz.length];
        for(int i=0;i<matriz.length;i++) {
            for(int j=0;j<matriz.length;j++) {
                double[][] det=new double[matriz.length-1][matriz.length-1];
                double detValor;
                for(int k=0;k<matriz.length;k++) {
                    if(k!=i) {
                        for(int l=0;l<matriz.length;l++) {
                            if(l!=j){
                                int indice1=k<i ? k : k-1 ;
                                int indice2=l<j ? l : l-1 ;
                                det[indice1][indice2]=matriz[k][l];
                            }
                        }
                    }
                }
                detValor=determinante(det);
                nm[i][j]=detValor * (double)Math.pow(-1, i+j+2);
            }
        }
        return nm;
    }

    /**
     * Matriz transpuesta
     * @param matriz
     * @return
     */
    public static double[][] transpuesta(double [][] matriz){
        double[][]salida=new double[matriz[0].length][matriz.length];
        for(int i=0; i<matriz[0].length; i++){
            for(int j=0; j<matriz.length; j++)
                salida[i][j]=matriz[j][i];
        }
        return salida;
    }

    /**
     * Determinante de una matriz
     * @param matriz
     * @return
     */
    public static double determinante(double[][] matriz){
        double det;
        if(matriz.length==2){
            det=(matriz[0][0]*matriz[1][1])-(matriz[1][0]*matriz[0][1]);
            return det;
        }
        double suma=0;
        for(int i=0; i<matriz.length; i++){
        double[][] nm=new double[matriz.length-1][matriz.length-1];
            for(int j=0; j<matriz.length; j++){
                if(j!=i){
                    for(int k=1; k<matriz.length; k++){
                        int indice=-1;
                        if(j<i)
                            indice=j;
                        else if(j>i)
                            indice=j-1;
                            nm[indice][k-1]=matriz[j][k];
                    }
                }
            }
            if(i%2==0)
                suma+=matriz[i][0] * determinante(nm);
            else
                suma-=matriz[i][0] * determinante(nm);
        }
        return suma;
    }

    /**
     * Imprime un escalar
     * @param n
     */
    public static void print(double n) {
    	System.out.println(String.format("%4.2f", n));
    }
    
    /**
     * Imprime una matriz
     * @param matriz
     */
    public static void print(double[][] matriz) {
        for(int i=0; i<matriz.length; i++) {
            for(int j=0; j<matriz[0].length; j++)
                System.out.print(String.format("%7.2f",matriz[i][j]));
            System.out.println();
        }
    }

    /**
     * Imprime una matriz
     * @param matriz
     */
    public static void print(ArrayList<ArrayList<Double>> arrayList) {
    	print(toArray(arrayList));
    }

    /**
     * Numero de filas de una matriz
     * @param matriz
     */
    public static int filas(double[][] matriz) {
    	return matriz.length;
    }

    /**
     * Numero de columnas de una matriz
     * @param matriz
     */
    public static int columnas(double[][] matriz) {
    	return matriz[0].length;
    }

    /**
     * Convierte un ArrayList<ArrayList<Double>> en una matriz de tipo double[][]
     * @param matriz
     */
    public static double[][] toArray(ArrayList<ArrayList<Double>> arrayList) {
    	int nFilas = arrayList.size();
    	int nColumnas = arrayList.get(0).size();
    	double[][] salida = new double[nFilas][nColumnas];
    	for(int i=0; i<nFilas; i++) {
    		ArrayList<Double> fila = arrayList.get(i);
    		for(int j=0; j<nColumnas; j++) {
    			salida[i][j] = fila.get(j);
    		}
    	}
    	return salida;
    }
    
    /**
     * Convierte un una matriz de tipo double[][] en un ArrayList<ArrayList<Double>>
     * @param matriz
     */
    public static ArrayList<ArrayList<Double>> toArrayList(double[][] matriz) {
    	ArrayList<ArrayList<Double>> arrayList = new ArrayList();
    	int nFilas = filas(matriz);
    	int nColumnas = columnas(matriz);
    	for(int i=0; i<nFilas; i++) {
    		ArrayList<Double> fila = new ArrayList();
    		for(int j=0; j<nColumnas; j++) {
    			fila.add(matriz[i][j]);
    		}
    		arrayList.add(fila);
    	}
    	return arrayList;
    }
    
    public static boolean DEBUG = false;
    
    public static void main(String argv[]) {    
        try {
          parser p = null; 	
          int i=0;
          while (i<argv.length) {
              if ("+v".equals(argv[i])) {
            	  DEBUG = true;
              } else  {
                  p = new parser(new Yylex(new FileReader(argv[i])));
              } 
              i++;
          }
          if (p!=null) {
              p.parse();   
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }

    
}