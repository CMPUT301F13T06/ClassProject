package story.book;

import android.util.Log;
import story.book.dataclient.IOClient;

/**
 * Controller responsible for creating, editing, displaying
 * the StoryInfo of the current Story for the StoryInfoActivity 
 * and EditStoryInformationActivity.
 * 
 * @author Vina Nguyen
 *
 */
public class StoryInfoController extends LocalEditingController {
	private StoryInfo storyInfo;
	private IOClient io;
	
	public StoryInfoController() {
		storyInfo = StoryApplication.getCurrentStory().getStoryInfo();
		io = StoryApplication.getIOClient();
	}
	
	/**
	 * 
	 * @return StoryInfo of the current story
	 */
	public StoryInfo getStoryInfo() {
		return storyInfo;
	}

	/**
	 * Saves the current story, defaulting the
	 * author if necessary.
	 */
	@Override
	public void saveStory() {
		if (storyInfo.getAuthor().equals("")) {
			storyInfo.setAuthor(StoryApplication.getNickname());
		}
		super.saveStory();
	}
}
