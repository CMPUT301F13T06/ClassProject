package story.book;

/**
 * Controller responsible for adding and removing <code>DecisionBranch</code>
 * objects to a <code>StoryFragment</code> for the 
 * <code>DecisionBranchListActivity</code>.
 * 
 * @author 	Alexander Cheung
 * @see 	StoryFragment
 * @see		DecisionBranch
 * @see 	LocalEditingController
 */
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
