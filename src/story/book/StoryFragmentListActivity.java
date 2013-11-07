/**
 * 
 */
package story.book;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.AdapterContextMenuInfo;

/**
 * StoryFragmentListActivity displays all story fragments contained
 * in the story that is currently open. Selecting a story fragment
 * will allow user to:
 * 		1. Edit the story fragment
 * 		2. Set the story fragment as the starting fragment (TODO)
 * 		3. Delete the story fragment from the story (TODO)
 * 
 * @author Jessica Surya
 *
 */
public class StoryFragmentListActivity extends Activity implements StoryView<Story> {
	ActionBar actionBar;
	ArrayList<StoryFragment> SFL;
	ArrayAdapter<StoryFragment> adapter;
	StoryCreationController SCC;
	
	int pos;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.story_fragment_read_activity);
		
		SCC = new StoryCreationController();
		SCC.getStory().addView(this);
		
		updateFragmentList();

		return;
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		
		SCC.saveStory();
	}
	
	@Override
	public void update(Story model) {
		updateFragmentList(); 
	}
	
	private void updateFragmentList() {
		SFL = new ArrayList<StoryFragment>();

		HashMap<Integer, StoryFragment> map = SCC.getFragments();
		for (Integer key : map.keySet()){
			SFL.add(map.get(key));
		}

		String title = SCC.getStory().getStoryInfo().getTitle();
		actionBar = getActionBar();
		actionBar.setTitle(title);
		
		adapter = new ArrayAdapter<StoryFragment>(this, android.R.layout.simple_list_item_1,
				SFL);

		ListView listview = new ListView(this);

		listview.setBackgroundColor(Color.WHITE);

		listview.setAdapter(adapter);
		setContentView(listview);

		registerForContextMenu(listview);
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView parent, View v, int position, long id) {
				pos = position;
				Intent i = new Intent(parent.getContext(), StoryFragmentEditActivity.class);
				i.putExtra("FID", SFL.get(pos).getFragmentID());
				startActivity(i);
			}

		});
	}
	
	private void editFragment(int FID) {
		Intent i = new Intent(this, StoryFragmentEditActivity.class);

		i.putExtra("FID", FID);
		startActivity(i);
	}
	
	private void addFragment() {
	    DialogFragment newFragment = new AddFragment();
        newFragment.show(getFragmentManager(), "addFragment");
	}

	public void onUserSelectValue(String title) {
		if (title == null) {
			//Title vaildation failed, re-prompt
			//addFragment();
			//TODO change flow in mock-up? can't block in Android
		} else {
			//Create fragment with this title
			StoryFragment fragment = SCC.newFragment(title);
			
			//Save the story before opening fragment to edit
			SCC.saveStory();
			//TODO want to change this flow in the mock-up?
			editFragment(fragment.getFragmentID());
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.fragment_list_menu, menu);
		inflater.inflate(R.menu.standard_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.title_activity_dashboard:
			Intent intent = new Intent(this, Dashboard.class);
			startActivity(intent);
			finish();
			return true;

		case R.id.add_fragment:
			addFragment();
			return true;
		case R.id.publish:
			SCC.saveStory();
			SCC.publishStory();
			return true;
		case R.id.change_info:
			SCC.saveStory();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.setHeaderTitle("Select an Option:");
		menu.add(0, v.getId(), 1, "Edit");  
		menu.add(0, v.getId(), 2, "Set as Starting Story Fragment"); 
		menu.add(0, v.getId(), 3, "Delete");
		menu.add(0, v.getId(), 4, "Cancel");
	}

	@Override  
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		pos = info.position;
		
		switch (item.getOrder()) {
		case 1:
			// Edit story fragment
			editFragment(SFL.get(pos).getFragmentID());
			break;

		case 2:
			// Set as starting story fragment
			SCC.setStartingFragment(SFL.get(pos).getFragmentID());
			break;
		case 3:
			//Delete
			int FID = SFL.get(pos).getFragmentID();
			if (FID == SCC.getStartingFragment()) {
				fragmentDeleteDialog();
			} else {
				SCC.deleteFragment(FID);
			}
			break;
		case 4:
			// Cancel options
			return false;
		}

		return true; 

	}
	
	private void fragmentDeleteDialog() {
		// Instantiate an AlertDialog.Builder with its constructor
		AlertDialog.Builder builder = new AlertDialog.Builder(
				StoryFragmentListActivity.this);
		// Chain together various setter methods to set the dialog
		// characteristics
		builder.setMessage(R.string.bad_frag_delete_msg).setTitle(
				R.string.dialog_title);

		// Add buttons to AlertDialog
		builder.setPositiveButton(R.string.ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// User clicked OK button
					}
				});
		// Create the AlertDialog
		AlertDialog dialog = builder.create();
		dialog.show();
	}
}
