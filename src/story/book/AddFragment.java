package story.book;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;

public class AddFragment extends DialogFragment {
	
	private StoryFragmentListActivity callingActivity;
    /**
     * http://developer.android.com/guide/topics/ui/dialogs.html,
     * http://stackoverflow.com/questions/12622742/get-value-from-dialogfragment
     */
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final EditText title = new EditText(getActivity());
		final StoryFragmentListActivity callingActivity = (StoryFragmentListActivity) getActivity();
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.add_fragment_title);
		builder.setView(title);
		builder.setPositiveButton(R.string.set, new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int id) {
		    	String value = title.getText().toString();
		    	if (value.equals("")) {
		    		SimpleWarningDialog.getWarningDialog(callingActivity.getString(R.string.bad_frag_title_msg), callingActivity);
		    		callingActivity.onUserSelectValue(null);
		    	} else {
			        callingActivity.onUserSelectValue(value);
			        dialog.dismiss();
		    	}
		    }
		})
		.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int id) {
			// User cancelled the dialog
		    }
		});
		// Create the AlertDialog object and return it
		return builder.create();
    }
	
	/**
	 * AlertDialog http://developer.android.com/guide/topics/ui/dialogs.html
	 * Method shows an alertDialog when the user selects the "Done" button but
	 * the required text field has no text input
	 */

	private void alertDialog() {
		// Instantiate an AlertDialog.Builder with its constructor
		AlertDialog.Builder builder = new AlertDialog.Builder(
				getActivity());
		// Chain together various setter methods to set the dialog
		// characteristics
		builder.setMessage(R.string.bad_frag_title_msg).setTitle(
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
