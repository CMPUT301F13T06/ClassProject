package story.book;

import java.util.ArrayList;

public interface StoryHandler {
    
    public abstract int getFreeSID();
    
    public abstract ArrayList<StoryInfo> getStoryList();
}
