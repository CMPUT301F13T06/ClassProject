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

import story.book.view.R;
import story.book.view.VideoPlayerActivity;

import org.apache.commons.io.FileUtils;

import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * 
 * @author Anthony Ou
 *
 */

public class VideoIllustration extends BinaryIllustration {
	
	public VideoIllustration(Uri data) {
		super();
		setContent(data.getLastPathSegment());
	}

	/**
	 * Called when the user picks from gallery
	 * so we copy the files over.
	 * 
	 * @param path
	 * @param savePath
	 * 
	 */
	public VideoIllustration(Uri path, Uri savePath) {
		super();
		setContent(savePath.getLastPathSegment());
		try{
			FileUtils.copyFile(new File(path.getPath()), new File(savePath.getPath()));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getContent() {
		return content;
	}

	@SuppressWarnings("deprecation")
	public View getView(String path, Boolean editMode, final Context C) {
		ImageView button = new ImageView(C);
		final String videoPath = path + content;
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(C, VideoPlayerActivity.class);
				intent.putExtra("VIDEO_PATH", videoPath);
				C.startActivity(intent);
			}
		});	
		
		Drawable[] layers = new Drawable[2];
		layers[0] = new BitmapDrawable(ThumbnailUtils.createVideoThumbnail(videoPath,
				5));
		layers[1] = (C.getResources().getDrawable(R.drawable.ic_action_play));
		LayerDrawable ld = new LayerDrawable(layers);		
		ld.setLayerInset(1, 40, 0, 40, 0);
		((BitmapDrawable)ld.getDrawable(1)).setGravity(Gravity.CENTER);
		button.setImageDrawable(ld);
		return button;
	}

	@Override
	public android.widget.RelativeLayout.LayoutParams getLayoutParam() {
		RelativeLayout.LayoutParams rl = 
				new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		rl.addRule(RelativeLayout.CENTER_VERTICAL);
		return rl;
	}
	
}
