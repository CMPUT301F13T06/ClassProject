/**
 * 
 */
package story.book;

import android.app.ActionBar;
import android.app.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

/**
 * @author jsurya
 *
 */
public class StoryFragmentReadActivity extends Activity implements StoryView {
	 // Declare Tab Variable
    ActionBar.Tab Tab1, Tab2;
    Fragment readingTab1 = new ReadingFragment();
    Fragment annotationsTab2 = new AnnotationFragment();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.story_fragment_read_activity);
		
		ActionBar actionBar = getActionBar();
		 
        // Create Actionbar Tabs
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
 
        // Set Tab Icon and Titles
        Tab1 = actionBar.newTab().setText(R.string.FragmentTitle);
        Tab2 = actionBar.newTab().setText("Annotations");
        
 
        // Set Tab Listeners
        Tab1.setTabListener(new TabListener(readingTab1));
        Tab2.setTabListener(new TabListener(annotationsTab2));
 
        // Add tabs to actionbar
        actionBar.addTab(Tab1);
        actionBar.addTab(Tab2);
        

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.standard_menu, menu);
	    return super.onCreateOptionsMenu(menu);
	}

	@Override
	public void update(StoryModel model) {
		// TODO Auto-generated method stub
		
	}

}
