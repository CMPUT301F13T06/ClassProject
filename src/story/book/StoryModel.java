package story.book;

import java.util.ArrayList;

/**
 * Template model class for the application.
 *  
 * @author 	Alexander Cheung
 * @param 	<V>	Any object implementing the <code>StoryView</code> interface
 * @see		StoryView
 */
public abstract class StoryModel<V extends StoryView> {
	
	private ArrayList<V> views;
	
	/**
	 * Default constructor initializes an empty <code>ArrayList</code> of
	 * <code>V</code> objects.
	 */
	public StoryModel () {
		   views = new ArrayList<V>();
	}

	/**
	 * Add a <code>V</code> object to the list of views.
	 * @param 	view	the <code>V</code> object to add
	 */
    public void addView(V view) {
        if (!views.contains(view)) {
            views.add(view);
        }
    }

	/**
	 * Remove a <code>V</code> object from the list of views.
	 * @param 	view	the <code>V</code> object to remove
	 */
    public void deleteView(V view) {
        views.remove(view);
    }

    /**
     * Notifies all views that the model has changed.
     */
    protected void notifyViews() {
        for (V view : views) {
            view.update(this);
        }
    }
}
