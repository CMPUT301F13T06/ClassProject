package story.book.test;

import org.junit.Test;

import story.book.model.VideoIllustration;
import story.book.view.Dashboard;
import android.net.Uri;
import android.test.ActivityInstrumentationTestCase2;

public class VideoIllustrationTest extends ActivityInstrumentationTestCase2
<story.book.view.Dashboard> {

	private VideoIllustration videoIllustration;
	
	public VideoIllustrationTest() {
		super(Dashboard.class);
		videoIllustration = new VideoIllustration(Uri.parse("http://www.google.com"));
	}
	
	@Test
	public void testCreation() {
		assertNotNull(videoIllustration);
	}
	
	@Test
	public void testGetContent() {
		assertEquals(videoIllustration.getContent(), Uri.parse("http://www.google.com").getLastPathSegment());
	}
	
	@Test
	public void testSetContent() {
		videoIllustration.setContent("http://www.google.ca");
		assertEquals(videoIllustration.getContent(), "http://www.google.ca");
	}
	
	@Test
	public void testGetView() {
		assertNotNull(videoIllustration.getView(null, false, getActivity()));
	}

}
