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

import java.util.ArrayList;

import story.book.StoryInfo.PublishState;
import story.book.dataclient.IOClient;
import story.book.dataclient.ESClient;

/**
 * StoryController to read and download locally stored stories. This controller
 * interfaces with the IO and ES clients.
 * 
 * @author Alexander Cheung
 *
 */
public class OnlineStoryController implements StoryController {
	
	private IOClient io;
	private ESClient es;
	
	public OnlineStoryController() {
		io = StoryApplication.getIOClient();
		es = StoryApplication.getESClient();
	}
	
	/**
	 * Sets the current application <code>Story</code> to the story with the
	 * specified SID using the ESClient.
	 * 
	 * @param	SID	the ID of the <code>Story</code> to load
	 */
	public void getStory(int SID) {
		StoryApplication.setCurrentStory(es.getStory(SID));
	}

	/**
	 * Saves the current application <code>Story</code> to local storage after
	 * checking for and resolving SID conflicts.
	 */
	public void saveStory() {
		checkSIDConflict(StoryApplication.getCurrentStory().getStoryInfo()
				.getSID());
		io.saveStory(StoryApplication.getCurrentStory());
	}

	/**
	 * Returns an <code>ArrayList</code> of <code>StoryInfo</code> from the
	 * server using the ESClient.
	 * 
	 * @return	the <code>ArrayList</code> of <code>StoryInfo</code> from the
	 * 			server
	 */
	public ArrayList<StoryInfo> getStoryList() {
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
			// ONLY IF the locally stored Story was never published 
			// (else we are just re-downloading)
			StoryInfo info = io.getStory(SID).getStoryInfo();
			if (info.getPublishState() == PublishState.UNPUBLISHED) {
				changeLocalSID(SID, io.getSID());
			}
		}
	}
	
	/**
	 * Changes the SID of a locally stored Story to a new SID. The old Story is
	 * deleted from storage and replaced by the same Story but with a new SID.
	 * 
	 * @param oldSID the SID of the Story to change
	 * @param newSID the new SID the Story will be assigned
	 */
	private void changeLocalSID(int oldSID, int newSID) {
		Story story = io.getStory(oldSID);
		story.getStoryInfo().setSID(newSID);
		io.deleteStory(oldSID);
		io.saveStory(story);
	}
}
