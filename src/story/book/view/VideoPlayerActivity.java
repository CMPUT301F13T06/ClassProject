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
package story.book.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceView;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoPlayerActivity extends Activity {
	VideoView videoView;
	DisplayMetrics displayMetrics;
	SurfaceView surfaceView;
	MediaController mediaController;
	String videoPath;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.video_player);
		
		Bundle extras = getIntent().getExtras();
		videoPath = extras.getString("VIDEO_PATH");
		fetchVideo();
	}

	public void fetchVideo() {
		videoView = (VideoView) findViewById(R.id.video_player_view);
		mediaController = new MediaController(this);
		displayMetrics = new DisplayMetrics();
		
		this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		int height = displayMetrics.heightPixels;
		int width = displayMetrics.widthPixels;
		
		mediaController.setAnchorView(videoView);
		videoView.setMinimumWidth(width);
		videoView.setMinimumHeight(height);
		videoView.setMediaController(mediaController);
		Log.d("videbug", "Video path set to " + videoPath);
		videoView.setVideoPath(videoPath);
		videoView.start();
	}
}
