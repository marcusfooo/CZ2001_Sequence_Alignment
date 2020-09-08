package src;

import jav.util.ArrayList;

public class KMP {
	
	public static ArrayList<Integer> KMPSearch(String query, String target) {
		int x = query.length();
		int y = target.length();
		
		// Array list to store all index positions of substring
		ArrayList<Integer> indexpos = new ArrayList<Integer>();
		
		int j = 0, i = 0; //i is index for query, j is index for target
		int[] nums = new int[x];
		
		preprocess(query, x, nums);
		
		while (j < y) {
			if (query.charAt(i) == target.charAt(j)) {
				i++;
				j++;
			}
			if (i == x) {
				//Entire substring has been found, add index position to indexpos
				indexpos.add(j-i+2);
				i = nums[i - 1];
			} else if (j < y && query.charAt(i) != query.charAt(j)){
				if (i != 0) {
					i = nums[i - 1];
				} else {
					j++;
				}
			}
		}
		
		return indexpos;
		
	}
	
	public void preprocess(String query, int x, int nums[]) {
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
	        if (nums[i] == nums[mis]) {
	            mis += 1;
	            nums[i] = mis;
	            i += 1;
	        }
	    
	        // when there is a mismatch,
	        // we will check the index of previous
	        // possible prefix.
	        else if (nums[i] != nums[mis] & mis != 0) {
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

