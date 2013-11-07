/**
 * 
 */
package story.book;

import java.util.ArrayList;
import java.util.Iterator;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * StoryFragmentEditActivity is the interface users can make changes
 * to illustrations contained in the story fragment which is currently
 * open. 
 * 
 * @author jsurya
 *
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class StoryFragmentEditActivity extends FragmentActivity implements StoryView<StoryFragment> {
	ActionBar actionBar;
	
	StoryFragment SF;
	FragmentCreationController FCC;
	ArrayList<StoryFragment> SFL;
	ArrayAdapter<StoryFragment> adapter;
	StoryCreationController SCC;
	StoryApplication SA;
	
	ArrayList<Illustration> illustrations;
	ArrayList<DecisionBranch> decisions;
	ArrayList<Button> buttons;
	
	//storyFragment passed as intent from FragmentList
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reading_fragment);
		
		SCC = new StoryCreationController();
		
		SFL = SCC.getFragments();
		SA = new StoryApplication();
		SF = SFL.get(0);
		
		FCC = new FragmentCreationController(SF);
		String title = SF.getFragmentTitle();
		
		actionBar = getActionBar();
		actionBar.setTitle(title);
		
		SF.addView(this);
		
	}
	
	@Override
	public void onStart() {
		super.onStart();
		illustrations = SF.getIllustrations();
		decisions = SF.getDecisionBranches();

		ArrayList<View> illustrationViews = new ArrayList<View>();

		for (Illustration<?> i : illustrations){
			illustrationViews.add(i.getView());
		}

		formatView(illustrationViews);
		LayoutParams p = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT); 
		for (View t: illustrationViews) {
			this.addContentView(t, p);
		}

		buttons = formatButton(decisions, this);
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, 
				LayoutParams.WRAP_CONTENT);
		
		//TODO: Fix the overlap of illustrations
		lp.addRule(RelativeLayout.ALIGN_BOTTOM);
		lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

		for (Button dbButton : buttons) {
			this.addContentView(dbButton, lp);
		}

	}

	@Override
	public void onPause(){
		super.onPause();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.add_illustration_menu, menu);

		inflater.inflate(R.menu.standard_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.text:
			// TODO: Pass illustration to fragment for editing

			return true;
			
		case R.id.take_photo:

			return true;
		case R.id.addGalleryPhoto:

			return true;
		case R.id.addDecisionBranch:

			return true;

		case R.id.audio:

			return true;

		case R.id.video:

			return true;

		case R.id.record_video:

			return true;
		case R.id.title_activity_dashboard:
			Intent intent = new Intent(this, Dashboard.class);
			startActivity(intent);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
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

	private ArrayList<Button> formatButton(ArrayList<DecisionBranch> db, Context c) {

		ArrayList<Button> buttonList = new ArrayList<Button>();

		Iterator<DecisionBranch> dbIterator = db.iterator();
		DecisionBranch d = null;
		Button button;
		while(dbIterator.hasNext()) {

			d = dbIterator.next();
			button = new Button(c);
			button.setText(d.getDecisionText());
			buttonList.add(button);
		}

		return buttonList;
	}

	@Override
	public void update(StoryFragment model) {
		// TODO Auto-generated method stub
		//display fragment contents
		
	}
}
