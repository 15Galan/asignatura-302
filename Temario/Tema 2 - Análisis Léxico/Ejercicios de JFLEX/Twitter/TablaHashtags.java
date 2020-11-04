import java.util.HashMap;

// Adaptación de TablaSimbolos.java del ejercicio rmv

class TablaHashtags {
	protected static HashMap<String,Integer> hm = new HashMap<>();
	
	public static void put(String hashtag) {
		int veces;
		
		hashtag = hashtag.toLowerCase();
		
		if (!hm.containsKey(hashtag)) {
			veces = 1;
		
		} else {
			veces = hm.get(hashtag) + 1;
		}
		
		hm.put(hashtag, veces);		// Reemplaza el valor si ya existe
	}
	
	public static int get(String hashtag) {
		
		return hm.get(hashtag);
	}

	public static void dump() {
		if (!hm.isEmpty()) {
			for (String item : hm.keySet()) {
				System.out.println(item + ": " + hm.get(item));
			}
		
		} else {
			System.out.println("No hay hashtags");
		}
	}
}

