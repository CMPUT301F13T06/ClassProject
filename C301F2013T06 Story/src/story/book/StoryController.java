package story.book;

import java.util.ArrayList;

public interface StoryController {
	public Story getStory(int SID); // generic
	public void saveStory(); // generic -> JSONifying the Story; publishes if remote
	public ArrayList<StoryInfo> getStoryList();
}
