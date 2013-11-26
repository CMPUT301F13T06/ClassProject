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

import java.util.ArrayList;

import story.book.dataclient.IOClient;
import story.book.model.StoryInfo;
import story.book.view.StoryApplication;

/**
 * StoryController abstract for getting Story objects, lists of stories and
 * saving stories.
 * 
 * @author Alex
 */
public abstract class StoryController {
	
	protected IOClient io;
	
	public StoryController() {
		io = StoryApplication.getIOClient();
	}
	
	/**
	 * Sets the current Story of the StoryApplication.
	 * 
	 * @param SID the SID of the Story to set
	 */
	abstract public void getStory(int SID);
		
	/**
	 * Gets an ArrayList of StoryInfo to be displayed to the user.
	 * @return the ArrayList of StoryInfo objects from a source
	 */
	abstract public ArrayList<StoryInfo> getStoryList();
	
	/**
	 * Save the Application current Story to a destination.
	 */
	abstract public void saveStory();
	
	/**
	 * Clears the folder of the previous story that
	 * was being viewed online.
	 */
	public void clearViewedStory() {
		if (StoryApplication.getViewMode()) {
			// Now that we are getting a new story,
			// clear out illustrations that were downloaded for viewing
			// the previous story
			int oldSID = StoryApplication.getCurrentStory().getStoryInfo().getSID();
			io.deleteStory(oldSID);
			StoryApplication.setViewMode(false);
		}
	}

}
