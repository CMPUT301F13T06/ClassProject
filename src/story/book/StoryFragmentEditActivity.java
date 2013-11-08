/**
 * 
 */
package story.book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * StoryFragmentEditActivity is the interface users can make changes
 * to illustrations contained in the story fragment which is currently
 * open. All text illustrations displayed dynamically as EditTexts.
 * All decision branches are displayed as buttons. Remove illustrations
 * or branches by long pressing and selecting the corresponding
 * option from the context menu. 
 * 
 * @author Jessica Surya
 *
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
public class StoryFragmentEditActivity extends FragmentActivity implements StoryView<StoryFragment> {
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
		SF.notifyViews();

		String title = SF.getFragmentTitle();

		actionBar = getActionBar();
		actionBar.setTitle(title);

		SF.addView(this);
		illustrationViews = new ArrayList<View>();
		loadFragmentContents();

	}

	@Override
	public void onStart() {
		super.onStart();

		displayFragment();
	}

	@Override
	public void onPause(){
		super.onPause();
		Log.d(String.valueOf(illustrations.size()), "# of illustrations");
		saveFragment();
		//FCC.saveStory();
		loadFragmentContents();
		Log.d(String.valueOf(illustrations.size()), "# of illustrations");
	}

	@Override
	public void onResume(){
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

	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.text:
			// TODO: Pass illustration to fragment for editing
			addNewTextIllustration(this.findViewById(R.id.reading_fragment));
			return true;

		case R.id.take_photo:

			return true;
		case R.id.addGalleryPhoto:

			return true;
		case R.id.addDecisionBranch:

			return true;

		case R.id.audio:

			return true;

		case R.id.video:

			return true;

		case R.id.record_video:

			return true;
		case R.id.title_activity_dashboard:
			Intent intent = new Intent(this, Dashboard.class);
			startActivity(intent);
			finish();
			return true;

		default:
			return super.onOptionsItemSelected(item);
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

		Log.d(String.valueOf(illustrationViews.size()), "DEBUG number of views 1");
		for (int i = 0; i < illustrationViews.size(); ++i) {
			Log.d(String.valueOf(illustrationViews.size()), "DEBUG number of views 2");
			Log.d(String.valueOf(i), "DEBUG view #");
			if (i <= illustrations.size()) {
				Illustration ill = illustrations.get(i);
				String illString = ((EditText) illustrationViews.get(i)).getText().toString();
				Log.d(illString, "DEBUG Contents of illustration");
				if (illString.equals("")) {
					// Existing illustration which had its contents removed
					FCC.removeTextIllustration((TextIllustration) ill);
				}
				else {
					// Existing illustration which has contents
					FCC.removeTextIllustration((TextIllustration) ill);
					FCC.addTextIllustration(illString);
				}
			}
			else {
				// New illustrations to be saved
				String illString = ((EditText) illustrationViews.get(i)).getText().toString();
				Log.d(illString, "DEBUG Contents of illustration");
				if (illString.equals("")) {
					// Empty EditText view
				}
				else {
					// Create a new illustration
					FCC.addTextIllustration(illString.toString());
				}
			}
		}
	}

	@Override
	public void update(StoryFragment model) {
		// TODO Auto-generated method stub
		//display fragment contents
		SF = SFL.get(FID);
		loadFragmentContents();
	}

	/**
	 * loadFragmentContents() loads illustration views from a saved story fragment
	 */
	private void loadFragmentContents() {

		illustrations = SF.getIllustrations();
		decisions = SF.getDecisionBranches();

		illustrationViews = new ArrayList<View>();
		for (Illustration i : illustrations){
			illustrationViews.add(((TextIllustration)i).getEditView());
		}

	}

	/**
	 * displayFragments() displays all text illustrations as views 
	 * and decision branches as buttons by getting them from the containing 
	 * fragment with <code>loadFragmentContents()</code> and formatting them 
	 * by calling <code>formatView</code> and <code>formatButton</code> respectively.
	 * 
	 */
	private void displayFragment() {

		RelativeLayout layout = (RelativeLayout) findViewById(R.id.reading_fragment);
		((ViewGroup) layout).removeAllViews();
		int position = 0;
		if (illustrationViews.isEmpty() == false ){
			// Display illustrations
			formatView(illustrationViews);
			for (View t: illustrationViews){
				t.setId(position + 1);
				RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(LayoutParams.
						WRAP_CONTENT,LayoutParams.WRAP_CONTENT); 
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
					lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
				}
				else {
					lp.addRule(RelativeLayout.ABOVE, position);
				}

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
			menu.add(0, v.getId(), 4, "Cancel"); 
		}

		else {
			menu.setHeaderTitle("Select an Option:");
			menu.add(0, v.getId(), 1, "Delete illustration");  
			menu.add(0, v.getId(), 4, "Cancel"); 
		}
	}


	@Override  
	public boolean onContextItemSelected(MenuItem item) {
		int index; 
		switch (item.getOrder()) {

		case 1:
			//Delete illustration
			saveFragment();
			displayFragment();
			View v = illustrationViews.get(itemPos);
			index = illustrationViews.indexOf(v);
			Illustration i = illustrations.get(index);

			FCC.removeTextIllustration((TextIllustration) i);

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

			break;

		case 4:
			// Cancel options
			return false;
		}

		return true; 

	}

	/**
	 * formatView() (FOR TEXTVIEWS ONLY) formats illustration textViews in an array list
	 * by changing:
	 * 		- text size (20)
	 * 		- text color (black)
	 * 		- padding on the left side
	 * 
	 * @param v
	 * 
	 */
	private void formatView(ArrayList<View> v) {
		Iterator<View> viewIterator = v.iterator();
		EditText x = null;
		while(viewIterator.hasNext()) {
			x = (EditText) viewIterator.next();
			x.setTextSize(20);
			x.setTextColor(Color.BLACK);
			x.setPaddingRelative(5, 0, 0, 10);
		}
	}

	/**
	 * formatButton() creates a button with the corresponding decision branch text
	 * for each decision branch in an array list of decision branches.
	 * 
	 * This returns method an array list of buttons.
	 * 
	 * @param db
	 * @param c
	 * @return ArrayList<Button>
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
}
