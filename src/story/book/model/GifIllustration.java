package story.book.model;

import java.io.*;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.net.Uri;
import android.view.*;
import android.webkit.WebView;

import java.io.File;

import org.apache.commons.io.FileUtils;

public class GifIllustration extends Illustration<String>{

	String content;

	public GifIllustration(Uri path, Uri savePath) {
		super();
		setContent(savePath.getLastPathSegment());
		try{
			FileUtils.copyFile(new File(path.getPath()), new File(savePath.getPath()));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public String getContent() {
		// TODO Auto-generated method stub
		return content;
	}

	@Override
	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public View getView(String path, Boolean editMode, Context context) {
		GifView gif = null;
		try{
			gif = new GifView(context, new File(path+content));
		}
		catch(Exception e) {}
		return gif;
	}

	public class GifView extends View {
		private Movie mMovie;
		private long mMovieStart;
		private static final boolean DECODE_STREAM = true;
		private byte[] streamToBytes(InputStream is) {
			ByteArrayOutputStream os = new ByteArrayOutputStream(1024);
			byte[] buffer = new byte[1024];
			int len;
			try {
				while ((len = is.read(buffer)) >= 0) {
					os.write(buffer, 0, len);
				}
			} catch (java.io.IOException e) {
			}
			return os.toByteArray();
		}
		public GifView(Context context, File gif) throws FileNotFoundException {
			super(context);
			setFocusable(true);
			java.io.InputStream is;
			// YOUR GIF IMAGE Here
			is  = (new FileInputStream(gif)); 
			if (DECODE_STREAM) {
				mMovie = Movie.decodeStream(is);
			} else {
				byte[] array = streamToBytes(is);
				mMovie = Movie.decodeByteArray(array, 0, array.length);
			}
		}
		@Override
		public void onDraw(Canvas canvas) {
			long now = android.os.SystemClock.uptimeMillis();
			if (mMovieStart == 0) { // first time
				mMovieStart = now;
			}
			if (mMovie != null) {
				int dur = mMovie.duration();
				if (dur == 0) {
					dur = 3000;
				}
				int relTime = (int) ((now - mMovieStart) % dur);
				Log.d("", "real time :: " +relTime);
				mMovie.setTime(relTime);
				mMovie.draw(canvas, getWidth() - 200, getHeight()-200);
				invalidate();
			}
		}
	}

}
