package story.book;

import java.util.ArrayList;
import java.util.Date;

import story.book.StoryInfo.PublishState;
import story.book.dataclient.IOClient;
import story.book.dataclient.ESClient;

/**
 * Story controller for the <code>LocalStoriesActivity</code> to manage locally
 * stored stories. This controller interfaces with the IO to 
 * save, and create Story objects.
 * 
 * @author Alexander Cheung
 *
 */
public class LocalStoryController implements StoryController {

	private IOClient io;
	
	public LocalStoryController() {
		io = StoryApplication.getIOClient();
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
		return io.getStoryInfoList();
	}
	
	/**
	 * Instantiates a new Story object and sets the current application Story
	 * to the new object.
	 */
	public void createStory() {
		StoryInfo info = new StoryInfo();
		info.setAuthor(StoryApplication.getNickname());
		info.setSID(io.getSID());
		StoryApplication.setCurrentStory(new Story(info));
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
}
