package story.book.test;

import org.junit.Test;

import story.book.model.AudioIllustration;
import story.book.view.Dashboard;
import android.net.Uri;
import android.test.ActivityInstrumentationTestCase2;

public class AudioIllustrationTest extends ActivityInstrumentationTestCase2
<story.book.view.Dashboard> {

	private AudioIllustration audioIllustration;
	
	public AudioIllustrationTest() {
		super(Dashboard.class);
		audioIllustration = new AudioIllustration(Uri.parse("http://www.google.com"));
	}
	
	@Test
	public void testCreation() {
		assertNotNull(audioIllustration);
	}
	
	@Test
	public void testGetContent() {
		assertEquals(audioIllustration.getContent(), Uri.parse("http://www.google.com").getLastPathSegment());
	}
	
	@Test
	public void testSetContent() {
		audioIllustration.setContent("http://www.google.ca");
		assertEquals(audioIllustration.getContent(), "http://www.google.ca");
	}
	
	@Test
	public void testGetView() {
		assertNotNull(audioIllustration.getView(null, false, getActivity()));
	}

}
