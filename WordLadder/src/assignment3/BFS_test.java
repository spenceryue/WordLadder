package assignment3;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class BFS_test {

	@Test
	public void test1() {
		// no duplicate inputs
		assertEquals(null,Main.getWordLadderBFS("aahed","aahed"));
	}
	
	@Test
	public void test2() {
		// no duplicates in word ladder
		HashSet<String> seen = new HashSet<String>();
		
		Set<String> dict = Main.makeDictionary();
		Object[] words = dict.toArray();
		
		// test 500 random start-end combinations
		for(int i=0; i<500; i++) {
			String start = (String)words[(int)Math.random()*words.length];
			String end = (String)words[(int)Math.random()*words.length];
			ArrayList<String> soln = Main.getWordLadderBFS(start,end);
			if (soln == null)
				continue;
			for (String s : soln)
				assert(seen.add(s)); // assert every word in soln is unique
			seen.clear();
		}
	}
	
	@Test
	public void test3() {
		// test the "shortest path" property of BFS
		
		// 1 letter difference ladder (0 rung)
		assertEquals(0+2,Main.getWordLadderBFS("yogis","yogin").size());
		
		// smart money (known to be 9 rung)
		assertEquals(9+2, Main.getWordLadderBFS("smart", "money").size());
	}
	
	@Test
	public void test4() {
		// proper handling of 5-letter words not in dict
		
		// 1 word not contained
		assertEquals(null,Main.getWordLadderBFS("aahed", "zzzzz"));
		assertEquals(null,Main.getWordLadderBFS("zzzzz", "aahed"));
		
		// both words not contained
		assertEquals(null,Main.getWordLadderBFS("aaaaa", "zzzzz"));
	}
	
	@Test
	public void test5() {
		// not 5-letter input
		
		// 1 word violation (>5 letters)
		assertEquals(null, Main.getWordLadderBFS("abadfagag", "aahed"));
		assertEquals(null, Main.getWordLadderBFS("aahed","abadfagag"));
		
		// 1 word violation (<5 letters)
		assertEquals(null, Main.getWordLadderBFS("aba", "aahed"));
		assertEquals(null, Main.getWordLadderBFS("aahed","aba"));
		
		// 2 word violation (both >5 letters)
		assertEquals(null, Main.getWordLadderBFS("abadfagag", "aahedagad"));
		
		// 2 word violation (both <5 letters)
		assertEquals(null, Main.getWordLadderBFS("ab", "a"));
		
		// 2 word violation (one >5 letters one <5 letters)
		assertEquals(null, Main.getWordLadderBFS("abadfagag", "a"));
		assertEquals(null, Main.getWordLadderBFS("a","abadfagag"));
	}
	
	@Test
	public void test6() {
		// words with no ladder
		// i.e. pick at least one word with no 1-letter difference
		// with any other word in the dictionary
		
		// zuzim cannot be converted by one letter difference into another word
		assertEquals(null,Main.getWordLadderBFS("aahed","zuzim"));
	}

}
