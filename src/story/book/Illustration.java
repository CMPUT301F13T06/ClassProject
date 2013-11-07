package story.book;

import android.view.View;

/**
 * Base class for Illustrations. Extend to implement different types of
 * Illustrations for different forms of media.
 * 
 * @author Alexander Cheung
 *
 * @param <T> the type of the content to be held by the Illustration.
 */
public abstract class Illustration {
	
	
	/**
	 * Gets the content of the Illustration.
	 * @return T the content of the Illustration.
	 */
	public abstract String getContent();
	
	/**
	 * Sets the content of the Illustration to object of type T.
	 * @param content the content to be stored in the Illustration, of type T.
	 */
	public abstract void setContent(String content);
	
	/**
	 * Returns the contents of the illustration in a View object for Activities
	 * to display.
	 * XXX: Needs review; should use generic types
	 * 
	 * @return the View object containing the contents of the illustration
	 */
	public abstract View getView();
}
