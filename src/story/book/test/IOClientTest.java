package story.book.test;

import org.junit.Test;

import story.book.*;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

public class IOClientTest extends ActivityInstrumentationTestCase2<story.book.MainActivity>{

	public IOClientTest() {
		super(MainActivity.class);
	}


	@Test
	public void testIOClient() throws Exception{
		StoryInfo si = new StoryInfo();
		si.setAuthor("anthony");
		Story  s = new Story(si);
		IOClient io = new IOClient(getActivity());

		io.saveStory("test", s);
		Log.d("author", io.getStory("test").getStoryInfo().getAuthor());
		assertEquals(io.getStory("test").getStoryInfo().getAuthor() , ("anthony"));
		io.saveStory("test", null);
		assertEquals(io.getStory("test"), null);
	}
//
//	@Test
//	public void testSaveStory() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetStory() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testJSONClient() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testSerializeStory() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testUnSerialize() {
//		fail("Not yet implemented");
//	}

}
