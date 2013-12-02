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
import android.widget.SimpleAdapter;
import java.util.HashMap;

/**
 * Class that is responsible for getting and setting the simple adapter for
 * LocalStoriesActivity.java and OnlineStoriesActivity.java
 * 
 * @author Nancy Pham-Nguyen
 */

public class ListAdapter {
	private SimpleAdapter sAdapter;

	/**
	 * Get the simple adapter
	 * @return
	 */
	public SimpleAdapter getSAdapter() {
		return sAdapter;
	}

	/**
	 * Set the simple adapter
	 * @param sAdapter
	 */
	public void setSAdapter(SimpleAdapter sAdapter) {
		this.sAdapter = sAdapter;
	}

	/**
	 * This method gets the position of the item of the adapter
	 */
	public int getFromAdapter(int pos) {
		return Integer.parseInt(((HashMap<String, String>) sAdapter
				.getItem(pos)).get("SID"));
	}
}