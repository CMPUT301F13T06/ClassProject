/* CMPUT301F13T06-Adventure Club: A choose-your-own-adventure story platform
 * Copyright (C) 2013 Alexander Cheung, Jessica Surya, Vina Nguyen, Anthony Ou,
 * Nancy Pham-Nguyen
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

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
	
	/**
	 * Constructor sets the value of the decision text and the destination
	 * fragment ID.
	 * 
	 * @param 	decisionText	the flavor text for the decision
	 * @param 	destinationID	the fragment ID of the destination fragment
	 */
	public DecisionBranch(String decisionText, int destinationID) {
		setDecisionText(decisionText);
		setDestinationID(destinationID);
	}

	/**
	 * Returns the decision text
	 * 
	 * @return	the flavor text for the decision
	 */
	public String getDecisionText() {
		return decisionText;
	}

	/**
	 * Set the flavor text to the specified String
	 * 
	 * @param 	decisionText	the desired flavor text
	 */
	public void setDecisionText(String decisionText) {
		this.decisionText = decisionText;
	}

	/**
	 * Returns the fragment ID of the destination fragment
	 * 
	 * @return	the fragment ID of the destination
	 */
	public int getDestinationID() {
		return destinationID;
	}

	/**
	 * Sets the destination fragment ID to the specified value
	 * 
	 * @param 	destinationID
	 */
	public void setDestinationID(int destinationID) {
		this.destinationID = destinationID;
	}
	
}
