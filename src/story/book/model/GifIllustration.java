package story.book.model;

import android.content.Context;
import android.view.View;
import android.net.Uri;
import android.webkit.WebView;

import java.io.File;

import org.apache.commons.io.FileUtils;

/**
 * Gif images come from here
 * 
 * @author Anthony Ou
 *
 */
public class GifIllustration extends BinaryIllustration{

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
	public View getView(String path, Boolean editMode, Context context) {
		GifView gif = new GifView(context, (path+content));
		gif.setDrawingCacheEnabled(true);
		return gif;
	}

	/**
	 * 
	 * source:
	 * http://droid-blog.net/2011/10/17/tutorial-how-to-play-animated-gifs-in-android-part-3/
	 *
	 */
	private class GifView extends WebView {
		public GifView(Context context, String path) {
			super(context);
			loadUrl("file://"+path);
		}
	}
}
