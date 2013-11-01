package story.book;

import java.util.ArrayList;

public class LocalStoryHandler extends StoryHandler {

	@Override
	public ArrayList<StoryInfo> getStoryList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Story getStory(int SID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Story createStory() {
		// TODO: Should this set default StoryInfo, or accept a StoryInfo as
		// a parameter?
		return new Story(new StoryInfo());
	}
}
