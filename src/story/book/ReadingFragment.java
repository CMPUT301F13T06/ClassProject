/**
 * 
 */
package story.book;

import java.util.ArrayList;
import java.util.Iterator;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * ReadingFragment is the interface users can use to read story
 * fragments. If the current fragment being read is not an ending 
 * fragment, the end of the page will have buttons which 
 * will allow users to pick a <code>DecisionBranch</code>
 * progress to the following story fragment in the story.
 * 
 * @author Jessica Surya
 *
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1) 
public class ReadingFragment extends Fragment {
	StoryReadController SRC;
	StoryFragment SF;
	ArrayList<Illustration> illustrations;
	ArrayList<DecisionBranch> decisions;
	ArrayList<Button> buttons;
	View rootView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.reading_fragment, container, false);
		
		SRC = new StoryReadController();
		SF = SRC.getStartingFragment();
		displayFragment(SF, rootView);
		return rootView;
	}

	/**
	 * formatView() (FOR TEXTVIEWS ONLY) formats illustration textViews in an array list
	 * by changing:
	 * 		- text size (20)
	 * 		- text color (black)
	 * 		- padding on the left side
	 * 
	 * @param v
	 */
	private void formatView(ArrayList<View> v) {
		Iterator<View> viewIterator = v.iterator();
		TextView x = null;
		while(viewIterator.hasNext()) {
			x = (TextView) viewIterator.next();
			x.setTextSize(20);
			x.setTextColor(Color.BLACK);
			x.setPaddingRelative(5, 0, 0, 10);
		}
	}

	/**
	 * formatButton() creates a button with the corresponding decision branch text
	 * for each decision branch in an array list of decision branches.
	 * 
	 * This returns method an array list of buttons.
	 * 
	 * @param db
	 * @param c
	 * @return ArrayList<Button>
	 */
	private ArrayList<Button> formatButton(ArrayList<DecisionBranch> db, Context c) {

		ArrayList<Button> buttonList = new ArrayList<Button>();

		Iterator<DecisionBranch> dbIterator = db.iterator();
		DecisionBranch d = null;
		Button button;
		while(dbIterator.hasNext()) {

			d = dbIterator.next();
			button = new Button(c);
			button.setText(d.getDecisionText());
			button.setOnClickListener(setListener(button, d.getDestinationID()));
			buttonList.add(button);
		}

		return buttonList;
	}

	/**
	 * displayFragment() displays all text illustrations as views 
	 * and decision branches as buttons by getting them from the containing 
	 * fragment and formatting them by calling <code>formatView</code>
	 * and <code>formatButton</code> respectively.
	 * 
	 */
	private void displayFragment(StoryFragment SF, View rootView) {

		illustrations = SF.getIllustrations();
		decisions = SF.getDecisionBranches();

		ArrayList<View> illustrationViews = new ArrayList<View>();

		for (Illustration i : illustrations){
			illustrationViews.add(((TextIllustration)i).getView());
		}

		formatView(illustrationViews);
		int pos = 0;

		for (View t: illustrationViews){
			t.setId(pos + 1);
			RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(LayoutParams.
					WRAP_CONTENT,LayoutParams.WRAP_CONTENT); 
			p.addRule(RelativeLayout.BELOW, pos);
			t.setLayoutParams(p);
			((ViewGroup) rootView).addView(t, p);
			pos++;
		}

		
		buttons = formatButton(decisions, rootView.getContext());
		int buttonIndex = 0;
		for (Button dbButton : buttons) {
			dbButton.setId(pos + 1);
			buttonIndex++;
			RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, 
					LayoutParams.WRAP_CONTENT);
			if (buttonIndex == 1) {
				lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			}
			else {
			lp.addRule(RelativeLayout.ABOVE, pos);
			}
			dbButton.setLayoutParams(lp);
			((ViewGroup) rootView).addView(dbButton, lp);
			pos++;
		}

	}

	private View.OnClickListener setListener(final Button b, final int destinationID) {
		return new View.OnClickListener() {
			public void onClick(View v) {
				SF = SRC.getStoryFragment(destinationID);
				//TODO: set marquee :)
				((StoryFragmentReadActivity) getActivity()).setActionBarTitle(SF.getFragmentTitle());
				update();
			}
		};
	}

	public void update() {
		((ViewGroup) this.getView()).removeAllViews();
		displayFragment(SF, rootView);
	}
}