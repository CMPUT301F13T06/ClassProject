/**
 * 
 */
package story.book;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * @author jsurya
 * @author anthony
 */
public class AddDecisionBranch extends DialogFragment {
    
    /**
     * http://developer.android.com/guide/topics/ui/dialogs.html
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	builder.setTitle("Set Decision Branch Label");
	builder.setMessage("Set decision")
	.setPositiveButton("Set", new DialogInterface.OnClickListener() {
	    public void onClick(DialogInterface dialog, int id) {
		//TODO setting the decision
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
