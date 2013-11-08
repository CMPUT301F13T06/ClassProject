package story.book.test;

import org.junit.Test;

import story.book.Dashboard;
import story.book.TextIllustration;
import android.test.ActivityInstrumentationTestCase2;

public class TextIllustrationTest extends ActivityInstrumentationTestCase2
		<story.book.Dashboard> {
	
	private TextIllustration textIllustration;
	
	public TextIllustrationTest() {
		super(Dashboard.class);
		textIllustration = new TextIllustration("Test illustration String");
	}
	
	@Test
	public void testCreation() {
		assertNotNull(textIllustration);
	}
	
	@Test
	public void testGetContent() {
		assertEquals(textIllustration.getContent(), 
				"Test illustration String");
	}

	@Test
	public void testSetContent() {
		textIllustration.setContent("New test String");
		assertEquals(textIllustration.getContent(), "New test String");
	}
	
	@Test
	public void testGetView() {
		assertNotNull(textIllustration.getView());
	}
	
}
