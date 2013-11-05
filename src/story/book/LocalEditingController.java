package story.book;

import story.book.dataclient.IOClient;

/**
 * Base abstract class for controllers for providing saving functionality to
 * controllers modifying the currently active <code>Story</code> object.
 * 
 * @author Alexander Cheung
 *
 */
public abstract class LocalEditingController {
	private IOClient io;
	
	public LocalEditingController() {
		io = StoryApplication.getIOClient();
	}
	
	/**
	 * Saves the current active Story to local storage using the 
	 * <code>IOClient</code>.
	 */
	public void saveStory() {
		io.saveStory(StoryApplication.getCurrentStory());
	}
}
