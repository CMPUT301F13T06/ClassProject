package story.book;

import com.google.gson.Gson;

public class JSONClient {
	
	private static Gson Gsonclient = new Gson();
	
	private JSONClient() {}
	
	/**
	 * 
	 * @param s is an Object of type story
	 * @return s as a serialized string.
	 */
	static String serializeStory(Story s){
		return Gsonclient.toJson(s); 
	}
	
	/**
	 * 
	 * @param Serial is a string of serialized story
	 * @return a null on failure or a Story object
	 */
	static Story unSerialize(String Serial){
		return Gsonclient.fromJson(Serial, Story.class);
	}

	/**
	 * 
	 * @param Serial is a string of serialized ElasticSearch response
	 * @return a null on failure or a Story object
	 */
	static Story unSerializeESResponse(String Serial){
		Story s = null;
		ElasticSearchResponse res = Gsonclient.fromJson(Serial, ElasticSearchResponse.class);
		s = res.getSource();
		return s;
	}
	
}
