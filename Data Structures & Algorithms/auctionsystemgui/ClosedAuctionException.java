package auctionsystemgui;
/**
 * Dennis Sosa
 * Assignment: #6 (Extracting Auction Data Online)
 * CSE 214 
 * Stony Brook University
 * @author DennisSosa
 * 
 * ClosedAuctionException Custom Exception Class
 */

public class ClosedAuctionException extends Exception {

	/**
	 * Throws an exception, and displays a message to the user
	 * indicating that the current auction is closed and no more 
	 * bids can be placed
	 */
	public ClosedAuctionException() {
		super("\nERROR: Current Auction is closed; no more bids can be placed.");
	}
	
}