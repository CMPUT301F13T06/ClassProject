package story.book.test;

import org.junit.Test;

import story.book.model.TextIllustration;
import story.book.view.Dashboard;
import android.test.ActivityInstrumentationTestCase2;

public class TextIllustrationTest extends ActivityInstrumentationTestCase2
		<story.book.view.Dashboard> {
	
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
		assertNotNull(textIllustration.getView(false));
	}
	
}
