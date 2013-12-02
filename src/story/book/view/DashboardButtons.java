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