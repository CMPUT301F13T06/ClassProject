/**
 * 
 */
package story.book;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * StoryFragmentEditActivity is the interface users can make changes
 * to illustrations contained in the story fragment which is currently
 * open. 
 * 
 * @author jsurya
 *
 */
@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
public class StoryFragmentEditActivity extends Activity {
    	ActionBar actionBar;
    	//FragmentCreationController FCC = new FragmentCreationController(storyFragment);
    	//storyFragment passed as intent from FragmentList
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.story_fragment_read_activity);
			actionBar = getActionBar();
			actionBar.setTitle(R.string.FragmentTitle);
			
		}
		
		@Override
		public void onPause(){
			super.onPause();
		}
		
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
		    // Inflate the menu items for use in the action bar
		    MenuInflater inflater = getMenuInflater();
		    inflater.inflate(R.menu.add_illustration_menu, menu);
		    inflater.inflate(R.menu.standard_menu, menu);
		    return super.onCreateOptionsMenu(menu);
		}
		
		public boolean onOptionsItemSelected(MenuItem item) {
	        // Handle item selection
	        switch (item.getItemId()) {
	        case R.id.title_activity_dashboard:
	            Intent intent = new Intent(this, Dashboard.class);
	            startActivity(intent);
	            return true;
	            
	        default:
	            return super.onOptionsItemSelected(item);
	        }
		}
}
