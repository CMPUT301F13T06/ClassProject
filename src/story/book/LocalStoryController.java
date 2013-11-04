package story.book;

import java.io.IOException;
import java.util.ArrayList;

public class LocalStoryController implements StoryController {

	private IOClient io;
	private ESClient es;
	
	public LocalStoryController() {
		io = StoryApplication.getIOClient();
		es = StoryApplication.getESClient();
	}
	
	public void getStory(int SID) {
		try {
			StoryApplication.setCurrentStory(io.getStory(SID));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<StoryInfo> getStoryList() {
		// TODO: Needs to be implemented with IOClient
		return null;
	}
	
	public void createStory() {
		StoryApplication.setCurrentStory(new Story(new StoryInfo()));
	}

	public void deleteStory(int SID) {
		// TODO: Needs support from IOClient
	}

	public void saveStory() {
		try {
			io.saveStory(StoryApplication.getCurrentStory());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Publishes the Story with the specified SID to the server.
	 * 
	 * @param SID the SID of the story being published
	 */
	public void publishStory(int SID) {
		// TODO: no need to check for SID conflict if story has already been
		// published before.
		
		// Load the specified Story as the current Story
		getStory(SID);
		
		// Check if the SID has any conflicts with the server
		int id = checkSIDConflict(SID);
		
		// Publish the story
		// XXX: there is a potential of SID conflict beyond this point
		es.publishStory(StoryApplication.getCurrentStory());
	}
	
	/**
	 * Checks if an SID already exists on the server. If there is a conflict, 
	 * it is resolved by changing the SID of the current Story to a new SID.
	 * 
	 * @param SID the SID being checked for
	 * @return 
	 */
	private int checkSIDConflict(int SID) {
		
		// Check if any remote story has conflicting SID. ESClient will return
		// the original ID if it is free, else it returns a free SID from the
		// server.
		int id = es.checkSID(SID);
		
		if (id != SID) {
			// Change the current Story's SID to the new SID supplied by the
			// ESClient.
			StoryApplication.getCurrentStory().getStoryInfo().setSID(id);
		}
		
		return id;
	}
	
}
