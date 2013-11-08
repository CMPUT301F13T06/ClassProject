package story.book;



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

	ListView listView;

	private LocalStoryController localController;
	ArrayAdapter<StoryInfo> a;
	int position;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.library_activity);

		localController = new LocalStoryController();

		listView = (ListView) findViewById(R.id.listView);
		a = new ArrayAdapter<StoryInfo>(this,
				android.R.layout.simple_list_item_1,
				localController.getStoryList());
		listView.setAdapter(a);

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
		intent.putExtra("SID", a.getItem(position).getSID());
		startActivity(intent);
	}

	public void editStory() {
		Intent intent = new Intent(this, EditStoryInformationActivity.class);
		intent.putExtra("SID", a.getItem(position).getSID());
		startActivity(intent);
	}

	/**
	 * Delete the story at the correct SID
	 */
	public void deleteStory() {
		// if long click on story then give option to delete
		localController.deleteStory(a.getItem(position).getSID());
		a.notifyDataSetChanged();
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
		a.clear();
		a.addAll(localController.getStoryList());
	}
}