package story.book;

import java.util.ArrayList;

public interface StoryController {
	
	public void getStory(int SID);
		
	public ArrayList<StoryInfo> getStoryList();
	
	public void saveStory();
}
