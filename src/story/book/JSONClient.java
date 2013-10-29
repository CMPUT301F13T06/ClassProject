package story.book;

import com.google.gson.Gson;

public class JSONClient {
	private Gson g;
	
	public JSONClient() {
		g = new Gson();
	}
	
	/**
	 * 
	 * @param s is an Object of type story
	 * @return s as a serialized string.
	 */
	public String serializeStory(Story s){
		return g.toJson(s).toString(); 
	}
	
	/**
	 * 
	 * @param Serial is a string of serialized story as a string
	 * @return a null on failure or a Story object
	 */
	public Story unSerialize(String Serial){
		Story s = null;
		g.fromJson(Serial, Story.class);
		return s;
	}
	
}
