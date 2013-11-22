package story.book.test;

import org.junit.Test;

import story.book.view.Dashboard;
import android.test.ActivityInstrumentationTestCase2;

public class AnnotationTest extends ActivityInstrumentationTestCase2
<story.book.view.Dashboard> {

	public AnnotationTest() {
		super(Dashboard.class);
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}