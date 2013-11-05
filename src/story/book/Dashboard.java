package story.book;


import android.os.Bundle;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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
	
    private String defaultName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard);

		enterName = (EditText) findViewById(R.id.enter_name);
		defaultName = this.getString(R.string.enter_name);
		displayNickname();
		
		tView1 = (TextView) findViewById(R.id.hello_adventurer);
		tView2 = (TextView) findViewById(R.id.start_adventure);
	}

	protected void onStart() {
		super.onStart();

		// Maybe use swtich statement for the buttons?
		
		final Intent localIntent = new Intent(this, LocalStoriesActivity.class);
		final Intent onlineIntent = new Intent(this, OnlineStoriesActivity.class);

		Button localButton = (Button) findViewById(R.id.local_stories);
		localButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivity(localIntent);
			}
		});

		Button onlineButton = (Button) findViewById(R.id.online_stories);
		onlineButton.setOnClickListener(new OnClickListener() {
		    public void onClick(View v) {
				startActivity(onlineIntent);
			}
		});

	}
    
	@Override
	protected void onPause() {
		super.onPause();
		
		setNickname();
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.main, menu);
	return true;
    }
    
    private void setNickname() {
		String name = enterName.getText().toString();
		if (name == "") {
			name = defaultName;
		}
		
    	SharedPreferences settings = getPreferences(MODE_PRIVATE);
    	SharedPreferences.Editor editor = settings.edit();
    	editor.putString("Nickname", name);
    	editor.commit();
    	
    	StoryApplication.setNickname(name);
    }
    
    private void displayNickname() {
    	SharedPreferences settings = getPreferences(MODE_PRIVATE);
    	String name = settings.getString("Nickname", defaultName );
    	
        if (name != defaultName) {
    		enterName.setText(name);
    	}
        
        StoryApplication.setNickname(name);
    }

}
