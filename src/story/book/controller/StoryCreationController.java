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

import java.util.Date;
import java.util.HashMap;

import android.net.Uri;

import story.book.dataclient.ESClient;
import story.book.dataclient.IOClient;
import story.book.model.Story;
import story.book.model.StoryFragment;
import story.book.model.StoryInfo;
import story.book.model.StoryInfo.PublishState;
import story.book.view.StoryApplication;

/**
 * Controller responsible for creating and editing instances of Story for the
 * StoryFragmentListActivity.
 * 
 * @author Alexander Cheung
 *
 */
public class StoryCreationController extends LocalEditingController {
	
	private Story story;
	private ESClient es;
	private IOClient io;
	
	public StoryCreationController() {
		super();
		es = StoryApplication.getESClient();
		io = StoryApplication.getIOClient();
		this.story = StoryApplication.getCurrentStory();
	}
	
	/** 
	 * Instantiates a new StoryFragment object, adds it to the current Story,
	 * and returns a reference to it.
	 * 
	 * @param fragmentTitle	The proposed title of the new StoryFragment.
	 */
	public StoryFragment newFragment(String fragmentTitle) {
		StoryFragment fragment = new StoryFragment(fragmentTitle);
		addFragment(fragment);
		return fragment;
	}
	
	/**
	 * Adds a instance of StoryFragment to the current Story.
	 * 
	 * @param storyFragment the instance of StoryFragment to add
	 */
	public void addFragment(StoryFragment storyFragment) {
		story.addFragment(storyFragment);
	}
	
	/**
	 * Removes an instance of StoryFragment from the current Story.
	 * 
	 * @param storyFragment the instance of StoryFragment to remove
	 */
	public void deleteFragment(int storyFragmentID) {
		story.removeFragment(storyFragmentID);
	}
	
	/**
	 * Returns an ArrayList of StoryFragment objects from the current Story.
	 * 
	 * @return the ArrayList of StoryFragment objects
	 */
	public HashMap<Integer, StoryFragment> getFragments() {
		return story.getStoryFragments();
	}

	/**
	 * Sets an instance of StoryFragment as the 
	 * starting fragment for the current Story.
	 * 
	 * @param fragmentID of StoryFragment
	 */
	public void setStartingFragment(int fragmentID) {
		story.getStoryInfo().setStartingFragmentID(fragmentID);
	}
	
	public Uri getFreeUri(String Extension) {
		return io.URIhandler(story.getStoryInfo().getSID(), Extension);
	}
	
	/**
	 * Get the starting fragment of the current Story.
	 * 
	 * @param fragmentID of StoryFragment
	 */
	public int getStartingFragment() {
		return story.getStoryInfo().getStartingFragmentID();
	}
	
	/**
	 * Gets the current Story.
	 */
	public Story getStory() {
		return story;
	}
	
	/**
	 * Publishes the Story with the specified SID to the server.
	 */
	public void publishStory() {

		
		Story story = StoryApplication.getCurrentStory();
		StoryInfo storyInfo = story.getStoryInfo();
		if (storyInfo.getPublishState() == PublishState.UNPUBLISHED) {
			// Check if the SID has any conflicts with the server and resolve it
			checkSIDConflict(storyInfo.getSID());	
		}
		storyInfo.setPublishState(PublishState.PUBLISHED);
		storyInfo.setPublishDate(new Date());
		StoryApplication.setCurrentStory(story);
		
		saveStory();
		
		// Publish the story
		es.saveStory(story);
	}
	
	/**
	 * Checks if an SID already exists on the server. If there is a conflict, 
	 * it is resolved by changing the SID of the current Story to a new SID.
	 * 
	 * @param SID the SID being checked for
	 * @return 
	 */
	private void checkSIDConflict(int SID) {
		
		// Check if any remote story has conflicting SID. ESClient will return
		// the original ID if it is free, else it returns a free SID from the
		// server.
		int id;
		if (!es.checkSID(SID)) {
			id = es.getSID();
		
			// Change the current Story's SID to the new SID supplied by the
			// ESClient.
			StoryApplication.getCurrentStory().getStoryInfo().setSID(id);
			
			//Removes the old one
			io.deleteStory(SID);
		}
	}

}
