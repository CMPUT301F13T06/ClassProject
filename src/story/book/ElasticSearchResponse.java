package story.book;

public class ElasticSearchResponse {
    String _index;
    String _type;
    String _id;
    int _version;
    boolean exists;
    Story _source;
    double max_score;
    public Story getSource() {
        return _source;
    }
}
