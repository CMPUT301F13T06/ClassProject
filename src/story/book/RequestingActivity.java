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

/**
 * Classes that want to use the RequestTextDialog 
 * need to implement this interface to get the returned text.
 * 
 * @author Vina Nguyen
 * 
 * @see RequestTextDialog
 * 
 * @see StoryFragmentEditActivity
 * @see StoryFragmentListActivity
 * @see DecisionPickerActivity
 */

public interface RequestingActivity {
	public void onUserSelectValue(String title);
}
