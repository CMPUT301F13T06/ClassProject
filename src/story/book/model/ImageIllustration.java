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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.view.DragEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.widget.ImageView;

public class ImageIllustration extends BinaryIllustration {
	
	/**
	 * The picture at the specified location will be formated here 
	 * for reduced size and correct orientation.
	 * 
	 * 
	 * @param content of the file name saved directly to the story folder.
	 * 
	 * @author Anthony Ou
	 * 
	 */
	public ImageIllustration(Uri content) {
		super();
		setContent(content.getLastPathSegment());
		save(content, content);
	}
	
	/**
	 * This is called when the path of the content is
	 * not in the correct story folder.
	 * 
	 * @param content
	 * @param savePath
	 */
	public ImageIllustration(Uri content, Uri savePath) {
		super();
		setContent(savePath.getLastPathSegment());
		save(content, savePath);

	}

	private void save(Uri givenPath, Uri savePath) {
		Bitmap bmp =  BitmapFactory.decodeFile(givenPath.getPath());
		try {
			if(bmp.getWidth() > bmp.getHeight()) {
				Matrix matrix = new Matrix();
				matrix.postRotate(90);
				Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
			}
			bmp.compress(CompressFormat.JPEG, 50, new FileOutputStream(givenPath.getPath()));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * return an ImageView 
	 */
	public View getView(String path, Boolean editMode, Context C) {
		
		ImageView a = new ImageView(C);
		a.setAdjustViewBounds(true);
		a.setImageURI(Uri.parse(path+content));
		a.setOnDragListener(new OnDragListener() {
			
			@Override
			public boolean onDrag(View v, DragEvent event) {
				// TODO Auto-generated method stub
				((ImageView)v).setMaxHeight((int) event.getX());
				((ImageView)v).setMaxWidth((int) event.getY());
				return false;
			}
		});
		a.setPadding(20, 10, 20, 10);
		return a;
	}
}
