import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import net.datastructures.LinkedQueue;


public class PopularWordsDriver {

	public static void main (String[] args) throws FileNotFoundException {
		ArrayList<String> fileInput = parseFile("params.txt");
		System.out.println(fileInput);
		displayHashmap("war_peace.txt", "name", null);
	}
	
	//parsing
	public static ArrayList<String> parseFile(String file) throws FileNotFoundException {
		File Resources = new File (file);
		Scanner myReader = new Scanner (Resources);
		ArrayList<String> wordList = new ArrayList<String>();
		while (myReader.hasNext()) {
			String word = myReader.next();
			wordList.add(word);
		}
		myReader.close();
		return wordList;
	}
	
	//check for errors and all the conditions inside the ArrayList
	public static ArrayList<String> wordFormat(ArrayList<String> wordList){
		ArrayList<String> actualwordList = new ArrayList<String>();
		for (int i = 0; i < wordList.size(); i++) {
			String curr = wordList.get(i);
			curr = curr.toLowerCase();
			
			if (curr.charAt(0) == '"') {
				curr = curr.substring(1, curr.length());
				curr = removePunctuation(curr);
				actualwordList.add(curr); 
				continue; 
			}
			
			if (curr.startsWith("'") && curr.endsWith("'")) { 
				curr = curr.substring(1, curr.length() - 1);
				curr = removePunctuation(curr);
				actualwordList.add(curr);
				continue;
			}
			
			if (curr.contains("--")) {
				String currSecond = curr;
				//first half of word
				curr = curr.substring(0, curr.indexOf("-")); 
				//second half of word
				currSecond = currSecond.substring(currSecond.indexOf("-")+2, currSecond.length()); 
				curr = removePunctuation(curr);
				currSecond = removePunctuation(currSecond);
				actualwordList.add(curr);
				actualwordList.add(currSecond);
				continue; 
			}
			
			if (curr.charAt(0) == '_') {
				curr = curr.substring(1, curr.length());
				curr = removePunctuation(curr);
				actualwordList.add(curr); 
				continue; 
			}
			
			if (curr.charAt(curr.length() - 1) == '"') {
				curr = curr.substring(0, curr.length()-1);
				curr = removePunctuation(curr);
				actualwordList.add(curr); 
				continue;
			}
			
			curr = removePunctuation(curr);
			actualwordList.add(curr);
		}
		return actualwordList;
	}
	
	//remove punctuation
	public static String removePunctuation (String word) { 
		CharSequence[] punctuations = {".", ",", "?", "!", ":", ";"}; 
		for (CharSequence mark: punctuations) {
			if (word.endsWith((String) mark)) {word = word.substring(0, word.length()-1);}
		}
		return word;
	}
	
	public static boolean isAlpha(String word) {
	    return word.matches("[a-zA-Z]+");
	}
	
	//place in hashmap
	public static HashMap<String, Integer> createMap(ArrayList<String> actualwordList) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < actualwordList.size(); i++) {
			String curr = actualwordList.get(i); 
			if (map.containsKey(curr)) { 
				map.put(curr, map.get(curr) + 1);
			}
			else { 
				map.put(curr, 1); 
			}
		}
		return map;
	}
	
	public static void displayHashmap (String file, String condition, String num) throws FileNotFoundException {
		ArrayList<String> wordList = parseFile(file);
		System.out.println(wordList);
		
		ArrayList<String> newWordList = wordFormat(wordList);
		System.out.println(newWordList);
		
		HashMap<String, Integer> wordMap = createMap(newWordList);
		
		//create linked queue of every entry set
		LinkedQueue<Map.Entry<String, Integer>> entryQueue = new LinkedQueue<Map.Entry<String, Integer>>();
		for (Map.Entry<String, Integer> entry : wordMap.entrySet()) { //add every entry into the Queue
			entryQueue.enqueue(entry);
		}
		
		//instantiate comparators
		FrequencyComp freqComp = new FrequencyComp();
		NameComp nameComp = new NameComp();
		ScarComp scarComp = new ScarComp();
		
		//frequency sort
		if (condition.toLowerCase().equals("frequency")) {
			MergeSort.mergeSort(entryQueue, freqComp);
			//if num not stated, print queue
			if (num == null || Integer.parseInt(num) > wordMap.size()) { 
				for (int i = 0; i < wordMap.size(); i++) {
					System.out.println(entryQueue.dequeue().toString());
				}
			}
			else {
				for (int i = 0; i < Integer.parseInt(num); i++) {
					System.out.println(entryQueue.dequeue().toString());
				}
			}
		}
		
		//name sort
		if (condition.toLowerCase().equals("name")) {
			MergeSort.mergeSort(entryQueue, nameComp);
			//if num not stated, print queue
			if (num == null || Integer.parseInt(num) > wordMap.size()) { 
				for (int i = 0; i < wordMap.size(); i++) {
					System.out.println(entryQueue.dequeue().toString());
				}
			}
			else {
				for (int i = 0; i < Integer.parseInt(num); i++) {
					System.out.println(entryQueue.dequeue().toString());
				}
			}
		}
		
		//scarcity sort
		if (condition.toLowerCase().equals("scarcity")) {
			MergeSort.mergeSort(entryQueue, scarComp);
			//if num not stated, print queue
			if (num == null || Integer.parseInt(num) > wordMap.size()) { 
				for (int i = 0; i < wordMap.size(); i++) {
					System.out.println(entryQueue.dequeue().toString());
				}
			}
			else {
				for (int i = 0; i < Integer.parseInt(num); i++) {
					System.out.println(entryQueue.dequeue().toString());
				}
			}
		}
	}
}
