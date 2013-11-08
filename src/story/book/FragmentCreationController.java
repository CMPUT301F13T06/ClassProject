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

/**
 * Controller responsible for adding and removing <code>Illustration</code>
 * objects of a <code>StoryFragment</code> object for the 
 * <code>StoryFragmentEditActivity</code>.
 * 
 * @author 	Alexander Cheung
 * @see 	Illustration
 * @see		TextIllustration
 * @see		StoryFragment
 * @see		LocalEditingController
 */
public class FragmentCreationController extends LocalEditingController {

	private StoryFragment storyFragment;
	
	/**
	 * Initializes the controller with the story fragment with the specified
	 * fragment ID.
	 * 
	 * @param 	fragmentID	the ID of the story fragment
	 */
	public FragmentCreationController(int fragmentID) {
		super();
		this.storyFragment = StoryApplication.getCurrentStory()
				.getStoryFragments().get(fragmentID);
	}
	
	/**
	 * Adds a TextIllustration to the fragment. Consider revising using java
	 * generics to incorporate different illustration types.
	 * 
	 * @param content
	 */
	public void addTextIllustration(String content) {
		storyFragment.addIllustration(new TextIllustration(content));
	}
	
	/**
	 * Removes the specified <code>TextIllustration</code> from the story
	 * fragment.
	 * 
	 * @param 	textIllustration	the <code>TextIllustration</code> to remove
	 */
	public void removeTextIllustration(TextIllustration textIllustration) {
		storyFragment.removeIllustration(textIllustration);
	}
	
	/**
	 * Removes all the <code>Illustration</code> from the story
	 * fragment.
	 */
	public void removeAllIllustrations() {
		storyFragment.removeAllIllustrations();
	}
	
	/**
	 * Sets all the specified <code>Illustration</code> from the story
	 * fragment.
	 * 
	 * @param 	textIllustration array to add
	 */
	public void setAllIllustrations(ArrayList<Illustration> illustrations) {
		storyFragment.setAllIllustrations(illustrations);
	}
	
}
