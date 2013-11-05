package story.book;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Dashboard extends Activity {

    public EditText enterName;
    private TextView tView1;
    private TextView tView2;

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.dashboard);

	enterName = (EditText) findViewById(R.id.enter_name);

	tView1 = (TextView) findViewById(R.id.hello_adventurer);
	tView2 = (TextView) findViewById(R.id.start_adventure);

	getActionBar().setTitle(R.string.title_activity_dashboard);
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
