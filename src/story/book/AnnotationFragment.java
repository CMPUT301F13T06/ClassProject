/**
 * 
 */
package story.book;

import java.util.zip.Inflater;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Annotation fragment is the right tab in <code>StoryFragmentReadActivity</code> 
 * This is the view for displaying all image annotations posted by users on the 
 * currently open in <code>Reading Fragment</code> (reading tab)
 * 
 * @author jsurya
 *
 */
@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
public class AnnotationFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		View rootView = inflater.inflate(R.layout.annotation_fragment, container, false);
		
		return rootView;
	}
	
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	    // Inflate the menu items for use in the action bar
		
		inflater.inflate(R.menu.add_illustration_menu, menu);
		super.onCreateOptionsMenu(menu, inflater);
	   
	}
}
