package story.book;

import story.book.dataclient.IOClient;

/**
 * Controller responsible for creating, editing, displaying
 * the StoryInfo of the current Story for the StoryInfoActivity 
 * and EditStoryInformationActivity.
 * 
 * @author Vina Nguyen
 *
 */
public class StoryInfoController {
	private Story story;
	private IOClient io;
	
	public StoryInfoController() {
		story = StoryApplication.getCurrentStory();
		io = StoryApplication.getIOClient();
	}
	
	/**
	 * 
	 * @return StoryInfo of the current story
	 */
	public StoryInfo getStoryInfo() {
		return story.getStoryInfo();
	}
	
	/**
	 * 
	 * @param StoryInfo to set for the current story
	 */
	public void setStoryInfo(StoryInfo info) {
		story.setStoryInfo(info);
	}

}
