package story.book.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

import story.book.*;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

public class IOClientTest extends ActivityInstrumentationTestCase2<story.book.MainActivity>{
	Story s;
	StoryInfo info;
	public IOClientTest() {
		super(MainActivity.class);
		 {
			info = new StoryInfo();
			info.setAuthor("Daniel");
			info.setTitle("Broken Star");
			info.setGenre("Science Fiction");
			info.setSynopsis("The princess of a destroyed kingdom is left with no one to guide her, until she finds a fallen star with a secret inside....");
			info.setPublishDate(new Date());
			info.setSID(600);
			
			s = new Story(info);
		}
	}


	@Test
	public void testIOClient() throws Exception{
		IOClient io = new IOClient(getActivity());
		io.saveStory((s.getStoryInfo().getSID()), s);
		
		StoryInfo sample_info = io.getStory((s.getStoryInfo().getSID())).getStoryInfo();
		
		assertEquals(sample_info.getAuthor() , ("Daniel"));
		assertTrue(sample_info.getTitle().equals(info.getTitle()));
		assertTrue(sample_info.getAuthor().equals(info.getAuthor()));
		assertTrue(sample_info.getGenre().equals(info.getGenre()));
		assertTrue(sample_info.getSynopsis().equals(info.getSynopsis()));
		assertEquals(sample_info.getSID(), info.getSID());
		
		
		io.saveStory(600, null);
		assertEquals(io.getStory(600), null);
	}
}
