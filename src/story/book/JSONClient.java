package story.book;

import com.google.gson.Gson;

public class JSONClient {
	
	private Gson Jsc = new Gson();
	
	public JSONClient() {}
	
	/**
	 * 
	 * @param s is an Object of type story
	 * @return s as a serialized string.
	 */
	protected String serializeStory(Story s){
		return Jsc.toJson(s); 
	}
	
	/**
	 * 
	 * @param Serial is a string of serialized story
	 * @return a null on failure or a Story object
	 */
	protected Story unSerialize(String Serial){
		return Jsc.fromJson(Serial, Story.class);
	}

	/**
	 * 
	 * @param Serial is a string of serialized ElasticSearch response
	 * @return a null on failure or a Story object
	 */
	protected Story unSerializeElasticRequest(String Serial){
		Story s = null;
		ElasticSearchResponse res = Jsc.fromJson(Serial, ElasticSearchResponse.class);
		s = res.getSource();
		return s;
	}
	
}
