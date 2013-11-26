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

import java.util.ArrayList;

import android.R;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 
 * @author Jessica Surya
 * @author Anthony Ou
 *
 */
public class Annotation {
	
	private String author = "";
	private Illustration<?> illustration;
	private String caption = "";
	
	public Annotation(String author, Illustration<?> illustration) {
		this.author = author;
		this.illustration = illustration;
	}
	
	public String getAuthor() {
		return this.author;
	}
	
	public String getCaption() {
		return this.caption;
	}
	
	public void setCaption(String caption) {
		this.caption = caption;
	}
	
	public Illustration<?> getIllustration() {
		return illustration;
	}
	
	public ArrayList<View> getView(String path, Boolean editMode, Context C) {
		ArrayList<View> illus = new ArrayList<View>();
		// Author tag
		TextIllustration author = new TextIllustration(this.author);
		TextView t = (TextView) author.getView(null, false, C);
		t.setText(this.author + " posted : ");
		t.setTextSize(15);
		illus.add(t);
		// Illustration
		if (this.illustration != null) {
			illus.add(this.illustration.getView(path, editMode, C));
		}
		// Caption
		TextIllustration text = new TextIllustration(this.caption);
		if (editMode) {
			EditText v = (EditText) text.getView(null, editMode, C);
			v.setHint("Enter text here");
			illus.add(v);
		}
		else {
			TextView v = (TextView) text.getView(null, editMode, C);
			illus.add(v);
		}
		return illus;
	}
	
}
