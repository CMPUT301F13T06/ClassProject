package story.book;

import java.util.ArrayList;

public abstract class StoryModel<V extends StoryView> {
	
	private ArrayList<V> views;
	
	public StoryModel () {
		   views = new ArrayList<V>();
	}

    public void addView(V view) {
        if (!views.contains(view)) {
            views.add(view);
        }
    }

    public void deleteView(V view) {
        views.remove(view);
    }

    protected void notifyViews() {
        for (V view : views) {
            view.update(this);
        }
    }
}
