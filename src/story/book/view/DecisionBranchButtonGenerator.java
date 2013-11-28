package story.book.view;

import java.util.ArrayList;
import java.util.Iterator;

import story.book.model.DecisionBranch;
import android.content.Context;
import android.widget.Button;

/**
 * This class creates a button with the corresponding decision branch text
 * for each decision branch in an array list of decision branches.
 * 
 * This returns method an array list of buttons.
 * 
 * @param DecisionBranch 	the decision branches associated with the fragment
 * @param Context 	the context where the button will be displayed
 * @return a custom ArrayList<Button> corresponding to the decision branches in a fragment
 * 
 * @author Jessica Surya
 */
public class DecisionBranchButtonGenerator {
	ArrayList<Button> buttonList;

	public ArrayList<Button> formatButton(ArrayList<DecisionBranch> db, Context c) {

		buttonList = new ArrayList<Button>();

		Iterator<DecisionBranch> dbIterator = db.iterator();
		DecisionBranch d = null;
		Button button;
		while(dbIterator.hasNext()) {
			d = dbIterator.next();
			button = new Button(c);
			button.setText(d.getDecisionText());
			buttonList.add(button);
		}
		return buttonList;
	}
}
