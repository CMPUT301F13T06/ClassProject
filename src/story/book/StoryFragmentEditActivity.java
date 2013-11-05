/**
 * 
 */
package story.book;

import android.app.ActionBar;
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
public class StoryFragmentEditActivity extends Activity {
    	ActionBar actionBar;
    	
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
	        case R.id.activity_main:
	            Intent intent = new Intent(this, Dashboard.class);
	            startActivity(intent);
	            return true;
	            
	        default:
	            return super.onOptionsItemSelected(item);
	        }
		}
}
