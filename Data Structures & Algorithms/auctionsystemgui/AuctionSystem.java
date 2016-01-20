package auctionsystemgui;
/**
 * Dennis Sosa
 * Assignment: #6 (Extracting Auction Data Online)
 * CSE 214
 * Stony Brook University
 * @author DennisSosa
 * 
 * AuctionSystem Class
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;


public class AuctionSystem{
	private static AuctionTable auctionTable;
	private static String username;


	/**
	 * The main method runs a menu driven application which allows the user to interact
	 * with the database by listing open Auctions, making bids on open Auctions, and creating
	 * new Auctions for different items; In addition, the class provides the functionality to load
	 * a saved (serialized) AuctionTable or create a new one if a saved table does not exist.
	 * @param args
	 * 		empty command-line argument
	 */
	public static void main(String[] args){
		
		//AuctionTable auctionTable = new AuctionTable();
		
		Scanner in = new Scanner(System.in); //Scanner for user-input
		
		boolean cont = true;

		String selection = "";
		char selChar = ' ';
		
		System.out.println("Welcome to Dennis Sosa's Online Auction Data Extractor.\n");
		System.out.println("Starting...");
		
		try{
			FileInputStream file = new FileInputStream("auction.obj");
			ObjectInputStream inStream = new ObjectInputStream(file);
			AuctionTable auctions;
			
			auctions = (AuctionTable) inStream.readObject();
			auctionTable = (AuctionTable) auctions.clone();
			System.out.println("Loading previous Auction Table...");
		} catch(FileNotFoundException ex){
			System.out.println("No previous auction table detected.");
			System.out.println("Creating new table...");
		} catch(IOException ex){
			System.out.println("No previous auction table detected.");
			System.out.println("Creating new table...");
		} catch(ClassNotFoundException ex){
			System.out.println("No previous auction table detected.");
			System.out.println("Creating new table...");
		}
		
		System.out.print("Please select a username: ");
                username = in.nextLine();
		//String un = in.nextLine();
		
		
		while(cont==true){ //program will continue as long as cont remains true
			//Main menu of program
			System.out.println();
			System.out.println("Menu:");
			System.out.println("\t(D) - Import Data from URL");
			System.out.println("\t(A) - Create a New Auction");
			System.out.println("\t(B) - Bid on an Item");
			System.out.println("\t(I) - Get Info on Auction");
			System.out.println("\t(P) - Print All Auctions");
			System.out.println("\t(R) - Remove Expired Auctions");
			System.out.println("\t(T) - Let Time Pass");
			System.out.println("\t(Q) - Quit");
			System.out.println();
			
			System.out.print("Please select an option: ");
			
			selection = in.nextLine().toUpperCase();
			selChar = selection.charAt(0); //reads char at position 0 of String, which should be expected
										   //to be only one character

			if(selection.length() > 1) 
				System.out.println("\nInvalid Entry, please try again.");
			
			//Goes on to the switch statement in accordance to user-input char
			else {
				switch(selChar){
					case 'D':
						System.out.print("Please enter a URL: ");
						String URL = in.nextLine();
						try{
							auctionTable = auctionTable.buildFromURL(URL);
							System.out.println("Loading...");
							System.out.println("Auction data loaded successfully!");
						} catch(IllegalArgumentException ex){
							System.out.println(ex.getMessage());
						}
						
						break;
					
					case 'A':
						System.out.println("\nCreating new Auction as "+username+".");
						System.out.print("Please enter an Auction ID: ");
						String auctionID0 = in.nextLine();
						System.out.print("Please enter an Auction time (hours): ");
						int auctionTime = in.nextInt();
						in.nextLine();
						System.out.print("Please enter some Item Info: ");
						String itemInfo = in.nextLine();
						Auction newAuction = new Auction(auctionTime,auctionID0,username,itemInfo);
						
						try{
							auctionTable.putAuction(auctionID0, newAuction);
							System.out.println("Auction "+auctionID0+" inserted into table.");
						} catch(IllegalArgumentException ex){
							System.out.println(ex.getMessage());
						}
						break;

					case 'B':
						System.out.print("Please enter an Auction ID: ");
						String auctionID = in.nextLine();
						
						if(auctionTable.getAuction(auctionID)!=null && auctionTable.getAuction(auctionID).getTimeRemaining()!=0){
							System.out.println("\nAuction "+auctionID+" is OPEN");
							double cb = auctionTable.getAuction(auctionID).getCurrentBid();
							
							if(cb==0)
								System.out.println("\tCurrent Bid: None");
							else
								System.out.printf("\tCurrent Bid: $ %.2f\n",cb);
							
							System.out.print("What would you like to bid?: ");
							double bidValue = in.nextDouble();
							in.nextLine();
							
							try{
								auctionTable.getAuction(auctionID).newBid(username,bidValue);
								
							} catch(ClosedAuctionException ex){
								System.out.println(ex.getMessage());
							}
							
						}
						
						else if(auctionTable.getAuction(auctionID)!=null && auctionTable.getAuction(auctionID).getTimeRemaining()==0){
							System.out.println("\nAuction "+auctionID+" is CLOSED");
							double cb = auctionTable.getAuction(auctionID).getCurrentBid();
							
							if(cb==0)
								System.out.println("\tCurrent Bid: None");
							else
								System.out.printf("\tCurrent Bid: $ %.2f\n",cb);
							
							System.out.println("You can no longer bid on this item.");
						}
						
						else if(auctionTable.getAuction(auctionID)==null)
							System.out.println("\nAuction "+auctionID+" was not found in the AuctionTable.");
						
						break;

					case 'I':
						System.out.print("Please enter an Auction ID: ");
						String auctionID2 = in.nextLine();
						
						if(auctionTable.getAuction(auctionID2)!=null){
							System.out.println("\nAuction "+auctionID2+":");
							System.out.println("\tSeller: "+auctionTable.getAuction(auctionID2).getSellerName());
							System.out.println("\tBuyer: "+auctionTable.getAuction(auctionID2).getBuyerName());
							System.out.println("\tTime: "+auctionTable.getAuction(auctionID2).getTimeRemaining());
							System.out.println("\tInfo: "+auctionTable.getAuction(auctionID2).getItemInfo());
							System.out.println();
						}
						
						else if(auctionTable.getAuction(auctionID2)==null){
							System.out.println("\nAuction "+auctionID2+" was not found in the AuctionTable.");
							System.out.println("No information can be retrieved.");
						}
							
						break;
						
					case 'P':
						auctionTable.printTable();
						break;

					case 'R':
						System.out.println();
						System.out.println("Removing expired auctions...");
						auctionTable.removeExpiredAuctions();
						System.out.println("All expired auctions removed.");
						break;

					case 'T':
						System.out.print("How many hours should pass: ");
						int numHours = in.nextInt();
						in.nextLine();
						
						System.out.println("Time passing...");
						try{
							auctionTable.letTimePass(numHours);
							System.out.println("Auction times updated.");
						} catch(IllegalArgumentException ex){
							System.out.println(ex.getMessage());
						}
						
						break;

					case 'Q':
						cont = false;
						System.out.println("Writing Auction Table to file...");
						//add Serialize the Auction Table
						try{
							FileOutputStream file = new FileOutputStream("auction.obj");
							ObjectOutputStream outStream = new ObjectOutputStream(file);
							AuctionTable auctions = new AuctionTable();
							auctions = (AuctionTable) auctionTable.clone();
							outStream.writeObject(auctions);
						} catch(FileNotFoundException ex){
							System.out.println("ERROR when making a new serializable file.");
						} catch(IOException ex){
							System.out.println("ERROR when making a new serializable file.");
						}
						
						System.out.println("Done!\n");
						System.out.println("Goodbye.");
						break;

					default:
						System.out.println("Invalid Entry, please try again.");
						break;
				}
			}
		}
	}
	
        /**
         * Receives the user name of the AuctionSystem
         * @return
         *      the user's name as a String
         */
	public static String getUsername(){
		return username;
	}
	
        /**
         * Receives the AuctionTable used in the AuctionSystem
         * @return 
         */
	public static AuctionTable getAuctionTable(){
		return auctionTable;
	}
	
        /**
         * Changes the current username to a different name
         * @param username
         *      new username for this AuctionSystem
         */
	public void setUserName(String username){
		this.username = username;
	}
	
        /**
         * Changes the current AuctionTable to a specified AuctionTable
         * @param auctionTable 
         *      new AuctionTable for this AuctionSystem
         */
	public void setAuctionTable(AuctionTable auctionTable){
		this.auctionTable = auctionTable;
	}
	
}