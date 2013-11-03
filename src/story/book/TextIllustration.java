package story.book;

import android.view.View;
import android.widget.TextView;

/**
 * Illustration class with textual content.
 * 
 * @author Alexander Cheung
 */
public class TextIllustration extends Illustration<String> {
	
	/**
	 * Initialize content in constructor.
	 * @param content is the String containing the text of the TextIllustration
	 */
	public TextIllustration(String content) {
		super();
		setContent(content);
	}
	
	@Override
	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String getContent() {
		return this.content;
	}
	
	@Override
	public View getView() {
		TextView textView = new TextView(StoryApplication.getContext());
		textView.setText(this.content);
		return textView;
	}
}
