package story.book.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import story.book.model.Annotation;
import story.book.model.ImageIllustration;
import story.book.model.TextIllustration;
import story.book.view.Dashboard;
import story.book.view.R;
import story.book.view.StoryApplication;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Environment;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ActivityTestCase;

public class ImageIllustrationTest extends ActivityTestCase  {

	Uri sampleImageUri;
	Uri sampleImageUri2;
	File sampleImageFile;
	File sampleDir;
	ImageIllustration imageIllustration;
	
	@Test
	public void testImageIllustrationTest() {

	    sampleDir = new File(Environment.getExternalStorageDirectory()
		         + File.separator
		         + "testtempdump"
		         + File.separator);
	    sampleDir.mkdirs();
		
	    sampleImageFile = new File(Environment.getExternalStorageDirectory()
	         + File.separator
	         + "testtempdump"
	         + File.separator
	         + "notreallya.png");
	    try {
			sampleImageFile.createNewFile();
		} catch (IOException e) {
			assertTrue(false);
			e.printStackTrace();
		}
	    sampleImageUri = Uri.parse("/sdcard/testtempdump/notreallya.png");
	    sampleImageUri2 = Uri.parse("/sdcard/testtempdump/notreallya2.png");
	    
	    Bitmap icon = BitmapFactory.decodeResource(getInstrumentation().getTargetContext().getResources(),
	    		R.drawable.ic_search);
	    try {
			icon.compress(CompressFormat.JPEG, 40, new FileOutputStream(sampleImageUri.getPath()));
		} catch (FileNotFoundException e) {
			assertTrue(false);
			e.printStackTrace();
		}
	    
	    imageIllustration = new ImageIllustration(sampleImageUri, sampleImageUri);
	    // test creation
	    assertNotNull(imageIllustration);
	    
	    // test get content
	    assertEquals(imageIllustration.getContent(), sampleImageUri.getLastPathSegment());
	    
	    // test get view
	    assertNotNull(imageIllustration.getView("/sdcard/testtempdump/", false, getInstrumentation().getTargetContext()));
	    
	    // test set content
	    try {
			icon.compress(CompressFormat.JPEG, 40, new FileOutputStream(sampleImageUri2.getPath()));
		} catch (FileNotFoundException e) {
			assertTrue(false);
			e.printStackTrace();
		}
	    imageIllustration.setContent(sampleImageUri2.getPath());
		assertEquals(imageIllustration.getContent(), sampleImageUri2.getPath());
		
		// Clean up
		try {
			FileUtils.deleteDirectory(sampleDir);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
