package story.book;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Model class for representing fragments of a story which extends the abstract
 * class <code>StoryModel</code>. A <code>StoryFragment</code> contains a 
 * collection of <code>Illustration</code> objects and 
 * <code>DecisionBranch</code> objects.
 * 
 * @author 	Alexander Cheung
 * @see		Story
 * @see		Illustration
 * @see		TextIllustration
 * @see		DecisionBranch
 *
 */
public class StoryFragment extends StoryModel<StoryView> {
	
	private ArrayList<Illustration> illustrations;
	private ArrayList<DecisionBranch> decisionBranches;
	// private ArrayList<Annotation> annotations;
	
	private String fragmentTitle;
	
	/**
	 * Returns a <code>StoryFragment</code> initialized with the specified
	 * title.
	 * 
	 * @param 	fragmentTitle	the title of the fragment
	 */
	public StoryFragment(String fragmentTitle) {
		setFragmentTitle(fragmentTitle);
		illustrations = new ArrayList<Illustration>();
		decisionBranches = new ArrayList<DecisionBranch>();
	}
	
	/**
	 * Sets the title of the fragment to the specified <code>String</code>.
	 * @param 	fragmentTitle	the desired title of the fragment
	 */
	private void setFragmentTitle(String fragmentTitle) {
		this.fragmentTitle = fragmentTitle;
		notifyViews();
	}
	
	/**
	 * Adds the specified <code>Illustration</code> to the fragment.
	 * 
	 * @param 	illustration	the <code>Illustration</code> to add
	 * @see		Illustration
	 */
	public void addIllustration(Illustration illustration) {
		this.illustrations.add(illustration);
		notifyViews();
	}
	
	/**
	 * Adds the specified <code>DecisionBranch</code> to the fragment.
	 * 
	 * @param	decisionBranch	the <code>DecisionBranch</code> to add
	 * @see		DecisionBranch
	 */
	public void addDecisionBranch(DecisionBranch decisionBranch) {
		decisionBranches.add(decisionBranch);
		notifyViews();
	}
	
	/**
	 * Returns the title of the fragment.
	 * 
	 * @return	the title of the fragment
	 */
	public String getFragmentTitle() {
		return this.fragmentTitle;
	}
	
	/**
	 * Removes the specified <code>Illustration</code> from the fragment.
	 * 
	 * @param 	illustration	the <code>Illustration</code> to remove
	 * @see		Illustration
	 */
	public void removeIllustration(Illustration illustration) {
		this.illustrations.remove(illustration);
		notifyViews();
	}
	
	/**
	 * Removes the specified <code>DecisionBranch</code> from the fragment.
	 * @param 	decisionBranch
	 * @see		DecisionBranch
	 */
	public void removeDecisionBranch(DecisionBranch decisionBranch) {
		decisionBranches.remove(decisionBranch);
		notifyViews();
	}
	
	/**
	 * Returns an <code>ArrayList</code> containing all 
	 * <code>Illustration</code> objects from the fragment.
	 * 
	 * @return	the <code>ArrayList</code> of <code>Illustration</code>
	 */
	public ArrayList<Illustration> getIllustrations() {
		return this.illustrations;
	}
	
	/**
	 * Returns an <code>ArrayList</code> containing all 
	 * <code>DecisionBranch</code> objects from the fragment.
	 * 
	 * @return	the <code>ArrayList</code> of <code>DecisionBranch</code>
	 */
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
