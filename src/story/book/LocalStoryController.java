package story.book;

import java.io.IOException;
import java.util.ArrayList;

public class LocalStoryController implements StoryController {

	private IOClient io;
	
	public LocalStoryController() {
		StoryApplication.getIOClient();
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
}
