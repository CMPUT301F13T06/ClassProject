package story.book;

import java.util.ArrayList;

public abstract class StoryModel {
	
	private ArrayList<StoryView> views;
	
	public StoryModel () {
		   views = new ArrayList<StoryView>();
	}

    public void addView(StoryView view) {
        if (!views.contains(view)) {
            views.add(view);
        }
    }

    public void deleteView(StoryView view) {
        views.remove(view);
    }

    public void notifyViews() {
        for (StoryView view : views) {
            view.update(this);
        }
    }
}
