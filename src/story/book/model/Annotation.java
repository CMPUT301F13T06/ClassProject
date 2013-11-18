package story.book.model;

import android.view.View;

public class Annotation<T extends Illustration> {
	
	private String author;
	private T illustration;
	
	public Annotation(String author, T illustration) {
		this.author = author;
		this.illustration = illustration;
	}
	
	public String getAuthor() {
		return this.author;
	}
	
	public View getView(Boolean editMode) {
		return this.illustration.getView(editMode);
	}
}
