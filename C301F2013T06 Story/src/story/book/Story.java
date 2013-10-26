package story.book;

import java.util.ArrayList;

/**
 * 
 */
public class Story {
	
	private ArrayList<StoryFragment> fragmentList;
	private StoryInfo storyInfo;
	
	public Story(StoryInfo storyInfo) {
		setStoryInfo(storyInfo);
		newFragmentList();
	}
	
	public void addFragment(StoryFragment storyFragment) {
		fragmentList.add(storyFragment);
	}
	
	public void removeFragment(StoryFragment storyFragment) {
		fragmentList.remove(storyFragment);
		removeBranchesToFragment(storyFragment);
	}
	
	public StoryInfo getStoryInfo() {
		return this.storyInfo;
	}
	
	public void setStoryInfo(StoryInfo storyInfo) {
		this.storyInfo = storyInfo;
	}
	
	public ArrayList<StoryFragment> getStoryFragments() {
		return this.fragmentList;
	}
	
	private void newFragmentList() {
		this.fragmentList = new ArrayList<StoryFragment>();
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
