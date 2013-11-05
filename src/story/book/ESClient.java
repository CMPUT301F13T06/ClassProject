package story.book;

import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.reflect.TypeToken;

/**
 * 
 * @author Vina Nguyen
 * 
 */

public class ESClient extends DataClient {	
	private ESConnection connector = new ESConnection();
	
	/**
	 *  Publishes a story.	 
	 *  
	 * @param SID is a Story's SID
	 */
	public void saveStory(Story story) {
		try {
			String stringSID = String.valueOf(story.getStoryInfo().getSID());
			OutputStreamWriter out = connector.getESWriter(stringSID);
			
			String story_string = super.serialize(story);
			out.write(story_string);
			
			connector.closeESWriter();
			
			/*connector.setFolder("SIDTests/");
			ArrayList<String> SIDs;
			
			try {
				String server_read = connector.getESRead("1");
				Type type = new TypeToken<ESData<ArrayList<String>>>(){}.getType();
				ESData<ArrayList<String>> es = (ESData<ArrayList<String>>) super.unSerialize(server_read, type);
				SIDs = es.getSource();
			} catch (NullPointerException e) {
				SIDs = new ArrayList<String>();
			}
			
			if (!SIDs.contains(stringSID)) {
				SIDs.add(stringSID);
				out = connector.getESWriter("1");
				String SID_string = super.serialize(SIDs);
				//out.write(SID_string);
				out.write(story_string);
				System.out.println(stringSID);
			} 
			
			connector.resetFolder();*/
			connector.closeESWriter();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Retrieves an online story (to be used for download or viewing).	 
	 *  
	 * @param SID is a Story's SID
	 * @return a null on failure or a Story object 
	 */
	public Story getStory(int SID) {
		try {			
			String server_read = connector.getESRead(String.valueOf(SID));
			
			Type type = new TypeToken<ESData<Story>>(){}.getType();
			ESData<Story> es = (ESData<Story>) super.unSerialize(server_read, type);
			
			return es.getSource();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public ArrayList<StoryInfo> getStoryInfoList() {
		try {
			String server_read = connector.getESRead("_search?");
			
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
		// TODO Auto-generated method stub
		return false;
	}

	public int getSID() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}