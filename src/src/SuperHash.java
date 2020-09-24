package src;

import java.util.*; 

public class SuperHash {
	
	// https://peerj.com/preprints/1758.pdf
	public static ArrayList<Integer> search(String query, String target) {
		int m = query.length(); 
        int n = target.length();
        int w = m/3;
		int ptr = 0;
		int skip, suffixShift;
		long qHead = hash(query.substring(0, w), w);
		long qBody = hash(query.substring(m/3, m/3 + w), w);
		int count_x = 0;
		int count_y = 0;
		
		ArrayList<Integer> indexpos = new ArrayList<Integer>();
				
		// Pre-processing bad shift table
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
		try {
			while (ptr<n-m) {
				skip = 1;		
				while(skip!=0) {
					skip = shift(hmap, hash(getWindow(target, ptr, w, m), w));
					ptr += skip;
					count_y++;
				}
				
				if (hash(target.substring(ptr, ptr+w), w) == qHead) {
					if (hash(target.substring(ptr+m/3, ptr+w+m/3), w) == qBody) {
						int j = 0;
						while(j<m) {
						if(target.charAt(ptr+j)==query.charAt(j)) {
							count_x++;
							j++;						
							} else break;
						}
						if (j==m) {
							indexpos.add(ptr+1);
							ptr += suffixShift;
						}
					}
					

				}
						
				ptr += suffixShift;
				
			}
		}
		catch(Exception e) {
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
