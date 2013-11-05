package story.book;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Model class for representing fragments of a story.
 * 
 * @author Alexander Cheung
 *
 */
public class StoryFragment extends StoryModel<StoryView> {
	
	private ArrayList<Illustration> illustrations;
	private ArrayList<DecisionBranch> decisionBranches;
	// private ArrayList<Annotation> annotations;
	
	private String fragmentTitle;
	
	public StoryFragment(String fragmentTitle) {
		setFragmentTitle(fragmentTitle);
		illustrations = new ArrayList<Illustration>();
		decisionBranches = new ArrayList<DecisionBranch>();
	}
	
	private void setFragmentTitle(String fragmentTitle) {
		this.fragmentTitle = fragmentTitle;
		notifyViews();
	}
	
	public void addIllustration(Illustration illustration) {
		this.illustrations.add(illustration);
		notifyViews();
	}
	
	public void addDecisionBranch(DecisionBranch decisionBranch) {
		decisionBranches.add(decisionBranch);
		notifyViews();
	}
	
	public String getFragmentTitle() {
		return this.fragmentTitle;
	}
	
	public void removeIllustration(Illustration illustration) {
		this.illustrations.remove(illustration);
		notifyViews();
	}
	
	public void removeDecisionBranch(DecisionBranch decisionBranch) {
		decisionBranches.remove(decisionBranch);
		notifyViews();
	}
	
	public ArrayList<Illustration> getIllustrations() {
		return this.illustrations;
	}
	
	public ArrayList<DecisionBranch> getDecisionBranches() {
		return this.decisionBranches;
	}
	
	/**
	 * Removes any <code>DecisionBranch</code> object whose destination 
	 * <code>StoryFragment</code> is the specified <code>StoryFragment</code>.
	 * 
	 * @param 	storyFragment 	the target <code>StoryFragment</code>
	 */
	public void removeBranchWithFragment(StoryFragment storyFragment) {
		Iterator<DecisionBranch> branchIterator = decisionBranches.iterator();
		DecisionBranch branchToCheck;
		ArrayList<DecisionBranch> branchesToRemove = new ArrayList<DecisionBranch>();
		
		while(branchIterator.hasNext()) {
			branchToCheck = branchIterator.next();
			if (branchToCheck.getDestination().equals(storyFragment))
				branchesToRemove.add(branchToCheck);
		}
		
		branchIterator = branchesToRemove.iterator();
		while(branchIterator.hasNext())
			removeDecisionBranch(branchIterator.next());
	}
	
	@Override
	public String toString() {
		return this.fragmentTitle;
	}
	
}
