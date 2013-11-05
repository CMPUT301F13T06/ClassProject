package story.book.test;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import story.book.dataclient.IOClient;
import story.book.Dashboard;
import story.book.Story;
import story.book.StoryInfo;
import android.test.ActivityInstrumentationTestCase2;

/**
 * 
 * @author Anthony Ou
 * 
 */
public class IOClientTest extends
	ActivityInstrumentationTestCase2<story.book.Dashboard> {
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

	s = new Story(info);

    }

    @Test
    public void testIOClient() throws Exception {
	IOClient io = new IOClient(getActivity());
	io.saveStory(s);
	StoryInfo sample_info = io.getStory((s.getStoryInfo().getSID()))
		.getStoryInfo();

	assertEquals(sample_info.getAuthor(), ("Daniel"));
	assertTrue(sample_info.getTitle().equals(info.getTitle()));
	assertTrue(sample_info.getAuthor().equals(info.getAuthor()));
	assertTrue(sample_info.getGenre().equals(info.getGenre()));
	assertTrue(sample_info.getSynopsis().equals(info.getSynopsis()));
	assertEquals(sample_info.getSID(), info.getSID());

	assertEquals(io.getStoryList().get(0), ("600"));

	ArrayList<StoryInfo> storyinfos = io.getStoryInfoList();
	assertEquals(storyinfos.get(0).getAuthor(), "Daniel");

	assertTrue(io.deleteStory(600));

	assertFalse(io.deleteStory(600));

	// io.saveStory(null);
	// assertEquals(io.getStory(600), null);

    }
}
