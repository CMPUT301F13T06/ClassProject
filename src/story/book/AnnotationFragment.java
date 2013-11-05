/**
 * 
 */
package story.book;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.view.LayoutInflater;
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
		View rootView = inflater.inflate(R.layout.annotation_fragment, container, false);
		return rootView;
	}
}
