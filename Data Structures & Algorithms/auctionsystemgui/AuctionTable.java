package auctionsystemgui;
/**
 * Dennis Sosa
 * Assignment: #6 (Extracting Auction Data Online)
 * CSE 214 
 * Stony Brook University
 * @author DennisSosa
 * 
 * AuctionTable Class
 */

import java.io.Serializable;
import big.data.DataSource;
import java.util.Hashtable;
import java.util.Enumeration;

public class AuctionTable extends Hashtable<String, Auction> implements Serializable{
	
	/**
	 * Uses BigData library to construct an AuctionTable from a remote data source
	 * @param URL
	 * 		String representing the URL for the remote data source
	 * <dt><b>Preconditions:</b><dd>
	 * 		URL represents a data source which can be connected to using the BigData library;
	 * 		the data source has proper syntax
	 * <dt><b>Postconditions:</b><dd>
	 * 		None
	 * @return
	 * 		the AuctionTable constructed from the remote data source
	 * @throws IllegalArgumentException
	 * 		indicates that the URL does not represent a valid data source
	 * 		(can't connect or invalid syntax)
	 */
	public static AuctionTable buildFromURL(String URL) throws IllegalArgumentException{
		AuctionTable auctionTable = new AuctionTable();
		
		if(!URL.contains("http://") && !URL.contains("https://"))
			throw new IllegalArgumentException("Invalid URL; URL does not reprsent a valid datasource.");
		
		DataSource ds = DataSource.connect(URL).load();
		String[] sellerNames = ds.fetchStringArray("listing/seller_info/seller_name");
		String[] cBs = ds.fetchStringArray("listing/auction_info/current_bid");
		double[] currentBids = new double[cBs.length];
		
		for(int i=0; i<cBs.length; i++){
			currentBids[i] = Double.parseDouble(cBs[i].substring(1).replaceAll("[^0-9\\.]",""));
		}
		
		String[] tLs = ds.fetchStringArray("listing/auction_info/time_left");
		int[] timeLefts = new int[tLs.length];
		
		for(int i=0; i<tLs.length; i++){
			timeLefts[i] = Integer.parseInt(tLs[i].substring(0,tLs[i].indexOf(" ")).replaceAll("[^0-9]",""));
		}
		
		String[] ID_nums = ds.fetchStringArray("listing/auction_info/id_num");
		String[] buyerNames = ds.fetchStringArray("listing/auction_info/high_bidder/bidder_name");
		String[] cpuInfo = ds.fetchStringArray("listing/item_info/cpu");
		String[] memoryInfo = ds.fetchStringArray("listing/item_info/memory");
		String[] hardDriveInfo = ds.fetchStringArray("listing/item_info/hard_drive");
		
		String[] itemInfos = new String[cpuInfo.length];
		
		for(int i=0; i<cpuInfo.length; i++){
			itemInfos[i] = (cpuInfo[i]+" - "+memoryInfo[i]+ " - "+hardDriveInfo[i]);
		}
		
		for(int i=0; i<sellerNames.length; i++){
			Auction auctionFound = new Auction(timeLefts[i],currentBids[i],ID_nums[i],
					sellerNames[i], buyerNames[i], itemInfos[i]);
			
			auctionTable.putAuction(ID_nums[i], auctionFound);
		}
		
		return auctionTable;
	}
	
	/**
	 * Manually posts an auction, and adds it into the table
	 * @param auctionID
	 * 		the unique key for this object
	 * @param auction
	 * 		the Auction to insert into the table with the corresponding auctionID
	 * <dt><b>Preconditions:</b><dd>
	 * 		None
	 * <dt><b>Postconditions:</b><dd>
	 * 		the item will be added to the table if all given parameters are correct
	 * @throws IllegalArgumentException
	 * 		indicates that the given auctionID is already stored in the table
	 */
	public void putAuction(String auctionID, Auction auction) throws IllegalArgumentException{
		if(containsKey(auctionID))
			throw new IllegalArgumentException("The given auctionID is already stored in the table.");
		
		this.put(auctionID, auction);
	}
	
	/**
	 * Get the information of an Auction that contains the given ID as a key
	 * @param auctionID
	 * 		the unique key for this object
	 * @return
	 * 		an Auction object with the given key, null otherwise
	 */
	public Auction getAuction(String auctionID){
		return this.get(auctionID);
	}
	
	/**
	 * Simulates the passing of time. Decrease the timeRemaining of all Auction objects
	 * by the amount specified. The value cannot go below 0.
	 * @param numHours
	 * 		the number of hours to decrease the timeRemaining value by
	 * <dt><b>Postconditions:</b><dd>
	 * 		all Auctions in the table have their timeRemaining timer decreased. If the original value is less
	 * 		than the decreased value, set the value to 0.
	 * @throws IllegalArgumentException
	 * 		indicates the given numHours is non positive
	 */
	public void letTimePass(int numHours) throws IllegalArgumentException{
		if(numHours < 0)
			throw new IllegalArgumentException("ERROR: Given numHours is non positive!");
		
		Enumeration<String> IDs = this.keys();
		while(IDs.hasMoreElements()){
			String auctionID = IDs.nextElement();
			Auction modified = this.getAuction(auctionID);
			modified.decrementTimeRemaining(numHours);
			this.put(auctionID, modified);
		}
	}
	
	/**
	 * Iterates over all Auction objects in the table and removes them if they are expired (timeRemaining == 0)
	 * <dt><b>Postconditions:</b><dd>
	 * 		Only open Auction remain in the table
	 */
	public void removeExpiredAuctions(){
		Enumeration<String> IDs = this.keys();
		while(IDs.hasMoreElements()){
			String auctionID = IDs.nextElement();
			if(this.getAuction(auctionID).getTimeRemaining() == 0)
				this.remove(auctionID);
		}
	}
	
	/**
	 * Prints the AuctionTable in tabular form
	 */
	public void printTable(){
		System.out.println();
		System.out.printf(" %-15s | %-18s | %-22s | %-24s | %-16s | %-16s\n","Auction ID","Bid","Seller","Buyer","Time","Item Info");
		String tableLine = "";
		for(int i=1; i<=153; i++)
			tableLine+="=";
		System.out.println(" "+tableLine);
		
		Enumeration<String> IDs = this.keys();
		while(IDs.hasMoreElements()){
			String auctionID = IDs.nextElement();
			if(this.getAuction(auctionID).toString().length()>154)
				System.out.println(this.getAuction(auctionID).toString().substring(0,154));
			else
				System.out.println(this.getAuction(auctionID).toString());
		}
	}
	
	public String toString(){
		String result = "\n";
		result += String.format(" %-15s | %-18s | %-22s | %-24s | %-16s | %-16s\n","Auction ID","Bid","Seller","Buyer","Time","Item Info");
		String tableLine = "";
		for(int i=1; i<=153; i++)
			tableLine+="=";
		result += (" "+tableLine+"\n");
		
		Enumeration<String> IDs = this.keys();
		while(IDs.hasMoreElements()){
			String auctionID = IDs.nextElement();
			if(this.getAuction(auctionID).toString().length()>154)
				result += (this.getAuction(auctionID).toString().substring(0,154)+"\n");
			else
				result += (this.getAuction(auctionID).toString()+"\n");
		}
		
		return result;
	}
	
}