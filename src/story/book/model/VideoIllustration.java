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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import story.book.view.VideoPlayerActivity;
import android.R;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
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
	 * @param path
	 * @param savePath
	 * 
	 * http://stackoverflow.com/questions/9292954/how-to-make-a-copy-of-a-file-in-android
	 */
	public VideoIllustration(Uri path, Uri savePath) {
		super();
		setContent(savePath.getLastPathSegment());
		try{
			InputStream in = new FileInputStream(new File(path.getPath()));
			OutputStream out = new FileOutputStream(new File(savePath.getPath()));

			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			in.close();
			out.close();
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
		layers[1] = (C.getResources().getDrawable(R.drawable.ic_media_play));
		LayerDrawable ld = new LayerDrawable(layers);		
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
