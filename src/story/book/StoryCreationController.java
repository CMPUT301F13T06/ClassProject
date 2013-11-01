package story.book;

public class StoryCreationController {
	
	private Story story;
	
	public StoryCreationController(Story story) {
		setStory(story);
	}
	
	/** 
	 * Instantiates a new StoryFragment object and adds it to the Story object
	 * being modified.
	 * 
	 * @param fragmentTitle	The proposed title of the new StoryFragment.
	 */
	public StoryFragment newFragment(String fragmentTitle) {
		return new StoryFragment(fragmentTitle);
	}
	
	public void addFragment(StoryFragment storyFragment) {
		story.addFragment(storyFragment);
	}

	public void editFragment() {
		// TODO: This might have to be implemented by the LocalStoriesActivity
	}
	
	public void deleteFragment(StoryFragment storyFragment) {
		story.removeFragment(storyFragment);
	}
	
	private void setStory(Story story) {
		this.story = story;
	}
	
}
