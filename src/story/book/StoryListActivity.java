package story.book;


import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.support.v4.app.NavUtils;


public class StoryListActivity extends Activity {

	//protected ArrayAdapter<StoryInfo> adapter;
	//protected ArrayList<StoryInfo> storyList;
	
	protected ArrayAdapter<String> adapter;
	protected ArrayList<String> storyList;
	ListView listView;
	
	public EditText text;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_story_list);
		
		//storyList = new ArrayList<StoryInfo>();
		//adapter=  new ArrayAdapter<StoryInfo>(this, R.layout.stories_list, storyList);
		
		text = (EditText) findViewById(R.id.test);
		
		storyList = new ArrayList<String>();
		adapter =  new ArrayAdapter<String>(this, R.layout.stories_list, storyList);
		
		
		listView = (ListView) findViewById(R.id.listView);
		listView.setAdapter(adapter);
		
		
	
		// Show the Up button in the action bar.
		setupActionBar();
	}
	
	protected void onStart(){
		super.onStart();
		
		Button testB = (Button) findViewById(R.id.testButton);
		testB.setOnClickListener(new OnClickListener() {
			public void onClick(View v){
			
				storyList.add(text.getText().toString());
				//storyList.add(0,storyInfo);
				adapter.notifyDataSetChanged();
				
			}
		});
		
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
		getMenuInflater().inflate(R.menu.story_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
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