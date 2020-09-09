package src;

import java.util.ArrayList;

public class Double_Hash {

	// http://www.cs.csi.cuny.edu/~zhangx/papers/P_2019_LISAT_Bicer_Zhang.pdf
	public static ArrayList<Integer> DHSearch(String query, String target) {
		int M = query.length(); 
        int N = target.length(); 
        int M1, skip; 
        int i = 0;
        int flag = 0;
        long thash1, thash2;
        ArrayList<Integer> indexpos = new ArrayList<Integer>();
            
        // Half hashing
        M1 = M/2;
        
        long qhash1 = hash(query, 0, M1);
        long qhash2 = hash(query, M1, M);
        int[] BadMatchTable = BMT(query, M);

        final long startTime = System.nanoTime(); // Start timer
        while (i<=N-M) {
        	skip = 1;
    		if(query.charAt(M-1)==target.charAt(i+M-1)) {
    			thash1 = hash(target, i , i+M1);  
    			if (thash1 == qhash1) {
    				thash2 = hash(target, i+M1 , i+M);				
    				if (thash2 == qhash2) {
    					flag = 1;
    					indexpos.add(i+1); //Add 1 to index for position
    				}
    			}
    		} 
    		
    		if(query.charAt(M-1)!=target.charAt(i+M-1)) {
    			for(int j=0;j<M;j++) {
        			if(query.charAt(M-j-1)==target.charAt(i+M-1)) {
        				skip = BadMatchTable[M-j-1];   
        				break;
        			}          			  			
        		}   
    		}
    				
    		i += skip;
        }
        
        if (flag==0) {
        	System.out.println("Not found");
        }
        
        final long endTime = System.nanoTime(); // End timer
        final long elapsedTime = (endTime - startTime)/1000;
		System.out.printf("Time taken: %d microseconds\n", elapsedTime);
        return indexpos;
	}
	
	private static int[] BMT(String query, int M) {
		// Returns Bad Match Table
		// Value = length of substring – index of each letter in the substring – 1
		int[] BadMatchTable = new int[M];
		for (int idx=0; idx< M; idx++) {
			BadMatchTable[idx] = M - idx - 1;
        }
		BadMatchTable[M-1] = M;
		return BadMatchTable;
		
	}
	
	private static long hash(String query, int beginIndex, int endIndex) {
		// Returns hash for half a window
		String query_temp = query.substring(beginIndex, endIndex);
		return BPHash(query_temp);
	}
	
	// From https://github.com/ArashPartow/hash/blob/master/GeneralHashFunctions_-_Java/GeneralHashFunctionLibrary.java
	private static long BPHash(String str)
	   {
	      long hash = 0;

	      for(int i = 0; i < str.length(); i++)
	      {
	         hash = hash << 7 ^ str.charAt(i);
	      }

	      return hash;
	   }

}