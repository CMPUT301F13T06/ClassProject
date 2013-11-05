package story.book;

import java.util.ArrayList;

/**
 * Story controller for the <code>LocalStoriesActivity</code> to manage locally
 * stored stories. This controller interfaces with the IO and ES clients to 
 * publish, save, and create Story objects.
 * 
 * @author Alexander Cheung
 *
 */
public class LocalStoryController implements StoryController {

	private IOClient io;
	private ESClient es;
	
	public LocalStoryController() {
		io = StoryApplication.getIOClient();
		es = StoryApplication.getESClient();
	}
	
	/**
	 * Sets the current application Story to the locally stored Story with 
	 * matching SID.
	 * 
	 * @param SID the SID of the Story to fetch
	 */
	public void getStory(int SID) {
		StoryApplication.setCurrentStory(io.getStory(SID));
	}
	
	/**
	 * Returns the list of StoryInfo corresponding to all locally stored 
	 * stories.
	 */
	public ArrayList<StoryInfo> getStoryList() {
		//return io.getStoryList();
		return null;
	}
	
	/**
	 * Instantiates a new Story object and sets the current application Story
	 * to the new object.
	 */
	public void createStory() {
		StoryApplication.setCurrentStory(new Story(new StoryInfo()));
	}

	/**
	 * Deletes the locally stored Story with the specified SID.
	 * 
	 * @param SID the SID of the story to delete.
	 */
	public void deleteStory(int SID) {
		io.deleteStory(SID);
	}

	/**
	 * Save the current application Story to local storage.
	 */
	public void saveStory() {
		io.saveStory(StoryApplication.getCurrentStory());
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
		
		// Check if the SID has any conflicts with the server and resolve it
		checkSIDConflict(SID);
		
		// Publish the story
		// XXX: there is a potential of SID conflict beyond this point
		es.saveStory(StoryApplication.getCurrentStory());
	}
	
	/**
	 * Checks if an SID already exists on the server. If there is a conflict, 
	 * it is resolved by changing the SID of the current Story to a new SID.
	 * 
	 * @param SID the SID being checked for
	 * @return 
	 */
	private void checkSIDConflict(int SID) {
		/*
		// Check if any remote story has conflicting SID. ESClient will return
		// the original ID if it is free, else it returns a free SID from the
		// server.
		if (!es.checkSID(SID))
			int id = es.getSID(SID);
		
			// Change the current Story's SID to the new SID supplied by the
			// ESClient.
			StoryApplication.getCurrentStory().getStoryInfo().setSID(id);
		}
		*/
	}
	
}
