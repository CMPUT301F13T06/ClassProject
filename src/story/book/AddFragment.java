package story.book;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;

/** 
 * AddFragment is a DialogFragment to get a title for a new fragment.
 * Its calling activity is StoryFragmentListActivity.	
 *
 * @author Vina Nguyen
 */

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
}
