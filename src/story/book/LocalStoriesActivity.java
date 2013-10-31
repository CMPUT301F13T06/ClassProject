package story.book;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.support.v4.app.NavUtils;

public class LocalStoriesActivity extends StoryListActivity {

	StoryInfo storyInfo;
	public EditText text;
	ListView view;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.activity_local_stories);
		
		//Get message from intent
		//Intent intent = getIntent();
		
		//text = (EditText) findViewById(R.id.test);
		
		//listView = (ListView) findViewById(R.id.listView);
		//listView.setAdapter(adapter);
		
		
		// Show the Up button in the action bar.
		setupActionBar();
	}
	
	protected void onStart(){
		super.onStart();
		/*
		Button testB = (Button) findViewById(R.id.testButton);
		testB.setOnClickListener(new OnClickListener() {
			public void onClick(View v){
			
				storyList.add(text.getText().toString());
				//storyList.add(0,storyInfo);
				adapter.notifyDataSetChanged();
				
			}
		});*/
		
		
		//storyList.add(0,storyInfo);
		//adapter.notifyDataSetChanged();
	}
	
	/**
	 * Add the newly created story to the list with it's story information
	 **/
	
	public void addStory(){

		//Intent intent = new Intent(this, EditStoryInformationActivity.class);
		//startActivity(intent);
		//test = (EditText) findViewById(R.id.title);
		//storyList.add(0,test.getText().toString());
		
		//storyList.add(0,storyInfo);
		//adapter.notifyDataSetChanged();
		
	}
	
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
			addStory();
			return true;
		case R.id.title_activity_dashboard:
			
			return true;
		
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
