package story.book.model;

import story.book.view.StoryApplication;
import android.net.Uri;
import android.view.View;
import android.widget.VideoView;

public class VideoIllustration extends Illustration<Uri> {

	private Uri content;
	
	public VideoIllustration(Uri data) {
		super();
		setContent(data);
	}
	
	public Uri getContent() {
		return content;
	}

	public View getView() {
		VideoView a = new VideoView(StoryApplication.getContext());
		a.setVideoURI(content);
		return a;
	}

	public void setContent(Uri content) {
		this.content = content;	
	}

	@Override
	public void deleteContent() {
		// TODO Auto-generated method stub
		
	}

}
