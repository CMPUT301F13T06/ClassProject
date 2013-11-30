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

package story.book.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import story.book.view.StoryView;

/**
 * Main class for representing stories which extends the abstract class
 * <code>StoryModel</code>. The "content" of a <code>Story</code> is a 
 * collection of <code>StoryFragment</code> objects. A story also contains an 
 * instance of <code>StoryInfo</code> which holds meta information about the
 * story.
 * 
 * @author 	Alexander Cheung
 * @see 	StoryModel
 * @see 	StoryView
 * @see 	StoryFragment
 * @see 	StoryInfo
 *
 */
public class Story extends StoryModel<StoryView> {
	
	private HashMap<Integer, StoryFragment> fragmentList;
	private StoryInfo storyInfo;
	private Integer nextFragmentID;
	
	/**
	 * <code>Story</code> constructor initializes a new <code>Story</code>
	 * object with a <code>StoryInfo</code> argument.
	 * 
	 * @param	storyInfo	a <code>StoryInfo</code> object assigned to the
	 * 						new <code>Story</code>
	 */
	public Story(StoryInfo storyInfo) {
		super();
		this.storyInfo = storyInfo;
		this.fragmentList = new HashMap<Integer, StoryFragment>();
		this.nextFragmentID = 0;
	}
	
	/**
	 * Adds the specified <code>StoryFragment</code> object to the end of the
	 * <code>fragmentList</code> and notifies associated views.
	 * 
	 * @param 	storyFragment	the <code>StoryFragment</code> to be added
	 */
	public void addFragment(StoryFragment storyFragment) {
		int ID = getNextFragmentID();
		if (fragmentList.size() == 0) {
			storyInfo.setStartingFragmentID(ID);
		}
		storyFragment.setFragmentID(ID);
		fragmentList.put(ID, storyFragment);
		notifyViews();
	}
	
	/**
	 * Removes the specified <code>StoryFragment</code> object from the
	 * <code>fragmentList</code> and notifies associated views. All
	 * <code>DecisionBranch</code> objects in any <code>StoryFragment</code>
	 * whose destination is the removed <code>StoryFragment</code> are removed
	 * from their containing <code>StoryFragment</code>.
	 * 
	 * @param 	storyFragmentID	the ID of the <code>StoryFragment</code> to be 
	 * 							removed
	 */
	public void removeFragment(int storyFragmentID) {
		fragmentList.remove(storyFragmentID);
		removeBranchesToFragment(storyFragmentID);
		notifyViews();
	}
	
	/**
	 * Returns the <code>StoryInfo</code> object associated with the 
	 * <code>Story</code>.
	 * 
	 * @return	the <code>StoryInfo</code> object associated with the 
	 * 			<code>Story</code>
	 */
	public StoryInfo getStoryInfo() {
		return this.storyInfo;
	}
	
	/**
	 * Sets the <code>StoryInfo</code> object associated with the 
	 * <code>Story</code> to the <code>StoryInfo</code> object specified in the
	 * argument.
	 * 
	 * @param 	storyInfo 	the <code>StoryInfo</code> object to be assigned to the
	 * 						<code>Story</code>
	 */
	public void setStoryInfo(StoryInfo storyInfo) {
		this.storyInfo = storyInfo;
		notifyViews();
	}
	
	/**
	 * Returns the story's collection of <code>StoryFragment</code> objects
	 * 
	 * @return	<code>HashMap</code> containing the story's fragments
	 */
	public HashMap<Integer, StoryFragment> getStoryFragments() {
		return this.fragmentList;
	}
	
	/**
	 * Returns the <code>StoryFragment</code> with the specified fragment ID
	 * 
	 * @return	<code>StoryFragment</code> with specified ID
	 */
	public StoryFragment getStoryFragment(int fragmentID) {
		return fragmentList.get(fragmentID);
	}
	
	/**
	 * Returns the next available fragment ID
	 * 
	 * @return	the next available fragment ID
	 */
	public int getNextFragmentID() {
		int nextID = nextFragmentID.intValue();
		nextFragmentID++;
		return nextID;
	}
	
	/**
	 * Removes all instances of <code>DecisionBranch</code> containing the 
	 * specified <code>StoryFragment</code> object from all 
	 * <code>StoryFragment</code> objects in the <code>Story</code>.
	 * 
	 * @param 	storyFragment 	the <code>StoryFragment</code> to remove
	 * @see		DecisionBranch
	 */
	private void removeBranchesToFragment(int storyFragmentID) {
		Iterator<StoryFragment> fragmentIterator = fragmentList.values().iterator();
		while(fragmentIterator.hasNext())
			fragmentIterator.next().removeBranchWithFragment(storyFragmentID);
	}
	
	/**
	 * Copies everything except inherited view list from StoryModel.
	 * This is to recover from serialization as the view list cannot be
	 * serialized.
	 * 
	 * @return a new Story object
	 */
	public Story copy() {
		Story copy = new Story(this.getStoryInfo());
		copy.fragmentList = this.fragmentList;
		copy.nextFragmentID = this.nextFragmentID;
		
		return copy;
	}
}
