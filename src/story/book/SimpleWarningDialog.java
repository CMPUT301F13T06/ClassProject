package story.book;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/** This is a facade for creating a simple warning dialog 
 * with title, message, and a confirmation button.
 * 
 * @author Nancy Pham-Nguyen	
 *
 * @author Vina Nguyen
 *
 * Reference: http://developer.android.com/guide/topics/ui/dialogs.html 
 */

public class SimpleWarningDialog {

	/**
	 * Creating a simple warning dialog with generic title, 
	 * the supplied message, and a confirmation button.
	 */
	public static void getWarningDialog(String message, Context context) {
		// Instantiate an AlertDialog.Builder with its constructor
				AlertDialog.Builder builder = new AlertDialog.Builder(
						context);
				// Chain together various setter methods to set the dialog
				// characteristics
				builder.setMessage(message).setTitle(
						R.string.dialog_title);

				// Add buttons to AlertDialog
				builder.setPositiveButton(R.string.ok,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// User clicked OK button
							}
						});
				// Create the AlertDialog
				AlertDialog dialog = builder.create();
				dialog.show();
	}
}
