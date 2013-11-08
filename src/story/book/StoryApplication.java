/* CMPUT301F13T06-Adventure Club: A choose-your-own-adventure story platform
 * Copyright (C) 2013 Alexander Cheung, Jessica Surya, Vina Nguyen, Anthony Ou,
 * Nancy Pham-Nguyen
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package story.book;

import java.util.Date;

import android.app.Application;
import android.content.Context;

import story.book.dataclient.IOClient;
import story.book.dataclient.ESClient;

/**
 * Application class for aggregating the Singleton IOClient, ESClient, and
 * the active instance of Story. The current instance of Story, 
 * <code>currentStory</code>, is the active Story object which can be read, 
 * edited or published by an application controller.
 * 
 * @author 	Alexander Cheung
 * @see 	Story
 * @see		IOClient
 * @see		ESClient
 */
public class StoryApplication extends Application {
	
    // Singleton IO and ES clients
    transient private static IOClient io = null;
    transient private static ESClient es = null;
    
    private static Story currentStory;
    private static Context context;
    private static String nickname;

    public void onCreate(){
        super.onCreate();
        context = getApplicationContext();
        
        // DEBUG
        
		StoryInfo info = new StoryInfo();
		info.setAuthor("Daniel Andy");
		info.setTitle("Broken Star");
		info.setGenre("Science Fiction");
		info.setSynopsis("The princess of a destroyed kingdom is left with no one to guide her, until she finds a fallen star with a secret inside....");
		info.setPublishDate(new Date());
		info.setSID(600);
		
		Story story = new Story(info);
		StoryFragment fragment1 = new StoryFragment("Finding the Star");
		// TODO
		TextIllustration text = new TextIllustration("It was a dark, clear night.");
		fragment1.addIllustration(text);
		StoryFragment fragment2 = new StoryFragment("Preparing for the Journey");
		TextIllustration text2 = new TextIllustration("She ventured into the locked dungeons to retrieve some potions.");
		TextIllustration text3 = new TextIllustration("She could not carry everything, she had to choose between potion A and potion B.");
		fragment2.addIllustration(text2);
		fragment2.addIllustration(text3);
		story.addFragment(fragment1);
		story.addFragment(fragment2);
		
		DecisionBranch branch = new DecisionBranch("She decides she must find the star.", fragment2.getFragmentID());
		DecisionBranch branch2 = new DecisionBranch("She declares she is too weak to find the star.", fragment1.getFragmentID());
		fragment1.addDecisionBranch(branch);
		
		
		setCurrentStory(story);
    }
    
    /**
     * Returns the application's <code>IOClient</code>.
     * 
     * @return	the application's <code>IOClient</code>
     */
    public static IOClient getIOClient() {
    	if (io == null) {
    		io = new IOClient(context);
    	}
    	return io;
    }
    
    /**
     * Returns the application's <code>ESClient</code>.
     * 
     * @return	the application's <code>ESclient</code>
     */
    public static ESClient getESClient() {
    	if (es == null) {
    		es = new ESClient();
    	}
    	return es;
    }
    
    /**
     * Returns the current application story
     * 
     * @return	the current <code>Story</code>
     */
    public static Story getCurrentStory() {
    	return currentStory;
    }
    
    /** 
     * Sets the current application story
     * 
     * @param 	story	the <code>Story</code> object to set as current
     */
    public static void setCurrentStory(Story story) {
    	currentStory = story;
    }
    
    /**
     * Returns the <code>Context</code> of the application.
     * 
     * @return	the application </code>Context</code>
     */
    public static Context getContext() {
    	return context;
    }
    
    /**
     * Sets the user's nickname to the specified <code>String</code>
     * 
     * @param 	name	the nickname of the user
     */
    public static void setNickname(String name) {
    	nickname = name;
    }
    
    /**
     * Returns the user's nickname
     * 
     * @return	the nickname of the user
     */
    public static String getNickname() {
    	if (nickname == null) {
    		return "Anonymous";
    	} else {
    		return nickname;
    	}
    }
}
