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

import android.util.Log;

import story.book.dataclient.IOClient;
import story.book.dataclient.ESClient;
import story.book.model.Story;
import story.book.model.StoryInfo;
import story.book.model.StoryInfo.PublishState;
import story.book.view.StoryApplication;

/**
 * StoryController to read and download locally stored stories. This controller
 * interfaces with the IO and ES clients.
 * 
 * @author Alexander Cheung
 *
 */
public class OnlineStoryController extends StoryController {
	
	private ESClient es;
	
	public OnlineStoryController() {
		super();
		
		es = StoryApplication.getESClient();
	}
	
	/**
	 * Sets the current application <code>Story</code> to the story with the
	 * specified SID using the ESClient.
	 * 
	 * @param	SID	the ID of the <code>Story</code> to load
	 */
	public void getStory(int SID) {
		checkSIDConflict(SID);
		StoryApplication.setCurrentStory(es.getStory(SID));
	}

	/**
	 * Saves the current application <code>Story</code> to local storage after
	 * checking for and resolving SID conflicts.
	 */
	public void saveStory() {
		super.clearViewedStory();
		io.saveStory(StoryApplication.getCurrentStory());
	}

	/**
	 * Returns an <code>ArrayList</code> of <code>StoryInfo</code> for stories
	 * matching the specified search term from the server using the ESClient.
	 * 
	 * @param 	term	the search term
	 * @return	the <code>ArrayList</code> of <code>StoryInfo</code> from the
	 * 			server
	 */
	public ArrayList<StoryInfo> search(String term) {
		return es.search(term);
	}
	
	/**
	 * Returns an <code>ArrayList</code> of <code>StoryInfo</code> from the
	 * server using the ESClient.
	 * 
	 * @return	the <code>ArrayList</code> of <code>StoryInfo</code> from the
	 * 			server
	 */
	public ArrayList<StoryInfo> getStoryList() {
		super.clearViewedStory();
		
		return es.getStoryInfoList();
	}

	/**
	 * Checks if an SID already exists in a locally stored Story. If there is
	 * a conflict, it is resolved by changing the SID of the locally stored
	 * Story to a new SID.
	 * 
	 * @param SID the SID being checked for
	 */
	private void checkSIDConflict(int SID) {
		// Check if any local story has a conflicting SID. IOClient will return
		// the original ID if it is free, else it returns a locally free SID.
		if (!io.checkSID(SID)) {
			// Change the locally stored Story with the original SID (SID) to
			// the new SID (id) supplied by the IOClient
			
			StoryInfo info = io.getStory(SID).getStoryInfo();
			int newSID = io.getSID();
			
			if (info.getPublishState() != PublishState.UNPUBLISHED) {
				// ONLY IF the locally stored story was never published
				// will we need to not worry about returning it
				// back to the original SID
				StoryApplication.setConflictedSID(newSID);
				Log.d("conflicted SID", String.valueOf(newSID));
			}
			super.changeLocalSID(SID, newSID);
		}
	}
	

}
