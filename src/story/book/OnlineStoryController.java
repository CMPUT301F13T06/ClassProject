package story.book;

import java.util.ArrayList;

public class OnlineStoryController implements StoryController {

	private ESClient es;
	
	public OnlineStoryController() {
		es = StoryApplication.getESClient();
	}
	
	public void getStory(int SID) {
		// TODO: Resolve error - need implementation in ESClient
		//StoryApplication.setCurrentStory(es.getStory(SID));
	}

	public void saveStory() {
		// TODO Auto-generated method stub
	}

	public ArrayList<StoryInfo> getStoryList() {
		// TODO Auto-generated method stub
		return null;
	}

}
