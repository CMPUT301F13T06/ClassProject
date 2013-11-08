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
package story.book;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

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
}
