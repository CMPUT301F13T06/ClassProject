/** StoryInfo.java
 * 
 */
package story.book;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StoryInfo {
	
	private String author;
	private String title;
	private String genre;
	private String synopsis;
	private int SID;

	private Date publishDate;
	
	private int startingFragmentID;
	
	enum PublishState {
		PUBLISHED,
		UNPUBLISHED,
		NEEDS_REPUBLISH
	}
	private PublishState publishState;
	
	public StoryInfo() {
		setAuthor("");
		setTitle("");
		setGenre("");
		setSynopsis("");
		setPublishState(PublishState.UNPUBLISHED);
	}
	
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
	
	/**
	 * Returns a <code>String</code> representation of the 
	 * <code>publishDate</code> <code>Date</code> object.
	 * 
	 * @return the <code>String</code> representation of the publish date
	 */
	public String getPublishDateString() {
		if (publishDate != null) {
			SimpleDateFormat stringForm = new SimpleDateFormat("MMMM dd yyyy");
			
			return stringForm.format(publishDate);
		} else
			return "";
	}
	
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	
	public int getStartingFragmentID() {
		return startingFragmentID;
	}
	
	public void setStartingFragmentID(int startingFragmentID) {
		this.startingFragmentID = startingFragmentID;
	}

	public int getSID() {
		return SID;
	}

	public void setSID(int SID) {
		this.SID = SID;
	}

	public PublishState getPublishState() {
		return publishState;
	}

	public void setPublishState(PublishState publishState) {
		this.publishState = publishState;
	}
	@Override
	public String toString(){
		return this.title + "\n" + this.author +"\n" + this.getPublishDateString();
	}
	
}
