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

import story.book.view.R;
import story.book.controller.StoryReadController;
import story.book.model.Illustration;
import story.book.model.StoryFragment;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.TabHost;
import android.widget.Toast;

/**
 * StoryFragmentReadActivity is an interface for users to read a story
 * fragment (left tab), as well as an interface for users to add 
 * annotations (right tab) on the story fragment which is currently being read.
 * 
 * @author Jessica Surya
 *
 */
@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
public class StoryFragmentReadActivity extends FragmentActivity {
	// Declare Tab Variable
	ActionBar.Tab Tab1, Tab2;
	Fragment readingTab1;
	Fragment annotationsTab2;
	ActionBar actionBar;
	StoryReadController SRC;
	ArrayList<Illustration> illustrations;
	int nextFragmentID;

	static StoryFragment SF = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.story_fragment_read_activity);

		SRC = new StoryReadController();
		SF = SRC.getStartingFragment();
		
		if (SF == null) {
			//If there are no fragments to read, do not instantiate this class
			Toast.makeText(getApplicationContext(), R.string.no_fragments, Toast.LENGTH_SHORT).show();
			finish();
			return;
		}
		
		String title = SF.getFragmentTitle();
		illustrations = SF.getIllustrations();

		readingTab1 = new ReadingFragment();
		annotationsTab2 = new AnnotationFragment();
		actionBar = getActionBar();
		setActionBarTitle(title);
		
		// Create Actionbar Tabs
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Set Tab Icon and Titles
		Tab1 = actionBar.newTab().setText("Story");
		Tab2 = actionBar.newTab().setText("Annotations");

		// Set Tab Listeners
		Tab1.setTabListener(new TabListener(readingTab1));
		Tab2.setTabListener(new TabListener(annotationsTab2));

		// Add tabs to action bar
		actionBar.addTab(Tab1);
		actionBar.addTab(Tab2);

	}

	@Override
	public void onStart() {
		super.onStart();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
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

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public StoryReadController getController() {
		return SRC;
	}
	
	public void setNextFragmentID(int destinationID) {
		nextFragmentID = destinationID;
	}
	
	public int getNextFragmentID() {
		return nextFragmentID;
	}
	
	public void setActionBarTitle(String title) {
		actionBar.setTitle(title);
	}
}
