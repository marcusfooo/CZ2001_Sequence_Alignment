package src;

import java.util.ArrayList;

public class BruteForce {
	public static ArrayList<Integer> BFSearch(String query, String target) {
		int x = query.length();
		int y = target.length();
		// Array list to store all index positions of substring
		ArrayList<Integer> indexpos = new ArrayList<Integer>();
		
		int count_x = 0; // count of successful comparison
		int i ,j; //i is index for target, j is index for query
		
		final long startTime = System.nanoTime(); // Start timer
		for (i=0; i<=y-x;i++) {
			j=0;
			for(j=0;j<x;j++) {
				if(target.charAt(i+j)==query.charAt(j)) {
					count_x++;
					continue;
				}
				else break;
			}
			if (j==x) {
				indexpos.add(i+1);
			}
		}
		
		final long endTime = System.nanoTime(); // End timer
		final long elapsedTime = (endTime - startTime)/1000;
		System.out.printf("Time taken: %d microseconds\n", elapsedTime);	
		System.out.printf("Target search count: %d\n", y);
		System.out.printf("Pattern hit count: %d\n\n", count_x);
		return indexpos;
		
	}

}
