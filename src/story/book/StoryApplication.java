package story.book;

import android.app.Application;

public class StoryApplication extends Application {
	
    // Singleton Handlers
    transient private static LocalStoryHandler localHandler = null;
    transient private static OnlineStoryHandler onlineHandler = null;
    
    private static Story currentStory;
    
    static LocalStoryHandler getLocalHandler() {
        if (localHandler == null) {
        	localHandler = new LocalStoryHandler();
        }
        return localHandler;
    }
    
    static OnlineStoryHandler getOnlineHandler() {
        if (onlineHandler == null) {
        	onlineHandler = new OnlineStoryHandler();
        }
        return onlineHandler;
    }
    
    static Story getCurrentStory() {
    	return currentStory;
    }
    
    static void setCurrentStory(Story story) {
    	currentStory = story;
    }
}
