package story.book.test;

import org.junit.Test;

import story.book.model.AudioIllustration;
import story.book.view.Dashboard;
import android.test.ActivityInstrumentationTestCase2;

public class AudioIllustrationTest extends ActivityInstrumentationTestCase2
<story.book.view.Dashboard> {

	private AudioIllustration audioIllustration;
	
	public AudioIllustrationTest() {
		super(Dashboard.class);
		audioIllustration = new AudioIllustration(null);
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
