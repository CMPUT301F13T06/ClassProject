package story.book;

import java.util.ArrayList;

public class OnlineStoryController implements StoryController {

	private ESClient es;
	private IOClient io;
	
	public OnlineStoryController() {
		es = StoryApplication.getESClient();
		io = StoryApplication.getIOClient();
	}
	
	public void getStory(int SID) {
		/* TODO: Dependency on ESClient
		StoryApplication.setCurrentStory(es.getStory(SID));
		*/
	}

	public void saveStory() {
		/* TODO: Dependency on ESClient
		io.saveStory();
		*/
	}

	public ArrayList<StoryInfo> getStoryList() {
		/* TODO
		return es.getStoryList();
		*/
		return null;
	}

}
