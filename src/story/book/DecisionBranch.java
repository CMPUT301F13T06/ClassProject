package story.book;

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
