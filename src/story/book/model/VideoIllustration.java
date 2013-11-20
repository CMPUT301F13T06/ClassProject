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

import story.book.view.StoryApplication;
import android.net.Uri;
import android.view.View;
import android.widget.VideoView;

/**
 * 
 * @author Anthony Ou
 *
 */
public class VideoIllustration extends Illustration<String> {

	private String content;
	
	public VideoIllustration(Uri data) {
		super();
		String[] splitstring = data.getPath().split("/");
		setContent(splitstring[splitstring.length-1]);
	}
	
	public String getContent() {
		return content;
	}

	public View getView(Boolean editMode) {
		return new VideoView(StoryApplication.getContext());
	}

	public void setContent(String content) {
		this.content = content;	
	}

}
