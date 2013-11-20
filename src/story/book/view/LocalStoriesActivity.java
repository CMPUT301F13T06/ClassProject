/* CMPUT301F13T06-Adventure Club: A choose-your-own-adventure story platform
 * Copyright (C) 2013 Alexander Cheung, Jessica Surya, Vina Nguyen, Anthony Ou,
 * Nancy Pham-Nguyen
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package story.book.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import story.book.view.R;
import story.book.controller.LocalStoryController;
import story.book.model.Story;
import story.book.model.StoryInfo;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;


/**
 * Activity that allows the user to create new stories, delete stories, edit
 * stories, or select a story to read from a list of their locally stored
 * stories. This activity uses a controller LocalStoryController to get the
 * story information to display in the list. The controller is also used to save
 * stories.
 * 
 * @author Nancy Pham-Nguyen
 * @author Anthony Ou
 */

public class LocalStoriesActivity extends Activity implements StoryView<Story> {

	CharSequence text;
	
	private ArrayList<String> list;
	private ArrayAdapter<String>testAdapter;
	ListView testView;
	
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

		localController = new LocalStoryController();

		// TODO Will move to SimpleAdapter in the future
		/*
		 * sList = new ArrayList<HashMap<String, String>>();
		 * 
		 * String[] from = new String[] { KEY_TITLE, KEY_ID }; int[] to = new
		 * int[] { R.id.textTest, R.id.textTest2 };
		 * 
		 * // for (int i = 0; i < from.length; i++) { testMap = new
		 * HashMap<String, String>(); testMap.put(KEY_TITLE, from[0]);
		 * testMap.put(KEY_ID, "" + "\n" + "hello"); sList.add(testMap); // }
		 * 
		 * sAdapter = new SimpleAdapter(this, sList, R.layout.stories_list,
		 * from, to);
		 */

		listView = (ListView) findViewById(R.id.listView);
		refreshList();

		registerForContextMenu(listView);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
				// read story with a single tap do not change this.
				localController.getStory(adapter.getItem(pos).getSID());
				readStory();
			}
		});

		Button luckyButton = (Button) findViewById(R.id.luckyButton);
		luckyButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(adapter.getCount() > 0) {
					localController.getStory(adapter.getItem( new Random().nextInt(adapter.getCount())).getSID());
					readStory();
				}
			}
		});

		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	/**
	 * Displays the list of local stories.
	 */
	public void refreshList() {
		adapter = new ArrayAdapter<StoryInfo>(this,
				android.R.layout.simple_list_item_1,
				localController.getStoryList());
		listView.setAdapter(adapter);
	}

	@Override
	public void onResume() {
		super.onResume();
		refreshList();
	}

	/**
	 * Add the newly created story to the list with it's story information
	 **/
	public void createStory() {
		localController.createStory();
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
		Intent intent = new Intent(this, StoryFragmentListActivity.class);
		startActivity(intent);
	}

	/**
	 * Delete the story at the correct SID
	 */
	public void deleteStory() {
		// if long click on story then give option to delete
		localController.deleteStory(adapter.getItem(position).getSID());
		refreshList();
	}

	private void openSearch() {

		//Get the intent, verify the action and get the query
		//Intent intent = getIntent();
		//if(Intent.ACTION_SEARCH.equals(intent.getAction())){
		//String query = intent.getStringExtra(SearchManager.QUERY);

		//doMySearch(query);
		
		/*
		list = new ArrayList<String>();
		list.add("YAY");
		testAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
		testView = (ListView) findViewById(R.id.listView);
		testView.setAdapter(testAdapter);
		
		testAdapter.notifyDataSetChanged();*/
		//}
	}
	
	private void doMySearch(String query){
		/*
		list = new ArrayList<String>();
		list.add("YAY");
		testAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
		listView.setAdapter(testAdapter);
		
		testAdapter.notifyDataSetChanged();
		
		//if(localController.search(query).equals(query)){
			
			
			
			
			//refreshList();
		//}*/
		
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
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();
		position = info.position;
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

	/**
	 * http://stackoverflow.com/questions/18832890/android-nullpointerexception-on-searchview-in-action-bar
	 * 
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// MenuInflater inflater = getMenuInflater();
		// inflater.inflate(R.menu.local_stories, menu);

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.local_stories, menu);

		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
				.getActionView();
		// Assumes current activity is the searchable activity
		searchView.setSearchableInfo(searchManager
				.getSearchableInfo(getComponentName()));
		searchView.setIconifiedByDefault(true); //iconify the widget
		searchView.setSubmitButtonEnabled(true);

		/*text = searchView.getQuery();
		searchView.setOnQueryTextListener(new OnQueryTextListener());
		.onQueryTextSubmit();*/
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
