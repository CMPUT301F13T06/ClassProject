/**
 * 
 */
package story.book;

import android.app.ActionBar;
import android.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * @author jsurya
 *
 */
public class StoryFragmentReadActivity extends Activity implements StoryView<Story> {
	 // Declare Tab Variable
    ActionBar.Tab Tab1, Tab2;
    Fragment readingTab1 = new ReadingFragment();
    Fragment annotationsTab2 = new AnnotationFragment();
    ActionBar actionBar;
	//StoryFragment sf = new StoryFragment(fragmentTitle);
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.story_fragment_read_activity);
		//R.string.FragmentTitle = sf.getFragmentTitle();
		actionBar = getActionBar();
		actionBar.setTitle(R.string.FragmentTitle);
		 
        // Create Actionbar Tabs
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
 
        // Set Tab Icon and Titles
        Tab1 = actionBar.newTab().setText("Story");
        Tab2 = actionBar.newTab().setText("Annotations");
 
        // Set Tab Listeners
        Tab1.setTabListener(new TabListener(readingTab1));
        Tab2.setTabListener(new TabListener(annotationsTab2));
 
        // Add tabs to action bar
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
	
	public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
        case R.id.activity_main:
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
            
        default:
            return super.onOptionsItemSelected(item);
        }
	}

	@Override
	public void update(Story model) {
		// TODO Auto-generated method stub
		
	}

}
