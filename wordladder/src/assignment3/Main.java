/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Joseph Akpala
 * jfa442
 * Spencer Yue
 * sty223
 * Slip days used: <0>
 * Summer 2016
 */


package assignment3;
import java.util.*;
import java.io.*;

public class Main {
	
	private final static Set<String> dict = makeDictionary();
	// Note:
	// It would be more convenient for you in DFS if the dictionary were global.
	// On the other hand, it also makes no sense in BFS to re-make the dictionary every time.
	// So I moved this construction to the class level and made it static.

	public static void main(String[] args) {
		// flags to specify the algorithm
		boolean bfs = false;
		boolean dfs = false;
		
		// parse args[]
		if (args.length != 1)
			usage(0);
		else if (args[0].toLowerCase().equals("-b"))
			bfs = true;
		else if (args[0].toLowerCase().equals("-d"))
			dfs = true;
		else
			usage(0);
		
		Scanner kb = new Scanner(System.in);
		
		// parse program commands and inputs
		while (kb.hasNextLine()) {
			String line = kb.nextLine();
			String[] tok = line.split(" ");
			
			// white space / invalid command
			if (tok.length == 0)
				continue;
			if (tok.length > 2)
				usage(1);
			
			// "/quit"?
			if (tok[0].startsWith("/")) {
				if (tok[0].toLowerCase().equals("/quit"));
				else
					System.err.println("invalid command "+tok[0]);
				break;
			} else if (tok.length == 1) {
				usage(1);
				continue;
			}
			
			// get inputs for ladder
			String start = tok[0];
			String end = tok[1];
			
			// build ladder (use algorithm flags)
			ArrayList<String> soln = null;
			if (bfs)
				soln = getWordLadderBFS(start,end);
			else if (dfs)
				soln = getWordLadderDFS(start,end);
			
			// print ladder
			if (soln == null)
				System.out.println("no word ladder can be found between "+start+" and "+end+".\n");
			else {
				System.out.println("a "+(soln.size()-2)+"-rung word ladder exists between "+start+" and "+end+".");
				for (String s : soln)
					System.out.println("\t"+s);
				System.out.println();
			}		
		}
		
		kb.close();
	}
	
	public static void usage(int v) {
		if (v==0) {
		System.err.println("WordLadder usage (choose only one argument): java assignment3.Main [-b] [-d]");
        System.err.println("\t-b\tUse BFS algorithm");
        System.err.println("\t-d\tUse DFS algorithm");
        System.exit(1);
		} else {
        System.err.println("Accepted usage (choose only one argument): [/quit] [<start> <end>]");
        System.err.println("\t/quit\t\tQuits program.");
        System.err.println("\t<start> <end>\tFinds a word ladder between starting word <start> and ending word <end>.");
		}
	}
	
	public static ArrayList<String> getWordLadderDFS(String start, String end) {

		// TODO some code

		// Set<String> dict = makeDictionary();
		
		// TODO more code
		
		return null; // replace this line later with real return
	}
	
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
    	
    	start = start.toUpperCase();
    	end = end.toUpperCase();
    	
    	// start equals end
    	if (start.equals(end))
			return null;
		
		// start or end not in dictionary
		if (!(dict.contains(start)&&dict.contains(end)))
			return null;
		
		// Data structures for BFS
		Queue<String> q = new LinkedList<String>();
		Set<String> discovered = new HashSet<String>(8938);
		Map<String,String> parent = new HashMap<String,String>(8938);
		char[] letter = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		
		// initialization
		q.add(start); discovered.add(start); parent.put(start, null);
		
		// BFS
		FindingLadder : 
		// while: there are more words to explore
		while (!q.isEmpty()) {
			String current = q.remove();
			// iterate: 5-letter words
			for (int i=0; i<5; i++)
				// iterate: 26 letters
				for (int j=0; j<26; j++)
					if (letter[j]==current.charAt(i))
						continue;
					else {
						// possible new word
						String next = current.substring(0,i)
									+ String.valueOf(letter[j])
									+ current.substring(i+1,5);
						// already discovered
						if (discovered.contains(next));
						else
							// end found
							if (next.equals(end)) {
								parent.put(end,current);
								break FindingLadder;
							// add new word to queue
							} else if (dict.contains(next)){
								q.add(next);
								discovered.add(next);
								parent.put(next,current);
							}
					}
		}
		
		// ladder not found
		if (!parent.containsKey(end))
			return null;
		
		// destroy the q queue, dict set, and discovered set
		q = null; discovered = null;
		System.gc();
		
		// use a stack to reverse order of ladder
		Stack<String> ladder = new Stack<String>();
		ladder.add(end);
		String current = end;
		while ((current = parent.get(current)) != null)
			ladder.add(current);
		
		// pop stack into array in correct order
		ArrayList<String> soln = new ArrayList<String>(ladder.size());
		while (!ladder.isEmpty())
			soln.add(ladder.pop().toLowerCase());
		
		return soln;
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
