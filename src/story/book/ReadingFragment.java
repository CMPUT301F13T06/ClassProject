/**
 * 
 */
package story.book;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * ReadingFragment is the interface users can use to read story
 * fragments. If the current fragment being read is not an ending 
 * fragment, the end of the page will have buttons which 
 * will allow users to pick a <code>DecisionBranch</code>
 * progress to the following story fragment in the story.
 * 
 * @author jsurya
 *
 */
public class ReadingFragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.reading_fragment, container, false);
		return rootView;
	}
}
