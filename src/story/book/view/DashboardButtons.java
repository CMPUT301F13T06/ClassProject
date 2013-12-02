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
import android.widget.Button;
import android.content.Intent;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

/**
 * Class that is responsible for creating and handling the buttons associated
 * to the activity Dashboard.java.
 * 
 * @author Alexander Cheung
 * @author Nancy Pham-Nguyen
 */

public class DashboardButtons {
	
	/**
	 * Parameter object which defines the format of a button for the
	 * <code>formatButton</code> method
	 * @author Alex
	 *
	 */
	static class ButtonFormat {
		
		public String connection;
		public String name;
		public Typeface font;
		
		/**
		 * Constructor initializing the format attributes of a button
		 * 
		 * @param connection	local or online
		 * @param name			name of the library
		 * @param font			<code>Typeface</code> for the font
		 */
		public ButtonFormat(String connection, String name, Typeface font) {
			this.connection = connection;
			this.name = name;
			this.font = font;
		}

		/**
		 * Formats the specified button using attributes from the  <code>ButtonFormat</code> parameter object.
		 * @param button 	the <code>Button</code> to format
		 */
		public void formatButton(Button button) {
			Spannable span = new SpannableString(connection + "\n" + name);
			span.setSpan(new RelativeSizeSpan(0.5f), 0, connection.length(),
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			button.setText(span);
			button.setTypeface(font);
		}
	}
	
	/**
	 * Initializes the button for the local story library
	 */
	public void initializeLocalButton(final Dashboard dashboard) {
		Button localButton = (Button) dashboard
				.findViewById(R.id.local_stories);
		final Intent localIntent = new Intent(dashboard,
				LocalStoriesActivity.class);
		new ButtonFormat(
				dashboard.getString(R.string.dashboard_local),
				dashboard.getString(R.string.dashboard_nook),
				Typeface.createFromAsset(dashboard.getApplicationContext()
						.getAssets(), "fonts/homey.ttf"))
				.formatButton(localButton);
		localButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				dashboard.startActivity(localIntent);
			}
		});
	}

	/**
	 * Initializes the button for the online story library
	 */
	public void initializeOnlineButton(final Dashboard dashboard) {
		Button onlineButton = (Button) dashboard
				.findViewById(R.id.online_stories);
		final Intent onlineIntent = new Intent(dashboard,
				OnlineStoriesActivity.class);
		new ButtonFormat(
				dashboard.getString(R.string.dashboard_online),
				dashboard.getString(R.string.dashboard_club),
				Typeface.createFromAsset(dashboard.getApplicationContext()
						.getAssets(), "fonts/adventure.ttf"))
				.formatButton(onlineButton);
		onlineButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (StoryApplication.checkInternetConnected())
					dashboard.startActivity(onlineIntent);
				else
					Toast.makeText(dashboard.getApplicationContext(),
							R.string.no_internet_library, Toast.LENGTH_SHORT)
							.show();
			}
		});
	}
}