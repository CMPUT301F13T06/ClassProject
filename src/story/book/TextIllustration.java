package story.book;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Illustration class with textual content.
 * 
 * @author Alexander Cheung
 */
public class TextIllustration extends Illustration {
	
	/**
	 * Initialize content in constructor.
	 * @param content is the String containing the text of the TextIllustration
	 */
	String content;
	public TextIllustration(String content) {
		super();
		setContent(content);
	}
	
	/**
	 * Sets the content of the content of the <code>TextIllustration</code> to
	 * the specified <code>String</code>.
	 * 
	 * @param	content	the <code>String</code> to set as the content
	 */
	public void setContent(String content) {
		this.content = content;
	}
	public String getContent() {
		return this.content;
	}
	
	/**
	 * Returns a <code>TextView</code> object initialized with the contents of
	 * the illustration.
	 * XXX: Needs review; should use generic types
	 * 
	 * @return	the <code>TextView</code> object with text set
	 */
	public View getView() {
		TextView textView = new TextView(StoryApplication.getContext());
		textView.setText(this.content);
		return textView;
	}
	
	/**
	 * Returns a <code>EditText</code> object initialized with the contents of
	 * the illustration.
	 * 
	 * @return	the <code>EditText</code> object with text set
	 */
	
	public EditText getEditView() {
		EditText editView = new EditText(StoryApplication.getContext());
		editView.setText(this.content);
		return editView;
	}
}
