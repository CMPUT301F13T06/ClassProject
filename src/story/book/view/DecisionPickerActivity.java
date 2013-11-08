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

import story.book.view.R;
import story.book.controller.DecisionBranchCreationController;
import story.book.controller.StoryCreationController;
import story.book.model.DecisionBranch;
import story.book.model.StoryFragment;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DialogFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/** 
 * DecisionPickerActivity lets the author select a fragment
 * and a title to create a decision branch for the current
 * story fragment being edited.
 * 
 * @author Jessica Surya
 * 
 * @author Vina Nguyen
 */

public class DecisionPickerActivity extends Activity implements RequestingActivity {
	ActionBar actionBar;
	ArrayList<StoryFragment> SFL;
	ArrayAdapter<StoryFragment> adapter;
	StoryCreationController SCC;
	
	DecisionBranchCreationController DBCC;
	
	int pos;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.story_fragment_read_activity);
		
		SCC = new StoryCreationController();
		
		savedInstanceState = getIntent().getExtras();
		int FID = savedInstanceState.getInt("FID");
		DBCC = new DecisionBranchCreationController(FID);
		
		updateFragmentList();

		return;
	}

	/** 
	 * Displays a list of fragments.
	 */
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
				//remember destination fragment
				pos = position;
				//get decision branch label
			    DialogFragment newFragment = new RequestTextDialog();
			    ((RequestTextDialog)newFragment).setParent(DecisionPickerActivity.this);
			    ((RequestTextDialog)newFragment).setHeader("New Decision Branch Label");
			    ((RequestTextDialog)newFragment).setWarning("Cannot create branch with empty label.");
			    newFragment.show(getFragmentManager(), "addBranch");
			}

		});
	}

	/** 
	 * Gets the title back from the RequestTextDialog
	 * and adds a decision branch using the selected
	 * fragment and the title.
	 * 
	 */
	@Override
	public void onUserSelectValue(String title) {
		SFL = new ArrayList<StoryFragment>();

		HashMap<Integer, StoryFragment> map = SCC.getFragments();
		for (Integer key : map.keySet()){
			SFL.add(map.get(key));
		}
		SFL.get(pos).getFragmentID();
		
		DecisionBranch db = new DecisionBranch(title, SFL.get(pos).getFragmentID());
		DBCC.addDecisionBranch(db);
		finish();
	}
	

}
