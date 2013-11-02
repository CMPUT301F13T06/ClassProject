package story.book;

/**
 * Base class for Illustrations. Extend to implement different types of
 * Illustrations for different forms of media.
 * 
 * @author Alex
 *
 * @param <T> the type of the content to be held by the Illustration.
 */
public abstract class Illustration<T> {
	
	protected T content;
	
	/**
	 * Gets the content of the Illustration.
	 * @return T the content of the Illustration.
	 */
	public abstract T getContent();
	
	/**
	 * Sets the content of the Illustration to object of type T.
	 * @param content the content to be stored in the Illustration, of type T.
	 */
	public abstract void setContent(T content);
}
