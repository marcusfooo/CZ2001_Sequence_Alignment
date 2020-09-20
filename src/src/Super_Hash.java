package src;

import java.util.*; 

public class Super_Hash {
	
	// https://peerj.com/preprints/1758.pdf
	public static ArrayList<Integer> search(String query, String target, int w) {
		int m = query.length(); 
        int n = target.length();
		int ptr = 0;
		long key;
		int skip, suffixShift;
		long qhash1 = hash(query.substring(0, w), w);
		int count_x = 0;
		int count_y = 0;
		
		ArrayList<Integer> indexpos = new ArrayList<Integer>();
				
		// Pre-processing bad shift table
		char sentinel = query.charAt(0); // Wanted to use sentinel as stopping char for other algos
		String temp = target + sentinel;
		HashMap<Long, Integer> hmap = Hash_Table(query, m, w);
		String suffix = query.substring(m-w,m);
		long suffix_key = hash(suffix, w);
		
		if (hmap.containsKey(suffix_key)) {
			suffixShift = hmap.get(suffix_key);
			hmap.replace(suffix_key, 0);		
		}
		else {
			suffixShift = m-w;
			hmap.put(suffix_key, 0);
		}		
				
		final long startTime = System.nanoTime(); // Start timer
		while (ptr < n-m) {
			key = hash(getWindow(temp, ptr, w, m), w);
			skip = shift(hmap, key);
			
			while(skip!=0) {
				if(ptr+skip>n-m) break;
				skip = shift(hmap, hash(getWindow(temp, ptr, w, m), w));
				ptr += skip;
			}
			count_y++;
				
			if (ptr > n) {
				break;
			}
			
			if (hash(temp.substring(ptr, ptr+w), w) == qhash1) {
				int j = w;
				while(j<m-w) {
				if(temp.charAt(ptr+j)==query.charAt(j)) {
					count_x++;
					j++;						
					} else break;
				}
				if (j==m-w) {
					indexpos.add(ptr+1);
					ptr += suffixShift;
				}

			}
					
			ptr += suffixShift;
			
		}
        
        final long endTime = System.nanoTime(); // End timer
        final long elapsedTime = (endTime - startTime)/1000;
		System.out.printf("Time taken: %d microseconds\n", elapsedTime);
		System.out.printf("Target search count: %d\n", count_y);
		System.out.printf("Pattern hit count: %d\n\n", count_x);
		
        return indexpos;
	}
	
	private static String getWindow(String temp, int ptr, int w, int m) {
		//Returns suffix window
		return temp.substring(ptr+m-w, ptr+m);
	}
	
	private static long hash(String str, int w) {
		long hashVal = 0;
		long seed = 131;
		for(int i=0;i<w;i++) {
			hashVal = (hashVal * seed + str.charAt(i));
		}
		
		return hashVal;
	}
	private static HashMap<Long, Integer> Hash_Table(String query, int m, int w) {
		long key;
		int value;
		
		HashMap<Long, Integer> hmap = new HashMap<Long, Integer>();
		// Rolling window except for suffix
		for(int i=0;i<m-w;i++) {
			char[] str = new char[w];
			for(int j=0;j<w;j++) {
				str[j] = query.charAt(i+j);
			}
			String infix = new String(str);
			key = hash(infix, w);
			value = m-w-i;
			hmap.put(key, value);
		}
		return hmap;
	}
	
	private static int shift(HashMap<Long, Integer> hmap, long key) {
		int skip = hmap.getOrDefault(key, 0);
		return skip;
	}
	

}
