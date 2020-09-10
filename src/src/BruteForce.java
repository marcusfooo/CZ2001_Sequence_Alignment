package src;

import java.util.ArrayList;

public class BruteForce {
	public static ArrayList<Integer> BFSearch(String query, String target) {
		int x = query.length();
		int y = target.length();
		
		// Array list to store all index positions of substring
		ArrayList<Integer> indexpos = new ArrayList<Integer>();
		
		int i ,j; //i is index for target, j is index for query
		
		final long startTime = System.nanoTime(); // Start timer
		for (i=0; i<y-x;i++) {
			j=0;
			while (j<x && (target.charAt(i+j) == query.charAt(j))) {
				j++;
			}
			if (j==x) {
				indexpos.add(i+1);
			}
		}
		
		final long endTime = System.nanoTime(); // End timer
		final long elapsedTime = (endTime - startTime)/1000;
		System.out.printf("Time taken: %d microseconds\n", elapsedTime);		
		return indexpos;
		
	}

}
