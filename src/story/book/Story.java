package story.book;

import java.util.ArrayList;

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
	
	/*
	 * This function removes all DecisionBranches containing the removed 
	 * fragment from all fragments in the story.
	 * 
	 * A > B > C > D
	 * remove B
	 * A ? C > D
	 * 
	 */
	private void removeBranchesToFragment(StoryFragment storyFragment) {
		// TODO
	}
}
