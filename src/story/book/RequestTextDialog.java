package story.book;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;

/** 
 * RequestTextDialog is a DialogFragment to get some text
 * and return it to its calling activity. The calling
 * activity must implement the RequstingActivity interface.
 *
 * @author Vina Nguyen
 * 
 * @see RequestingActivity
 */

public class RequestTextDialog extends DialogFragment {
	
	private RequestingActivity parentActivity;
	private String header = "";
	private String warning = "";
	
	public void setParent(RequestingActivity a) {
		parentActivity = a;
	}
	
	public void setHeader(String h) {
		header = h;
	}
	
	public void setWarning(String w) {
		warning = w;
	}
	
    /**
     * Ref:
     * http://developer.android.com/guide/topics/ui/dialogs.html,
     * http://stackoverflow.com/questions/12622742/get-value-from-dialogfragment
     */
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final EditText title = new EditText(getActivity());
		final RequestingActivity callingActivity = parentActivity;
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(header);
		builder.setView(title);
		builder.setPositiveButton(R.string.set, new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int id) {
		    	
		    	String value = title.getText().toString();
		    	
		    	// Return text if non-empty, other-wise prompt warning
		    	if (value.equals("")) {
		    		SimpleWarningDialog.getWarningDialog(warning, ((Activity)callingActivity));
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
