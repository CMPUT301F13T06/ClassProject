package story.book.test;

import org.junit.Test;

import story.book.model.Annotation;
import story.book.model.AudioIllustration;
import story.book.model.DecisionBranch;
import story.book.model.ImageIllustration;
import story.book.model.Story;
import story.book.model.StoryFragment;
import story.book.model.StoryInfo;
import story.book.model.TextIllustration;
import story.book.model.VideoIllustration;
import story.book.view.Dashboard;
import android.net.Uri;
import android.test.ActivityInstrumentationTestCase2;

public class StoryFragmentTest extends ActivityInstrumentationTestCase2
		<story.book.view.Dashboard> {

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
		// This test contains errors and will not pass
		ImageIllustration imageIllustration = new ImageIllustration(Uri.parse("http://www.google.com"));
		storyFragment1.addIllustration(imageIllustration);
		assertFalse(storyFragment1.getIllustrations().isEmpty());
		assertTrue(storyFragment1.getIllustrations()
				.contains(imageIllustration));
		storyFragment1.removeIllustration(imageIllustration);
		assertTrue(storyFragment1.getIllustrations().isEmpty());
	}
	
	@Test
	public void testAddRemoveVideoIllustration() {
		VideoIllustration videoIllustration = new VideoIllustration(Uri.parse("http://www.google.com"));
		storyFragment1.addIllustration(videoIllustration);
		assertFalse(storyFragment1.getIllustrations().isEmpty());
		assertTrue(storyFragment1.getIllustrations()
				.contains(videoIllustration));
		storyFragment1.removeIllustration(videoIllustration);
		assertTrue(storyFragment1.getIllustrations().isEmpty());
	}
	
	@Test
	public void testAddRemoveAudioIllustration() {
		AudioIllustration audioIllustration = new AudioIllustration(Uri.parse("http://www.google.com"));
		storyFragment1.addIllustration(audioIllustration);
		assertFalse(storyFragment1.getIllustrations().isEmpty());
		assertTrue(storyFragment1.getIllustrations()
				.contains(audioIllustration));
		storyFragment1.removeIllustration(audioIllustration);
		assertTrue(storyFragment1.getIllustrations().isEmpty());
	}
	
	@Test
	public void testAddRemoveAnnotation() {
		Annotation annotation = new Annotation("Dummy", new TextIllustration("Text stuff and things"));
		storyFragment1.addAnnotation(annotation);
		assertFalse(storyFragment1.getAnnotations().isEmpty());
		storyFragment1.removeAnnotation(annotation);
		assertTrue(storyFragment1.getAnnotations().isEmpty());
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
