package story.book;

import java.util.ArrayList;

public interface StoryController {
	
	public Story getStory(int SID); // generic
		
	public ArrayList<StoryInfo> getStoryList();
}
