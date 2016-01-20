/**
 * Dennis Sosa
 * Assignment: #7 (Directed Graph of WebPage Objects)
 * CSE 214
 * Stony Brook University
 * @author DennisSosa
 * 
 * Comparator Helper Class: URLComparator Class
 */

import java.util.*;

public class URLComparator implements Comparator {

	/** 
	 * A Helper Class that helps with sorting.
	 * sorts alphabetically ASCENDING based the URL of the WebPage
	 */
	public int compare(Object o1, Object o2) {
		WebPage w1 = (WebPage) o1;
		WebPage w2 = (WebPage) o2;
		
		return(w1.getUrl().compareTo(w2.getUrl()));
	}

}
