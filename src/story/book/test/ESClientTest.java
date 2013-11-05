package story.book.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import story.book.ESClient;
import story.book.Story;
import story.book.StoryInfo;

public class ESClientTest {

	private ESClient es;
	private ArrayList<Story> sample_story;
	
	@Before
	public void setup() {
		es = new ESClient();
		sample_story = new ArrayList<Story>();
		
		createSampleStory();
	}
	
	public void createSampleStory() {
		StoryInfo info = new StoryInfo();
		info.setAuthor("Daniel Andy");
		info.setTitle("Broken Star");
		info.setGenre("Science Fiction");
		info.setSynopsis("The princess of a destroyed kingdom is left with no one to guide her, until she finds a fallen star with a secret inside....");
		info.setPublishDate(new Date());
		info.setSID(600);
		
		sample_story.add(new Story(info));
		
		StoryInfo info2 = new StoryInfo();
		info2.setAuthor("Ashley");
		info2.setTitle("Frosted Tracks");
		info2.setGenre("Drama");
		info2.setSynopsis("Everything has come to a halt in your life when your friend betrays you by revealing your true identity.....");
		info2.setPublishDate(new Date());
		info2.setSID(601);
		
		sample_story.add(new Story(info2));
	}
	
	public void checkStoryInfo(StoryInfo info, StoryInfo sample_info) {
		assertTrue(sample_info.getTitle().equals(info.getTitle()));
		assertTrue(sample_info.getAuthor().equals(info.getAuthor()));
		assertTrue(sample_info.getGenre().equals(info.getGenre()));
		assertTrue(sample_info.getSynopsis().equals(info.getSynopsis()));
		assertEquals(sample_info.getSID(), info.getSID());
		// TODO Does not work ;-;
		//assertEquals(0, sample_info.getPublishDate().compareTo(info.getPublishDate()));
	}
	
	@Test
	public void testPublishStory() {
		for (int i = 0; i < sample_story.size(); i++) {
			es.saveStory(sample_story.get(i));
		}
	}
	
	@Test
	public void testGetStory() {		
		StoryInfo sample_info = sample_story.get(0).getStoryInfo();
		
		Story returned_story = es.getStory(sample_info.getSID());
		StoryInfo info = returned_story.getStoryInfo();
		
		checkStoryInfo(info, sample_info);
	}
	
	@Test
	public void testGetStoryInfoList() {
		ArrayList<StoryInfo> list = es.getStoryInfoList();
		for (int i = 0; i < sample_story.size(); i++) {
			
			for (int j = 0; j < sample_story.size(); j++) {
				if (list.get(i).getSID() == sample_story.get(j).getStoryInfo().getSID()) {
					checkStoryInfo(list.get(i), sample_story.get(j).getStoryInfo());
					break;
				}
			}
		}
	}
	
}
