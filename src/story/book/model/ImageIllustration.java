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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import story.book.view.StoryApplication;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.view.DragEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.widget.ImageView;

public class ImageIllustration extends Illustration<Uri> {

	private Uri content;
	
	/**
	 * The picture at the specified location will be formated here 
	 * for reduced size and correct orrientation.
	 * @param content
	 * 
	 * @author Anthony Ou
	 * 
	 */
	public ImageIllustration(Uri content) {
		super();
		setContent(content);
		Bitmap bmp =  BitmapFactory.decodeFile(content.getPath());
		try {
			Matrix matrix = new Matrix();
			matrix.postRotate(90);
			Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
			bmp.compress(CompressFormat.JPEG, 0, new FileOutputStream(content.getPath()));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Uri getContent() {
		// TODO Auto-generated method stub
		return content;
	}
	
	public void setContent(Uri content) {
		this.content = content;
	}

	/**
	 * return an ImageView 
	 */
	public View getView(Boolean editMode) {
		
		ImageView a = new ImageView(StoryApplication.getContext());
		a.setImageURI(content);
		a.setAdjustViewBounds(true);
		a.setOnDragListener(new OnDragListener() {
			
			@Override
			public boolean onDrag(View v, DragEvent event) {
				// TODO Auto-generated method stub
				((ImageView)v).setMaxHeight((int) event.getX());
				((ImageView)v).setMaxWidth((int) event.getY());
				return false;
			}
		});

		return a;
	}

	@Override
	public void deleteContent() {	
		new File(content.getPath()).delete();
	}
}
