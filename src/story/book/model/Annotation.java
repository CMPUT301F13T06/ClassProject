package story.book.model;

import java.util.ArrayList;

import android.R;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Annotation {
	
	private String author = "";
	private Illustration<?> illustration;
	private String caption = "";
	
	public Annotation(String author, Illustration<?> illustration) {
		this.author = author + " posted: ";
		this.illustration = illustration;
	}
	
	public String getAuthor() {
		return this.author;
	}
	
	public String getCaption() {
		return this.caption;
	}
	
	public void setCaption(String caption) {
		this.caption = caption;
	}
	
	public Illustration<?> getIllustration() {
		return illustration;
	}
	
	public ArrayList<View> getView(String path, Boolean editMode, Context C) {
		ArrayList<View> illus = new ArrayList<View>();
		// Author tag
		TextIllustration author = new TextIllustration(this.author);
		TextView t = (TextView) author.getView(null, false, C);
		t.setTextSize(15);
		illus.add(t);
		// Illustration
		if (this.illustration != null) {
			illus.add(this.illustration.getView(path, editMode, C));
		}
		// Caption
		TextIllustration text = new TextIllustration(this.caption);
		if (editMode) {
			EditText v = (EditText) text.getView(null, editMode, C);
			v.setHint("Enter text here");
			illus.add(v);
		}
		else {
			TextView v = (TextView) text.getView(null, editMode, C);
			illus.add(v);
		}
		return illus;
	}
	
}
