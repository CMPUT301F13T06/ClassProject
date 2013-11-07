package story.book;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Activity that allows the user to edit the information of their story or enter
 * information for their newly created story
 * 
 * @author Nancy Pham-Nguyen
 * 
 */

public class EditStoryInformationActivity extends Activity  {

	// public final int ids[] = {R.id.author, R.id.genre, R.id.synopsis};

	public EditText editTitle;
	public EditText editAuthor;
	public EditText editGenre;
	public EditText editSynopsis;

	public ArrayList<StoryInfo> storyList;

	StoryInfoController storyInfoController = new StoryInfoController();

	StoryInfo storyInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_story_information);

		storyInfo = storyInfoController.getStoryInfo();

		setStoryInfo();
		displayStoryInfo();

		// TextView[] textViews = new TextView[ids.length];

		// for(int i =0; i < ids.length; i++){
		// textViews[i] = new TextView(this);
		// }

	}

	/**
	 * Display the information that the the user has entered
	 */
	// TODO call onCreate AND onUpdate
	private void displayStoryInfo() {
		editTitle = (EditText) findViewById(R.id.enter_title);
		editTitle.setText(storyInfo.getTitle());
		editAuthor = (EditText) findViewById(R.id.enter_author);
		editAuthor.setText(storyInfo.getAuthor());
		editGenre = (EditText) findViewById(R.id.enter_genre);
		editGenre.setText(storyInfo.getGenre());
		editSynopsis = (EditText) findViewById(R.id.enter_synopsis);
		editSynopsis.setText(storyInfo.getSynopsis());

	}

	/**
	 * Method that sets the information of the story entered by the user
	 */
	private void setStoryInfo() {
		editTitle = (EditText) findViewById(R.id.enter_title);
		storyInfo.setTitle(editTitle.getText().toString());
		editAuthor = (EditText) findViewById(R.id.enter_author);
		storyInfo.setAuthor(editAuthor.getText().toString());
		editGenre = (EditText) findViewById(R.id.enter_genre);
		storyInfo.setGenre(editGenre.getText().toString());
		editSynopsis = (EditText) findViewById(R.id.enter_synopsis);
		storyInfo.setSynopsis(editSynopsis.getText().toString());
		storyInfoController.setStoryInfo(storyInfo);

	}

	protected void onStart() {
		super.onStart();
		// final Intent intent = new Intent(this,
		// StoryFragmentListActivity.class);

		// test for StoryInfoActivity
		final Intent intent = new Intent(this, StoryFragmentListActivity.class);

		// When the user selects the Done button do something
		Button doneButton = (Button) findViewById(R.id.done);
		doneButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// if the title field is empty then an AlertDialog will appear
				if (editTitle.getText().toString().equals("")) {
					alertDialog();
				} else {
					// Navigate to the list of Story Fragments
					startActivity(intent);
				}
				/*
				 * if(editAuthor.getText().toString() == ""){ //set the author
				 * to nickname that was entered in the dashboard }
				 */
			}

		});
	}

	/**
	 * AlertDialog http://developer.android.com/guide/topics/ui/dialogs.html
	 * Method shows an alertDialog when the user selects the "Done" button but
	 * the required text field has no text input
	 */

	public void alertDialog() {
		// Instantiate an AlertDialog.Builder with its constructor
		AlertDialog.Builder builder = new AlertDialog.Builder(
				EditStoryInformationActivity.this);
		// Chain together various setter methods to set the dialog
		// characteristics
		builder.setMessage(R.string.dialog_message).setTitle(
				R.string.dialog_title);

		// Add buttons to AlertDialog
		builder.setPositiveButton(R.string.ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// User clicked OK button
					}
				});
		builder.setNegativeButton(R.string.cancel,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// User clicked Cancel button

					}
				});

		// Create the AlertDialog
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_story_information, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.title_activity_dashboard:
			Intent intent = new Intent(this, Dashboard.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	

}