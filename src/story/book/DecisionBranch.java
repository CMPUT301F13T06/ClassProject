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
	private int destinationID;
	
	public DecisionBranch(String decisionText, int destinationID) {
		setDecisionText(decisionText);
		setDestinationID(destinationID);
	}

	public String getDecisionText() {
		return decisionText;
	}

	public void setDecisionText(String decisionText) {
		this.decisionText = decisionText;
	}

	public int getDestinationID() {
		return destinationID;
	}

	public void setDestinationID(int destinationID) {
		this.destinationID = destinationID;
	}
	
}
