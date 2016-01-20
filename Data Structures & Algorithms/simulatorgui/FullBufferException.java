package simulatorgui;

/**
 * Dennis Sosa
 * Assignment: #4 (Router Network Simulator)
 * CSE 214 
 * Stony Brook University
 * @author DennisSosa
 * 
 * FullBufferException Custom Exception Class
 */

public class FullBufferException extends Exception {
	private int maxBufferSize;

	/**
	 * Throws an exception, and displays a message to the user
	 * indicating that there is no more room in the Router Buffer of each Router;
	 * maxBufferSize is instantiated
	 * @param maxBufferSize
	 * 		number Packets in each Router Buffer
	 */
	public FullBufferException(int maxBufferSize) {
		super("\nThere is no more room in the Router Buffer of each Router"
				+ " to add a Packet to any Router Buffer.\nMax Buffer Size: " + maxBufferSize+"\n");
		this.maxBufferSize = maxBufferSize;
	}

	/**
	 * Receives the value of the maxBufferSize
	 * which caused this Custom Exception Class to be called
	 * @return
	 * 		the value of maxBufferSize as an int
	 */
	public int getMaxBufferSize(){
		return maxBufferSize;
	}
}

