package story.book;

import com.google.gson.Gson;

public abstract class JSONClient {
	
	protected static Gson Gsonclient = new Gson();
	
	protected JSONClient() {}
	
	/**
	 * 
	 * @param s is an Object of type story
	 * @return s as a serialized string.
	 */
	protected String serializeStory(Story s){
		return Gsonclient.toJson(s); 
	}
	
	/**
	 * 
	 * @param Serial is a string of serialized story
	 * @return a null on failure or a Story object
	 */
	protected Story unSerialize(String Serial){
		return Gsonclient.fromJson(Serial, Story.class);
	}

}
