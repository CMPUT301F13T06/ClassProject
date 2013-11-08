package story.book.test;

import org.junit.After;
import org.junit.Test;

import android.test.ActivityInstrumentationTestCase2;
import story.book.model.DecisionBranch;
import story.book.model.Story;
import story.book.model.StoryFragment;
import story.book.model.StoryInfo;
import story.book.view.Dashboard;

public class StoryTest extends ActivityInstrumentationTestCase2
<story.book.view.Dashboard> {

	private Story story;
	
	public StoryTest() {
		super(Dashboard.class);
		setup();
	}
	
	public void setup() {
		story = new Story(new StoryInfo());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreation() {
		assertNotNull(story);
	}
	
	@Test
	public void testAddRemoveFragment() {
		StoryFragment testFragment = new StoryFragment("Test fragment 1");
		story.addFragment(testFragment);
		assertEquals(story.getStoryFragments().size(), 1);
		assertEquals(story.getStoryFragments().get(0), testFragment);
		story.removeFragment(testFragment.getFragmentID());
		assertTrue(story.getStoryFragments().isEmpty());
	}
	
	@Test
	public void testGetStoryFragments() {
		StoryFragment testFragment1 = new StoryFragment("Test fragment 1");
		StoryFragment testFragment2 = new StoryFragment("Test fragment 2");
		
		story.addFragment(testFragment1);
		story.addFragment(testFragment2);
		
		assertNotNull(story.getStoryFragments());
		assertEquals(story.getStoryFragments().size(), 2);
	}
	
	@Test
	public void testDeepFragmentRemoval() {
		StoryFragment testFragment1 = new StoryFragment("Test fragment 1");
		StoryFragment testFragment2 = new StoryFragment("Test fragment 2");
		StoryFragment testFragment3 = new StoryFragment("Test fragment 3");
		
		story.addFragment(testFragment1);
		story.addFragment(testFragment2);
		story.addFragment(testFragment3);
		
		testFragment1.addDecisionBranch(new DecisionBranch("Branch from 1 to 2", testFragment2.getFragmentID()));
		testFragment1.addDecisionBranch(new DecisionBranch("Branch from 1 to 3", testFragment3.getFragmentID()));
		
		assertEquals(testFragment1.getDecisionBranches().size(), 2);
		story.removeFragment(testFragment2.getFragmentID());
		assertEquals(testFragment1.getDecisionBranches().size(), 1);
		assertEquals(testFragment1.getDecisionBranches().get(0).getDestinationID(), testFragment3.getFragmentID());
		story.removeFragment(testFragment3.getFragmentID());
		assertEquals(testFragment1.getDecisionBranches().size(), 0);
	}

}
