/**
 * 
 */
package story.book;

import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * @author jsurya
 * 
 */
public class AddDecisionBranch extends Fragment {
	private ActionBar actionBar;
	private LinearLayout list;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.onCreate(savedInstanceState);
		final View rootView = inflater.inflate(R.layout.add_decision_branch_fragment, container, false);
		actionBar.setTitle(R.string.FragmentTitle);
		
		
		list = (LinearLayout)this.getView().findViewById(R.id.allDecisionBranches);
		return rootView;	
	}
	
}
