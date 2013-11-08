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

package story.book.controller;

import story.book.model.DecisionBranch;
import story.book.model.StoryFragment;
import story.book.view.StoryApplication;

/**
 * Controller responsible for adding and removing <code>DecisionBranch</code>
 * objects to a <code>StoryFragment</code>.
 * <code>DecisionBranchListActivity</code>.
 * 
 * @author 	Alexander Cheung
 * @see 	StoryFragment
 * @see		DecisionBranch
 * @see 	LocalEditingController
 */
public class DecisionBranchCreationController extends LocalEditingController {
	
	private StoryFragment storyFragment;
	
	/**
	 * Initializes the story fragment to the story fragment with the specified
	 * ID.
	 * 
	 * @param 	storyFragmentID	the ID of the story fragment
	 */
	public DecisionBranchCreationController(int storyFragmentID) {
		super();
		this.storyFragment = StoryApplication.getCurrentStory()
				.getStoryFragments().get(storyFragmentID);
	}
	
	/**
	 * Adds the specified <code>DecisionBranch</code> object to the story
	 * fragment.
	 * 
	 * @param 	decisionBranch	the <code>DecisionBranch</code> to add
	 */
	public void addDecisionBranch(DecisionBranch decisionBranch) {
		storyFragment.addDecisionBranch(decisionBranch);
	}
	
	/**
	 * Removes the specified <code>DecisionBranch</code> from the story 
	 * fragment.
	 * 
	 * @param decisionBranch
	 */
	public void removeDecisionBranch(DecisionBranch decisionBranch) {
		storyFragment.removeDecisionBranch(decisionBranch);
	}

}
