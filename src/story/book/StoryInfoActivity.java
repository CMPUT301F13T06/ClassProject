package story.book;

import java.util.ArrayList;
import java.util.Date;

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

	public ArrayList<String> infoList;

	TextView title;
	TextView author;
	TextView genre;
	TextView synopsis;

	StoryInfoController storyInfoController = new StoryInfoController();
	StoryInfo storyInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_story_info);

		Intent intent = getIntent();
		// String message =
		// intent.getStringExtra(LocalStoriesActivity.EXTRA_MESSAGE);

		title = (TextView) findViewById(R.id.title);
		author = (TextView) findViewById(R.id.author);
		genre = (TextView) findViewById(R.id.genre);
		synopsis = (TextView) findViewById(R.id.synopsis);

		storyInfo = storyInfoController.getStoryInfo();

		// infoList = new ArrayList<String>();
		// author.setText("Author: " + " " + message);

		displayStoryInfo();
	}

	private void displayStoryInfo() {
		title.setText("Title: " + storyInfo.getTitle());
		author.setText("Author: " + storyInfo.getAuthor());
		genre.setText("Genre: " + storyInfo.getGenre());
		synopsis.setText("Synopsis: " + storyInfo.getSynopsis());
	}

	protected void onStart() {
		super.onStart();

		final Intent intent = new Intent(this, StoryFragmentReadActivity.class);

		Button viewButton = (Button) findViewById(R.id.view);
		viewButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				startActivity(intent);

			}
		});

		// infoList.add(storyInfo.getAuthor());
		// infoList.add(storyInfo.getTitle());
		// infoList.add(storyInfo.getGenre());
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
