package story.book;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public abstract class JSONClient {
	
	protected static Gson Gsonclient = new Gson();
	
	protected JSONClient() {}
	
	/**
	 * 
	 * @param an object
	 * @return object as a serialized string.
	 */
	protected String serialize(Object data){
		return Gsonclient.toJson(data); 
	}
	
	/**
	 * 
	 * @param Serial is a string of serialized object of Type T
	 * @return a null on failure or an object of Type T
	 */
	protected <T> T unSerialize(String serial, Class<T> type){
		return Gsonclient.fromJson(serial, type);
	}

}
