/** StoryInfo.java
 * 
 */
package story.book;

import java.util.Date;

public class StoryInfo {
	
	private String author;
	private String title;
	private String genre;
	private String synopsis;
	private int SID;

	private Date publishDate;
	private StoryFragment startingFragment;
	
	public StoryInfo() {}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public String getSynopsis() {
		return synopsis;
	}
	
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	
	public Date getPublishDate() {
		return publishDate;
	}
	
	public String getPublishDateString() {
		if (publishDate != null)
			return publishDate.toString();
		else
			return "";
	}
	
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	
	public StoryFragment getStartingFragment() {
		return startingFragment;
	}
	
	public void setStartingFragment(StoryFragment startingFragment) {
		this.startingFragment = startingFragment;
	}

	public int getSID() {
		return SID;
	}

	public void setSID(int SID) {
		this.SID = SID;
	}
	
}
