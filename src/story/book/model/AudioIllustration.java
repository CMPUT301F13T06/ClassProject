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

package story.book.model;

import java.io.File;

import story.book.view.AudioButton;
import story.book.view.AudioRecorderButton;
import story.book.view.StoryApplication;
import android.net.Uri;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.widget.Button;

/**
 * 
 * @author Anthony Ou
 * 
 * @author Vina Nguyen
 *
 */
public class AudioIllustration extends Illustration<Uri>{

	Uri content;
	
	AudioRecorderButton recorderView;
	
	public AudioIllustration(Uri data) {
		super();
		setContent(data);
	}

	public Uri getContent() {
		return content;
	}

	/**
	 * return a buttonview that can listen for presses when the user
	 * wants to record audio or play audio
	 */
	public View getView() {
		if (recorderView == null && content == null) {
			// Use an AudioRecorderButton view to allow 
			// recording if AudioIllustration was not initialized with content
			// and if an AudioRecorderButton was not already called
			recorderView = new AudioRecorderButton(null, StoryApplication.getContext());
			return recorderView;
		} else {
			if (recorderView != null) { 
				// If AudioRecorderButton was used, set the content
				// to the results
				Uri data = recorderView.getContent();
				this.setContent(data);
			}
			
			// Return an AudioButton to play the Illustration's content
			AudioButton playView = new AudioButton(content, StoryApplication.getContext());
			return playView;
		}		
	}

	public void setContent(Uri content) {
		this.content = content;
	}
	
	public void deleteContent() {
		new File(content.getPath()).delete();
	}

}
