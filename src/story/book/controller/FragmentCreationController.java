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

import android.net.Uri;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import story.book.model.Annotation;
import story.book.model.Illustration;
import story.book.model.StoryFragment;
import story.book.model.TextIllustration;
import story.book.view.StoryApplication;

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

	public Uri getFreeUri(String Extension) {
		return io.URIhandler(StoryApplication.getCurrentStory().getStoryInfo()
				.getSID(), Extension);
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

	/**
	 * saveAnnotation() saves newly added annotation objects by assigning a caption
	 * from its view and adding it to the storyFragment by calling 
	 * <code>addAnnotation(Annotation annotation)</code>
	 * 
	 * @param Pair data structure which contains an ArrayList of the annotation's views
	 * (Author tag/media/caption), and the <code>Annotation</code> object itself
	 * @param String caption to be set in the annotation
	 */
	public void saveAnnotations(Pair<ArrayList<View>, Annotation> i, String cap) {
		if (i.first.size() == 2 && cap.isEmpty())
			addAnnotationWithCaption(i.second, cap);
	}
	
	private void addAnnotationWithCaption(Annotation annotation, String caption) {
		annotation.setCaption(caption);
		this.addAnnotation(annotation);
	}

	/**
	 * Adds an <code>Annotation</code> to the story fragment.
	 * @param 	annotation	the <code>Annotation</code> to add
	 */
	public void addAnnotation(Annotation annotation) {
		storyFragment.addAnnotation(annotation);
		StoryApplication.getESClient().publishAnnotation(annotation, 
				StoryApplication.getCurrentStory());
		saveStory();
	}

}
