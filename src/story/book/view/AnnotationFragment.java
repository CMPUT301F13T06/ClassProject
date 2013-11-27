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

import java.io.File;
import java.util.ArrayList;

import story.book.controller.FragmentCreationController;
import story.book.controller.StoryInfoController;
import story.book.controller.StoryReadController;
import story.book.model.Annotation;
import story.book.model.AudioIllustration;
import story.book.model.ImageIllustration;
import story.book.model.StoryFragment;
import story.book.model.StoryInfo;
import story.book.model.VideoIllustration;
import story.book.model.StoryInfo.PublishState;
import android.annotation.TargetApi;
import android.app.ActionBar.LayoutParams;
import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

/**
 * Annotation fragment is the right tab in <code>StoryFragmentReadActivity</code> 
 * This is the view for displaying all image annotations posted by users on the 
 * currently open in <code>Reading Fragment</code> (reading tab)
 * 
 * @author Jessica Surya
 * @author Anthony Ou
 */
@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
public class AnnotationFragment extends Fragment implements StoryView {
	StoryReadController SRC;
	StoryFragment SF;
	ArrayList<Pair<ArrayList<View>, Annotation>> annotationList;
	View rootView;
	int nextFragmentID;
	FragmentCreationController FCC;

	String author;
	Boolean editMode = true;

	Boolean canAnnotate = false;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		rootView = inflater.inflate(R.layout.annotation_fragment, container, false);

		author = StoryApplication.getNickname();
		SRC = ((StoryFragmentReadActivity)this.getActivity()).getController();
		SF = SRC.getStartingFragment();
		FCC = new FragmentCreationController(SF.getFragmentID());
		
		StoryInfoController SIC = new StoryInfoController();
		StoryInfo info = SIC.getStoryInfo();
		if (info.getPublishState() == PublishState.PUBLISHED) {
			canAnnotate = true;
		}
		
		annotationList = new ArrayList<Pair<ArrayList<View>, Annotation>>();
		SF.addView(this);
		getFragmentAnnotations();
		loadAnnotations();
		displayAnnotations();

		if (savedInstanceState != null && savedInstanceState.containsKey("cameraImageUri")) {
			auri = Uri.parse(savedInstanceState.getString("cameraImageUri"));
		}
		if(auri != null)
			Log.v("some uri", auri.toString());
		return rootView;

	}

	@Override
	public void onResume() {
		super.onResume();
		int readingFragmentID = ((StoryFragmentReadActivity) getActivity()).getFragmentID();
		if (readingFragmentID != SF.getFragmentID()) {
			getFragmentAnnotations();
			loadAnnotations();
		}
		displayAnnotations();
	}

	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// Inflate the menu items for use in the action bar
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.add_annotation_menu, menu);
	}

	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (auri != null) {
			outState.putString("cameraImageUri", auri.toString());
		}
	}
	
	Uri auri;
	private enum Actions {PHOTO, VIDEO, GALLERY, VIDEOPICK}
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		getFragmentAnnotations();
		loadAnnotations();
		switch (item.getItemId()) {
		
		case R.id.text:
			addNewTextAnnotation();
			return true;
		case R.id.take_photo:
			Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			auri = FCC.getFreeUri(".jpg");
			i.putExtra(MediaStore.EXTRA_OUTPUT, auri);
			startActivityForResult(i, Actions.PHOTO.ordinal());
			return true;
		case R.id.addGalleryPhoto:
			i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
			startActivityForResult(i, Actions.GALLERY.ordinal());
			return true;
		case R.id.audio:
			AudioIllustration audio = new AudioIllustration(FCC.getFreeUri(".3gp"));
			Annotation audioAnn = new Annotation(StoryApplication.getNickname(), audio);
			annotationList.add(new Pair<ArrayList<View>, Annotation>(
					audioAnn.getView(FCC.getStoryPath(), editMode, this.getActivity()), audioAnn));
			return true;
		case R.id.video:
			i = new Intent(Intent.ACTION_PICK,
					android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
			i.setType("video/*");
			startActivityForResult(i, Actions.VIDEOPICK.ordinal());
			return true;
		case R.id.record_video:
			i = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
			i.putExtra(MediaStore.EXTRA_OUTPUT, FCC.getFreeUri(".mp4"));
			i.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
			startActivityForResult(i, Actions.VIDEO.ordinal());
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/**
	 * loadAnnotations() loads annotation views from the current story fragment
	 */
	private void loadAnnotations() {
		ArrayList<Annotation> annotations = SF.getAnnotations();

		annotationList.clear();
		for (Annotation i : annotations) {
			annotationList.add(new Pair<ArrayList<View>, Annotation>(i.getView(SRC.getStoryPath(), false, this.getActivity()), i));
		}
	}

	/**
	 * displayAnnotations() displays all annotations as views 
	 * by getting them from the corresponding fragment 
	 * 
	 * @param StoryFragment 	StoryFragment object
	 * @param View	 where annotations will be displayed
	 */
	private void displayAnnotations() {
		RelativeLayout layout = (RelativeLayout) rootView.findViewById(R.id.annotation_fragment);

		int position = 0;
		if (annotationList.isEmpty() == false) {
			// Display illustrations
			((ViewGroup) layout).removeAllViews();
			for (Pair <ArrayList<View>, Annotation> t: annotationList) {
				for (View v : t.first) {
					v.setId(position + 1);
					RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(LayoutParams.
							MATCH_PARENT,LayoutParams.WRAP_CONTENT); 
					p.addRule(RelativeLayout.BELOW, position);
					v.setLayoutParams(p);
					((ViewGroup) layout).addView(v, p);
					position++;
				}
			}
			if(annotationList.size() > SF.getAnnotations().size()) {
				showPostButton(position);
			}
		}
		
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		Annotation ann;
		if(resultCode == android.app.Activity.RESULT_OK ) {
			if(requestCode == Actions.PHOTO.ordinal()) {
				ImageIllustration image = new ImageIllustration(auri);
				ann = new Annotation(StoryApplication.getNickname(), image);
				annotationList.add(
						new Pair<ArrayList<View>, Annotation>(ann.getView(FCC.getStoryPath(), editMode, this.getActivity()), 
								ann));
			}
			if(requestCode == Actions.VIDEO.ordinal()) {
				VideoIllustration video = new VideoIllustration(auri);
				ann = new Annotation(StoryApplication.getNickname(), video);
				annotationList.add(
						new Pair<ArrayList<View>, Annotation>(ann.getView(FCC.getStoryPath(), editMode, this.getActivity()), 
								ann));
			}
			if(requestCode == Actions.GALLERY.ordinal()) {
				File f;
				Cursor cursor = this.getActivity().getContentResolver().query(data.getData(), null, null, null, null);
				if (cursor == null) { // Source is Dropbox or other similar local file path
					f = new File((data.getData().getPath()));
				} else { 
					cursor.moveToFirst(); 
					int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
					f = new File(cursor.getString(idx));
				}
				ImageIllustration image = new ImageIllustration(Uri.fromFile(f), FCC.getFreeUri(".jpg"));
				ann = new Annotation(StoryApplication.getNickname(), image);
				annotationList.add(
						new Pair<ArrayList<View>, Annotation>(ann.getView(FCC.getStoryPath(), editMode, this.getActivity()), 
								ann));
			}
			if(requestCode == Actions.VIDEOPICK.ordinal()) {
				File f;
				Cursor cursor = this.getActivity().getContentResolver().query(data.getData(), null, null, null, null);
				if (cursor == null) { // Source is Dropbox or other similar local file path
					f = new File((data.getData().getPath()));
				} else { 
					cursor.moveToFirst(); 
					int idx = cursor.getColumnIndex(MediaStore.Video.VideoColumns.DATA);
					f = new File(cursor.getString(idx));
				}
				VideoIllustration video = new VideoIllustration(Uri.fromFile(f), FCC.getFreeUri(".mp4"));
				ann = new Annotation(StoryApplication.getNickname(), video);
				annotationList.add(
						new Pair<ArrayList<View>, Annotation>(ann.getView(FCC.getStoryPath(), editMode, this.getActivity()), 
								ann));
			}
			displayAnnotations();
		}
	}


	/**
	 * addNewTextIllustration() creates a new EditText for users to enter the text
	 * for a TextIllustration.
	 */
	private void addNewTextAnnotation() {
		Annotation Ann = new Annotation(StoryApplication.getNickname(), null);
		annotationList.add(new Pair<ArrayList<View>, Annotation>(Ann.getView(null, true, this.getActivity()), Ann));
		displayAnnotations();
	}

	/**
	 * Loads the annotations for the fragment; used after a decision branch
	 * has been selected in the <code>ReadingFragment</code>
	 */
	public void getFragmentAnnotations() {
		SF.deleteView(this);
		int readingFragmentID = ((StoryFragmentReadActivity) getActivity()).getFragmentID();
		SF = SRC.getStoryFragment(readingFragmentID);
		FCC = new FragmentCreationController(SF.getFragmentID());
		SF.addView(this);
	}

	/**
	 * saveAnnotation() saves any new annotations
	 */
	public void saveAnnotations() {
		int top = annotationList.size();

		if(annotationList.size() > SF.getAnnotations().size()) {
			Pair<ArrayList<View>, Annotation> i = annotationList.get(top-1);
			String cap = ((EditText)i.first.get(i.first.size()-1)).getText().toString();
			Log.d("DEBUG: Text annotation to be saved", String.valueOf(cap));
			if (i.first.size() == 2 ) {
				// Saving a text caption
				if(cap.length() > 0) {
					// Set caption
					i.second.setCaption(cap);
					FCC.addAnnotation(i.second);
				}
			}
			else {
				i.second.setCaption(cap);
				FCC.addAnnotation(i.second);
			}
		}
	}

	/**
	 * update(SF); redisplays annotations to show changes to model
	 */
	@Override
	public void update(Object model) {
		Log.d(String.valueOf(model), "DEBUG: Updated");
		getFragmentAnnotations();
		loadAnnotations();
		displayAnnotations();
	}
	
	/**
	 *Displays a "Post" button which will save the annotation 
	 *
	 * @param position of object which Post Button is to appear below of
	 */
	private void showPostButton(int position) {
		RelativeLayout layout = (RelativeLayout) rootView.findViewById(R.id.annotation_fragment);
		Button post = new Button(this.getActivity());
		post.setText("Post");
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, 
				LayoutParams.WRAP_CONTENT);
	
		lp.setMargins(0, 10, 0, 0);
		lp.addRule(RelativeLayout.BELOW, position);
		post.setLayoutParams(lp);
	
		post.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (StoryApplication.checkInternetConnected()) {
					saveAnnotations();
					displayAnnotations();
				} else {
					Toast.makeText(StoryApplication.getContext(), R.string.no_internet_annotation, Toast.LENGTH_SHORT).show();
				}
				
			}
		});
		
		final ScrollView scroll = (ScrollView) rootView.findViewById(R.id.fragment_scrollable);
		scroll.post(new Runnable() {            
		    @Override
		    public void run() {
		           scroll.fullScroll(View.FOCUS_DOWN);              
		    }
		});
		((ViewGroup) layout).addView(post, lp);
	}
}
