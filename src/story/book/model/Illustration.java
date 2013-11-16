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

package story.book.model;

import android.view.View;

/**
 * Base class for Illustrations. Extend to implement different types of
 * Illustrations for different forms of media.
 * 
 * @author Alexander Cheung
 * @author Anthony Ou
 *
 * @param <T> the type of the content to be held by the Illustration.
 */
public abstract class Illustration<T> {
	
	
	/**
	 * Gets the content of the Illustration.
	 * @return T the content of the Illustration.
	 */
	public abstract T getContent();
	
	/**
	 * Sets the content of the Illustration to object of type T.
	 * @param content the content to be stored in the Illustration, of type T.
	 */
	public abstract void setContent(T content);
	
	/**
	 * Returns the contents of the illustration in a View object for Activities
	 * to display.
	 * XXX: Needs review; should use generic types
	 * 
	 * @return the View object containing the contents of the illustration
	 */
	public abstract View getView();
	
	public abstract void deleteContent();
}
