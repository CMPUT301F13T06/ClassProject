package story.book;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Activity that allows the user to view the information of their story and then
 * view the story
 * 
 * @author Nancy Pham-Nguyen
 * 
 */

public class StoryInfoActivity extends Activity  {

	TextView title;
	TextView author;
	TextView date;
	TextView genre;
	TextView synopsis_text;

	StoryInfoController storyInfoController;
	StoryInfo storyInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_story_info);

		author = (TextView) findViewById(R.id.author);
		date = (TextView) findViewById(R.id.date);
		genre = (TextView) findViewById(R.id.genre);
		synopsis_text = (TextView) findViewById(R.id.synopsis_text);

		storyInfoController = new StoryInfoController();
		storyInfo = storyInfoController.getStoryInfo();
		
		displayStoryInfo();

		final Intent intent = new Intent(this, StoryFragmentReadActivity.class);
		Button viewButton = (Button) findViewById(R.id.view);
		viewButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivity(intent);
			}
		});
	}

	private void displayStoryInfo() {
		
		setTitle(storyInfo.getTitle());
		
		author.setText(this.getString(R.string.author) + " " + storyInfo.getAuthor());
		date.setText(this.getString(R.string.date) + " " + storyInfo.getPublishDateString());
		genre.setText(this.getString(R.string.genre)+ " " + storyInfo.getGenre());
		synopsis_text.setText(storyInfo.getSynopsis());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.story_info, menu);
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
