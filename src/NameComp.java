import java.util.Comparator;
import java.util.Map.Entry;
import java.util.Map;

public class NameComp implements Comparator<Map.Entry<String, Integer>>{
	public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
		return e1.getKey().compareTo(e2.getKey());
	}
}