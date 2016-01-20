package simulatorgui;
/**
 * Dennis Sosa
 * Assignment: #4 (Router Network Simulator)
 * CSE 214
 * Stony Brook University
 * @author DennisSosa
 * 
 * Simulator Class
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Simulator{
	public static final int MAX_PACKETS = 3;
	
	public static int packetsDropped;//simulate method only returns second to last
	//line of sample format for output, packetsDropped cannot be printed in simulate
	//must be printed in main method...
	
	/**
	 * Runs the simulator, calculates the
	 * average time each packet spends within the network
	 * @return
	 * 		the average time each packet spends within the network
	 */
	public double simulate(int totalServiceTime, int totalPacketsArrived){
		return (double) totalServiceTime/totalPacketsArrived;
	}
	
	
	/**
	 * Runs the simulator, calculates the
	 * average time each packet spends within the network
	 * @param dispatcher
	 * 		level 1 router
	 * @param routers
	 * 		level 2 routers
	 * @param currentAdd
	 * 		keeping track of current added packets (Router)
	 * @param destination
	 * 		level 3 router (to keep track of bandwidth sending)
	 * @param totalServiceTime
	 * 		running sum of total time each packer is in network
	 * @param totalPacketsArrived
	 * 		total number of packets that has been successfully forwarded
	 * 		to the destination
	 * @param packetsDropped
	 * 		records the number of packets that have been dropped due
	 * 		to a congested network
	 * @param arrivalProb
	 * 		probability of a new packet arriving at the Dispatcher
	 * @param numIntRouters
	 * 		number of Intermediate routers in network
	 * @param maxBufferSize
	 * 		maximum number of Packets an Intermediate Router can accommodate for
	 * @param minPacketSize
	 * 		minimum size of a Packet
	 * @param maxPacketSize
	 * 		maximum size of a Packet
	 * @param bandwidth
	 * 		maximum number of Packets the Destination router can accept
	 * 		at a given simulation unit
	 * @param duration
	 * 		number of simulation units
	 * @return
	 * 		the average time each packet spends within the network
	 */
	public double simulate(Router dispatcher, ArrayList<Router> routers, Router currentAdd, Router destination,
			int totalServiceTime,int totalPacketsArrived,int packetsDropped,double arrivalProb,int numIntRouters,
			int maxBufferSize,int minPacketSize,int maxPacketSize,int bandwidth,int duration){
		Simulator sim = new Simulator();
                Packet tempPacket = new Packet();
                tempPacket.packetCount = 0;
                
		for(int i=1; i<=duration; i++){
			dispatcher = new Router();
			for(int j=1; j<=MAX_PACKETS; j++){
				if(Math.random() < arrivalProb){
					Packet p = new Packet();
					p.packetCount++;
					p.setId(p.packetCount);
					p.setPacketSize(sim.randInt(minPacketSize, maxPacketSize));
					p.setTimeArrive(i);
					p.setTimeToDest(p.getPacketSize()/100);
					dispatcher.enqueue(p);
				}
			}
			currentAdd = (Router) dispatcher.clone();
			
			System.out.println("\nTime: "+i);

			if(dispatcher.isEmpty()){
				System.out.println("No packets arrived.");
				if(i>1){
					int packetArrival = 0;
					int bandwidthTracker = 0;
					
					for(int z=1; z<=numIntRouters; z++){
						if(routers.get(z).isEmpty())
							continue;
						
						Router temp = routers.get(z);
						
						if(temp.get(0).getTimeToDest()!=0 && (!currentAdd.contains(temp.get(0))||currentAdd.isEmpty()))
							temp.get(0).setTimeToDest(temp.get(0).getTimeToDest()-1);
						
						if(temp.get(0).getTimeToDest()==0){
							if(destination.contains(temp.get(0))&&destination.get(0).equals(temp.get(0))){
								if(bandwidthTracker!=bandwidth){
									packetArrival = (i-destination.peek().getTimeArrive());
									totalServiceTime += (i-destination.peek().getTimeArrive());
									
									System.out.println("Packet "+destination.dequeue().getId()+" has successfully reached its destination: +"+packetArrival);
									
									temp.dequeue();
									totalPacketsArrived++;
									routers.set(z,temp);
									bandwidthTracker++;
								}
							}
							
							else if(!destination.contains(temp.get(0))){
								destination.enqueue(temp.get(0));
								
								if(bandwidthTracker!=bandwidth){
									packetArrival = (i-destination.peek().getTimeArrive());
									totalServiceTime += (i-destination.peek().getTimeArrive());
									
									System.out.println("Packet "+destination.dequeue().getId()+" has successfully reached its destination: +"+packetArrival);
									
									temp.dequeue();
									totalPacketsArrived++;
									routers.set(z,temp);
									bandwidthTracker++;
								}
							}
						} 
					}
				}
			}
			
			else{
				Packet[] dispatcherArray = dispatcher.toArray(new Packet[dispatcher.size()]);
			
				for(int k=0; k<dispatcherArray.length; k++){
					System.out.println("Packet "+dispatcherArray[k].getId()+" arrives at dispatcher with"
						+" size "+dispatcherArray[k].getPacketSize()+".");
				}
			
				for(int x=0; x<dispatcherArray.length; x++){
					try{
						int sendIndex = dispatcher.sendPacketTo(routers, numIntRouters, maxBufferSize);
				
						System.out.println("Packet "+dispatcherArray[x].getId()+" sent to Router "+
								sendIndex+".");
					
						routers.get(sendIndex).enqueue(dispatcher.dequeue());
					
					} catch(FullBufferException ex){
						System.out.println(ex.getMessage());
						System.out.println("\nNetwork is congested. Packet "+dispatcherArray[x].getId()+" is dropped.\n");
						sim.packetsDropped++;
					}
				}
				
				if(i>1){
					int packetArrival = 0;
					int bandwidthTracker = 0;
					
					for(int z=1; z<=numIntRouters; z++){
						if(routers.get(z).isEmpty())
							continue;
						
						Router temp = routers.get(z);
						
						if(temp.get(0).getTimeToDest()!=0 && (!currentAdd.contains(temp.get(0))||currentAdd.isEmpty()))
							temp.get(0).setTimeToDest(temp.get(0).getTimeToDest()-1);
						
						if(temp.get(0).getTimeToDest()==0){
							if(destination.contains(temp.get(0))&&destination.get(0).equals(temp.get(0))){
								if(bandwidthTracker!=bandwidth){
									packetArrival = (i-destination.peek().getTimeArrive());
									totalServiceTime += (i-destination.peek().getTimeArrive());
									
									System.out.println("Packet "+destination.dequeue().getId()+" has successfully reached its destination: +"+packetArrival);
									
									temp.dequeue();
									totalPacketsArrived++;
									routers.set(z,temp);
									bandwidthTracker++;
								}
							}
							
							else if(!destination.contains(temp.get(0))){
								destination.enqueue(temp.get(0));
								
								if(bandwidthTracker!=bandwidth){
									packetArrival = (i-destination.peek().getTimeArrive());
									totalServiceTime += (i-destination.peek().getTimeArrive());
									
									System.out.println("Packet "+destination.dequeue().getId()+" has successfully reached its destination: +"+packetArrival);
									
									temp.dequeue();
									totalPacketsArrived++;
									routers.set(z,temp);
									bandwidthTracker++;
								}
							}
						} 
					}
				}
				
			}
			
			for(int y=1; y<=numIntRouters; y++){
				if(routers.get(y).isEmpty())
					System.out.println("R"+y+": {}");
				else
					System.out.println("R"+y+": {"+routers.get(y).toString()+"}");
			}

		}
	
		System.out.println("\nSimulation ending...");
		System.out.println("Total service time: "+totalServiceTime);
		System.out.println("Total packets served: "+totalPacketsArrived);
	
		return (double) totalServiceTime/totalPacketsArrived;
	}

	/**
	 * Helper method that can generate a random number between minVal & maxVal
	 * @param minVal
	 * 		minVal of range for random number
	 * @param maxVal
	 * 		maxVal of range for random number
	 * @return
	 * 		the randomly generated number
	 */
	private int randInt(int minVal, int maxVal){
		return (int)(Math.random()*((maxVal-minVal)+1))+minVal;
	}

	/**
	 * Main method that tests the simulator. All values are received from user input.
	 * Prompts the user for inputs to the simulator.
	 * Runs the simulator, and outputs the result.
	 * Prompts the user whether he or she wants to run another simulation
	 * @param args
	 * 		empty command-line argument
	 */
	public static void main(String[] args) throws IllegalArgumentException,Exception{
		Simulator sim = new Simulator();
		Scanner in = new Scanner(System.in);
		boolean cont = true;
		
		int totalServiceTime = 0;
		int totalPacketsArrived = 0;
		
		
		while(cont){
			
			System.out.println("\nStarting simulator...\n");
			System.out.print("Enter the number of Intermediate routers: ");
			int numIntRouters = in.nextInt();
			in.nextLine();
		
			System.out.print("\nEnter the arrival probability of a packet: ");
			double arrivalProb = in.nextDouble();
			in.nextLine();
			
			try{
				arrivalProb = checkArrivalProb(arrivalProb);
			} catch(IllegalArgumentException ex){
				System.out.println(ex.getMessage());
				break;
			}
		
			System.out.print("\nEnter the maxmimum buffer size of a router: ");
			int maxBufferSize = in.nextInt();
			in.nextLine();
		
			System.out.print("\nEnter the minimum size of a packet: ");
			int minPacketSize = in.nextInt();
			in.nextLine();
		
			System.out.print("\nEnter the maximum size of a packet: ");
			int maxPacketSize = in.nextInt();
			in.nextLine();
                        
                        if(maxPacketSize < minPacketSize || minPacketSize > maxPacketSize){
                            System.out.print("Invalid input for minPacketSize & maxPacketSize.");
                            continue;
                        }
                        
			System.out.print("\nEnter the bandwidth size: ");
			int bandwidth = in.nextInt();
			in.nextLine();
		
			System.out.print("\nEnter the simulation duration: ");
			int duration = in.nextInt();
			in.nextLine();
		
			try{
				duration = checkDuration(duration);
			} catch(IllegalArgumentException ex){
				System.out.println(ex.getMessage());
			}
			
			if(arrivalProb==0||duration==0||numIntRouters==0||maxBufferSize==0||bandwidth==0){
				System.out.println("\nNo action to be performed.");
				continue;
			}
			
			Router dispatcher = new Router();
			Router destination = new Router();
			ArrayList<Router> routers = new ArrayList<Router>(numIntRouters+1);
			Router currentAdd = new Router();
		
			totalServiceTime = 0;
			totalPacketsArrived = 0;
			packetsDropped = 0;
		
			routers.add(new Router());
		
			for(int i=1; i<=numIntRouters; i++){
				routers.add(new Router());
			}
			
			double avgTime = sim.simulate(dispatcher, routers,currentAdd, destination,totalServiceTime,
					totalPacketsArrived,packetsDropped,arrivalProb,numIntRouters,
					maxBufferSize,minPacketSize,maxPacketSize,bandwidth,duration);
			
			System.out.printf("Average service time per packet: %.2f\n",avgTime);
			System.out.println("Total packets dropped: "+packetsDropped);
			
			System.out.print("Do you want to try another simulation? (y/n): ");
			char ch = in.nextLine().charAt(0);
			
			switch(ch){
				case 'y':
					cont = true;
					break;
				case 'n':
					cont = false;
					System.out.println("\nProgram terminating successfully...");
					break;
				default:
					System.out.println("\nInvalid Entry.");
					cont = false;
					System.out.println("\nProgram terminating successfully...");
					break;
			}
			
		}
	}
	
	//Checking for valid entries
	public static double checkArrivalProb(double arrivalProb) throws IllegalArgumentException{
		if(arrivalProb < 0.0 || arrivalProb > 1.0)
			throw new IllegalArgumentException("\nInvalid entry for arrivalProb.\n");
		return arrivalProb;
	}
	
	public static int checkDuration(int duration) throws IllegalArgumentException{
		if(duration < 0)
			throw new IllegalArgumentException("\nNo Simulation.\n");
		return duration;
	}

}

