/**
 * 
 */
package story.book;

import java.util.ArrayList;
import java.util.Iterator;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
@TargetApi(Build.VERSION_CODES.JELLY_BEAN) 
public class ReadingFragment extends Fragment {
	StoryReadController SRC;
	StoryFragment SF;
	ArrayList<Illustration> illustrations;
	View rootView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.reading_fragment, container, false);
		SRC = new StoryReadController();
		SF = SRC.getStartingFragment();
		illustrations = SF.getIllustrations();

		ArrayList<View> illustrationViews = new ArrayList<View>();

		for (Illustration<?> i : illustrations){
			illustrationViews.add(i.getView());
		}

		formatView(illustrationViews);

		for (View t: illustrationViews){
			((ViewGroup) rootView).addView(t);
		}
		
		return rootView;

	}

	private void formatView(ArrayList<View> v) {
		Iterator<View> viewIterator = v.iterator();
		TextView x = null;
		while(viewIterator.hasNext()) {
			x = (TextView) viewIterator.next();
			x.setTextSize(20);
			x.setTextColor(Color.BLACK);
			x.setPaddingRelative(5, 0, 0, 0);
		}
	}
}