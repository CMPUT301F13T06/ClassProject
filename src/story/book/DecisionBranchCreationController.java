package story.book;

public class DecisionBranchCreationController extends LocalEditingController {
	
	private StoryFragment storyFragment;
	
	public DecisionBranchCreationController(StoryFragment storyFragment) {
		super();
		this.storyFragment = storyFragment;
	}
	
	public void addDecisionBranch(DecisionBranch decisionBranch) {
		storyFragment.addDecisionBranch(decisionBranch);
	}
	
	public void removeDecisionBranch(DecisionBranch decisionBranch) {
		storyFragment.removeDecisionBranch(decisionBranch);
	}

}
