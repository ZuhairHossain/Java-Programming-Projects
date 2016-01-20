/**
 * Dennis Sosa
 * Assignment: #7 (Directed Graph of WebPage Objects)
 * CSE 214
 * Stony Brook University
 * @author DennisSosa
 * 
 * WebGraph Class
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class WebGraph {
	public static final int MAX_PAGES = 40;
	private ArrayList<WebPage> pages;
	private ArrayList<String> containedUrls;
	private int[][] links;

	/**
	 * Constructs a WebGraph object using the indicated files as
	 * the source for pages and edges; the WebGraph organizes the WebPage
	 * objects as a directed graph.
	 * @param pagesFile
	 * 		a String of the relative path to the file containing the page information
	 * @param linksFile
	 * 		a String of the relative path to the file containing the link information
	 * <dt><b>Preconditions:</b><dd>
	 * 		Both parameters reference text files which exist &
	 * 		the files follow proper format as outlined in the "Reading Graph from File" section
	 * <dt><b>Postconditions:</b><dd>
	 * 		A WebGraph has been constructed and initialized based on the text files
	 * @return
	 * 		the WebGraph constructed from the text files
	 * @throws IllegalArgumentException
	 * 		indicates that either of the files does not reference a valid text file,
	 * 		or indicates that the files are not formatted correctly
	 * @throws FileNotFoundException
	 * 		thrown if file was not found from the Scanner object
	 */
	public static WebGraph buildFromFiles(String pagesFile, String linksFile)
		throws IllegalArgumentException, FileNotFoundException{

		WebGraph graph = new WebGraph();
		graph.pages = new ArrayList<WebPage>();
		graph.containedUrls = new ArrayList<String>();
		graph.links = new int[MAX_PAGES][MAX_PAGES];
		
		if(!pagesFile.contains(".txt") | !linksFile.contains(".txt"))
			throw new IllegalArgumentException(pagesFile + " or " + linksFile + "does not reference a valid text file"+
				"/the files are not formatted correctly.");
		File pFile = new File(pagesFile);
		File lFile = new File(linksFile);

		Scanner in = new Scanner(pFile);

		while(in.hasNextLine()){
			ArrayList<String> kws = new ArrayList<String>();

			String read = in.nextLine().trim();
			if(!read.contains(" ") | read.equals(""))
				throw new IllegalArgumentException("Invalid format for pagesFile.");
			
			String url = read.substring(0,read.indexOf(" "));
			String[] keywords = read.substring(read.indexOf(" ")+1).split(" ");

			for(int i=0; i<keywords.length; i++)
				kws.add(keywords[i]);
			
			graph.addPage(url,kws);
		}

		in.close();

		in = new Scanner(lFile);

		while(in.hasNextLine()){

			String read = in.nextLine().trim();
			if(!read.contains(" "))
				throw new IllegalArgumentException("Invalid format for linksFile.");
			
			String[] linkEdges = read.split(" ");
			
			if(linkEdges.length>2)
				throw new IllegalArgumentException("Invalid format for linksFile.");
			
			graph.addLink(linkEdges[0],linkEdges[1]); 
		}

		in.close();

		graph.updatePageRanks();
		
		//Collections.sort(graph.pages, new IndexComparator());
		
		return graph;
	}

	/**
	 * Adds a page to the WebGraph
	 * @param url
	 * 		the URL of the webpage (must not already exist in the WebGraph)
	 * @param keywords
	 * 		the keywords associated with the WebPage
	 * <dt><b>Preconditions:</b><dd>
	 * 		url is unique and does not exist as the URL of a WebPage already in the graph;
	 * 		url and keywords are not null
	 * <dt><b>Postconditions:</b><dd>
	 * 		The page has been added to pages at index 'i' and
	 * 		links has been logically extended to include the new
	 * 		row and column indexed by 'i'
	 * @throws IllegalArgumentException
	 * 		indicates that url is not unique and already exists in the graph,
	 * 		or indicates that either argument is null
	 */
	public void addPage(String url, ArrayList<String> keywords) throws IllegalArgumentException{
		if(containedUrls!=null && containedUrls.contains(url))
			throw new IllegalArgumentException("\nERROR: URL is not unique and already exists in the graph.");
		if(url == null || keywords == null)
			throw new IllegalArgumentException("\nERROR: \'URL\' or \'keywords\' is null.");
		
		if(pages!=null)
			Collections.sort(this.pages, new IndexComparator());
		
		containedUrls.add(url);
		WebPage webP = new WebPage(url,keywords);
		pages.add(webP);
		pages.get(pages.size()-1).setIndex(pages.size()-1);
		
		this.updatePageRanks();
	}

	/**
	 * Adds a link from the WebPage with the URL indicated by source to
	 * the WebPage with the URL indicated by destination
	 * @param source
	 * 		the URL of the page which contains the hyperlink to destination
	 * @param destination
	 * 		the URL of the page which the hyperlink points to
	 * <dt><b>Preconditions:</b><dd>
	 * 		Both parameters reference WebPages which exist in the graph
	 * <dt><b>Postconditions:</b><dd>
	 * 		None
	 * @throws IllegalArgumentException
	 * 		indicates that either of the URLs are null or could not be found in pages
	 */
	public void addLink(String source, String destination) throws IllegalArgumentException{
		if(source == null || destination == null)
			throw new IllegalArgumentException("\nERROR: \'source\' or \'destination\' is null.");

		boolean foundSource = false;
		boolean foundDestination = false;
		int sourceIndex = 0;
		int destinationIndex = 0;
		
		if(pages!=null)
			Collections.sort(this.pages, new IndexComparator());
		
		for(int i=0; i<pages.size(); i++){
			if(pages.get(i).getUrl().equals(source)){
				foundSource = true;
				sourceIndex = i;
			}

			else if(pages.get(i).getUrl().equals(destination)){
				foundDestination = true;
				destinationIndex = i;
			}

		}

		if(!(foundSource & foundDestination))
			throw new IllegalArgumentException("\nERROR: \'source\' or \'destination\' was not found in pages.");

		links[sourceIndex][destinationIndex] = 1;
		
		this.updatePageRanks();

	}

	/**
	 * Removes the WebPage from the graph with the given URL
	 * @param url
	 * 		the URL of the page to remove from the graph
	 * <dt><b>Preconditions:</b><dd>
	 * 		None
	 * <dt><b>Postconditions:</b><dd>
	 * 		The WebPage with the indicated URL has been removed from the graph, and its
	 * 		corresponding row and column has been removed from the adjacency matrix;
	 * 		All pages that have an index greater than the index that was removed should decrease
	 * 		their index value by 1;
	 * 		If url is null or could not be found in pages, the method ignores the input and does nothing.
	 */
	public void removePage(String url){
		if(url == null)
			return;
		
		int removedIndex = 0;
		boolean found = false;

		Collections.sort(this.pages, new IndexComparator());
		
		for(int i=0; i<pages.size(); i++){
			if(pages.get(i).getUrl().equals(url)){
				
				for(int k=0; k<pages.size(); k++){
					for(int j=i; j<pages.size()-1; j++){
						links[k][j] = links[k][j+1];
					}
				}
				
				for(int k=0; k<pages.size(); k++){
					for(int j=i; j<pages.size()-1; j++){
						links[j][k] = links[j+1][k];
					}
				}
				
				removedIndex = i;
				found = true;
				pages.remove(i);
				containedUrls.remove(url);
				break;
			}
		}

		if(!found)
			return;
		
		for(int i=removedIndex; i<pages.size(); i++){
			if(i>pages.size())
				break;
			
			pages.get(i).setIndex(pages.get(i).getIndex()-1);
			
		}
		
		this.updatePageRanks();
	}

	/**
	 * Removes the link from WebPage with the URL indicated by source to the WebPage with the URL
	 * indicated by destination
	 * @param source
	 * 		the URL of the WebPage to remove the link
	 * @param destination
	 * 		the URL of the link to be removed
	 * <dt><b>Preconditions:</b><dd>
	 * 		None
	 * <dt><b>Postconditions:</b><dd>
	 * 		The entry in links for the specified hyperlink has been set to 0 (no link);
	 * 		If either of the URLs could not be found, the input is ignored and the method
	 * 		does nothing.
	 */
	public void removeLink(String source, String destination){
		
		if(source == null || destination == null)
			return;

		boolean foundSource = false;
		boolean foundDestination = false;
		int sourceIndex = 0;
		int destinationIndex = 0;
		Collections.sort(this.pages, new IndexComparator());
		
		for(int i=0; i<pages.size(); i++){
			if(pages.get(i).getUrl().equals(source)){
				foundSource = true;
				sourceIndex = i;
			}

			else if(pages.get(i).getUrl().equals(destination)){
				foundDestination = true;
				destinationIndex = i;
			}

		}

		if(!(foundSource & foundDestination)){
			System.out.println("One link not found.");
			return;
		}

		links[sourceIndex][destinationIndex] = 0;
		
		this.updatePageRanks();
	}

	/**
	 * Calculates and assigns the PageRank for every page in the WebGraph
	 * Note: This operation should not be performed after any alteration of the graph structure
	 * <dt><b>Preconditions:</b><dd>
	 * 		None
	 * <dt><b>Postconditions:</b><dd>
	 * 		All WebPages in the graph have been assigned their proper PageRank
	 */
	public void updatePageRanks(){
		int pageRank = 0;
		for(int i=0; i<pages.size(); i++){
			pageRank=0;
			for(int j=0; j<pages.size(); j++){
				if(links[j][i]==1)
					pageRank++;
			}
			pages.get(i).setRank(pageRank);
		}
	}

	/**
	 * Prints the WebGraph in tabular form
	 */
	public void printTable(){
		System.out.println();
		System.out.printf(" %-15s | %-19s | %-22s | %-24s | %s \n","Index","URL","PageRank","Links","Keywords");
		String tableLine = "";
		for(int i=1; i<=147; i++)
			tableLine+="-";
		System.out.println(" "+tableLine);
		String linksDest = "";
		
		for(int i=0; i<pages.size(); i++){
			linksDest = "";
			for(int j=0; j<pages.size(); j++){
				if(links[pages.get(i).getIndex()][pages.get(j).getIndex()] == 1)
					linksDest += (pages.get(j).getIndex()+", ");
			}
			
			if(linksDest.length() > 2)
				linksDest = linksDest.substring(0,linksDest.length()-2);
			
			if(!linksDest.equals("")){
				String[] arr = linksDest.split("\\, ");
				int[] linkNum = new int[arr.length];
			
				for(int k=0; k<arr.length; k++)
					linkNum[k] = Integer.parseInt(arr[k]);
			
				Arrays.sort(linkNum);
				linksDest = "";
			
				for(int k=0; k<linkNum.length; k++)
					linksDest += (linkNum[k]+", ");
			}
			
			String k = pages.get(i).getKeywords().toString();
			k = k.substring(1, k.length()-1);
			
			if(linksDest.length() > 2)
				linksDest = linksDest.substring(0,linksDest.length()-2);
			
			//System.out.println(pages.get(i).toString().replace("***",linksDest));
			
			System.out.println(pages.get(i).toString()+String.format(" %-24s | %s",linksDest,k));
		}
	}
	
	/**
	 * Sort 'pages' numerically ASCENDING based on index of the WebPage
	 */
	public void sortIndex(){
		Collections.sort(this.pages, new IndexComparator());
		return;
	}
	
	/**
	 * Sort 'pages' alphabetically ASCENDING based on the URL of the WebPage
	 */
	public void sortRank(){
		Collections.sort(this.pages, new RankComparator());
		return;
	}
	
	/**
	 * Sort 'pages' numerically DESCENDING based on the PageRank of the WebPage
	 */
	public void sortUrl(){
		Collections.sort(this.pages, new URLComparator());
		return;
	}
	
	
	/**
	 * Searches the 'pages' to find the WebPages with the indicated keyword
	 * @param keyword
	 * 		keyword being searched for in pages
	 */
	public void searchForKeyword(String keyword){
		ArrayList<WebPage> pagesTemp = new ArrayList<WebPage>();
		boolean found = false;
		
		for(int i=0; i<pages.size(); i++){
			if(pages.get(i).getKeywords().contains(keyword)){
				found = true;
				pagesTemp.add(pages.get(i));
			}
		}
		
		if(!found){
			System.out.println("\nNo search results found for the keyword "+keyword);
			return;
		}
		
		Collections.sort(pagesTemp, new RankComparator());
		
		System.out.println();
		System.out.printf(" %-15s | %-16s | %-21s \n","Rank","PageRank","URL");
		String tableLine = "";
		for(int i=1; i<=57; i++)
			tableLine+="-";
		System.out.println(" "+tableLine);
		
		for(int i=0; i<pagesTemp.size(); i++){
			System.out.printf(" %-15d |  %-15d | %-21s \n",i+1,pagesTemp.get(i).getRank(),
					pagesTemp.get(i).getUrl());
		}

	}
}