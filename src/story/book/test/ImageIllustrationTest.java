package story.book.test;

import java.io.File;

import org.junit.Test;

import story.book.model.Annotation;
import story.book.model.ImageIllustration;
import story.book.model.TextIllustration;
import story.book.view.Dashboard;
import android.net.Uri;
import android.os.Environment;
import android.test.ActivityInstrumentationTestCase2;

public class ImageIllustrationTest extends ActivityInstrumentationTestCase2
<story.book.view.Dashboard> {

	Uri sampleImageUri;
	File sampleImageFile;
	ImageIllustration imageIllustration;
	
	// Errors exist in this test related to the fact that ImageIllustration is
	// tightly coupled by many dependencies related to system io
	public ImageIllustrationTest() {
		super(Dashboard.class);

	    sampleImageFile = new File(Environment.getExternalStorageDirectory()
	         + File.separator
	         + "testtempdump"
	         + File.separator
	         + "notreallya.png");
	    sampleImageFile.mkdirs();
	    sampleImageUri = Uri.parse("/sdcard/testtempdump/notreallya.png");
	    
	    imageIllustration = new ImageIllustration(sampleImageUri, sampleImageUri);
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
