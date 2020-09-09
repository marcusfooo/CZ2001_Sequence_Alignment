package src;

import java.util.*;

public class KMP{
	
	/*public static void main(String[] args) {
		
		ArrayList<Integer> positions = new ArrayList<Integer>();
			
		String target = "ABABDABACDABABCABAB"; 
        String query = "ABABCABAB"; 
        
        int x = query.length();
        int[] nums = new int[x];
         
        preprocess(query, x, nums);
        
        for (int j = 0;j <nums.length; j++) {
        	System.out.println(nums[j]);
        }
        
        positions = KMP.KMPSearch(query, target);
        
        for(Integer pos : positions) {
			System.out.print(pos + " ");
		}
		
	}*/
	
	public static ArrayList<Integer> KMPSearch(String query, String target) {
		int x = query.length();
		int y = target.length();
		
		// Array list to store all index positions of substring
		ArrayList<Integer> indexpos = new ArrayList<Integer>();
		
		int j = 0, i = 0; //i is index for query, j is index for target
		int[] nums = new int[x];
		
		preprocess(query, x, nums);
		
		final long startTime = System.nanoTime(); // Start timer
		while (i < y) {
			if (query.charAt(j) == target.charAt(i)) {
				i++;
				j++;
			}
			if (j == x) {
				//Entire substring has been found, add index position to indexpos
				indexpos.add(i - j + 1);
				j = nums[j - 1];
			} else if (i < y && query.charAt(j) != target.charAt(i)){
				if (j != 0) {
					j = nums[j - 1];
				} else {
					i = i + 1;
				}
			}
		}
		
		final long endTime = System.nanoTime(); // End timer
		final long elapsedTime = (endTime - startTime)/1000;
		System.out.printf("Time taken: %d microseconds\n", elapsedTime);		
		return indexpos;
		
	}
	
	public static void preprocess(String query, int x, int nums[]) {
		int i, mis, j;
		// initializing the array nums with 0's
		for (j=0; j<x; j++) {
			nums[j]=0;
		}
		
	    // nums[0] will always take on 0,
	    // so we will start from nums[1]
	    i = 1;
	    // mis will indicate the index of first mismatch
	    mis = 0;
	    
	    while (i < x) {
	        // prefix = suffix till mis-1
	        if (query.charAt(i) == query.charAt(mis)) {
	            mis += 1;
	            nums[i] = mis;
	            i += 1;
	        }
	    
	        // when there is a mismatch,
	        // we will check the index of previous
	        // possible prefix.
	        else if (query.charAt(i) != query.charAt(mis) & mis != 0) {
	            //we will not increment i here.
	            mis = nums[mis-1];
	        }
	        else {
	            // when mis = 0, we move to the next letter,
	            // and since there was no any prefix found which 
	            // is equal to the suffix for index i, 
	        	// keep the value as 0 for that letter
	            nums[i] = 0;
	            i += 1;
	        }
	    }
	}
	
}

