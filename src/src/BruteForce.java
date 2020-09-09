package src;

import java.util.ArrayList;

public class BruteForce {
	public static ArrayList<Integer> BFSearch(String query, String target) {
		int x = query.length();
		int y = target.length();
		
		// Array list to store all index positions of substring
		ArrayList<Integer> indexpos = new ArrayList<Integer>();
		
		int i; //i is index for target
		
		final long startTime = System.nanoTime(); // Start timer
		for (i=0; i<y-x;i++) {
			String check = target.substring(i,i+x);
			if (query.equals(check)) {
				indexpos.add(i+1);
			}
		}
		
		final long endTime = System.nanoTime(); // End timer
		final long elapsedTime = (endTime - startTime)/1000;
		System.out.printf("Time taken: %d microseconds\n", elapsedTime);		
		return indexpos;
		
	}

}
