package simulatorgui;
/**
 * Dennis Sosa
 * Assignment: #4 (Router Network Simulator)
 * CSE 214
 * Stony Brook University
 * @author DennisSosa
 * 
 * Router Class
 */

import java.util.ArrayList;
import java.util.LinkedList;

public class Router extends LinkedList<Packet> {

	/**
	 * Adds a new item to the rear of this queue (router buffer)
	 * @param p
	 * 		new Packet object being added to this Router
	 */
	public void enqueue(Packet p){
		addLast(p);
	}

	/**
	 * Removes the front item from this queue (router buffer)
	 * @return
	 * 		the front item being removed from this implemented queue
	 */
	public Packet dequeue(){
		return removeFirst();
	}

	/**
	 * Receives the front item of this implemented queue (router buffer)
	 * for inspection
	 * @return
	 * 		the front item of this queue
	 */
	public Packet peek(){
		return getFirst();
	}

	/**
	 * Obtain the String representation of this Router object, which is a 
	 * neatly formatted output of the content of the contained Packets
	 * @return
	 * 		the representation of this Router as a String
	 */
	public String toString(){
		Packet[] p = this.toArray(new Packet[this.size()]);
		int length = p.length;
		String result = "";

		for(int i=0; i<length; i++){
			if(i==length-1)
				result += (p[i].toString());
			else
				result += (p[i].toString()+", ");
		}

		return result;
	}
	
	/**
	 * Loops through the list of Intermediate routers (Router Buffer),
	 * Finds the router with the most free buffer space (contains least Packets),
	 * & Returns the index of the router.
	 * If all router buffers are full, throw an exception
	 * @param routers
	 * 		Intermediate routers list
	 * @throws FullBufferException
	 * 		Indicates that all router buffers are full	
	 * @return
	 * 		index of the router
	 */
	public static int sendPacketTo(ArrayList<Router> routers, int numIntRouters,
			int maxBufferSize) throws FullBufferException{
		int mostBufferSpace = 1;
		
		boolean full = true;
		boolean allEqual = true;
		
		for(int i=1; i<routers.size(); i++){
			if(routers.get(i).size() != maxBufferSize)
				full = false;
		}
		
		if(full)
			throw new FullBufferException(maxBufferSize);
		
		
		for(int i=1; i<routers.size(); i++){
			if(routers.get(i).isEmpty())
				return i;
		}
		
		for(int i=1; i<routers.size(); i++){
			if(routers.get(i).size()!=routers.get(mostBufferSpace).size()){
				allEqual = false;
				break;
			}
		}
		
		if(allEqual)
			return 1;
		
		for(int i=1; i<routers.size(); i++){
			if(routers.get(i).size() == maxBufferSize)
				continue;
			
			if(routers.get(i).size() < routers.get(mostBufferSpace).size())
				mostBufferSpace = i;
		}
		
		return mostBufferSpace;
		
	}

}