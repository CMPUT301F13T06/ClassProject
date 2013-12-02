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
		return gif;
	}

	private class GifView extends WebView {
		public GifView(Context context, String path) {
			super(context);
			loadUrl("file://"+path);
		}
	}
}
