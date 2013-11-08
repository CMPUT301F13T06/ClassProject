/* CMPUT301F13T06-Adventure Club: A choose-your-own-adventure story platform
 * Copyright (C) 2013 Alexander Cheung, Jessica Surya, Vina Nguyen, Anthony Ou,
 * Nancy Pham-Nguyen
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
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
		return this.author +"\n" + this.getPublishDateString();
	}
	
}
