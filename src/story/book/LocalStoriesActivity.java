package story.book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

/**
 * Activity that allows the user to create new stories or select a story to read
 * from their local stories
 * 
 * @author Nancy Pham-Nguyen
 * 
 */
public class LocalStoriesActivity extends Activity implements
StoryView<Story> {

    public EditText text;
    ListView listView;
    TextView tView1;
    TextView tView2;

    SimpleAdapter sAdapter;

    List<HashMap<String, String>> sList;
    HashMap<String, String> testMap;

    protected ArrayAdapter<StoryInfo> adapter;
    protected ArrayList<StoryInfo> storyList;

    ArrayList<StoryInfo> storyInfo;
    private LocalStoryController localController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_local_stories);
	localController = new LocalStoryController();
	
	storyList = localController.getStoryList();
	listView = (ListView) findViewById(R.id.listView);
	listView.setAdapter(new ArrayAdapter<StoryInfo>(this, android.R.layout.simple_list_item_1, storyList));


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

    public void itemSelected() {
	final Intent intent = new Intent(this, StoryInfoActivity.class);
	listView.setOnItemClickListener(new OnItemClickListener() {

	    public void onItemClick(AdapterView<?> parent, View v, int pos,
		    long id) {

		// start new activity when an entry is selected
		startActivity(intent);

	    }
	});

    }

    /**
     * Add the newly created story to the list with it's story information
     **/

    public void createStory() {

	localController.createStory();
	localController.saveStory();
	Intent intent = new Intent(this, EditStoryInformationActivity.class);
	startActivity(intent);
    }

    /**
     * Method that is called when a user chooses to read the story
     * 
     * @param id
     */
    public void readStory(int id) {
	Intent intent = new Intent(this, StoryInfoActivity.class);
	startActivity(intent);

    }

    public void editStory(int id) {
	Intent intent = new Intent(this,EditStoryInformationActivity.class);
	startActivity(intent);
    }

    /**
     * Delete the story at the correct SID
     */
    public void deleteStory(int id) {
	// if long click on story then give option to delete
	localController.deleteStory(id);
    }

    private void openSearch() {

    }

    /**
     * Set up the {@link android.app.ActionBar}.
     */
    private void setupActionBar() {

	getActionBar().setDisplayHomeAsUpEnabled(true);

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
	    readStory(Long.valueOf(info.id).intValue());
	    return true;
	case R.id.edit_story:
	    editStory(Long.valueOf(info.id).intValue());
	    return true;
	case R.id.delete_story:
	    deleteStory(Long.valueOf(info.id).intValue());
	    return true;
	default:
	    return true;
	}
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.local_stories, menu);
	return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
	switch (item.getItemId()) {
	case R.id.action_search:
	    openSearch();
	    return true;
	case R.id.action_create_story:
	    createStory();
	    return true;
	case R.id.title_activity_dashboard:
	    NavUtils.navigateUpFromSameTask(this);
	    return true;
	}
	return super.onOptionsItemSelected(item);
    }

    @Override
    public void update(Story model) {
	// TODO Auto-generated method stub

    }

}
