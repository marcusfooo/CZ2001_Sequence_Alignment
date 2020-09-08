package src;

public class Rabin_Boyer {

	// http://www.cs.csi.cuny.edu/~zhangx/papers/P_2019_LISAT_Bicer_Zhang.pdf
	static int search(String query, String target) {
		int M = query.length(); 
        int N = target.length(); 
        int M1, skip; 
        int i = 0;
        int flag = 0;
        long thash1, thash2;
            
        // Half hashing
        M1 = M/2;
        
        long qhash1 = hash(query, 0, M1);
        long qhash2 = hash(query, M1, M);
        int[] BadMatchTable = BMT(query, M);
        
        for(i=0; i<=N-M; i++) {
        	skip = 1;
        	
    		if(query.charAt(M-1)==target.charAt(i+M-1)) {
    			thash1 = hash(target, i , i+M1);   			
    			if (thash1 == qhash1) {
    				thash2 = hash(target, i+M1 , i+M); 				
    				if (thash2 == qhash2) {
    					System.out.printf("Position at %d",i);
    					flag = 1;
    					return i;
    				}
    			}
    		}    
    		
    		for(int j=0;j<M;j++) {
    			if(query.charAt(M-j-1)==target.charAt(i+M-1)) {
    				skip = BadMatchTable[M-j-1] - 1;
    				break;
    			}   
    		}
    		
    		i += skip;
    		System.out.printf("Skipped %d chars\n", skip);
    		
        }
        if (flag==0) {
        	System.out.println("Not found");
        }
        
        return -1;
	}
	
	public static int[] BMT(String query, int M) {
		// Returns Bad Match Table
		// Value = length of substring – index of each letter in the substring – 1
		int[] BadMatchTable = new int[M];
		int idx;
		for (idx=0; idx< M; idx++) {
			BadMatchTable[idx] = M - idx - 1;
        }
		BadMatchTable[idx-1] = M;
		return BadMatchTable;
		
	}
	
	public static long hash(String query, int beginIndex, int endIndex) {
		// Returns hash for half a window
		String query_temp = query.substring(beginIndex, endIndex);
		return BPHash(query_temp);
	}
	
	// From https://github.com/ArashPartow/hash/blob/master/GeneralHashFunctions_-_Java/GeneralHashFunctionLibrary.java
	public static long BPHash(String str)
	   {
	      long hash = 0;

	      for(int i = 0; i < str.length(); i++)
	      {
	         hash = hash << 7 ^ str.charAt(i);
	      }

	      return hash;
	   }
	
	public static void main(String[] args) {

	}

}
