import java.util.HashMap;

class TablaSimbolos {
	protected static HashMap<String,String> hm = new HashMap<String,String>();
	
	public static void put(String variable, String valor) {
		hm.put("$"+variable, valor);
	}
	
	public static String get(String variable) {
		String valor = hm.get(variable);
		return valor!=null ? valor : "";
	}

	public static void dump() {
		System.out.println("-------------");
		for (String item : hm.keySet()) {
		    System.out.println(item+"="+hm.get(item));
		}	
	}

}