package story.book;

import java.util.ArrayList;

/**
 * StoryController interface for getting Story objects, lists of stories and
 * saving stories.
 * 
 * @author Alex
 */
public interface StoryController {
	
	/**
	 * Sets the current Story of the StoryApplication.
	 * 
	 * @param SID the SID of the Story to set
	 */
	public void getStory(int SID);
		
	/**
	 * Gets an ArrayList of StoryInfo to be displayed to the user.
	 * @return the ArrayList of StoryInfo objects from a source
	 */
	public ArrayList<StoryInfo> getStoryList();
	
	/**
	 * Save the Application current Story to a destination.
	 */
	public void saveStory();

}
