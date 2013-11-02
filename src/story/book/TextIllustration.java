package story.book;

/**
 * Illustration class with textual content.
 * @author Alex
 *
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
}
