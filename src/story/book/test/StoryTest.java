package story.book.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import story.book.DecisionBranch;
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
	
	@Test
	public void testDeepFragmentRemoval() {
		StoryFragment testFragment1 = new StoryFragment("Test fragment 1");
		StoryFragment testFragment2 = new StoryFragment("Test fragment 2");
		StoryFragment testFragment3 = new StoryFragment("Test fragment 3");
		
		testFragment1.addDecisionBranch(new DecisionBranch("Branch from 1 to 2", testFragment2));
		testFragment1.addDecisionBranch(new DecisionBranch("Branch from 1 to 3", testFragment3));
		
		story.addFragment(testFragment1);
		story.addFragment(testFragment2);
		story.addFragment(testFragment3);
		
		assertEquals(testFragment1.getDecisionBranches().size(), 2);
		story.removeFragment(testFragment2);
		assertEquals(testFragment1.getDecisionBranches().size(), 1);
		assertEquals(testFragment1.getDecisionBranches().get(0).getDestination(), testFragment3);
		story.removeFragment(testFragment3);
		assertEquals(testFragment1.getDecisionBranches().size(), 0);
	}

}
