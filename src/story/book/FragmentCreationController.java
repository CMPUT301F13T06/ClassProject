package story.book;

/**
 * Controller responsible for adding and removing <code>Illustration</code>
 * objects of a <code>StoryFragment</code> object for the 
 * <code>StoryFragmentEditActivity</code>.
 * 
 * @author 	Alexander Cheung
 * @see 	Illustration
 * @see		TextIllustration
 * @see		StoryFragment
 * @see		LocalEditingController
 */
public class FragmentCreationController extends LocalEditingController {

	private StoryFragment storyFragment;
	
	/**
	 * Initializes the controller with the story fragment with the specified
	 * fragment ID.
	 * 
	 * @param 	fragmentID	the ID of the story fragment
	 */
	public FragmentCreationController(int fragmentID) {
		super();
		this.storyFragment = StoryApplication.getCurrentStory()
				.getStoryFragments().get(fragmentID);
	}
	
	/**
	 * Adds a TextIllustration to the fragment. Consider revising using java
	 * generics to incorporate different illustration types.
	 * 
	 * @param content
	 */
	public void addTextIllustration(String content) {
		storyFragment.addIllustration(new TextIllustration(content));
	}
	
	/**
	 * Removes the specified <code>TextIllustration</code> from the story
	 * fragment.
	 * 
	 * @param 	textIllustration	the <code>TextIllustration</code> to remove
	 */
	public void removeTextIllustration(TextIllustration textIllustration) {
		storyFragment.removeIllustration(textIllustration);
	}
	
}
