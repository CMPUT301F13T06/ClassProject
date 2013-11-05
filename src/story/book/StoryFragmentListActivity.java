/**
 * 
 */
package story.book;

import android.app.Activity;
import android.os.Bundle;

/**
 * StoryFragmentListActivity displays all story fragments contained
 * in the story that is currently open.
 * 
 * @author Jessica Surya
 *
 */
public class StoryFragmentListActivity extends Activity {
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.story_fragment_read_activity);
		
	}
}
