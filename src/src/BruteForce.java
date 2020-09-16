package src;

import java.util.ArrayList;

public class BruteForce {
	public static ArrayList<Integer> BFSearch(String query, String target) {
		int x = query.length();
		int y = target.length();
		int count_x = 0; // count of comparison
		int count_y = 0; // count of successful comparison
		
		// Array list to store all index positions of substring
		ArrayList<Integer> indexpos = new ArrayList<Integer>();
		
		int i ,j; //i is index for target, j is index for query
		
		final long startTime = System.nanoTime(); // Start timer
		for (i=0; i<y-x;i++) {
			j=0;
			while (j<x) {
				if (target.charAt(i+j) == query.charAt(j)) {
					count_x++; 
					count_y++;
					j++;
				}
				else {
					count_x++;
					break;
				}
			}
			if (j==x) {
				indexpos.add(i+1);
			}
		}
		
		final long endTime = System.nanoTime(); // End timer
		final long elapsedTime = (endTime - startTime)/1000;
		System.out.printf("Time taken: %d microseconds\n", elapsedTime);
		System.out.printf("Total no. of comparisons: %d\n", count_x);
		System.out.printf("Total no. of successful comparisons: %d\n", count_y);
		return indexpos;
		
	}

}
