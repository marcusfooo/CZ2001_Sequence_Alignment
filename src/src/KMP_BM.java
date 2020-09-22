package src;

import java.util.*;

public class KMP_BM{
	
	static int ALPHA = 256;	
	public static ArrayList<Integer> KMPSearch(String query, String target) {
		int x = query.length();
		int y = target.length();
		int count_x = 0, count_y = 0; //count_x is pattern hit, count_y is search count
		
		// Array list to store all index positions of substring
		ArrayList<Integer> indexpos = new ArrayList<Integer>();
		
		int j = 0, i = 0, last = x-1, check = last;//j is index for query, i is index for target

		int[] nums = preprocessKMP(query, x);
		int[] nums2 =preprocessBM(query, x);
		
		final long startTime = System.nanoTime(); // Start timer
		while (check<y) {
			
			if (j<=0) {
				while (query.charAt(last) != target.charAt(check)) {
					count_y++;
					if (check == (y - 1)) {
						final long endTime = System.nanoTime(); // End timer
						final long elapsedTime = (endTime - startTime)/1000;
						System.out.printf("Time taken: %d microseconds\n", elapsedTime);
						System.out.printf("Target search count: %d\n", count_y);
						System.out.printf("Pattern hit count: %d\n\n", count_x);
						return indexpos;
					}
					
					check += nums2[(int)target.charAt(check + 1)];
					if (check > (y - 1)) {
						final long endTime = System.nanoTime(); // End timer
						final long elapsedTime = (endTime - startTime)/1000;
						System.out.printf("Time taken: %d microseconds\n", elapsedTime);
						System.out.printf("Target search count: %d\n", count_y);
						System.out.printf("Pattern hit count: %d\n\n", count_x);
						return indexpos;
					}
				}
				
				i=check-last;
				while ((j<last) && (target.charAt(i)==query.charAt(j))) {
					count_x++;
					i++;
					j++;
				}
				if (j==last) {
					indexpos.add(i-last+1);
					i++;
				}
				if (j<=0) {i++;}
				else {
					count_y++;
					j= nums[j];
				}
			}
			else {
				while ((j<x) && (target.charAt(i)==query.charAt(j))){
					count_x++;
					i++;
					j++;
				}
				if (j==x) {
					indexpos.add(i-x+1);
				}
				j=nums[j];
				count_y++;

			}
			check = i + last -j;
		}
		
		final long endTime = System.nanoTime(); // End timer
		final long elapsedTime = (endTime - startTime)/1000;
		
		System.out.printf("Time taken: %d microseconds\n", elapsedTime);
		System.out.printf("Target search count: %d\n", count_y);
		System.out.printf("Pattern hit count: %d\n\n", count_x);		
		
		return indexpos;
		
	}
	
	public static int[] preprocessKMP(String query, int x) {
		int i, mis, j;
		int[] nums = new int[x];
		
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
	    
	    return nums;
	}
	
	public static int[] preprocessBM(String query, int x) {
		// Returns Bad Match Table
		// Value = length of substring – index of each letter in the substring – 1

		int[] nums2 = new int[ALPHA];
		/*for (int idx=0; idx< x; idx++) {
			nums2[idx] = x - idx - 1;
        }
		nums2[x-1] = x;*/
		for (int i = 0; i < ALPHA; i++) {
			nums2[i] = x + 1;
		}
		for (int i = 0; i < x; ++i) {
			nums2[(int)query.charAt(i)] = x - i;
		}
		return nums2;
	}
}