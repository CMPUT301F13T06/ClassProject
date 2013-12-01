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
public class ES_ID_Response {    
	int took;
	boolean timed_out;
	transient Object _shards;
	IDDataMatches hits;

	public Collection<IDdata> getHits() {
		return hits.getHits();        
	}

	public Collection<String> getSources() {
		Collection<String> out = new ArrayList<String>();
		
		for (IDdata essrt : getHits()) {
			out.add( essrt.getID() );
		}

		return out;
	}
	private class IDDataMatches {
		int total;
		double max_score;
		Collection<IDdata> hits;
		
		public Collection<IDdata> getHits() {
			return hits;
		}
	}
	private class IDdata {
		String _index;
		String _type;
		String _id;
		
		double _score;
		
		public String getID() {return _id;}
	}
}
