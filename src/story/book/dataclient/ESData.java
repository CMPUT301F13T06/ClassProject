package story.book.dataclient;

/**
 * ESData<T> represents a data item stored at _index/_type/_id 
 * on our Elastic Search server. 
 * 
 * From:
 * ES Demo (https://github.com/rayzhangcl/ESDemo, Creative Commons 0) 10/2013
 */
public class ESData<T> {
	String _index;
	String _type;
	String _id;
	
	int _version;
	boolean exists; // if a data item exists
	double max_score;
	T fields;
	T _source; // the data

	public T getSource() {
		return _source;
	}
	
	public T getFields() {
		return fields;
	}
	
	public String getID() {
		return _id;
	}
}
