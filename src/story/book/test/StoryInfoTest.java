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
		assertEquals(storyInfo.getAuthor(), "");
	}
	
	@Test
	public void testSetAuthor() {
		storyInfo.setAuthor("test author");
		assertEquals(storyInfo.getAuthor(), "test author");
	}
	
	@Test
	public void testGetTitle() {
		assertEquals(storyInfo.getTitle(), "");
	}
	
	@Test
	public void testSetTitle() {
		storyInfo.setTitle("test title");
		assertEquals(storyInfo.getTitle(), "test title");
	}

	@Test
	public void testGetGenre() {
		assertEquals(storyInfo.getGenre(), "");
	}
	
	@Test
	public void testSetGenre() {
		storyInfo.setGenre("test genre");
		assertEquals(storyInfo.getGenre(), "test genre");
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
