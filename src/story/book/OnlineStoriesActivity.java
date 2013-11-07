package story.book;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class OnlineStoriesActivity extends Activity {

	private OnlineStoryController onlineController = new OnlineStoryController();
	ArrayList<StoryInfo> storyInfo;
	
	TextView tView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_online_stories);
		
		tView = (TextView) findViewById(R.id.textTest);
		storyInfo = onlineController.getStoryList();
		
		displayList();
		
		// Show the Up button in the action bar.
		setupActionBar();
	}

	private void displayList(){
		if (storyInfo != null){
			tView.setText(storyInfo.toString());
		}
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
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void openSearch() {
		// TODO Auto-generated method stub
		
	}

}
