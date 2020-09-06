# CZ2001_Sequence_Alignment
CZ2001_Sequence Alignment
Group Project

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