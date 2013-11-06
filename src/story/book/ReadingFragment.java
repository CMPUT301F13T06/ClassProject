/**
 * 
 */
package story.book;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
	StoryReadController SRC;
	StoryFragment SF;
	ArrayList<Illustration> illustrations;
	ArrayAdapter<Illustration> adapter;
	View rootView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.reading_fragment, container, false);
		SRC = new StoryReadController();
		SF = SRC.getStartingFragment();
//	 	illustrations = SF.getIllustrations();
//		
//		adapter = new ArrayAdapter<Illustration>(rootView.getContext(), android.R.layout.simple_list_item_1,
//				illustrations);
//		
//		ListView listview = new ListView(rootView.getContext());
//
//		listview.setBackgroundColor(Color.WHITE);
//
//		listview.setAdapter(adapter);
		return rootView;
		
		
	}
	
}
