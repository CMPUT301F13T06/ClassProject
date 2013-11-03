package story.book;

import java.util.ArrayList;

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
	
	public String getFragmentTitle() {
		return this.fragmentTitle;
	}
	
	public void removeIllustration(Illustration illustration) {
		this.illustrations.remove(illustration);
		notifyViews();
	}
	
	public ArrayList<Illustration> getIllustrations() {
		return this.illustrations;
	}
	
	public ArrayList<DecisionBranch> getDecisionBranches() {
		return this.decisionBranches;
	}
	
}
