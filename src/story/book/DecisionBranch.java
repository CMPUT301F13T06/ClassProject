package story.book;

/**
 * Represents a decision that a reader of the story can take. Contains a
 * <code>String</code> containing a message describing the decision and the 
 * <code>StoryFragment</code> that the decision leads to.
 * 
 * @author 	Alexander Cheung
 * @see		StoryFragment
 * 
 */
public class DecisionBranch {
	private String decisionText;
	private StoryFragment destination;
	
	public DecisionBranch(String decisionText, StoryFragment destination) {
		setDecisionText(decisionText);
		setDestination(destination);
	}

	public String getDecisionText() {
		return decisionText;
	}

	public void setDecisionText(String decisionText) {
		this.decisionText = decisionText;
	}

	public StoryFragment getDestination() {
		return destination;
	}

	public void setDestination(StoryFragment destination) {
		this.destination = destination;
	}
	
}
