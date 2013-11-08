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

import android.util.Log;

/**
 * Controller for accessing the current story and starting fragment for 
 * reading and adding annotations.
 * 
 * @author Alexander Cheung
 *
 */
public class StoryReadController {
	private Story story;
	
	/**
	 * Initializes the story to the current application story
	 */
	public StoryReadController() {
		story = StoryApplication.getCurrentStory();
	}
	
	/**
	 * 
	 * @return returns the fragment ID of the starting fragment
	 */
	public StoryFragment getStartingFragment() {
		int FID = story.getStoryInfo().getStartingFragmentID();
		if (FID != -1 ) {
			return story.getStoryFragments().get(FID);
		} else {
			return null;
		}
	}
	
	/**
	 * Gets the <code>StoryFragment</code> with the specified fragment ID from
	 * the current application story.
	 *  
	 * @param 	storyFragmentID	the ID of the fragment to get
	 * @return	the <code>StoryFragment</code> with the specified ID
	 */
	public StoryFragment getStoryFragment(int storyFragmentID) {
		return story.getStoryFragments().get(storyFragmentID);
	}
}
