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
import java.util.Random;
import java.util.Set;

import story.book.dataclient.IOClient;
import story.book.model.Story;
import story.book.model.StoryFragment;
import story.book.view.StoryApplication;

/**
 * Controller for accessing the current story and starting fragment for 
 * reading and adding annotations.
 * 
 * @author Alexander Cheung
 *
 */
public class StoryReadController {
	private FragmentManager fragmentManager;
	private IOHelper ioHelper;
	
	/**
	 * Initializes the story to the current application story
	 */
	public StoryReadController() {
		fragmentManager = new FragmentManager();
		ioHelper = new IOHelper();
	}
	
	/**
	 * @return returns the fragment ID of the starting fragment
	 */
	public StoryFragment getStartingFragment() {
		return fragmentManager.getStartingFragment();
	}
	
	/**
	 * Gets the <code>StoryFragment</code> with the specified fragment ID from
	 * the current application story.
	 *  
	 * @param 	storyFragmentID	the ID of the fragment to get
	 * @return	the <code>StoryFragment</code> with the specified ID
	 */
	public StoryFragment getStoryFragment(int storyFragmentID) {
		return fragmentManager.getStoryFragment(storyFragmentID);
	}
	
	/**
	 * @return the local file path to the current story's directory
	 */
	public String getStoryPath() {
		return ioHelper.getStoryPath();
	}
	
	public int getRandomFragmentID(int currentID) {
		ArrayList<Integer> IDs = fragmentManager.getFragmentIDs();
		int n = currentID;
		if (IDs.size() == 1) {
			return n;
		}
		
		Random rand = new Random();
		
		while (n == currentID) {
			n = rand.nextInt(IDs.size());
		}
		
		return IDs.get(n);
	}
	
	/**
	 * Helper class responsible for managing the fragments and fragment IDs of
	 * the controller's story object as well as the object itself.
	 * 
	 * @author Alex
	 */
	private class FragmentManager {
		
		private Story story;
		
		public FragmentManager() {
			story = StoryApplication.getCurrentStory();
		}
		
		/**
		 * 
		 * @return returns the fragment ID of the starting fragment
		 */
		public StoryFragment getStartingFragment() {
			return (story.getStoryInfo().getStartingFragmentID() != -1) ? 
					story.getStoryFragment(story.getStoryInfo().getStartingFragmentID()) : null;
		}
		
		/**
		 * 
		 * @return returns all fragment IDs
		 */
		public ArrayList<Integer> getFragmentIDs() {
			Set<Integer> keys =  story.getStoryFragments().keySet();
			return new ArrayList<Integer>(keys);
		}
		
		/**
		 * Gets the <code>StoryFragment</code> with the specified fragment ID from
		 * the current application story.
		 *  
		 * @param 	storyFragmentID	the ID of the fragment to get
		 * @return	the <code>StoryFragment</code> with the specified ID
		 */
		public StoryFragment getStoryFragment(int storyFragmentID) {
			return story.getStoryFragment(storyFragmentID);
		}
	}
	
	/**
	 * Helper class responsible for managing IO operations and interacting with
	 * the IOClient.
	 * 
	 * @author Alex
	 */
	private class IOHelper {
		
		private IOClient io;
		
		public IOHelper() {
			io = StoryApplication.getIOClient();
		}
		
		/**
		 * @return the local file path to the current story's directory
		 */
		public String getStoryPath() {
			return io.getLocalDirectory() 
					+ StoryApplication.getCurrentStory().getStoryInfo().getSID() 
					+ "/";
		}
	}
	
}
