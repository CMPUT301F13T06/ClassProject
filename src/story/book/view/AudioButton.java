package story.book.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

/**
 * AudioButton extends ImageButton. Clicking it
 * allows the user to play and stop audio.
 * 
 * @author Vina Nguyen
 * 
 * Ref: http://developer.android.com/guide/topics/media/audio-capture.html
 */

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
public class AudioButton extends ImageButton {

	protected ButtonState currentState;
	protected PlayState playButton;
	protected StopPlayState stopButton;

	protected MediaPlayer player;
	protected Uri audioData;
	
	public AudioButton(Uri data, Context context) {
		super(context);
		
		audioData = data;
		
		playButton = new PlayState();
		stopButton = new StopPlayState();
		
		currentState = playButton;
		this.setImageResource(currentState.getImage());
		
		this.setOnClickListener(stateClick);
	}
	
	OnClickListener stateClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			ImageButton b = (ImageButton) v;
			currentState.clickResponse();
		
			// switch to next state
			currentState = currentState.nextState();
			b.setImageResource(currentState.getImage());
		}

	};

	protected interface ButtonState {	 
		public void clickResponse();
		public ButtonState nextState();
		public int getImage();
	}
     
	protected class StopPlayState implements  ButtonState {
		@Override
		public void clickResponse() {
			if (player != null ) {
				player.release();
				player = null;
			}
		}

		@Override
		public ButtonState nextState() {
			return playButton;
		}
		
		@Override
		public int getImage() {
			return R.drawable.ic_action_stop;
		}
	}
     
	protected class PlayState implements ButtonState {
		@Override
		public void clickResponse() {
			if (audioData == null) {
				// nothing to play
				return;
			}
 
			player = new MediaPlayer();
 
			try {
				player.setDataSource(StoryApplication.getContext(), audioData);
				player.prepare();
				player.start();
			} catch (Exception e) {
				//TODO
				e.printStackTrace();
			}
		}

		@Override
		public ButtonState nextState() {
			return stopButton;
		}
		
		@Override
		public int getImage() {
			return R.drawable.ic_action_play;
		}
	}
   
}
