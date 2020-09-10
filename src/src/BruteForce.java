package src;

import java.util.ArrayList;

public class BruteForce {
	public static ArrayList<Integer> BFSearch(String query, String target) {
		int x = query.length();
		int y = target.length();
		
		// Array list to store all index positions of substring
		ArrayList<Integer> indexpos = new ArrayList<Integer>();
		
		int i, j; //i is index for query, j is index for target
		
		final long startTime = System.nanoTime(); // Start timer
		
		for (j=0; j<y-x;j++) {
			for (i=0; i<x; i++)
			{
				if (target.charAt(i+j) != query.charAt(i))
					break;
			}
			if (i==x)
				indexpos.add(j+1);
			}
		final long endTime = System.nanoTime(); // End timer
		final long elapsedTime = (endTime - startTime)/1000;
		System.out.printf("Time taken: %d microseconds\n", elapsedTime);		
		return indexpos;
		
	
	
		
	}
}


