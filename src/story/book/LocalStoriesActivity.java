package story.book;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.support.v4.app.NavUtils;

/**
 * Activity that allows the user to create new stories or select a story to read from
 * their local stories
 * 
 * @author Nancy Pham-Nguyen
 *
 */

public class LocalStoriesActivity extends StoryListActivity implements StoryView<Story>{

	
	public EditText text;
	ListView view;
	
	//public ArrayList<String> storyInfo;
	public ArrayList<StoryInfo> storyInfo;
	
	public final static String EXTRA_MESSAGE ="story.book.MainActivity";
	
	
	LocalStoryController localController = new LocalStoryController();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.activity_local_stories);
		
		
		//Get message from intent
		//Intent intent = getIntent();
		
		//storyInfo = new ArrayList<String>();
		
		storyInfo = localController.getStoryList();
		
		text = (EditText) findViewById(R.id.test);
		
		
	String storeAuthor = text.getText().toString();
	
	
		//storyInfo.setAuthor(storeAuthor);
		
		listView = (ListView) findViewById(R.id.listView);
		listView.setAdapter(adapter);
		
		longClick();
		//itemSelected();
		
		// Show the Up button in the action bar.
		setupActionBar();
	}
	
	protected void onStart(){
		super.onStart();
		
		
		Button testB = (Button) findViewById(R.id.testButton);
		testB.setOnClickListener(new OnClickListener() {
			public void onClick(View v){
			
				//storyList.addAll(0,storyInfo);
				storyList.add(0,text.getText().toString());
				//storyList.add(storyInfo);
				
				
				adapter.notifyDataSetChanged();
				
			}
		});
	}
	
	public void sendMessage(View v){
		StoryInfo storyInfo = new StoryInfo();
		Intent intent = new Intent(this, StoryInfoActivity.class);
		
		
		
		
		String storeAuthor = text.getText().toString();
		storyInfo.setAuthor(storeAuthor);
		
		
		intent.putExtra(EXTRA_MESSAGE,storyInfo.getAuthor());
		startActivity(intent);
	}
	
	public void longClick(){
		listView.setOnLongClickListener(new View.OnLongClickListener(){
			public boolean onLongClick(View v){
				v.setSelected(true);
				Context context = getApplicationContext();
				CharSequence start = "Start";
				int duration = Toast.LENGTH_SHORT;

				Toast toast = Toast.makeText(context, start, duration);
				toast.show();
				return true;
			}
		});
	}
	
	public void itemSelected(){
		listView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View v, int pos,
					long id) {
				
					// start new activity when an entry is selected
					sendMessage(v);
		}
		});

	}
	
	/**
	 * Add the newly created story to the list with it's story information
	 **/
	
	public void createStory(){
		
		localController.createStory();
		localController.saveStory();
		Intent intent = new Intent(this, EditStoryInformationActivity.class);
		startActivity(intent);
	}
	
	/**
	 * Method that is called when a user chooses to read the story
	 */
	public void readStory(){
		Intent intent = new Intent(this, StoryFragmentReadActivity.class);
		startActivity(intent);
		
	}
	
	public void editStory(){
		
	}
	
	/**
	 * Delete the story at the correct SID
	 */
	public void deleteStory(){
		//if long click on story then give option to delete
		int SID;
		//localController.deleteStory(SID);
	}
	
	/**
	 * Method that shows the popup menu
	 * @param v
	 */
	public void showPopup(View v){
		PopupMenu popup = new PopupMenu(this,v);
		MenuInflater inflater = popup.getMenuInflater();
		inflater.inflate(R.menu.local_stories, popup.getMenu());
		popup.show();
	}
	
	private void openSearch(){
		
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}
	
	/**
	 * Method to create a floating context menu when an item in the list
	 * is clicked and held on
	 */
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo){
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.context_menu,menu);
	}
	
	/**
	 * 
	 */
	@Override
	public boolean onContextItemSelected(MenuItem item){
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		switch(item.getItemId()){
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
				return super.onContextItemSelected(item);
		}
	}
		
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.local_stories, menu);
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
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


	@Override
	public void update(Story model) {
		// TODO Auto-generated method stub
		
	}

}
