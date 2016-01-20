/**
 * Dennis Sosa
 * Assignment: #7 (Directed Graph of WebPage Objects)
 * CSE 214 
 * Stony Brook University
 * @author DennisSosa
 * 
 * WebPage Class
 */

import java.util.ArrayList;

public class WebPage {
	private String url;
	private int index;
	private int rank;
	private ArrayList<String> keywords;

	/**
	 * Creates a new WebPage object with uninstantiated parameters
	 * Represents a WebPage (hyperlinked document) in the WebGraph
	 */
	public WebPage(){

	}

	/**
	 * Creates a new WePage object with given values
	 * Represents a Webpage (hyperlinked document) in the WebGraph
	 * Loading Constructor
	 * @param url
	 * 		unique URL of this WebPage object
	 * @param keywords
	 * 		Collection of Strings containing the keywords describing this WebPage
	 */
	public WebPage(String url, ArrayList<String> keywords){
		this.url = url;
		this.keywords = keywords;
	}

	/**
	 * Receives the WebPage's position in the adjacency matrix
	 * @return
	 * 		the index of the WebPage as an int
	 */
	public int getIndex(){
		return index;
	}
	
	/**
	 * Receives the WebPage's rank in the WebGraph
	 * @return
	 * 		the rank of the WebPage as an int
	 */
	public int getRank(){
		return rank;
	}

	/**
	 * Receives the WebPage's URL 
	 * @return
	 * 		current WebPage's URL as a String
	 */
	public String getUrl(){
		return url;
	}
	
	/**
	 * Receives the current WebPage's keywords
	 * @return
	 * 		current WebPage's keywords as an ArrayList<String>
	 */
	public ArrayList<String> getKeywords(){
		return keywords;
	}

	/**
	 * Changes a particular WebPage's rank to a new specified rank
	 * @param rank
	 * 		new rank for the WebPage object
	 */
	public void setRank(int rank){
		this.rank = rank;
	}

	/**
	 * Changes a particular WebPage's index to a new specified index
	 * @param index
	 * 		new index for the WebPage object
	 */
	public void setIndex(int index){
		this.index = index;
	}

	/**
	 * Obtain the String representation of this WebPage object,
	 * which is a neatly formatted String of the data members of the
	 * WebPage in tabular form
	 * @return
	 * 		the representation of this WebPage as a String
	 */
	public String toString(){
		//String[] keys = keywords.toArray(new String[keywords.size()]);
		
		//int length = keys.length;
		/*
		String result = "";
		
		for(int i=0; i<length; i++){
			if(i==length-1)
				result += (keys[i]);
			else
				result += (keys[i]+", ");
		}*/
		
		String finalResult = String.format(" %-15d | %-19s | %-22d |",index,(url!=null)?url:"",
				rank);
		
		return finalResult;
	}
	
	

}