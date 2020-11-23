import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class KeyValueStore {

	Map<String, Map<String, Object>> keyValue;
	
	Map<String, Object> attributeStore;
	
	Map<Attributes, List<String>> invertedIndex;

	
	public KeyValueStore() {
		keyValue = new HashMap<String, Map<String,Object>>();
		attributeStore = new HashMap<String, Object>();
		invertedIndex = new HashMap<Attributes, List<String>>();
	}
	
	public void addValue(String key, Map<String, Object> value) throws Exception {
		for (Entry<String, Object> entry: value.entrySet()) {
			if (attributeStore.get(entry.getKey()) != null && !entry.getValue().getClass().getTypeName().equals(attributeStore.get(entry.getKey()))) {
				throw new Exception(entry.getKey() + " NOT VALID for " + key + ". Expected " + attributeStore.get(entry.getKey()) + ", Found " + entry.getValue().getClass().getTypeName());
			} else {
				attributeStore.put(entry.getKey(), entry.getValue().getClass().getTypeName());
				
				Attributes attr = new Attributes(entry.getKey(), entry.getValue().toString());
				if (invertedIndex.get(attr) ==null) {
					List<String> listOfValues = new ArrayList<String>();
					listOfValues.add(key);
					invertedIndex.put(attr, listOfValues);
				} else {
					List<String> listOfValues = invertedIndex.get(attr);
					listOfValues.add(key);
					invertedIndex.put(attr, listOfValues);
				}
			}
		}
		keyValue.put(key, value);
	}
	
	public Map<String, Object> deleteValue(String key) {
		return keyValue.remove(key);
	}
	
	public List<String> getIndexValue(String attributeKey, Object attributeValue) {
		Attributes attr = new Attributes(attributeKey, attributeValue.toString());
		return invertedIndex.get(attr);
	}
	
	public void print() {
		System.out.println(attributeStore);
	}
	public void printStore() {
		System.out.println(keyValue);
	}
	public void printIndex() {
		invertedIndex.forEach((k,v)-> {
			System.out.print(k + " = ");
			v.forEach(item -> System.out.print(item));
			System.out.println();
		});
	}
}

class Attributes {
	String key;
	String value;
	
	public Attributes(String key, String value) {
		this.key = key;
		this.value = value;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public int hashCode() {
		int hash = 7;
		hash = 31*hash + this.getKey().length();
		hash = 31*hash + this.getValue().hashCode();
		return hash;
	}
	
	@Override
	public boolean equals(Object obj) {
		Attributes attr = (Attributes) obj;
		if (attr.getKey().equals(this.getKey()) && attr.getValue().equals(this.getValue())) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "[ " + this.getKey() + " : " + this.getValue() + " ]";
	}
	
}
