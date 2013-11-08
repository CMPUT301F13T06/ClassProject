package story.book;

import java.util.ArrayList;
import java.util.HashMap;

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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

/**
 * Activity that allows the user to create new stories, delete stories, edit stories, 
 * or select a story to read from a list of their locally stored stories.
 * This activity uses a controller LocalStoryController to get the story information
 * to display in the list. The controller is also used to save stories.
 *
 * @author Nancy Pham-Nguyen
 * @author Anthony Ou
 */

public class LocalStoriesActivity extends Activity implements StoryView<Story> {

    public static final String KEY_TITLE = "Title";
    public static final String KEY_ID = "id";

    ListView listView;

    SimpleAdapter sAdapter;

    ArrayList<HashMap<String, String>> sList;
    HashMap<String, String> testMap;


    private LocalStoryController localController;
    ArrayAdapter<StoryInfo> adapter;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.library_activity);

	// localController.getStory();

	localController = new LocalStoryController();

	sList = new ArrayList<HashMap<String, String>>();

	String[] from = new String[] { KEY_TITLE, KEY_ID };
	int[] to = new int[] { R.id.textTest, R.id.textTest2 };

	// for (int i = 0; i < from.length; i++) {
	testMap = new HashMap<String, String>();
	testMap.put(KEY_TITLE, from[0]);
	testMap.put(KEY_ID, "" + "\n" + "hello");
	sList.add(testMap);
	// }

	sAdapter = new SimpleAdapter(this, sList, R.layout.stories_list, from,
		to);

	listView = (ListView) findViewById(R.id.listView);
	adapter = new ArrayAdapter<StoryInfo>(this, android.R.layout.simple_list_item_1, localController.getStoryList());
	listView.setAdapter(adapter);

	registerForContextMenu(listView);
	listView.setOnItemClickListener(new OnItemClickListener() {
	    @Override
	    public void onItemClick(AdapterView<?> parent, View view, int pos,
		    long id) {
		position = pos;

	    }
	});
	// Show the Up button in the action bar.
	getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void refresh(){
	adapter.clear();
	adapter.addAll(localController.getStoryList());
    }
    
    @Override
    public void onResume() {
	super.onStart();
	refresh();
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
     * @param SID
     */
    public void readStory() {
	Intent intent = new Intent(this, StoryInfoActivity.class);
	intent.putExtra("calledByOffline", false);
	startActivity(intent);
    }

    public void editStory() {
	Intent intent = new Intent(this, EditStoryInformationActivity.class);
	startActivity(intent);
    }

    /**
     * Delete the story at the correct SID
     */
    public void deleteStory() {
	// if long click on story then give option to delete
	localController.deleteStory(adapter.getItem(position).getSID());
	
    }

    private void openSearch() {

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
	localController.getStory(adapter.getItem(position).getSID());
	switch (item.getItemId()) {
	case R.id.read_story:
	    readStory();
	    return true;
	case R.id.edit_story:
	    editStory();
	    return true;
	case R.id.delete_story:
	    deleteStory();
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
	    finish();
	    NavUtils.navigateUpFromSameTask(this);
	    return true;
	}
	return super.onOptionsItemSelected(item);
    }

    @Override
    public void update(Story model) {
	// TODO Auto-generated method stub
	adapter.clear();
	adapter.addAll(localController.getStoryList());
    }
}