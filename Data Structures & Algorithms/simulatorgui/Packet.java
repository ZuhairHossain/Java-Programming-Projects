package simulatorgui;

/**
 * Dennis Sosa
 * Assignment: #4 (Router Network Simulator)
 * CSE 214
 * Stony Brook University
 * @author DennisSosa
 * 
 * Packet Class
 */

public class Packet{
	public static int packetCount;
	private int id;
	private int packetSize;
	private int timeArrive;
	private int timeToDest; //value starts at packetSize/100

	
	/**
	 * Creates a new Packet object with uninstantiated parameters
	 */
	public Packet(){

	}

	/**
	 * Creates a new Packet object using given values.
	 * Overloaded constructor
	 * @param id
	 * 		unique identifier for the Packet; determined by packetCount
	 * @param packetSize
	 * 		the size of the packet being sent; value determined by Math.random() method
	 * @param timeArrive
	 * 		the time this Packet is created
	 * @param timeToDest
	 * 		contains the number of simulation units it takes for a packet to arrive
	 * 		at destination router (packetSize/100)
	 */
	public Packet(int id, int packetSize, int timeArrive,
		int timeToDest){
		
		this.id = id;
		this.packetSize = packetSize;
		this.timeArrive = timeArrive;
		this.timeToDest = timeToDest;
	}

	/**
	 * Changes a particular Packet's id to a new specified id
	 * @param id
	 * 		new unique identifier for a Packet object
	 */
	public void setId(int id){
		this.id = id;
	}

	/**
	 * Receives the id of a particular Packet
	 * @return
	 * 		the id of a Packet as an int
	 */
	public int getId(){
		return id;
	}

	/**
	 * Changes a particular Packet's packetSize to a new
	 * specified packetSize
	 * @param packetSize
	 * 		new size of Packet being sent
	 */
	public void setPacketSize(int packetSize){
		this.packetSize = packetSize;
	}

	/**
	 * Receives the packetSize of a particular Packet
	 * @return
	 * 		the packetSize of a Packet as an int
	 */
	public int getPacketSize(){
		return packetSize;
	}

	/**
	 * Changes a particular Packet's timeArrive to a new specified
	 * timeArrive
	 * @param timeArrive
	 * 		new timeArrive of a Packet
	 */
	public void setTimeArrive(int timeArrive){
		this.timeArrive = timeArrive;
	}

	/**
	 * Receives the timeArrive value of a particular Packet
	 * @return
	 * 		the timeArrive value of a Packet as an int
	 */
	public int getTimeArrive(){
		return timeArrive;
	}

	/**
	 * Changes a particular Packet's timeToDest value to a new
	 * specified timeToDest
	 * @param timeToDest
	 * 		new timeToDest of the Packet
	 */
	public void setTimeToDest(int timeToDest){
		this.timeToDest = timeToDest;
	}

	/**
	 * Receives the timeToDest of a particular Packet
	 * @return
	 * 		the timeToDest value of a Packet as an int
	 */
	public int getTimeToDest(){
		return timeToDest;
	}

	/**
	 * Obtain the String representation of this Packet object, which is a 
	 * neatly formatted output of the content of the Packet
	 * @return
	 * 		the representation of this Packet as a String
	 */
	public String toString(){
		
		return "["+id+", "+timeArrive+", "+timeToDest+"]";
	}

}

