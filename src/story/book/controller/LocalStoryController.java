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
import story.book.model.Story;
import story.book.model.StoryInfo;
import story.book.view.StoryApplication;

/**
 * Story controller for the <code>LocalStoriesActivity</code> to manage locally
 * stored stories. This controller interfaces with the IO to 
 * save, and create Story objects.
 * 
 * @author Alexander Cheung
 *
 */
public class LocalStoryController extends StoryController {
	
	/**
	 * Sets the current application Story to the locally stored Story with 
	 * matching SID.
	 * 
	 * @param SID the SID of the Story to fetch
	 */
	public void getStory(int SID) {		
		StoryApplication.setCurrentStory(io.getStory(SID));
	}
	
	/**
	 * Returns the list of StoryInfo corresponding to all locally stored 
	 * stories.
	 */
	public ArrayList<StoryInfo> getStoryList() {
		super.clearViewedStory();
		
		return io.getStoryInfoList();
	}
	
	/**
	 * Instantiates a new Story object and sets the current application Story
	 * to the new object.
	 */
	public void createStory() {
		StoryApplication.setCurrentStory(new Story(
				new StoryInfo(StoryApplication.getNickname(), io.getSID())));
	}

	/**
	 * 
	 * @param term
	 * @return
	 */
	public ArrayList<StoryInfo> search(String term) {
		return io.search(term);
	}
	
	/**
	 * Deletes the locally stored Story with the specified SID.
	 * 
	 * @param SID the SID of the story to delete.
	 */
	public void deleteStory(int SID) {
		io.deleteStory(SID);
	}

	/**
	 * Save the current application Story to local storage.
	 */
	public void saveStory() {
		io.saveStory(StoryApplication.getCurrentStory());
	}
}
