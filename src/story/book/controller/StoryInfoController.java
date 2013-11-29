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

import story.book.dataclient.IOClient;
import story.book.model.StoryInfo;
import story.book.view.StoryApplication;

/**
 * Controller responsible for creating, editing, displaying
 * the StoryInfo of the current Story for the StoryInfoActivity 
 * and EditStoryInformationActivity.
 * 
 * @author Vina Nguyen
 *
 */
public class StoryInfoController extends LocalEditingController {
	private StoryInfo storyInfo;
	private IOClient io;
	
	public StoryInfoController() {
		storyInfo = StoryApplication.getCurrentStory().getStoryInfo();
		io = StoryApplication.getIOClient();
	}
	
	/**
	 * 
	 * @return StoryInfo of the current story
	 */
	public StoryInfo getStoryInfo() {
		return storyInfo;
	}

	/**
	 * Saves the current story, defaulting the
	 * author if necessary.
	 */
	@Override
	public void saveStory() {
		if (storyInfo.getAuthor().equals(""))
			storyInfo.setAuthor(StoryApplication.getNickname());
		super.saveStory();
	}
}
