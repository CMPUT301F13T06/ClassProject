package story.book;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Main model class for representing stories.
 * 
 * @author Alexander Cheung
 *
 */
public class Story extends StoryModel<StoryView> {
	
	private ArrayList<StoryFragment> fragmentList;
	private StoryInfo storyInfo;
	
	public Story(StoryInfo storyInfo) {
		super();
		this.storyInfo = storyInfo;
		this.fragmentList = new ArrayList<StoryFragment>();
	}
	
	public void addFragment(StoryFragment storyFragment) {
		fragmentList.add(storyFragment);
		notifyViews();
	}
	
	public void removeFragment(StoryFragment storyFragment) {
		fragmentList.remove(storyFragment);
		removeBranchesToFragment(storyFragment);
		notifyViews();
	}
	
	public StoryInfo getStoryInfo() {
		return this.storyInfo;
	}
	
	public void setStoryInfo(StoryInfo storyInfo) {
		this.storyInfo = storyInfo;
		notifyViews();
	}
	
	public ArrayList<StoryFragment> getStoryFragments() {
		return this.fragmentList;
	}
	
	/**
	 * Removes all instances DecisionBranch containing the specified instance
	 * of StoryFragment from all StoryFragment objects in the Story.
	 * 
	 * @param storyFragment the instance of StoryFragment to remove branches to
	 */
	private void removeBranchesToFragment(StoryFragment storyFragment) {
		Iterator<StoryFragment> fragmentIterator = fragmentList.iterator();
		while(fragmentIterator.hasNext())
			fragmentIterator.next().removeBranchWithFragment(storyFragment);
	}
}
