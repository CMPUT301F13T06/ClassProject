package story.book;

import android.app.Application;

public class StoryApplication extends Application {
    // Singleton Models
    transient private static LocalStoryModel localModel = null;
    transient private static OnlineStoryModel onlineModel = null;
    
    // Singleton Controllers
    transient private static LocalStoryController localController = null;
    transient private static OnlineStoryController onlineController = null;
    
    static LocalStoryModel getLocalModel() {
        if (localModel== null) {
        	localModel = new LocalStoryModel();
        }
        return localModel;
    }
    
    static OnlineStoryModel getOnlineModel() {
        if (onlineModel == null) {
        	onlineModel = new OnlineStoryModel();
        }
        return onlineModel;
    }
    
    static LocalStoryController getlocalController() {
        if (localController == null) {
        	localController = new LocalStoryController();
        }
        return localController;
    }
    
    static OnlineStoryController getOnlineController() {
        if (onlineController == null) {
        	onlineController = new OnlineStoryController();
        }
        return onlineController;
    }
}
