package story.book.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import story.book.Story;
import story.book.StoryFragment;
import story.book.StoryInfo;

public class StoryTest {

	private Story story;
	
	@Before
	public void setUp() throws Exception {
		story = new Story(new StoryInfo());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreation() {
		assertEquals(story.getStoryFragments().size(), 0);
	}
	
	@Test
	public void testAddRemoveFragment() {
		StoryFragment testFragment = new StoryFragment("Test fragment 1");
		story.addFragment(testFragment);
		assertEquals(story.getStoryFragments().size(), 1);
		assertEquals(story.getStoryFragments().get(0), testFragment);
		story.removeFragment(testFragment);
		assertTrue(story.getStoryFragments().isEmpty());
	}

}
