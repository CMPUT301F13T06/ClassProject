package story.book.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import story.book.DecisionBranch;
import story.book.StoryFragment;
import story.book.TextIllustration;

public class StoryFragmentTest {

	private StoryFragment storyFragment1;
	private StoryFragment storyFragment2;
	private StoryFragment storyFragment3;
	
	@Before
	public void setUp() throws Exception {
		storyFragment1 = new StoryFragment("Test Fragment 1");
		storyFragment2 = new StoryFragment("Test Fragment 2");
		storyFragment3 = new StoryFragment("Test Fragment 3");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreation() {
		assertTrue(storyFragment1.getDecisionBranches().isEmpty());
		assertTrue(storyFragment1.getIllustrations().isEmpty());
		assertEquals(storyFragment1.getFragmentTitle(), "Test Fragment 1");
	}
	
	/* This test fails because Android libraries are not referenced.
	@Test
	public void testAddRemoveTextIllustration() {
		TextIllustration textIllustration = new TextIllustration("Sample content text");
		storyFragment1.addIllustration(textIllustration);
		assertTrue(!storyFragment1.getIllustrations().isEmpty());
		assertTrue(storyFragment1.getIllustrations().contains(textIllustration));
		storyFragment1.removeIllustration(textIllustration);
		assertTrue(storyFragment1.getIllustrations().isEmpty());
	}
	*/
	
	@Test
	public void testAddRemoveDecisionBranch() {
		DecisionBranch branch1 = new DecisionBranch("Branch to storyFragment2", storyFragment2);
		storyFragment1.addDecisionBranch(branch1);
		assertTrue(!storyFragment1.getDecisionBranches().isEmpty());
		assertTrue(storyFragment1.getDecisionBranches().contains(branch1));
		storyFragment1.removeDecisionBranch(branch1);
		assertTrue(storyFragment1.getDecisionBranches().isEmpty());
	}

	@Test
	public void followDecisionBranch() {
		DecisionBranch branch12 = new DecisionBranch("Branch to storyFragment2 from 1", storyFragment2);
		DecisionBranch branch23 = new DecisionBranch("Branch to storyFragment3 from 2", storyFragment3);
		DecisionBranch branch13 = new DecisionBranch("Branch to storyFragment3 from 1", storyFragment3);

		storyFragment1.addDecisionBranch(branch12);
		storyFragment2.addDecisionBranch(branch23);
		storyFragment1.addDecisionBranch(branch13);
		
		assertEquals(storyFragment1.getDecisionBranches().get(0).getDestination(), storyFragment2);
		assertEquals(storyFragment2.getDecisionBranches().get(0).getDestination(), storyFragment3);
		assertEquals(storyFragment1.getDecisionBranches().get(1).getDecisionText(), "Branch to storyFragment3 from 1");
		assertEquals(storyFragment1.getDecisionBranches().get(1).getDestination(), storyFragment3);
	}
}
