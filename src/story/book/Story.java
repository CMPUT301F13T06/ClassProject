package story.book;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Main class for representing stories which extends the abstract class
 * <code>StoryModel</code>. The "content" of a <code>Story</code> is a 
 * collection of <code>StoryFragment</code> objects. A story also contains an 
 * instance of <code>StoryInfo</code> which holds meta information about the
 * story.
 * 
 * @author 	Alexander Cheung
 * @see 	StoryModel
 * @see 	StoryView
 * @see 	StoryFragment
 * @see 	StoryInfo
 *
 */
public class Story extends StoryModel<StoryView> {
	
	private ArrayList<StoryFragment> fragmentList;
	private StoryInfo storyInfo;
	
	/**
	 * <code>Story</code> constructor initializes a new <code>Story</code>
	 * object with a <code>StoryInfo</code> argument.
	 * 
	 * @param	storyInfo	a <code>StoryInfo</code> object assigned to the
	 * 						new <code>Story</code>
	 */
	public Story(StoryInfo storyInfo) {
		super();
		this.storyInfo = storyInfo;
		this.fragmentList = new ArrayList<StoryFragment>();
	}
	
	/**
	 * Adds the specified <code>StoryFragment</code> object to the end of the
	 * <code>fragmentList</code> and notifies associated views.
	 * 
	 * @param 	storyFragment	the <code>StoryFragment</code> to be added
	 */
	public void addFragment(StoryFragment storyFragment) {
		if (fragmentList.size() == 0) {
			storyInfo.setStartingFragment(storyFragment);
		}
		fragmentList.add(storyFragment);
		notifyViews();
	}
	
	/**
	 * Removes the specified <code>StoryFragment</code> object from the
	 * <code>fragmentList</code> and notifies associated views. All
	 * <code>DecisionBranch</code> objects in any <code>StoryFragment</code>
	 * whose destination is the removed <code>StoryFragment</code> are removed
	 * from their containing <code>StoryFragment</code>.
	 * 
	 * @param 	storyFragment	the <code>StoryFragment</code> to be removed
	 * @see 	removeBranchesToFragment(StoryFragment storyFragment)
	 */
	public void removeFragment(StoryFragment storyFragment) {
		fragmentList.remove(storyFragment);
		removeBranchesToFragment(storyFragment);
		notifyViews();
	}
	
	/**
	 * Returns the <code>StoryInfo</code> object associated with the 
	 * <code>Story</code>.
	 * 
	 * @return	the <code>StoryInfo</code> object associated with the 
	 * 			<code>Story</code>
	 */
	public StoryInfo getStoryInfo() {
		return this.storyInfo;
	}
	
	/**
	 * Sets the <code>StoryInfo</code> object associated with the 
	 * <code>Story</code> to the <code>StoryInfo</code> object specified in the
	 * argument.
	 * 
	 * @param 	storyInfo 	the <code>StoryInfo</code> object to be assigned to the
	 * 						<code>Story</code>
	 */
	public void setStoryInfo(StoryInfo storyInfo) {
		this.storyInfo = storyInfo;
		notifyViews();
	}
	
	public ArrayList<StoryFragment> getStoryFragments() {
		return this.fragmentList;
	}
	
	/**
	 * Removes all instances of <code>DecisionBranch</code> containing the 
	 * specified <code>StoryFragment</code> object from all 
	 * <code>StoryFragment</code> objects in the <code>Story</code>.
	 * 
	 * @param 	storyFragment 	the <code>StoryFragment</code> to remove
	 * @see		DecisionBranch
	 */
	private void removeBranchesToFragment(StoryFragment storyFragment) {
		Iterator<StoryFragment> fragmentIterator = fragmentList.iterator();
		while(fragmentIterator.hasNext())
			fragmentIterator.next().removeBranchWithFragment(storyFragment);
	}
}
