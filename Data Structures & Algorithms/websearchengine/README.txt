/**
 * Dennis Sosa
 * Assignment: #7 (Directed Graph of WebPage Objects)
 * CSE 214
 * Stony Brook University
 * @author DennisSosa
 * 
 */

 Purpose Description:

 	• The World Wide Web is an open source information space where documents (pages) and other web resources are identified by URLs and can be accessed via hyperlinks. The system can be described as a directed graph (or 'web') of pages in which a link from page A to page B corresponds to a directed edge from node A to node B. Each document can be abbreviated as a handful of important keywords describing what the document is about. Given the vast amount of information available on the web, searching for the most relevant pages containing a keyword in an efficient manner is an incredibly important operation. In this assignment, you will create a basic search engine to generate a sorted list of web pages using a simplified version of Google's PageRank algorithm.
	For this assignment, you will build a directed graph of WebPage objects by reading two files: pages.txt and links.txt (instructions on how to do so are included further below). Once the graph is constructed, you will run a basic search engine that prompts the user to enter a keyword.You will then perform the PageRank algorithm, and display the pages to the user in tabular form sorted by descending PageRank.

 	• Main Program: SearchEngine.java
 	
*** Please take a look at the "SampleInput-Output.pdf" file for an example of how the program is ran.
