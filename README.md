# Super Hash
Super Hash is a self-developed hybrid algorithm which combines concepts from the BM bad character shift, hashing from Rabin Karp, as well as a pre-check search of the head, body and tail of the query string from the Raita algorithm. The query string is split into rolling windows of size m/3 and used to generate a hash table with the hash values as the key and the bad window shift as the value, in contrast to BM’s bad character shift.

Before a search, the tail, head and body windows are sequentially checked with the target before an actual character-character search is done. If the check fails at any of the 3 searches, the iteration fails, and the pointer is shifted by searching the hash table for a matching window, similar to searching for a bad character in BM. If no instances of the window are found, m-w characters can be safely skipped. In essence, the pointer perpetually stays in the skipping loop until it finds a target which has matching windows for the tail, head and body. Super Hash then does a character-character search instead of the hash-search method to ensure no inaccurate string matches are derived due to hash collisions. This considerably reduces the average-case search time as the probability of all 3 windows matching is extremely low, allowing the algorithm to skip through the majority of the target string.

Super Hash has an average case time complexity of n/m, outperforming state-of-the-art searching algorithms in the average case

Main.java contains the menu for algorithm selection, as well as times the algorithms.

Users can input 
- 1,2,3 for algos
- "A","T","C","G","U" for string queries
- file name(for e.g YP_009742608) for loading FASTA files

Kindly create your own classes for the individual algorithm. The classes should follow this API and be imported in the Main.java file:
algo(query, target)

If you encounter an issue, do the following steps:
- File > Switch Workplace > Other > The repo you cloned
- Then right click on your repo in eclipse > Build Path > Configure Build Path > Libraries > Remove yrc-proteomics > Add External Jar > locate libs/trc-proteomics and add to ModulePath
