/* CMPUT301F13T06-Adventure Club: A choose-your-own-adventure story platform
 * Copyright (C) 2013 Alexander Cheung, Jessica Surya, Vina Nguyen, Anthony Ou,
 * Nancy Pham-Nguyen
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package story.book.view;

import java.util.ArrayList;

import story.book.controller.StoryReadController;
import story.book.model.DecisionBranch;
import story.book.model.Illustration;
import story.book.model.StoryFragment;
import story.book.model.TextIllustration;
import story.book.view.R;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * Annotation fragment is the right tab in <code>StoryFragmentReadActivity</code> 
 * This is the view for displaying all image annotations posted by users on the 
 * currently open in <code>Reading Fragment</code> (reading tab)
 * 
 * @author Jessica Surya
 *
 */
@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
public class AnnotationFragment extends Fragment {
	StoryReadController SRC;
	StoryFragment SF;
	//	ArrayList<Annotation> annotation;
	ArrayList<DecisionBranch> decisions;
	ArrayList<Button> buttons;
	View rootView;
	int nextFragmentID;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		View rootView = inflater.inflate(R.layout.annotation_fragment, container, false);
		SRC = new StoryReadController();
		SF = SRC.getStartingFragment();
		displayAnnotations(SF, rootView);

		return rootView;

	}

	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// Inflate the menu items for use in the action bar
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.add_annotation_menu, menu);

	}

	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.fromCamera:
			return true;

		case R.id.fromGallery:

			return true;
		case R.id.add_new:
			Intent intent = new Intent();
			startActivity(intent);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}
	/**
	 * displayAnnotations() displays all annotations as views 
	 * by getting them from the corresponding fragment 
	 * 
	 * @param StoryFragment 	StoryFragment object
	 * @param View	 where annotations will be displayed
	 */
	private void displayAnnotations(StoryFragment SF, View rootView) {

		//		annotation = SF.getAnnotations();
		//
		//		ArrayList<View> AnnotationViews = new ArrayList<View>();
		//
		//		for (Annotation i : annotations){
		//			AnnotationViews.add(((TextIllustration)i).getView());
		//		}
		//
		//		int pos = 0;
		//
		//		for (View t: AnnotationViews){
		//			t.setId(pos + 1);
		//			RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(LayoutParams.
		//					WRAP_CONTENT,LayoutParams.WRAP_CONTENT); 
		//			p.addRule(RelativeLayout.BELOW, pos);
		//			t.setLayoutParams(p);
		//			((ViewGroup) rootView).addView(t, p);
		//			pos++;
		//		}

	}

	public void update() {
		((ViewGroup) this.getView()).removeAllViews();
		displayAnnotations(SF, rootView);
	}

	/*
	 * Loads the annotations for the next fragment (after a decision branch
	 * has been selected in the <code>ReadingFragment</code>
	 */
	public void getNextAnnotations() {
		nextFragmentID = ((StoryFragmentReadActivity) getActivity()).getNextFragmentID();
		SF = SRC.getStoryFragment(nextFragmentID);
		update();
	}
}
