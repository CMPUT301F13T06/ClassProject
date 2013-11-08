package story.book;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.support.v4.app.NavUtils;

/**
 * An activity that displays the stories stored on the device
 * 
 * @author Nancy Pham-Nguyen
 * @author Anthony Ou
 */
public class OnlineStoriesActivity extends Activity implements StoryView<Story>{

    private StoryController onlineController;
    ArrayList<StoryInfo> storyInfo;
    ListView listView;

    protected ArrayAdapter<StoryInfo> adapter;

    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.library_activity);

	onlineController = new OnlineStoryController();

	adapter = new ArrayAdapter<StoryInfo>(this, android.R.layout.simple_list_item_1, onlineController.getStoryList());
	listView = (ListView) findViewById(R.id.listView);
	registerForContextMenu(listView);

	listView.setAdapter(adapter);

	listView.setOnItemClickListener(new OnItemClickListener() {
	    @Override
	    public void  onItemClick(AdapterView<?> parent , View view,
		    int pos, long id) {
		position = pos;
	    }
	});
	// Show the Up button in the action bar.
	setupActionBar();
    }

    @Override
    public void onResume() {
	super.onResume();
	adapter.clear();
	adapter.addAll(onlineController.getStoryList());
    }

    /**
     * http://developer.android.com/guide/topics/ui/menus.html#context-menu
     */

    @Override
    public boolean onContextItemSelected(MenuItem item) {
	onlineController.getStory(adapter.getItem(position).getSID());
	Intent intent = new Intent(this, StoryInfoActivity.class);
	intent.putExtra("calledByOnline", true);
	startActivity(intent);
	return true;
    }


    /**
     * Set up the {@link android.app.ActionBar}.
     */
    private void setupActionBar() {

	getActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.online_stories, menu);
	return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
	switch (item.getItemId()) {
	case R.id.action_search:
	    openSearch();
	case R.id.title_activity_dashboard:
	    NavUtils.navigateUpFromSameTask(this);
	    finish();
	    return true;
	}
	return super.onOptionsItemSelected(item);
    }

    private void openSearch() {
	// TODO Auto-generated method stub

    }

    @Override
    public void update(Story model) {
	// TODO Auto-generated method stub

    }

}
