package auctionsystemgui;
/**
 * Dennis Sosa
 * Assignment: #6 (Extracting Auction Data Online)
 * CSE 214
 * Stony Brook University
 * @author DennisSosa
 * 
 * Auction Class
 */

import java.io.Serializable;

public class Auction implements Serializable {
	private int timeRemaining;
	private double currentBid;
	private String auctionID;
	private String sellerName;
	private String buyerName;
	private String itemInfo;
        private String bidAccept;

	/**
	 * Creates a new Auction object with uninstantiated parameters
	 * Represents an active auction currently in the database
	 */
	public Auction(){

	}

	/**
	 * Creates a new Auction object using given values.
	 * Represents an active auction currently in the database
	 * Loaded constructor
	 * @param auctionID
	 * 		unique identification for this Auction
	 * @param sellerName
	 * 		contains the seller's name for this Auction
	 * @param buyerName
	 * 		contains the buyer's name for this Auction
	 * @param itemInfo
	 * 		contains information of the item for this Auction
	 */
	public Auction(String auctionID, String sellerName, String buyerName,
		String itemInfo){
		this.auctionID = auctionID;
		this.sellerName = sellerName;
		this.buyerName = buyerName;
		this.itemInfo = itemInfo;
	}

	/**
	 * Creates a new Auction object using given values.
	 * Represents an active auction currently in the database
	 * Overloaded constructor
	 * @param timeRemaining
	 * 		the time remaining for this Auction
	 * @param currentBid
	 * 		the current bid for this Auction
	 * @param auctionID
	 * 		unique identification for this Auction
	 * @param sellerName
	 * 		contains the seller's name for this Auction
	 * @param buyerName
	 * 		contains the buyer's name for this Auction
	 * @param itemInfo
	 * 		contains information of the item for this Auction
	 */
	public Auction(int timeRemaining, double currentBid, String auctionID, String sellerName, 
		String buyerName, String itemInfo){
		this.auctionID = auctionID;
		this.sellerName = sellerName;
		this.buyerName = buyerName;
		this.itemInfo = itemInfo;
		this.timeRemaining = timeRemaining;
		this.currentBid = currentBid;
	}
	
	/**
	 * Creates a new Auction object using given values.
	 * Represents an active auction currently in the database
	 * Loaded constructor
	 * @param timeRemaining
	 * 		the time remaining for this Auction
	 * @param auctionID
	 * 		unique identification for this Auction
	 * @param sellerName
	 * 		contains the seller's name for this Auction
	 * @param itemInfo
	 * 		contains information of the item for this Auction
	 */
	public Auction(int timeRemaining, String auctionID, String sellerName, String itemInfo){
			this.timeRemaining = timeRemaining;
			this.auctionID = auctionID;
			this.sellerName = sellerName;
			this.itemInfo = itemInfo;
	}
	
	/**
	 * Decreases the time remaining for this auction by the specified amount.
	 * If time is greater than the current remaining time for the auction, then the time
	 * remaining is set to 0 (no negative times)
	 * @param time
	 * 		value of time to be decreased from the timeRemaining in this Auction
	 * <dt><b>Postconditions:</b><dd>
	 * 		timeRemaining has been decremented by the indicated amount and is greater than or equal to 0
	 */
	public void decrementTimeRemaining(int time){
		if(time > timeRemaining)
			timeRemaining = 0;
		else
			timeRemaining -= time;
	}

	/**
	 * Makes a new bid on this auction. If bidAmt is larger than currentBid, then the value
	 * of currentBid is replaced by bidAmt and buyerName is replaced by bidderName
	 * @param bidderName
	 * 		buyer's name for the current Auction
	 * @param bidAmt
	 * 		buyer's bid amount for the current Auction
	 * @throws ClosedAuctionException
	 * 		indicates that the current Auction is closed and no more bids can be placed (timeRemaining == 0)
	 */
	public void newBid(String bidderName, double bidAmt) throws ClosedAuctionException{
		if(timeRemaining == 0)
			throw new ClosedAuctionException();

		if(bidAmt > currentBid){
                        bidAccept = "";
			currentBid = bidAmt;
			buyerName = bidderName;
			System.out.println("Bid accepted.");
                        bidAccept += ("Bid accepted.\n");
		}
                else{
			System.out.println("Bid not accepted.");
                        bidAccept += ("Bid not accepted.\n");
                }
	}

	/**
	 * Receives the timeRemaining of a particular Auction
	 * @return
	 * 		the timeRemaining of an Auction as an int
	 */
	public int getTimeRemaining(){
		return timeRemaining;
	}

	/**
	 * Receives the current (highest) bid of a particular Auction
	 * @return
	 * 		the current bid of an Auction as a double
	 */
	public double getCurrentBid(){
		return currentBid;
	}

	/**
	 * Receives the auction ID of a particular Auction
	 * @return
	 * 		the unique identification of an Auction as a String
	 */
	public String getAuctionID(){
		return auctionID;
	}

	/**
	 * Receives the seller's name of a particular Auction
	 * @return
	 * 		the seller's name of an Auction as a String
	 */
	public String getSellerName(){
		return sellerName;
	}

	/**
	 * Receives the buyer's (bidder's) name of a particular Auction
	 * @return
	 * 		the buyer's name of an Auction as a String
	 */
	public String getBuyerName(){
		return buyerName;
	}

	/**
	 * Receives the item information for a particular Auction
	 * @return
	 * 		the item's information of an Auction as a String
	 */
	public String getItemInfo(){
		return itemInfo;
	}

	/**
	 * Obtain the String representation of this Auction object, which is a 
	 * neatly formatted output of the content of the Auction
	 * @return
	 * 		the representation of this Auction as a String
	 */
	public String toString(){
		String result = String.format(" %-15s | $ %-16.2f | %-22s | %-24s | %-16s | %-16s",(auctionID!=null)?auctionID:"",currentBid,
				(sellerName!=null)?sellerName:"",(buyerName!=null)?buyerName:"",(timeRemaining+" hours"), (itemInfo!=null)?itemInfo:"");
		
		return result;
	}
        
        public String getBidAccept(){
            return bidAccept;
        }
}