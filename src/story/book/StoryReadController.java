package story.book;

public class StoryReadController {
	private Story story;
	
	public StoryReadController() {
		story = StoryApplication.getCurrentStory();
	}
	
	public StoryFragment getStartingFragment() {
		return story.getStoryFragments().get(story.getStoryInfo().getStartingFragmentID());
	}
	
	public StoryFragment getStoryFragment(int storyFragmentID) {
		return story.getStoryFragments().get(storyFragmentID);
	}
}
