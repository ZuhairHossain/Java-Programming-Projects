/**
 * Dennis Sosa
 * Assignment: #7 (Directed Graph of WebPage Objects)
 * CSE 214
 * Stony Brook University
 * @author DennisSosa
 * 
 * Comparator Helper Class: RankComparator Class
 */

import java.util.*;

public class RankComparator implements Comparator {
	
	/** 
	 * A Helper Class that helps with sorting.
	 * sorts numerically DESCENDING based on the PageRank of the WebPage
	 */
	public int compare(Object o1, Object o2) {
		WebPage w1 = (WebPage) o1;
		WebPage w2 = (WebPage) o2;
		
		if(w1.getRank() == w2.getRank())
			return 0;
		else if(w1.getRank() < w2.getRank())
			return 1;
		else
			return -1;
	}
	
}

