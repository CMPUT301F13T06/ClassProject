package story.book;

public class StoryInfoController {
	private Story story;
	
	public StoryInfoController() {
		story = StoryApplication.getCurrentStory();
	}
	
	public StoryInfo getStoryInfo() {
		return story.getStoryInfo();
	}

}
