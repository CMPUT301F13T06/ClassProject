/* CMPUT301F13T06-Adventure Club: A choose-your-own-adventure story platform
 * Copyright (C) 2013 Alexander Cheung, Jessica Surya, Vina Nguyen, Anthony Ou,
 * Nancy Pham-Nguyen
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 * 
 * Fonts from external sources:
 * http://www.fonts2u.com/homey.font
 * http://www.fonts101.com/fonts/view/Standard/697/Adventure
 * http://www.1001fonts.com/roboto-slab-font.html
 */
package story.book.view;
import story.book.view.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Activity that serves as the main menu where the user can 
 * navigate to a list of their local stories or to a list of online stories.
 * Every view is able to navigate back to this Dashboard.
 * 
 * @author Nancy Pham-Nguyen
 * @author Vina Nguyen
 * @author Alexander Cheung
 */

public class Dashboard extends Activity {

	private DashboardButtons dashboardButtons = new DashboardButtons();
	private EditText enterName;
	private String defaultName;
	
	TextView tView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard);
		
		tView = (TextView) findViewById(R.id.start_adventure);
		enterName = (EditText) findViewById(R.id.enter_name);
		defaultName = this.getString(R.string.default_nickname);
		
		tView.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(),
				"fonts/RobotoSlab-Light.ttf"));
		
		displayNickname();
		dashboardButtons.initializeLocalButton(this);
		dashboardButtons.initializeOnlineButton(this);
	}
	
	
	@Override
	protected void onPause() {
		super.onPause();
		setNickname();
	}

	/**
	 * Method where the user can set their nickname. If 
	 * they do not enter anything, set the name to "Anonymous"
	 */
	private void setNickname() {
		String name = name();
		SharedPreferences settings = getPreferences(MODE_PRIVATE);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString("Nickname", name);
		editor.commit();
		
		StoryApplication.setNickname(name);
	}

	private String name() {
		String name = enterName.getText().toString();
		if (name.equals("")) {
			name = defaultName;
		}
		return name;
	}
	
	/**
	 * Displays the nickname of the user whether it is the one
	 * they entered or "Anonymous"
	 */
	private void displayNickname() {
		SharedPreferences settings = getPreferences(MODE_PRIVATE);
		String name = settings.getString("Nickname", defaultName );
		
		if (!name.equals(defaultName)) {
			enterName.setText(name);
		}
		
		StoryApplication.setNickname(name);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.dashboard_menu, menu);
		boolean result = super.onCreateOptionsMenu(menu);
		return result;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch(item.getItemId()) {
		case R.id.action_help:
			openHelp();
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	private void openHelp() {
		Intent intent = new Intent(this, HelpActivity.class);
		startActivity(intent);
	}
	
}
