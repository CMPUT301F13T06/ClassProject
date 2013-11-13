package story.book.test;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import story.book.dataclient.IOClient;
import story.book.model.Story;
import story.book.model.StoryFragment;
import story.book.model.StoryInfo;
import story.book.model.TextIllustration;
import story.book.view.Dashboard;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

/**
 * 
 * @author Anthony Ou
 * 
 */
public class IOClientTest extends
ActivityInstrumentationTestCase2<story.book.view.Dashboard> {
	Story s;
	StoryInfo info;

	public IOClientTest() {
		super(Dashboard.class);

		info = new StoryInfo();
		info.setAuthor("Daniel");
		info.setTitle("Broken Star");
		info.setGenre("Science Fiction");
		info.setSynopsis("The princess of a destroyed kingdom is left with no one to guide her, "
				+ "until she finds a fallen star with a secret inside....");
		info.setPublishDate(new Date());
		info.setSID(600);
		StoryFragment fragment1 = new StoryFragment("Finding the Star");
		// TODO
		TextIllustration text = new TextIllustration("It was a dark, clear night.");
		fragment1.addIllustration(text);
		StoryFragment fragment2 = new StoryFragment("Preparing for the Journey");
		TextIllustration text2 = new TextIllustration("She ventured into the locked dungeons to retrieve some potions.");
		TextIllustration text3 = new TextIllustration("She could not carry everything, she had to choose between potion A and potion B.");
		fragment2.addIllustration(text2);
		fragment2.addIllustration(text3);

		s = new Story(info);
		s.addFragment(fragment1);
		s.addFragment(fragment2);
		
	}
	IOClient io;
	@Test
	public void testIOClient() throws Exception {
		io = new IOClient(getActivity());
		io.saveStory(s);
		StoryInfo sample_info = io.getStory((s.getStoryInfo().getSID()))
				.getStoryInfo();

		assertEquals(sample_info.getAuthor(), ("Daniel"));
		assertTrue(sample_info.getTitle().equals(info.getTitle()));
		assertTrue(sample_info.getAuthor().equals(info.getAuthor()));
		assertTrue(sample_info.getGenre().equals(info.getGenre()));
		assertTrue(sample_info.getSynopsis().equals(info.getSynopsis()));
		assertEquals(sample_info.getSID(), info.getSID());
		io.URIhandler(600);
		assertTrue(io.getStoryList().contains("600"));
		Log.d(String.valueOf(io.getSID()), "some free sid");
		assertTrue(io.search("Daniel").size()==1);
		assertTrue(io.search("fewa rewrwe").size()==0);
//		ArrayList<StoryInfo> storyinfos = io.getStoryInfoList();
//		assertEquals(storyinfos.get(0).getAuthor(), "Daniel");
		assertFalse(io.checkSID(600));
		assertTrue(io.getSID() != 600);
		//	assertTrue(io.deleteStory(600));
		//	assertTrue(io.checkSID(600));
		//	assertFalse(io.deleteStory(600));

		// io.saveStory(null);
		// assertEquals(io.getStory(600), null);

	}
}
