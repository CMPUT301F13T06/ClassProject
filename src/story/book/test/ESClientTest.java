package story.book.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import story.book.ESClient;
import story.book.Story;
import story.book.StoryInfo;

public class ESClientTest {

	private ESClient es;
	private Story sample_story;
	
	@Before
	public void setup() {
		es = new ESClient();
		createSampleStory();
	}
	
	public void createSampleStory() {
		StoryInfo info = new StoryInfo();
		info.setAuthor("Daniel");
		info.setTitle("Broken Star");
		info.setGenre("Science Fiction");
		info.setSynopsis("The princess of a destroyed kingdom is left with no one to guide her, until she finds a fallen star with a secret inside....");
		info.setPublishDate(new Date());
		info.setSID(600);
		
		sample_story = new Story(info);
	}
	
	@Test
	public void testPublishStory() {
		es.openSaveConnection(sample_story);
	}
	
	@Test
	public void testGetStory() {
		StoryInfo sample_info = sample_story.getStoryInfo();
		
		Story returned_story = es.openGetConnection(sample_info.getSID());
		StoryInfo info = returned_story.getStoryInfo();
		
		assertTrue(sample_info.getTitle().equals(info.getTitle()));
		assertTrue(sample_info.getAuthor().equals(info.getAuthor()));
		assertTrue(sample_info.getGenre().equals(info.getGenre()));
		assertTrue(sample_info.getSynopsis().equals(info.getSynopsis()));
		assertEquals(sample_info.getSID(), info.getSID());
		// TODO Does not work ;-;
		//assertEquals(sample_info.getPublishDate().compareTo(info.getPublishDate()), 0);
	}

}
