/**
 * Dennis Sosa
 * Assignment: #7 (Directed Graph of WebPage Objects)
 * CSE 214
 * Stony Brook University
 * @author DennisSosa
 * 
 * Comparator Helper Class: IndexComparator Class
 */

import java.util.*;

public class IndexComparator implements Comparator {
	
	/** 
	 * A Helper Class that helps with sorting.
	 * sorts numerically ASCENDING based on the index of the WebPage
	 */
	public int compare(Object o1, Object o2) {
		WebPage w1 = (WebPage) o1;
		WebPage w2 = (WebPage) o2;
		
		if(w1.getIndex() == w2.getIndex())
			return 0;
		else if(w1.getIndex() > w2.getIndex())
			return 1;
		else
			return -1;
	}
	
}
