package story.book;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * Activity that serves as the main menu where the user can 
 * navigate to a list of their local stories or to a list of online stories.
 * Every view is able to navigate back to this Dashboard.
 * 
 * @author Nancy Pham-Nguyen
 * @author Vina Nguyen
 */

public class Dashboard extends Activity {

	private EditText enterName;
    private String defaultName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard);

		enterName = (EditText) findViewById(R.id.enter_name);
		defaultName = this.getString(R.string.default_nickname);
		displayNickname();
		
		final Intent localIntent = new Intent(this, LocalStoriesActivity.class);
		final Intent onlineIntent = new Intent(this, OnlineStoriesActivity.class);

		Button localButton = (Button) findViewById(R.id.local_stories);
		localButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//Navigate to the Local Stories
				startActivity(localIntent);
			}
		});

		Button onlineButton = (Button) findViewById(R.id.online_stories);
		onlineButton.setOnClickListener(new OnClickListener() {
		    public void onClick(View v) {
		    	//Navigate to the Online Stories
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
    
    /**
     * Method where the user can set their nickname. If 
     * they do not enter anything, set the name to "Anonymous"
     */
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
    
    /**
     * Displays the nickname of the user whether it is the one
     * they entered or "Anonymous"
     */
    private void displayNickname() {
    	SharedPreferences settings = getPreferences(MODE_PRIVATE);
    	String name = settings.getString("Nickname", defaultName );
    	
        if (name != defaultName) {
    		enterName.setText(name);
    	}
        
        StoryApplication.setNickname(name);
    }

}
