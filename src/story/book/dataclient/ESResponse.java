package story.book.dataclient;

import java.util.ArrayList;
import java.util.Collection;

/**
 * ESResponse<T> is response matching a query to our 
 * Elastic Search server, which includes header information 
 * in addition to the collection of data items.
 * 
 * From:
 * ES Demo (https://github.com/rayzhangcl/ESDemo, Creative Commons 0) 10/2013
 */
public class ESResponse<T> {    
	int took;
	boolean timed_out;
	transient Object _shards;
	ESDataMatches<T> hits;
	boolean exists;
	
	public Collection<ESData<T>> getHits() {
		return hits.getHits();        
	}

	public Collection<T> getSources() {
		Collection<T> out = new ArrayList<T>();
		
		if (hits.getTotal() > 0) {
			for (ESData<T> essrt : getHits()) {
				out.add( essrt.getSource() );
			}
		}
		
		return out;
	}
	
	public Collection<T> getFields() {
		Collection<T> out = new ArrayList<T>();
		
		if (hits.getTotal() > 0) {
			for (ESData<T> essrt : getHits()) {
				out.add( essrt.getFields() );
			}
		}
		
		return out;
	}
	
	public Collection<String> getIDs() {
		Collection<String> out = new ArrayList<String>();
		
		if (hits.getTotal() > 0) {
			for (ESData<T> essrt : getHits()) {
				out.add( essrt.getID() );
			}
		}

		return out;
	}
}
