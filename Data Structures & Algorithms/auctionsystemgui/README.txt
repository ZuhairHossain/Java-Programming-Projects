/**
 * Dennis Sosa
 * Assignment: #6 (Extracting Auction Data Online)
 * CSE 214
 * Stony Brook University
 * @author DennisSosa
 * 
 */

Purpose Description:
	• When working with data, we must choose the best data structure to manipulate the data set in order to achieve efficient results. A very basic array can store information without a problem, but is inefficient when we are constantly storing and searching for data. The idea of a table is created to increase the efficiency and avoid such problems. Tables store data using a tuple <Key, Value>. We choose a special field of an object, and use it to uniquely identify the entire object.Therefore, when we store and search for an object, we will use the key to save or search for the object. Ideally, this will reduce the algorithm to O(1). For this assignment, you are allowed to use a HashTable, or a HashMap.
	For this assignment, we are working with a real auction data online. The data will be in the form of an XML file. There may be extraneous data that we do not need in our analysis. We will selectively limit our object construction with information that we are interested in.

	To get started, visit the following websites:
	
	http://tinyurl.com/nbf5g2h - Ebay Auction Data
	http://tinyurl.com/p7vub89 - Yahoo Auction Data

	Manually extracting the data will be a tedious and error prone job. You will learn how to use a data extraction tool to analyze an XML file. You can download the file: Big Data Jar.

 	• There are two main programs: AuctionSystem.java & AuctionSystemGUI.java
		->(PACKAGE: “auctionsystemgui”)

 	• AuctionSystemGUI.java is ran on NetBeans IDE 8.0.2, and for some reason NetBeans sometimes gives me problems with spaces and indents, please don’t mind that if my program turns out to be like that when you open it, as I have already fixed that on my machine.

 	• AuctionSystem.java is the console version of HW6, with use of Scanner from java API for user-input

 	• AuctionSystemGUI.java is the GUI version of HW6, with use of JavaFX from java API

 	• AuctionSystemGUI.java: Click on any button of your choice in correspondence to the AuctionSystem selection menu. Enter required inputs (if necessary), enter, and observe the output in the middle pane.

 	
 	• If you want to quit the interface, click “(Q) Quit” button on the right pane.

*** Please take a look at the "SampleInput-Output.pdf" file for an example of how the program is ran.


