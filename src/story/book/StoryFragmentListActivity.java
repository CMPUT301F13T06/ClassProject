/**
 * 
 */
package story.book;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.GridView;

/**
 * StoryFragmentListActivity displays all story fragments contained
 * in the story that is currently open.
 * 
 * @author jsurya
 *
 */
public class StoryFragmentListActivity extends Activity {
	ActionBar actionBar;
	ArrayList<StoryFragment> SFL;
	ArrayAdapter<StoryFragment> adapter;
	StoryCreationController SCC;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.story_fragment_read_activity);
//		SCC = new StoryCreationController();
//		SFL = SCC.getFragments();
		actionBar = getActionBar();
		actionBar.setTitle(R.string.StoryTitle);

		
//		adapter = new ArrayAdapter<StoryFragment>(getBaseContext(),
//				android.R.layout.simple_list_item_1,
//				SFL);
		
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
