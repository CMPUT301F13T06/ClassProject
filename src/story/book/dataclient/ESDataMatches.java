package story.book.dataclient;

import java.util.Collection;

/**
 * ESDataMatches<T> represents a collection of data items
 * matching a query to our Elastic Search server.
 * 
 * From:
 * ES Demo (https://github.com/rayzhangcl/ESDemo, Creative Commons 0) 10/2013
 */
public class ESDataMatches<T> {
	int total;
	double max_score;
	Collection<ESData<T>> hits;
	
	public Collection<ESData<T>> getHits() {
		return hits;
	}
}
