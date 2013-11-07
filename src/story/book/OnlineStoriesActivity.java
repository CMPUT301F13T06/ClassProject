package story.book;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.support.v4.app.NavUtils;

/**
 * 
 * @author Nancy Pham-Nguyen
 *
 */
public class OnlineStoriesActivity extends Activity {

    private OnlineStoryController onlineController = new OnlineStoryController();
    ArrayList<StoryInfo> storyInfo;

    TextView tView;
    ListView listView;

    protected ArrayAdapter<StoryInfo> adapter;
    protected ArrayList<StoryInfo> storyList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_local_stories);

	onlineController = new OnlineStoryController();
	storyList = onlineController.getStoryList();
	adapter = new ArrayAdapter<StoryInfo>(this, android.R.layout.simple_list_item_1, storyList);

	listView = (ListView) findViewById(R.id.listView);
	listView.setAdapter(adapter);
	adapter.notifyDataSetChanged();
	registerForContextMenu(listView);
	listView.setOnItemClickListener(new OnItemClickListener() {
	    @Override
	    public void  onItemClick(AdapterView<?> parent , View view,
		    int pos, long id) {



	    }
	});
	// Show the Up button in the action bar.
	setupActionBar();
    }

    private void  readStory(long id){

    }

    /**
     * Method to create a floating context menu when an item in the list is
     * clicked and held on
     * http://developer.android.com/guide/topics/ui/menus.html#context-menu
     */

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
	    ContextMenuInfo menuInfo) {
	super.onCreateContextMenu(menu, v, menuInfo);
	MenuInflater inflater = getMenuInflater();
	inflater.inflate(R.menu.context_menu, menu);
    }

    /**
     * http://developer.android.com/guide/topics/ui/menus.html#context-menu
     */

    @Override
    public boolean onContextItemSelected(MenuItem item) {
	AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
	switch (item.getItemId()) {
	case R.id.read_story:
	    readStory(info.id);
	    return true;
	case R.id.edit_story:
	    // editStory(info.id);
	    return true;
	case R.id.delete_story:
	    //	deleteStory(info.id);
	    return true;
	default:
	    return true;
	}
    }


    /**
     * Set up the {@link android.app.ActionBar}.
     */
    private void setupActionBar() {

	getActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.online_stories, menu);
	return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
	switch (item.getItemId()) {
	case R.id.action_search:
	    openSearch();
	case R.id.title_activity_dashboard:
	    NavUtils.navigateUpFromSameTask(this);
	    return true;
	}
	return super.onOptionsItemSelected(item);
    }

    private void openSearch() {
	// TODO Auto-generated method stub

    }

}
