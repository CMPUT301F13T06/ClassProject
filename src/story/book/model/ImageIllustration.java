package story.book.model;

import story.book.view.StoryApplication;
import android.net.Uri;
import android.view.DragEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.widget.ImageView;

public class ImageIllustration extends Illustration<Uri> {

	private Uri content;
	
	public ImageIllustration(Uri content) {
		super();
		setContent(content);
	}
	
	public Uri getContent() {
		// TODO Auto-generated method stub
		return content;
	}
	
	public void setContent(Uri content) {
		this.content = content;
	}

	public View getView() {
		ImageView a = new ImageView(StoryApplication.getContext());
		a.setImageURI(content);
		a.setAdjustViewBounds(true);
		a.setOnDragListener(new OnDragListener() {
			
			@Override
			public boolean onDrag(View v, DragEvent event) {
				// TODO Auto-generated method stub
				((ImageView)v).setMaxHeight((int) event.getX());
				((ImageView)v).setMaxWidth((int) event.getY());
				return false;
			}
		});

		return a;
	}
}
