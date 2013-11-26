package story.book.dataclient;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import story.book.model.Story;
import story.book.model.StoryInfo;

import android.util.Log;

import com.google.gson.reflect.TypeToken;

/**
 * ESClient is a DataClient class that represents 
 * an online storage location for Stories. Stories
 * are saved to an Elastic Search server.
 * 
 * @author Vina Nguyen
 * 
 */

public class ESClient extends DataClient {	
	private String def_folder = "stories/";
	private String SID_folder = "SIDs/0";
	private String Binary_folder = "binaries2/";
	
	/**
	 *  Publishes a story.	 
	 *  
	 * @param SID is a Story's SID
	 */
	public void saveStory(Story story) {
		try {
			int SID = story.getStoryInfo().getSID();
			String stringSID = String.valueOf(SID);
			String story_string = super.serialize(story);
			
			// Write the Story to the server
			String result = new ESWrite(def_folder).execute(stringSID, story_string).get();

			// Read the existing SIDList, if any
			SIDList list = readSIDList();
			
			ArrayList<Integer> SIDs = list.getSIDs();
			// If we are publishing a story for the first time, add the SID to the list
			if (!SIDs.contains(Integer.valueOf(SID))) {
				SIDs.add(Integer.valueOf((SID)));
				String SID_string = super.serialize(list);
				
				// Write the updated SID list to the server
				new ESWrite(SID_folder).execute("", SID_string);
			} 
			
			// Write the binary data to the server
			BinaryList binaryList = new BinaryList();
			binaryList.encodeStoryIllustrations(story);

			String binary_string = super.serialize(binaryList);
			result = new ESWrite(Binary_folder).execute(stringSID, binary_string).get();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *  Reads the in-use SID list from the server. 
	 *  
	 * @return SIDList
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	private SIDList readSIDList() {
		String server_read;
		try {
			server_read = new ESRead(SID_folder).execute("").get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			server_read = "";
		} 
		
		SIDList list = new SIDList();
		
		if (!server_read.equals("")) {
			Type type = new TypeToken<ESData<SIDList>>(){}.getType();
			ESData<SIDList> es = (ESData<SIDList>) super.unSerialize(server_read, type);
			list = es.getSource();
		}
		
		return list;
	}
	
	/**
	 * Retrieves an online story (to be used for download or viewing).	 
	 *  
	 * @param SID is a Story's SID
	 * @return a null on failure or a Story object 
	 */
	public Story getStory(int SID) {
		try {			
			
			String sid_string = String.valueOf(SID);
			
			// Get the story from the server
			String server_read = new ESRead(def_folder).execute(sid_string ).get();
			Type type = new TypeToken<ESData<Story>>(){}.getType();
			ESData<Story> es = (ESData<Story>) super.unSerialize(server_read, type);
			
			Story story = es.getSource();
			
			// Get the illustrations from the server
			server_read = new ESRead(Binary_folder).execute(sid_string ).get();
			type = new TypeToken<ESData<BinaryList>>(){}.getType();
			ESData<BinaryList> es_bl = (ESData<BinaryList>) super.unSerialize(server_read, type);
			BinaryList bl = es_bl.getSource();
			bl.decodeStory(story);
			
			return  story;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public ArrayList<StoryInfo> getStoryInfoList() {
		try {
			String server_read = new ESRead(def_folder).execute("_search?").get();
			
			Type type = new TypeToken<ESResponse<Story>>(){}.getType();
			ESResponse<Story> es = (ESResponse<Story>) super.unSerialize(server_read, type);			
			
			ArrayList<StoryInfo> storyInfoList = new ArrayList<StoryInfo>();
			ArrayList<Story> stories = (ArrayList<Story>) es.getSources();
			for(Story s: stories) {
				storyInfoList.add(s.getStoryInfo());
			}
			
			return storyInfoList;			
			 
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Boolean checkSID(int SID) {
		SIDList list = readSIDList();
		ArrayList<Integer> SIDs = list.getSIDs();
		if (!SIDs.contains(Integer.valueOf(SID))) {
			return true;
		} else {
			return false;
		}
	}

	public int getSID() {
		SIDList list = readSIDList();
		ArrayList<Integer> SIDs = list.getSIDs();
		
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			if (!SIDs.contains(i)) {
				return i;
			} 
		}
		
		return -1;
	}
}