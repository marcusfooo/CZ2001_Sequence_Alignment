package src;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import org.yeastrc.proteomics.fasta.*;

public class Main {

	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		ArrayList<Integer> positions = new ArrayList<Integer>();
		ArrayList<Integer> menu = new ArrayList<Integer>();
		menu.add(1);
		menu.add(2);
		menu.add(3);
		int choice = 0;
		
		// User Menu
		while(!menu.contains(choice)) {
			try {
				System.out.println("Enter your choice of algorithms:\n1)Algo 1\n2)Algo 2\n3)Brute-Force\n");
				choice = sc.nextInt();
			}
			catch(Exception e) {
				sc.reset();
				sc.next();
				System.out.println("Please provide a valid Int input.");
				continue;
			}
		}			
		
		String query = userQuery();		
		String target = userTarget();
		
		// Start timer
		final long startTime = System.currentTimeMillis(); 
		switch(choice) {
		case 1:
			System.out.println("Run Algo 1");
			System.out.printf("Your query is %s\n", query);
			
			positions = KMP.KMPSearch(query, target);
			System.out.println("The positions of the queried strings are:");
			
			for(Integer pos : positions) {
				System.out.print(pos + " ");
			}
			System.out.println("");
			break;
		case 2:
			System.out.println("Run Algo 2");
			System.out.printf("Your query is %s\n", query);
			break;
		case 3:
			System.out.println("Run Brute Force");
			System.out.printf("Your query is %s\n", query);
			break;
		}
		
		final long endTime = System.currentTimeMillis();
		final long elapsedTime = endTime - startTime;
		System.out.printf("Time taken: %d\n", elapsedTime);
		System.out.printf("Target String:\n%s", target);
		sc.close();
	}
	
	private static String userQuery() {
		// Method for reading user input
		// Restrict User inputs
		
		sc.nextLine(); // Escape \n
		
		ArrayList<Character> proteins = new ArrayList<Character>();
		proteins.add('A');
		proteins.add('T');
		proteins.add('C');
		proteins.add('G');
		proteins.add('U');
		
		while(true) {
			try {
				System.out.println("Enter your String query input");
				String userInput = sc.nextLine().toUpperCase();
				for (int i=0;i < userInput.length(); i++){
					if(!proteins.contains(userInput.charAt(i))) {
						throw new Exception();
					}
				}
				
				return userInput;
			}
			catch(Exception e) {
				System.out.println("Please provide a valid String input.");
				sc.reset();
				continue;
			}
		}	
		
	}
	
	private static String userTarget() {
		// Method for reading user input
		// Restrict User inputs
				
		while(true) {
			try {
				System.out.println("Enter your Target file location");
				String user_file = sc.nextLine();
				String file_path =  "data/" + user_file;
				FASTAFileParser parser = FASTAFileParserFactory.getInstance().getFASTAFileParser(new File(file_path));
				for ( FASTAEntry entry = parser.getNextEntry(); entry != null;) {						
					System.out.println( "Found " + entry.getHeaders().size() + " headers for this FASTA entry." );
					return entry.getSequence();
										
				}


			}
			catch(Exception e) {
				System.out.println("Please provide a valid String input.");
				sc.reset();
				continue;
			}
		}	
		
	}

}
