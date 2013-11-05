package story.book;

/**
 * ESData<T> represents a data item stored at _index/_type/_id 
 * on our Elastic Search server. 
 */
public class ESData<T> {
    String _index;
    String _type;
    String _id;
    
    int _version;
    boolean exists; // if a data item exists
    double max_score;
    T _source; // the data

    public T getSource() {
        return _source;
    }
}
