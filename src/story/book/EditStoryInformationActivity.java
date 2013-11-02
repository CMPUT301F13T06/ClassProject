package story.book;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class EditStoryInformationActivity extends Activity {
	
	//public final int ids[] = {R.id.author, R.id.genre, R.id.synopsis};
	
	public EditText editTitle;
	public EditText editAuthor;
	public EditText editGenre;
	public EditText editSynopsis;
	
	
	
	public ArrayList<String> storyList;
	
	StoryInfo storyInfo = new StoryInfo();
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_story_information);
		
		editTitle = (EditText) findViewById(R.id.enter_title);
		editAuthor = (EditText) findViewById(R.id.enter_author);
		editGenre = (EditText) findViewById(R.id.enter_genre);
		editSynopsis = (EditText) findViewById(R.id.enter_synopsis);
		
		
		
		//storyList.add(editTitle.getText().toString());
		
		//TextView[] textViews = new TextView[ids.length];
		
		//for(int i =0; i < ids.length; i++){
			//textViews[i] = new TextView(this);
		//}
		
		/*
		editAuthor = (EditText) findViewById(R.id.enter_author);
		String storeAuthor = editAuthor.getText().toString();
		storyInfo.setAuthor(storeAuthor);*/
		

		
	}

	
	
	protected void onStart(){
		super.onStart();
		
		Button doneButton = (Button) findViewById(R.id.done);
		doneButton.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				//if the title field is empty then an AlertDialog will appear
				if(editTitle.getText().toString().equals("")){
					alertDialog();
				}
				else{
					//Navigate to the list of Story Fragments
				}
				/*
				if(editAuthor.getText().toString() == ""){
					//set the author to nickname that was entered in the dashboard
				}*/
			}
			
		});
	}
	
	/**AlertDialog http://developer.android.com/guide/topics/ui/dialogs.html
	 * 	
	 */
	
	public void alertDialog(){
		// Instantiate an AlertDialog.Builder with its constructor
		AlertDialog.Builder builder = new AlertDialog.Builder(EditStoryInformationActivity.this);
		//Chain together various setter methods to set the dialog characteristics
		builder.setMessage(R.string.dialog_message).setTitle(R.string.dialog_title);
		
		
		
		//Add buttons to AlertDialog
		builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int id){
				//User clicked OK button
			}
		});
		builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int id){
				//User clicked Cancel button
				
			}
		});
		
		//Create the AlertDialog
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
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
