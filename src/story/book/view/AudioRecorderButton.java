package story.book.view;

import android.content.Context;
import android.media.MediaRecorder;
import android.net.Uri;

/**
 * AudioRecorderButton extends AudioButton. Clicking it
 * allows the user to play and stop audio (as in AudioButton),
 * but with the additionally functionality to first record 
 * the audio before playback. Currently only records once.
 * 
 * @author Vina Nguyen
 * 
 * Ref: http://developer.android.com/guide/topics/media/audio-capture.html
 */

public class AudioRecorderButton extends AudioButton {

	MediaRecorder recorder;
	
	RecordState recorderButton;
	StopRecordState recorderStopButton;
	
	public AudioRecorderButton(Uri data, Context context) {
		super(data, context);
		
		recorderButton = new RecordState();
		recorderStopButton = new StopRecordState();
		
		currentState = recorderButton;
		this.setImageResource(currentState.getImage());
	}
	
	 private class RecordState implements ButtonState {
			@Override
			public void clickResponse() {
				recorder = new MediaRecorder();
				recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
				recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
				recorder.setOutputFile(audioData.getPath());
				recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

				try {
					recorder.prepare();
					recorder.start();
				} catch (Exception e) {
					//TODO
					e.printStackTrace();
				}
			}

			@Override
			public ButtonState nextState() {
				return recorderStopButton;
			}

			@Override
			public int getImage() {
				return R.drawable.ic_action_mic;
			}
		}

		private class StopRecordState implements  ButtonState {
			@Override
			public void clickResponse() {
				recorder.stop();
				recorder.release();
				recorder = null;
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

		public Uri getContent() {
			return audioData;
		}
}