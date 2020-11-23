import java.util.HashMap;
import java.util.Map;

public class MainClass {
	public static void main(String[] args) throws Exception {

		KeyValueStore store = new KeyValueStore();
		populateValues(store);
		getIndexValue(store);
	}

	private static void getIndexValue(KeyValueStore store) {
		store.getIndexValue("pollution_level", "high")
		.forEach(System.out::println);
	}

	private static void populateValues(KeyValueStore store) throws Exception {

		Map<String, Object> delhi = new HashMap<String, Object>();
		delhi.put("pollution_level", "true");
		delhi.put("population", "10 Million");
		store.addValue("delhi", delhi);

		Map<String, Object> jakarta = new HashMap<String, Object>();
		jakarta.put("Latitude", -6.0);
		jakarta.put("Longitude", 106.0);
		jakarta.put("pollution_level", "high");
		store.addValue("jakarta", jakarta);

		Map<String, Object> bangalore = new HashMap<String, Object>();
		bangalore.put("Latitude", 12.94);
		bangalore.put("Longitude", 77.64);
		bangalore.put("pollution_level", "high");
		bangalore.put("free_food", true);
		store.addValue("bangalore", bangalore);

		Map<String, Object> india = new HashMap<String, Object>();
		india.put("capital", "delhi");
		india.put("population", "1.2 Billion");
		store.addValue("india", india);

		Map<String, Object> crocin = new HashMap<String, Object>();
		crocin.put("category", "cold flu");
		crocin.put("manufacturer", "GSK");
		store.addValue("crocin", crocin);

		//store.printIndex();

	}

}
