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

/**
 * @author jsurya
 *
 */
public class AddDecisionBranch extends Fragment {
	ActionBar actionBar;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.onCreate(savedInstanceState);
		final View rootView = inflater.inflate(R.layout.edit_text_illustration, container, false);
		
		actionBar.setTitle(R.string.FragmentTitle);
		return rootView;
		
	}
}
