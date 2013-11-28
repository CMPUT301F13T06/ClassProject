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
import story.book.controller.OnlineStoryController;
import story.book.model.Story;
import story.book.model.StoryInfo;
import android.os.Bundle;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.SimpleAdapter;
import android.support.v4.app.NavUtils;

/**
 * Activity that allows the user to view stories from an available list of 
 * stories online and download them to their local stories list.
 * It uses a controller called OnlineStoryController. There is also an "I'm Feeling Lucky" ala Google
 * button that the user can select to choose a random story for them.
 * 
 * @author Nancy Pham-Nguyen
 * @author Anthony Ou
 */
public class OnlineStoriesActivity extends Activity implements StoryView<Story>{

	ListView listView;
	ArrayList<HashMap<String, String>> sList;
	SimpleAdapter sAdapter;
	SearchView searchView;

	private OnlineStoryController onlineController;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.library_activity);

		onlineController = new OnlineStoryController();

		sList = new ArrayList<HashMap<String,String>>();

		listView = (ListView) findViewById(R.id.listView);
		refreshList(onlineController.getStoryList());
		registerForContextMenu(listView);	

		listView.setOnItemClickListener(new OnItemClickListener() {
			/*
			 * on click of an online story to display the story info immediately. 
			 */
			@Override
			public void  onItemClick
			(AdapterView<?> parent , View view, int pos, long id) {
				onlineController.getStory(getFromAdapter(pos));
				readStory();
			}});

		Button luckyButton = (Button) findViewById(R.id.luckyButton);
		luckyButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(sAdapter.getCount() > 0) {
					//generate a random story for the user
					onlineController.getStory(getFromAdapter(new Random()
					.nextInt(sAdapter.getCount())));

					readStory();
				}
			}
		});

		// Show the Up button in the action bar.
		setupActionBar();
	}

	/**
	 * Displays the list of online stories.
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

		String[] from = new String[] {"Title", "Author", "Date", "SID"};
		int[] to = new int[] {R.id.listItem1, R.id.listItem2, R.id.listItem3};

		sAdapter = new SimpleAdapter(this, sList, R.layout.stories_list, from, to);
		listView.setAdapter(sAdapter);

	}

	/**
	 * This method gets the position of the item of the adapter
	 */
	private int getFromAdapter(int pos){
		return Integer.parseInt(((HashMap<String,String>) sAdapter.getItem(pos)).get("SID"));
	}

	/**
	 * Method that is called when a user chooses to read the story
	 * 
	 */
	public void readStory() {
		Intent intent = new Intent(this, StoryInfoActivity.class);
		intent.putExtra("calledByOnline", false);
		startActivity(intent);
	}

	@Override
	public void onResume() {
		super.onResume();
		refreshList(onlineController.getStoryList());
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}


	/**
	 * http://stackoverflow.com/questions/18832890/android-nullpointerexception-on-searchview-in-action-bar
	 * http://stackoverflow.com/questions/17874951/searchview-onquerytextsubmit-runs-twice-while-i-pressed-once
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_bar, menu);
		getMenuInflater().inflate(R.menu.standard_menu, menu);

		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		searchView = (SearchView) menu.findItem(R.id.search_bar)
				.getActionView();
		// Assumes current activity is the searchable activity
		searchView.setSearchableInfo(searchManager
				.getSearchableInfo(getComponentName()));
		searchView.setIconifiedByDefault(true); //iconify the widget
		searchView.setSubmitButtonEnabled(true);


		handleSearch();
		return true;
	}

	/**
	 * Updates the adapter with only the search results
	 * @param query
	 */
	private void searchResults(String query){	
		refreshList(onlineController.search(query));
	}

	/**
	 * Handle the search when the submit button is clicked on in the action bar
	 */
	private void handleSearch(){
		searchView.setOnQueryTextListener(new OnQueryTextListener(){
			@Override
			public boolean onQueryTextChange(String newText) {
				// TODO Auto-generated method stub
				if(newText.isEmpty()){
					refreshList(onlineController.getStoryList());
					return true;
				}
				else{
					return false;
				}
			}


			@Override
			public boolean onQueryTextSubmit(String query) {
				// TODO Auto-generated method stub
				//Do something when the user selects the submit button
				searchResults(query);				
				return true;
			}
		});

	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.title_activity_dashboard:
				NavUtils.navigateUpFromSameTask(this);
				finish();
				return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void update(Story model) {
		// TODO Auto-generated method stub

	}

}
