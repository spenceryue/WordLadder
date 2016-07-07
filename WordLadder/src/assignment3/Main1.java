/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Fall 2015
 */


package assignment3;
import java.util.*;
import java.io.*;

public class Main {
//	static int index =0;

	public static void main(String[] args) {
		ArrayList<String> answer = new ArrayList<String>();
		Scanner kb = new Scanner(System.in);
		String userInput1 = kb.nextLine();
		String userInput2 = kb.nextLine();
		answer = getWordLadderDFS(userInput1,userInput2);
		System.out.println(answer);
		// TODO methods to read in words, output ladder

	}
	
	public static boolean dFS (String neighbor, String value, Set<String> visited, ArrayList<String> path,Set<String> dic){
		boolean wordLadder=false;
		int index=0;
		String holder = neighbor;
		String[] s1 = neighbor.split("");
		String[] s2 = value.split("");
		if(neighbor.equals(null)){
			return false;
		}
		if(neighbor.equals(value)){
			return true;
		}
		
		for(int x =0; x<value.length(); x++){
			if(s1[x].equals(s2[x])){
				index=x+1;
				if(index>=value.length()){
					index=0;
				}
				break;
			}
		}
		
		while(index<neighbor.length()){
			for(int i= 'a'; i<'{';i++){
				StringBuilder builder = new StringBuilder(5);

				char c = (char) i;// neighbor.charAt(index);
				s1[index]=String.valueOf(c);
				for(String s : s1) {
					builder.append(s);
				}
				neighbor = builder.toString();
				if(dic.contains(neighbor.toUpperCase()) && !visited.contains(neighbor)){
					visited.add(neighbor);
					path.add(neighbor);
					wordLadder=dFS(neighbor,value,visited,path,dic);
					if(wordLadder){
						return true;
					}
					path.remove(neighbor);
					break;
				}	
				visited.add(neighbor);
			}
			neighbor=holder.toString();
			s1 = neighbor.split("");
			index++;
		}
		
		return false;
	}
	
	
	
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		Set<String> dict = makeDictionary();
		boolean found=false;
//		String[] s1 = start.split("");
//		String[] s2 = end.split("");
		int counter=0;
		Set<String> visitedWords = new HashSet<String>();
		ArrayList<String> pathList = new ArrayList<String>();
		
		visitedWords.add(start);
		pathList.add(start);
		found = dFS(start,end,visitedWords,pathList,dict);
		
		if(found){
			return pathList;
		}
		System.out.println("wordladder doesn't exist");
		return null;
	}
	
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
		
		// TODO some code
		Set<String> dict = makeDictionary();
		// TODO more code
		
		return null; // replace this line later with real return
	}
    
	public static Set<String>  makeDictionary () {
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		try {
			infile = new Scanner (new File("five_letter_words.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary File not Found!");
			e.printStackTrace();
			System.exit(1);
		}
		while (infile.hasNext()) {
			words.add(infile.next().toUpperCase());
		}
		return words;
	}
}
