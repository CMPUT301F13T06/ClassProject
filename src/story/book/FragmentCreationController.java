package story.book;

public class FragmentCreationController {

	private StoryFragment storyFragment;
	
	public FragmentCreationController(StoryFragment storyFragment) {
		setStoryFragment(storyFragment);
	}
	
	/**
	 * Adds a TextIllustration to the fragment. Consider revising using java
	 * generics to incorporate different illustration types.
	 * 
	 * @param content
	 */
	public void addTextIllustration(String content) {
		
	}
	
	private void setStoryFragment(StoryFragment storyFragment) {
		this.storyFragment = storyFragment;
	}
}
