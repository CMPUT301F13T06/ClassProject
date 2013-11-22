package story.book.test;

import org.junit.Test;

import story.book.model.Annotation;
import story.book.model.TextIllustration;
import story.book.view.Dashboard;
import android.test.ActivityInstrumentationTestCase2;

public class AnnotationTest extends ActivityInstrumentationTestCase2
<story.book.view.Dashboard> {

	private Annotation annotation;
	
	public AnnotationTest() {
		super(Dashboard.class);
		annotation = new Annotation("Zero", new TextIllustration("test text"));
	}
	
	@Test
	public void testCreation() {
		assertNotNull(annotation);
	}
	
	@Test
	public void testGetAuthor() {
		assertEquals(annotation.getAuthor(), "Zero");
	}

	@Test
	public void testGetIllustration() {
		assertNotNull(annotation.getIllustration());
		assertEquals(annotation.getIllustration().getContent(), "Zero");
	}
	
	
}