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
	
	public FragmentCreationController(StoryFragment storyFragment) {
		super();
		this.storyFragment = storyFragment;
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
	
	public void removeTextIllustration(TextIllustration textIllustration) {
		storyFragment.removeIllustration(textIllustration);
	}
	
}
