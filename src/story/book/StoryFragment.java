package story.book;

import java.util.ArrayList;

public class StoryFragment {
	
	private ArrayList<Illustration> illustrations;
	private ArrayList<DecisionBranch> decisionBranches;
	// private ArrayList<Annotation> annotations;
	
	private String fragmentTitle;
	
	public StoryFragment(String fragmentTitle) {
		setFragmentTitle(fragmentTitle);
		newIllustrationList();
		newDecisionBranchList();
	}
	
	public void addIllustration(Illustration illustration) {
		this.illustrations.add(illustration);
	}
	
	public void removeIllustration(Illustration illustration) {
		this.illustrations.remove(illustration);
	}
	
	public ArrayList<Illustration> getIllustrations() {
		return this.illustrations;
	}
	
	public ArrayList<DecisionBranch> getDecisionBranches() {
		return this.decisionBranches;
	}
	
	public String getFragmentTitle() {
		return this.fragmentTitle;
	}
	
	private void setFragmentTitle(String fragmentTitle) {
		this.fragmentTitle = fragmentTitle;
	}
	
	private void newIllustrationList() {
		illustrations = new ArrayList<Illustration>();
	}
	
	private void newDecisionBranchList() {
		decisionBranches = new ArrayList<DecisionBranch>();
	}
}
