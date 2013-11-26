package story.book.test;

import org.junit.Test;

import story.book.model.StoryInfo;
import story.book.view.Dashboard;
import android.test.ActivityInstrumentationTestCase2;

public class StoryInfoTest extends ActivityInstrumentationTestCase2
<story.book.view.Dashboard> {

	private StoryInfo storyInfo;
	
	public StoryInfoTest() {
		super(Dashboard.class);
		storyInfo = new StoryInfo();
	}
	
	@Test
	public void testCreation() {
		assertNotNull(storyInfo);
	}
	
	@Test
	public void testGetAuthor() {

	}
	
	@Test
	public void testSetAuthor() {
		
	}
	
	@Test
	public void testGetTitle() {
		
	}
	
	@Test
	public void testSetTitle() {
		
	}

	@Test
	public void testGetGenre() {
		
	}
	
	@Test
	public void testSetGenre() {
		
	}
	
	@Test
	public void testGetSynopsis() {
		
	}
	
	@Test
	public void testSetSynopsis() {
		
	}
	
	@Test
	public void testGetPublishDate() {
		
	}
	
	@Test
	public void testGetPublishDateString() {
		
	}
	
	@Test
	public void testSetPublishDate() {
		
	}
	
	@Test
	public void testGetStartingFragmentID() {
		
	}
	
	@Test
	public void testSetStartingFragmentID() {
		
	}
	
	@Test
	public void testGetSID() {
		
	}
	
	@Test
	public void testSetSID() {
		
	}
	
	@Test
	public void testGetPublishState() {
		
	}
	
	@Test
	public void testSetPublishState() {
		
	}
	
	@Test
	public void testToString() {
		
	}
}
