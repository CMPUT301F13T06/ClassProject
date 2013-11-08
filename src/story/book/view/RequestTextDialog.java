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
 */

package story.book.view;

import story.book.view.R;
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
