package story.book.dataclient;

import java.util.ArrayList;

/**
 * Wraps an ArrayList of SIDs in order to allow serialization with JSON.
 * 
 * @author Vina Nguyen
 */

public class SIDList {
	ArrayList<Integer> SIDs;
	
	public SIDList() {
		SIDs = new ArrayList<Integer>();
	}

	public ArrayList<Integer> getSIDs() {
		return SIDs;
	}
}
