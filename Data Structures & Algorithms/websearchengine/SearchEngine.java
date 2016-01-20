/**
 * Dennis Sosa
 * Assignment: #7 (Directed Graph of WebPage Objects)
 * CSE 214 
 * Stony Brook University
 * @author DennisSosa
 * 
 * SearchEngine Class
 */
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class SearchEngine {
	public static final String PAGES_FILE = "pages.txt";
	public static final String LINKS_FILE = "links.txt";
	private static WebGraph web;

	/**
	 * Provide a menu prompt and implements the main method which runs 
	 * a menu driven application which allows the user to interact
	 * with WebGraph operations.
	 * @param args
	 * 		empty command-line argument
	 */
	public static void main(String[] args){
		Scanner in = new Scanner(System.in); //Scanner for user-input
		
		boolean cont = true;

		String selection = "";
		char selChar = ' ';
		
		System.out.println("Welcome to Dennis Sosa's Directed Graph of WebPage Objects.\n");
		try{
			WebGraph webTemp = new WebGraph();
			System.out.println("Loading WebGraph data...");
			web = webTemp.buildFromFiles(PAGES_FILE,LINKS_FILE);
			System.out.println("Success!");
		} catch(IllegalArgumentException ex){
			System.out.println(ex.getMessage());
		} catch(FileNotFoundException ex){
			System.out.println(ex.getMessage());
		}
				
		while(cont==true){ //program will continue as long as cont remains true
			//Main menu of program
			System.out.println();
			System.out.println("Menu:");
			System.out.println("\t(AP) - Add a new page to the graph.");
			System.out.println("\t(RP) - Remove a page from the graph.");
			System.out.println("\t(AL) - Add a link between pages in the graph.");
			System.out.println("\t(RL) - Remove a link between pages in the graph.");
			System.out.println("\t(P) - Print the graph.");
			System.out.println("\t(S) - Search for pages with a keyword.");
			System.out.println("\t(Q) - Quit.");
			System.out.println();
			
			System.out.print("Please select an option: ");
			
			selection = in.nextLine().toUpperCase();
			
			if(!selection.equals(""))
				selChar = selection.charAt(0); //reads char at position 0 of String, which should be expected
										   //to be only one character

			if(selection.length() > 2 || selection.length() < 1) 
				System.out.println("\nInvalid Entry, please try again.");

			else if(selection.equals("AP")){
				System.out.print("Enter a URL: ");
				String addUrl = in.nextLine();
				System.out.print("Enter keywords (space-seperated): ");
				
				String[] keyArr = in.nextLine().trim().split(" ");
				ArrayList<String> keys = new ArrayList<String>();
				
				for(int i=0; i<keyArr.length; i++){
					keys.add(keyArr[i]);
				}
				
				try{
					web.addPage(addUrl,keys);
					System.out.println();
					System.out.println(addUrl+" successfully added to the WebGraph!");
				} catch(IllegalArgumentException ex){
					System.out.println(ex.getMessage());
				}
				
			}

			else if(selection.equals("RP")){
				System.out.print("Enter a URL: ");
				String removeUrl = in.nextLine();
				
				web.removePage(removeUrl);
				System.out.println();
				System.out.println(removeUrl+" has been successfully removed from the WebGraph (if it exists)!");
			}

			else if(selection.equals("AL")){
				System.out.print("Enter a source URL: ");
				String sourceUrl = in.nextLine();
				System.out.print("Enter a destination URL: ");
				String destUrl = in.nextLine();
				
				try{
					web.addLink(sourceUrl,destUrl);
					System.out.println();
					System.out.println("Link successfully added from "+sourceUrl+" to "+destUrl+"!");
				} catch(IllegalArgumentException ex){
					System.out.println(ex.getMessage());
				}
			}

			else if(selection.equals("RL")){
				System.out.print("Enter a source URL: ");
				String sourceUrl = in.nextLine();
				System.out.print("Enter a destination URL: ");
				String destUrl = in.nextLine();
				
				try{
					web.removeLink(sourceUrl,destUrl);
					System.out.println();
					System.out.println("Link removed from "+sourceUrl+" to "+destUrl+"(if it exists)!");
				} catch(IllegalArgumentException ex){
					System.out.println(ex.getMessage());
				}
			}

			//Goes on to the switch statement in accordance to user-input char
			else {
				switch(selChar){
					case 'P':
						System.out.println("\n\t(I) - Sort based on index (ASC)");
						System.out.println("\t(U) - Sort based on URL (ASC)");
						System.out.println("\t(R) - Sort based on rank (DSC)\n");

						System.out.print("Please select an option: ");
			
						selection = in.nextLine().toUpperCase();
						if(!selection.equals(""))
							selChar = selection.charAt(0); //reads char at position 0 of String, which should be expected
										   //to be only one character

						if(selection.length() > 1 || selection.length() < 1) 
							System.out.println("\nInvalid Entry, please try again.");

						else{
							switch(selChar){
								case 'I':
									web.sortIndex();
									System.out.println();
									web.printTable();
									break;

								case 'U':
									web.sortUrl();
									System.out.println();
									web.printTable();
									break;

								case 'R':
									web.sortRank();
									System.out.println();
									web.printTable();
									break;

								default:
									System.out.println("Invalid Entry, please try again.");
									break;
							}
						}
						
						break;
					
					case 'S':
						System.out.print("Search keyword: ");
						String keyW = in.nextLine();
						web.searchForKeyword(keyW);
						break;

					case 'Q':
						cont = false;						
						System.out.println("\nGoodbye.");
						break;

					default:
						System.out.println("Invalid Entry, please try again.");
						break;
				}
			}
		}
	}
}