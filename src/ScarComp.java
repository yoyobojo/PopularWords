import java.util.Comparator;
import java.util.Map.Entry;
import java.util.Map;

public class ScarComp implements Comparator<Map.Entry<String, Integer>>{
	public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
		if (e1.getValue() == e2.getValue()) {
			return e1.getKey().compareTo(e2.getKey());
		}
		else {
			return e1.getValue().compareTo(e2.getValue());	
		}
	}
}