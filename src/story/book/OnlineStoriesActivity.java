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

package story.book;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.support.v4.app.NavUtils;

/**
 * Activity that allows the user to view stories from an available list of 
 * stories online and download them to their local stories list.
 * It uses a controller called OnlineStoryController.
 * 
 * @author Nancy Pham-Nguyen
 * @author Anthony Ou
 */
public class OnlineStoriesActivity extends Activity implements StoryView<Story>{

    private StoryController onlineController;
    ArrayList<StoryInfo> storyInfo;
    ListView listView;

    protected ArrayAdapter<StoryInfo> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.library_activity);

	onlineController = new OnlineStoryController();

	adapter = new ArrayAdapter<StoryInfo>(this, android.R.layout.simple_list_item_1, onlineController.getStoryList());
	listView = (ListView) findViewById(R.id.listView);
	registerForContextMenu(listView);

	listView.setAdapter(adapter);

	listView.setOnItemClickListener(new OnItemClickListener() {
	    @Override
	    public void  onItemClick
	    (AdapterView<?> parent , View view, int pos, long id) {
		onlineController.getStory(adapter.getItem(pos).getSID());
		Log.d(StoryApplication.getCurrentStory().getStoryInfo().getAuthor(), "Online control");
		Intent intent = new Intent(parent.getContext(), StoryInfoActivity.class);
		intent.putExtra("calledByOnline", true);
		startActivity(intent);
	    }});
	// Show the Up button in the action bar.
	setupActionBar();
    }

    @Override
    public void onResume() {
	super.onResume();
	adapter.clear();
	adapter.addAll(onlineController.getStoryList());
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
	    finish();
	    return true;
	}
	return super.onOptionsItemSelected(item);
    }

    private void openSearch() {
	// TODO Auto-generated method stub

    }

    @Override
    public void update(Story model) {
	// TODO Auto-generated method stub

    }

}
