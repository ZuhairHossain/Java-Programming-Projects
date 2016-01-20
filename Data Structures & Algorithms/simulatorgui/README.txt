/**
 * Dennis Sosa
 * Assignment: #4 (Router Network Simulator)
 * @author DennisSosa
 * 
 */

Purpose Description:

	• In the field of networking, information is packed into a single entity called a packet. A packet contains the sender's information, and also extra overhead bits that describes certain properties of the packet. Some of these bits are used to determine the path it takes to maneuver through the network. Packets travel through routers across the network, until they have arrived to their specified destination. 
	In this assignment, you will imitate a simple network of routers by writing a simulator.

	BRIEF:
	The router topology implemented in today's world is far too complicated to model in a small assignment.
	Therefore, we will simplify this model for our simulation. Assume we have 3 levels of routers:
		• Level 1: Dispatcher Router
			All packets in our simulation will arrive at this router. The job of this router is to send off the queued packets to one of the available routers in the second level. You will write a simple algorithm that determines which Level 2 routers a packet should jump to. (Discussed later.)
		• Level 2: Intermediate Hop Routers
			These routers will accept a packet from the dispatcher router. There will be a user-determined amount of these routers. Depending the size of the packet, it takes a variable amount of time to process the packet.
			Once the arrived packets have been processed, the router can send those packets to their destination.
		• Level 3: Destination Router
			To simplify our simulation, we assume all packets have the same destination. The job of this router is to accept incoming packets after they have been sent from the second level. However, due to a limited bandwidth (bottleneck in the network), this router can only accept a limited amount of packets at a given moment. This router will not be used in our simulation, but note its purpose.

 	• There are two main programs: Simulator.java & SimulatorGUI.java->(PACKAGE: “simulatorgui”)

 	• SimulatorGUI.java is ran on NetBeans IDE 8.0.2, and for some reason NetBeans sometimes gives me problems with spaces and indents, please don’t mind that if my program turns out to be like that when you open it, as I have already fixed that on my machine.

 	• Simulator.java is the console version of HW4, with use of Scanner from java API for user-input

 	• SimulatorGUI.java is the GUI version of HW4, with use of JavaFX from java API

 	• SimulatorGUI.java: Click on the button "Start Simulator" to get started, then type in your desired inputs and enter with the button "Enter Simulator Inputs" at the bottom.

 	• After each simulation, there will be a message asking if you would like to try another simulation, which simply tells the user to click on "Start Simulator" again.
 	
 	• If you want to quit, click "Quit" button at right pane.

*** Please take a look at the "SampleInput-Output.pdf" file for an example of how the program is ran.
