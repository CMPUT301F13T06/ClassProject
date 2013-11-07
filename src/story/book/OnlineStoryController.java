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
	
	public void getStory(int SID) {
		StoryApplication.setCurrentStory(es.getStory(SID));
	}

	public void saveStory() {
		checkSIDConflict(StoryApplication.getCurrentStory().getStoryInfo()
				.getSID());
		io.saveStory(StoryApplication.getCurrentStory());
	}

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
