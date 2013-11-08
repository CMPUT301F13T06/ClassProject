package story.book.test;

import org.junit.Test;

import story.book.Dashboard;
import story.book.DecisionBranch;
import story.book.Story;
import story.book.StoryFragment;
import story.book.StoryInfo;
import story.book.TextIllustration;
import android.test.ActivityInstrumentationTestCase2;

public class StoryFragmentTest extends ActivityInstrumentationTestCase2
		<story.book.Dashboard> {

	private StoryFragment storyFragment1;
	private StoryFragment storyFragment2;
	private StoryFragment storyFragment3;
	
	private Story story;
	
	public StoryFragmentTest() {
		super(Dashboard.class);
		setup();
	}

	public void setup() {
		storyFragment1 = new StoryFragment("Test Fragment 1");
		storyFragment2 = new StoryFragment("Test Fragment 2");
		storyFragment3 = new StoryFragment("Test Fragment 3");
		
		story = new Story(new StoryInfo());
		story.addFragment(storyFragment1);
		story.addFragment(storyFragment2);
		story.addFragment(storyFragment3);
	}
	
	@Test
	public void testCreation() {
		assertNotNull(storyFragment1);
	}
	
	@Test
	public void testAddRemoveTextIllustration() {
		TextIllustration textIllustration = new TextIllustration("Sample "
				+ "content text");
		storyFragment1.addIllustration(textIllustration);
		assertFalse(storyFragment1.getIllustrations().isEmpty());
		assertTrue(storyFragment1.getIllustrations()
				.contains(textIllustration));
		storyFragment1.removeIllustration(textIllustration);
		assertTrue(storyFragment1.getIllustrations().isEmpty());
	}
	
	@Test
	public void testAddRemoveImageIllustration() {
		// TODO: ImageIllustration not implemented
		fail("Not yet implemented");
	}
	
	@Test
	public void testAddRemoveVideoIllustration() {
		// TODO: VideoIllustration not implemented
		fail("Not yet implemented");
	}
	
	@Test
	public void testAddRemoveAudioIllustration() {
		// TODO: AudioIllustration not implemented
		fail("Not yet implemented");
	}
	
	@Test
	public void testCombinationIllustrationTypes() {
		// TODO: All illustrations not implemented
		fail("Not yet implemented");
	}
	
	@Test
	public void testAddRemoveAnnotation() {
		// TODO: Annotations not implemented
		fail("Not yet implemented");
	}
	
	@Test
	public void testAddRemoveDecisionBranch() {
		DecisionBranch branch1 = new DecisionBranch("Branch to storyFragment2",
				storyFragment2.getFragmentID());
		storyFragment1.addDecisionBranch(branch1);
		assertFalse(storyFragment1.getDecisionBranches().isEmpty());
		assertTrue(storyFragment1.getDecisionBranches().contains(branch1));
		storyFragment1.removeDecisionBranch(branch1);
		assertTrue(storyFragment1.getDecisionBranches().isEmpty());
	}

	@Test
	public void followDecisionBranch() {
		DecisionBranch branch12 = new DecisionBranch("Branch to storyFragment2"
				+ " from 1", storyFragment2.getFragmentID());
		DecisionBranch branch23 = new DecisionBranch("Branch to storyFragment3"
				+ " from 2", storyFragment3.getFragmentID());
		DecisionBranch branch13 = new DecisionBranch("Branch to storyFragment3"
				+ " from 1", storyFragment3.getFragmentID());

		storyFragment1.addDecisionBranch(branch12);
		storyFragment2.addDecisionBranch(branch23);
		storyFragment1.addDecisionBranch(branch13);
		
		assertEquals(storyFragment1.getDecisionBranches().get(0)
				.getDestinationID(), storyFragment2);
		assertEquals(storyFragment2.getDecisionBranches().get(0)
				.getDestinationID(), storyFragment3);
		assertEquals(storyFragment1.getDecisionBranches().get(1)
				.getDecisionText(), "Branch to storyFragment3 from 1");
		assertEquals(storyFragment1.getDecisionBranches().get(1)
				.getDestinationID(), storyFragment3);
	}
}
