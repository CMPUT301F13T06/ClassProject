/**
 * 
 */
package story.book;

import android.widget.TextView;

/**
 *
 */
public class TextIllustration implements Illustration<TextView> {
	
	private String contents;
	
	public TextIllustration(String contents) {
		setContents(contents);
	}
	
	private void setContents(String contents) {
		this.contents = contents;
	}

	@Override
	public TextView getContent() {
		// TODO Auto-generated method stub
		return null;
	}
}
