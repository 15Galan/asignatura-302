import java.util.HashMap;

public class TablaSimbolos {
	
	public final static String ERROR_NOEXISTE = "ERROR6: No se ha encontrado este indentificador en la tabla de simbolos";
	
	private static HashMap<String,double[][]> tabla = new HashMap<String,double[][]>();
	
	public static double[][] buscar(String ident) {
		return tabla.get(ident);
	}
	
	public static void insertar(String ident, double[][] matriz) {
		tabla.put(ident, matriz);
	}
	
}