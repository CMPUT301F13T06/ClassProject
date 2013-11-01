package story.book;

import java.util.ArrayList;

public abstract class StoryHandler {
    
	/* XXX: This only needs to be done by the OnlineStoryHandler
    public abstract int getFreeSID();
    */
	
	private ArrayList<Story> stories;
	
    public abstract Story getStory(int SID);
    
    public abstract ArrayList<StoryInfo> getStoryList();
    
    protected void addStory(Story story) {
    	this.stories.add(story);
    }
    
    protected void removeStory(Story story) {
    	this.stories.remove(story);
    }
}
