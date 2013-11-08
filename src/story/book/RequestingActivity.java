package story.book;

/**
 * Classes that want to use the RequestTextDialog 
 * need to implement this interface to get the returned text.
 * 
 * @author Vina Nguyen
 * 
 * @see RequestTextDialog
 * 
 * @see StoryFragmentEditActivity
 * @see StoryFragmentListActivity
 * @see DecisionPickerActivity
 */

public interface RequestingActivity {
	public void onUserSelectValue(String title);
}
