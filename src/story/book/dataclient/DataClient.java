package story.book.dataclient;

import java.lang.reflect.Type;
import java.util.ArrayList;

import story.book.Story;
import story.book.StoryInfo;

import com.google.gson.Gson;

/**
 * DataClient is an abstract class that represents 
 * a storage location that contains Stories. Stories
 * can be saved and retrieved using a DataClient.
 * 
 * @author Anthony Ou
 * 
 * @author Vina Nguyen
 * 
 */
public abstract class DataClient {
	
	protected static Gson Gsonclient = new Gson();
	
	protected DataClient() {}
	
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
	 * @param Serial is a string of serialized object of Type type
	 * @return a null on failure or an object (responsibility of caller to cast it)
	 */
	protected Object unSerialize(String serial, Type type){
		return Gsonclient.fromJson(serial, type);
	}
	
	/**
	 * 
     * @param aStory, the story to be saved
     */
	public abstract void saveStory(Story aStory);
	
    /**
     * 
     * @param SID of story
     * @returns A Story with the given SID
     */
	public abstract Story getStory(int SID);
	
    /**
     * 
     * @return true if the SID is not in use, false other-wise
     */
	public abstract Boolean checkSID(int SID);
	
    /**
     * 
     * @return -1 on failure, a free SID other-wise
     */
	public abstract int getSID();
	
    /**
     * 
     * @return an array list of StoryInfos
     */
	public abstract ArrayList<StoryInfo> getStoryInfoList();
	
}
