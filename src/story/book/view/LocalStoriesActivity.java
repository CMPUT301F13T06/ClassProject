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
import android.graphics.Typeface;
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
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.SimpleAdapter;

/**
 * Activity that allows the user to create new stories, delete stories, edit
 * stories, or select a story to read from a list of their locally stored
 * stories. This activity uses a controller LocalStoryController to get the
 * story information to display in the list. The controller is also used to save
 * stories. There is a "I'm Feeling Lucky" ala Google button that chooses a random
 * story for the user.
 * 
 * @author Nancy Pham-Nguyen
 * @author Anthony Ou
 */

public class LocalStoriesActivity extends Activity implements StoryView<Story> {

	ListView listView;

	SimpleAdapter sAdapter;
	ArrayList<HashMap<String, String>> sList;

	SearchView searchView;

	private LocalStoryController localController;

	int position;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.library_activity);

		localController = new LocalStoryController();
	
		sList = new ArrayList<HashMap<String, String>>();

		listView = (ListView) findViewById(R.id.listView);
		refreshList(localController.getStoryList());

		registerForContextMenu(listView);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos,
					long id) {
				// read story with a single tap.
				localController.getStory(getFromAdapter(pos));
				readStory();
			}
		});

		Button luckyButton = (Button) findViewById(R.id.luckyButton);
		luckyButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (sAdapter.getCount() > 0) {
					//generates a random story for the user
					localController.getStory(getFromAdapter(new Random()
							.nextInt(sAdapter.getCount())));
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
	public void refreshList(ArrayList<StoryInfo> storyList) {
		sList.clear();

		for (StoryInfo storyInfo : storyList) {
			HashMap<String, String> item = new HashMap<String, String>();

			item.put("Title", storyInfo.getTitle());
			item.put("Author", storyInfo.getAuthor());
			item.put("Date", storyInfo.getPublishDateString());
			item.put("SID", String.valueOf(storyInfo.getSID()));

			sList.add(item);

		}

		String[] from = new String[] { "Title", "Author", "Date", "SID" };
		int[] to = new int[] { R.id.listItem1, R.id.listItem2, R.id.listItem3 };

		sAdapter = new SimpleAdapter(this, sList, R.layout.stories_list, from,
				to);
		listView.setAdapter(sAdapter);

	}
	
	/**
     * This method gets the position of the item of the adapter
     */
	private int getFromAdapter(int pos) {
		return Integer.parseInt(((HashMap<String, String>) sAdapter
				.getItem(pos)).get("SID"));

	}

	@Override
	public void onResume() {
		super.onResume();
		refreshList(localController.getStoryList());
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
	 */
	public void readStory() {
		Intent intent = new Intent(this, StoryInfoActivity.class);
		intent.putExtra("calledByOffline", false);
		startActivity(intent);
	}

	/**
	 * Allows the user to edit an existing local story that was not downloaded.
	 */
	public void editStory() {
		//if long click on story, give the option to edit the story
		Intent intent = new Intent(this, StoryFragmentListActivity.class);
		startActivity(intent);
	}

	/**
	 * Delete the story at the correct SID
	 */
	public void deleteStory() {
		// if long click on story, give the option to delete the story
		localController.deleteStory(getFromAdapter(position));
		refreshList(localController.getStoryList());
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
		menu.setHeaderTitle("Select an Option:");
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
		localController.getStory(getFromAdapter(position));

		switch (item.getItemId()) {
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
	 * http://developer.android.com/training/search/setup.html
	 * http://stackoverflow.com/questions/18832890/android-nullpointerexception-
	 * on-searchview-in-action-bar
	 * http://stackoverflow.com/questions/17874951/searchview
	 * -onquerytextsubmit-runs-twice-while-i-pressed-once
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.local_stories, menu);

		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		searchView = (SearchView) menu.findItem(R.id.action_search)
				.getActionView();
		// Assumes current activity is the searchable activity
		searchView.setSearchableInfo(searchManager
				.getSearchableInfo(getComponentName()));
		searchView.setIconifiedByDefault(true); // iconify the widget
		searchView.setSubmitButtonEnabled(true);

		handleSearch();
		return true;
	}

	/**
	 * Updates the adapter with only the search results
	 * @param query
	 */
	private void searchResults(String query) {
		// show the list with just the search results
		refreshList(localController.search(query));
	}

	/**
	 * Handle the search when the submit button is clicked on in the action bar
	 */
	private void handleSearch() {
		searchView.setOnQueryTextListener(new OnQueryTextListener() {

			@Override
			public boolean onQueryTextChange(String newText) {
				// TODO Auto-generated method stub
				if (newText.isEmpty()) {
					//refreshes the story list if there is no text entered to search
					refreshList(localController.search(newText));
					return true;
				} else {
					return false;
				}
			}

			@Override
			public boolean onQueryTextSubmit(String query) {
				// TODO Auto-generated method stub
				// Show the search results when the user selects the submit button
				searchResults(query);
				return true;
			}
		});

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
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
		refreshList(localController.getStoryList());
	}
}
