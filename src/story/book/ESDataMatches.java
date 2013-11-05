package story.book;

import java.util.Collection;

/**
 * ESDataMatches<T> represents a collection of data items
 * matching a query to our Elastic Search server.
 */
public class ESDataMatches<T> {
	int total;
	double max_score;
	Collection<ESData<T>> hits;
	
	public Collection<ESData<T>> getHits() {
		return hits;
	}
}
