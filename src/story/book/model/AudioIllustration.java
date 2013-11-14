package story.book.model;

import java.io.File;

import story.book.view.StoryApplication;
import android.net.Uri;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.widget.Button;

public class AudioIllustration extends Illustration<Uri>{

	Uri content;

	public Uri getContent() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setContent(Uri content) {
		// TODO Auto-generated method stub

	}

	/**
	 * return a buttonview that can listen for presses when the user
	 * wants to record audio or play audio
	 */
	public View getView() {
		Button b = new Button(StoryApplication.getContext());
		b.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(content == null) {
					if(event.getAction() == MotionEvent.ACTION_DOWN){

					}
					if(event.getAction() == MotionEvent.ACTION_UP){
					}
				}
				return false;
			}
		});
		
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
			}
		});
		
		b.setOnDragListener(new OnDragListener() {

			@Override
			public boolean onDrag(View v, DragEvent event) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		return null;
	}

	public void deleteContent() {
		new File(content.getPath()).delete();
	}

}
