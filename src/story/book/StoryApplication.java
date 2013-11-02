package story.book;

import android.app.Application;
import android.content.Context;

public class StoryApplication extends Application {
	
    // Singleton IO and ES clients
    transient private static IOClient io = null;
    transient private static ESClient es = null;
    
    private static Story currentStory;
    private static Context context;

    public void onCreate(){
        super.onCreate();
        context = getApplicationContext();
    }
    
    public static IOClient getIOClient() {
    	if (io == null) {
    		io = new IOClient(context);
    	}
    	return io;
    }
    
    public static ESClient getESClient() {
    	if (es == null) {
    		es = new ESClient();
    	}
    	return es;
    }
    
    public static Story getCurrentStory() {
    	return currentStory;
    }
    
    public static void setCurrentStory(Story story) {
    	currentStory = story;
    }
    
}
