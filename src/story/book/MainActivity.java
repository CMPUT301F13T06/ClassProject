package story.book;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	public EditText enterName;
	private TextView tView1;
	private TextView tView2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		enterName = (EditText) findViewById(R.id.enter_name);

		tView1 = (TextView) findViewById(R.id.hello_adventurer);
		tView2 = (TextView) findViewById(R.id.start_adventure);

	}

	protected void onStart() {
		super.onStart();

		// Maybe use swtich statement for the buttons?

		final Intent intent = new Intent(this, LocalStoriesActivity.class);

		Button localButton = (Button) findViewById(R.id.local_stories);
		localButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				startActivity(intent);
			}
		});

		// intent = new Intent(this, OnlineStoriesActivity.class);
		Button onlineButton = (Button) findViewById(R.id.online_stories);
		onlineButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivity(intent);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
