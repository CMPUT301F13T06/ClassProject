package story.book;

/**
 * Controller responsible for creating and editing instances of Story for the
 * StoryFragmentListActivity.
 * 
 * @author Alexander Cheung
 *
 */
public class StoryCreationController {
	
	private Story story;
	
	public StoryCreationController() {
		this.story = StoryApplication.getCurrentStory();
	}
	
	/** 
	 * Instantiates a new StoryFragment object, adds it to the current Story,
	 * and returns a reference to it.
	 * 
	 * @param fragmentTitle	The proposed title of the new StoryFragment.
	 */
	public StoryFragment newFragment(String fragmentTitle) {
		return new StoryFragment(fragmentTitle);
	}
	
	/**
	 * Adds a instance of StoryFragment to the current Story.
	 * 
	 * @param storyFragment the instance of StoryFragment to add
	 */
	public void addFragment(StoryFragment storyFragment) {
		story.addFragment(storyFragment);
	}
	
	/**
	 * Removes an instance of StoryFragment from the current Story.
	 * 
	 * @param storyFragment the instance of StoryFragment to remove
	 */
	public void deleteFragment(StoryFragment storyFragment) {
		story.removeFragment(storyFragment);
	}
	
}
