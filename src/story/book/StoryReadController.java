package story.book;

public class StoryReadController {
	private Story story;
	
	public StoryReadController() {
		story = StoryApplication.getCurrentStory();
	}
	
	public StoryFragment getStartingFragment() {
		return story.getStoryInfo().getStartingFragment();
	}
}
