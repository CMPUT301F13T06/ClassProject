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

import story.book.view.VideoPlayerActivity;
import android.R;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.widget.ImageButton;

/**
 * 
 * @author Anthony Ou
 *
 */
public class VideoIllustration extends Illustration<String> {

	private String content;
	
	public VideoIllustration(Uri data) {
		super();
		setContent(data.getLastPathSegment());
	}
	
	public String getContent() {
		return content;
	}

	public View getView(String path, Boolean editMode, final Context C) {
		ImageButton button = new ImageButton(C);
		final String videoPath = path + content;
		button.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
		    	Intent intent = new Intent(C, VideoPlayerActivity.class);
		    	intent.putExtra("VIDEO_PATH", videoPath);
		    	C.startActivity(intent);
		    }
		});
		button.setImageDrawable(C.getResources().getDrawable(R.drawable.ic_media_play));
		button.setBackgroundColor(-16777216);
		return button;
	}

	public void setContent(String content) {
		this.content = content;	
	}

}
