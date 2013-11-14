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


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import story.book.view.R;
import story.book.controller.DecisionBranchCreationController;
import story.book.controller.FragmentCreationController;
import story.book.controller.StoryCreationController;
import story.book.model.DecisionBranch;
import story.book.model.Illustration;
import story.book.model.ImageIllustration;
import story.book.model.StoryFragment;
import story.book.model.TextIllustration;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.DialogFragment;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

/**
 * StoryFragmentEditActivity is the interface users can make changes
 * to illustrations contained in the story fragment which is currently
 * open. All text illustrations displayed dynamically as EditTexts.
 * All decision branches are displayed as buttons. Remove illustrations
 * or branches by long pressing and selecting the corresponding
 * option from the context menu. 
 * 
 * @author Jessica Surya
 * @author Vina Nguyen
 *
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
public class StoryFragmentEditActivity extends FragmentActivity implements StoryView<StoryFragment>, RequestingActivity {
	ActionBar actionBar;

	StoryFragment SF;
	FragmentCreationController FCC;
	DecisionBranchCreationController DBCC;
	ArrayList<StoryFragment> SFL;
	StoryCreationController SCC;

	ArrayList<Illustration> illustrations;
	ArrayList<DecisionBranch> decisions;
	ArrayList<Button> buttons;
	ArrayList<View> illustrationViews;
	ArrayList<Button> buttonList;

	int itemPos;
	int FID;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.reading_fragment);

		savedInstanceState = getIntent().getExtras();

		FID = savedInstanceState.getInt("FID");

		SCC = new StoryCreationController();
		FCC = new FragmentCreationController(FID);
		DBCC = new DecisionBranchCreationController(FID);
		SFL = new ArrayList<StoryFragment>();

		HashMap<Integer, StoryFragment> map = SCC.getFragments();
		for (Integer key : map.keySet()){
			SFL.add(map.get(key));
		}

		SF = SFL.get(FID);

		String title = SF.getFragmentTitle();

		actionBar = getActionBar();
		actionBar.setTitle(title);

		SF.addView(this);
		illustrationViews = new ArrayList<View>();
		loadFragmentContents();

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		SF.deleteView(this);
	}

	@Override
	public void onPause() {
		super.onPause();
		saveFragment();
		FCC.saveStory();
	}

	@Override
	public void onResume() {
		super.onResume();
		displayFragment();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.add_illustration_menu, menu);

		inflater.inflate(R.menu.standard_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	private enum Actions {PHOTO, VIDEO}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.text:
			addNewTextIllustration(this.findViewById(R.id.reading_fragment));
			return true;

		case R.id.take_photo:
			Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/test.jpg")));
			startActivityForResult(i, Actions.PHOTO.ordinal());
			return true;

		case R.id.addGalleryPhoto:

			return true;
		case R.id.addDecisionBranch:
			i = new Intent(this, DecisionPickerActivity.class);
			i.putExtra("FID", FID);
			startActivity(i);
			return true;

		case R.id.audio:

			return true;

		case R.id.video:
			
			return true;

		case R.id.record_video:
			i = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
			i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/test.avi")));
			startActivityForResult(i, Actions.VIDEO.ordinal());
			return true;
		case R.id.title_activity_dashboard:
			i = new Intent(this, Dashboard.class);
			startActivity(i);
			finish();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == Actions.PHOTO.ordinal() && resultCode == RESULT_OK) {
			ImageView a = new ImageView(this);
			a.setImageURI(Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/test.jpg")));
			illustrationViews.add(a);
			displayFragment();
		}
		if(requestCode == Actions.VIDEO.ordinal() && resultCode == RESULT_OK) {
			VideoView a = new VideoView(this);
			a.setVideoURI(Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/test.avi")));
			illustrationViews.add(a);
			displayFragment();
		}
			
	}
	/**
	 * addNewTextIllustration() creates a new EditText for users to enter the text
	 * for a TextIllustration.
	 */
	private void addNewTextIllustration(View v) {
		// TODO Auto-generated method stub
		EditText newText = new EditText(v.getContext());
		newText.setHint("Enter text here");
		illustrationViews.add(newText);
		displayFragment();
	}

	/**
	 * saveFragment() saves the current state and layout of the fragment
	 */
	public void saveFragment() {
		
		ArrayList<Illustration> currentView = new ArrayList<Illustration>();
		int top = illustrationViews.size();
		for (int i = 0; i < top; i++) {
			String illString = ((EditText) illustrationViews.get(i)).getText().toString();
			if (illString.length() > 0) {
				currentView.add(new TextIllustration(illString));
			}
			else {
				illustrationViews.remove(i);
			}
		}

		FCC.removeAllIllustrations();
		FCC.setAllIllustrations(currentView);
	}

	@Override
	public void update(StoryFragment model) {
		//display fragment contents
		SF = SFL.get(FID);
		loadFragmentContents();
		displayFragment();
	}

	/**
	 * loadFragmentContents() loads illustration views from a saved story fragment
	 */
	private void loadFragmentContents() {

		illustrations = SF.getIllustrations();
		decisions = SF.getDecisionBranches();

		illustrationViews = new ArrayList<View>();
		for (Illustration i : illustrations) {
			illustrationViews.add(((TextIllustration)i).getEditView());
		}

	}

	/**
	 * displayFragments() displays all text illustrations as views 
	 * and decision branches as buttons by getting them from the containing 
	 * fragment.
	 * 
	 * Used for updating all views in the activity.
	 * 
	 * http://stackoverflow.com/questions/6583019/dynamic-textview-in-relative-layout
	 * http://stackoverflow.com/questions/3995215/add-and-remove-views-in-android-dynamically
	 * 
	 */
	private void displayFragment() {

		RelativeLayout layout = (RelativeLayout) findViewById(R.id.reading_fragment);
		((ViewGroup) layout).removeAllViews();
		int position = 0;
		if (illustrationViews.isEmpty() == false ){
			// Display illustrations
			for (View t: illustrationViews){
				t.setId(position + 1);
				RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(LayoutParams.
						MATCH_PARENT,LayoutParams.WRAP_CONTENT); 
				p.addRule(RelativeLayout.BELOW, position);
				t.setLayoutParams(p);
				registerForContextMenu(t);
				((ViewGroup) layout).addView(t, p);
				position++;
			}
		}

		if (decisions.isEmpty() == false) {
			int buttonIndex = 0;
			// Display buttons
			buttons = formatButton(decisions, this);
			for (Button dbButton : buttons) {
				dbButton.setId(position + 1);
				buttonIndex++;
				RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, 
						LayoutParams.WRAP_CONTENT);
				if (buttonIndex == 1) {
					lp.setMargins(0, 50, 0, 0);
				}
				lp.setMargins(0, 10, 0, 0);
				lp.addRule(RelativeLayout.BELOW, position);
				dbButton.setLayoutParams(lp);
				registerForContextMenu(dbButton);
				((ViewGroup) layout).addView(dbButton, lp);
				position++;
			}
		}
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);

		itemPos = v.getId() - 1;

		if (v instanceof Button) {
			menu.setHeaderTitle("Select an Option:");
			menu.add(0, v.getId(), 2, "Delete decision branch");  
			menu.add(0, v.getId(), 3, "Edit decision branch text"); 
			menu.add(0, v.getId(), 6, "Cancel"); 
		}

		else {
			menu.setHeaderTitle("Select an Option:");
			menu.add(0, v.getId(), 1, "Delete illustration");  
			menu.add(0, v.getId(), 4, "Move illustration up"); 
			menu.add(0, v.getId(), 5, "Move illustration down"); 
			menu.add(0, v.getId(), 6, "Cancel"); 
		}
	}


	@Override  
	public boolean onContextItemSelected(MenuItem item) {
		int index; 
		switch (item.getOrder()) {

		case 1:
			//Delete illustration
			illustrationViews.remove(itemPos);
			displayFragment();

			break;

		case 2:
			// Delete decision branch

			Button b = buttonList.get(itemPos-illustrationViews.size());
			index =  buttonList.indexOf(b);
			DecisionBranch branch = decisions.get(index);
			DBCC.removeDecisionBranch(branch);
			displayFragment();

			break;

		case 3:
			// Edit decision branch text
			DialogFragment newFragment = new RequestTextDialog();
			((RequestTextDialog)newFragment).setParent(StoryFragmentEditActivity.this);
				((RequestTextDialog)newFragment).setParent(this);
				((RequestTextDialog)newFragment).setHeader(this.getString(R.string.add_branch_title));
				((RequestTextDialog)newFragment).setWarning(this.getString(R.string.bad_branch_msg));
				newFragment.show(getFragmentManager(), "addFragment");
			break;
			
		case 4:
			// Move illustration up
			if (itemPos > 0){
				View above = illustrationViews.get(itemPos-1);
				illustrationViews.set(itemPos-1, illustrationViews.get(itemPos));
				illustrationViews.set(itemPos, above);
				displayFragment();
			}
			
			break;
			
		case 5:
			// Move illustration down
			if (itemPos < illustrationViews.size()-1) {
				View below = illustrationViews.get(itemPos+1);
				illustrationViews.set(itemPos+1, illustrationViews.get(itemPos));
				illustrationViews.set(itemPos, below);
				displayFragment();
			}
			
			break;
			
		case 6:
			// Cancel options
			return false;
		}

		return true; 

	}

	/**
	 * formatButton() creates a button with the corresponding decision branch text
	 * for each decision branch in an array list of decision branches.
	 * 
	 * This returns method an array list of buttons.
	 * 
	 * @param DecisionBranch 	the decision branches associated with the fragment
	 * @param Context 	the context where the button will be displayed
	 * @return an custom ArrayList<Button> corresponding to the decision branches in a fragment
	 */
	private ArrayList<Button> formatButton(ArrayList<DecisionBranch> db, Context c) {

		buttonList = new ArrayList<Button>();

		Iterator<DecisionBranch> dbIterator = db.iterator();
		DecisionBranch d = null;
		Button button;
		while(dbIterator.hasNext()) {
			d = dbIterator.next();
			button = new Button(c);
			button.setText(d.getDecisionText());
			buttonList.add(button);
		}
		return buttonList;
	}

	@Override
	public void onUserSelectValue(String title) {
		Button b = buttonList.get(itemPos - illustrationViews.size());
		int index =  buttonList.indexOf(b);
		// Remove the edited Decision Branch
		DecisionBranch branch = decisions.get(index);
		DBCC.removeDecisionBranch(branch);
		// Re-add the Decision Branch
		branch.setDecisionText(title);
		DBCC.addDecisionBranch(branch);
	}
}
