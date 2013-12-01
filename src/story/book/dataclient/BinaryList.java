package story.book.dataclient;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;

import android.util.Base64;

import story.book.model.Annotation;
import story.book.model.BinaryIllustration;
import story.book.model.Illustration;
import story.book.model.Story;
import story.book.model.StoryFragment;
import story.book.view.StoryApplication;

/**
 * Used for storying BinaryIllustrations on the sever
 * in Base64.
 * 
 * @author Vina Nguyen
 * 
 * @see BinaryIllustration
 */

public class BinaryList {
	private ArrayList<String> contents;
	private ArrayList<byte[]> base64data;
	private int SID;
	
	public BinaryList(int SID) {
		contents = new ArrayList<String>();
		base64data = new ArrayList<byte[]>();
		this.SID = SID;
	}
	
	public ArrayList<String> getContentsArray(){
		return contents;
	}
	
	public ArrayList<byte[]> getDataArray(){
		return base64data;
	}
	
	public void appendBinaryList(BinaryList b) {
		ArrayList<String> c = b.getContentsArray();
		ArrayList<byte[]> d = b.getDataArray();
		for (int i = 0; i < c.size(); i++) {
			contents.add(c.get(i));
			base64data.add(d.get(i));
		}
	}
	
	/**
	 * Encode all the files associated with  BinaryIllustrations 
	 * of a story into base64.
	 * 
	 * @param Story to encode illustrations for
	 */
	public void encodeStoryIllustrations(Story s) {
		HashMap<Integer, StoryFragment> fragmentMap = s.getStoryFragments();
		
		for (Integer key: fragmentMap.keySet()) {
			StoryFragment f = fragmentMap.get(key);
			
			//encode Illustrations
			ArrayList<Illustration> illusrations = f.getIllustrations();
			for (Illustration i: illusrations) {
				if (i instanceof BinaryIllustration) {
					encode((BinaryIllustration)i);
				}
			}
			
		}
	}
	
	/**
	 * Encode the file associated with annotations with a BinaryIllustration  
	 * into base64.
	 * 
	 * @param Annotation to encode
	 * @param Story the annotation is associated with
	 */
	public String encodeStoryAnnotation(Annotation a, Story s) {
		Illustration i = a.getIllustration();
		if (i instanceof BinaryIllustration) {
			encode((BinaryIllustration)i);
			return ((BinaryIllustration)i).getContent();
		}
		return "";
	}
	
	
	/**
	 * Encode a binary illustration.
	 */
	private void encode(BinaryIllustration b) {
		contents.add(b.getContent());
		base64data.add(b.encodeIllustration(getStoryPath()));
	}
	
	public void decodeStory(Story s) {
		HashMap<Integer, StoryFragment> fragmentMap = s.getStoryFragments();
		
		for (Integer key: fragmentMap.keySet()) {
			StoryFragment f = fragmentMap.get(key);
			
			//decode Illustrations
			ArrayList<Illustration> illusrations = f.getIllustrations();
			for (Illustration i: illusrations) {
				if (i instanceof BinaryIllustration) {
					decode((BinaryIllustration)i);
				}
			}
			
			//decode Illustrations
			ArrayList<Annotation> annotations = f.getAnnotations();
			for (Annotation a: annotations) {
				Illustration i = a.getIllustration();
				if (i instanceof BinaryIllustration) {
					decode((BinaryIllustration)i);
				}
			}
			
		}
	}
	
	/**
	 * Decode a binary illustration.
	 */
	private void decode(BinaryIllustration b) {
		int i = contents.indexOf(b.getContent());
		//b.decodeIllustration(getStoryPath(), base64data.get(i));
	}
	
	private String getStoryPath() {
		return StoryApplication.getIOClient().getLocalDirectory() 
				+ SID
				+ "/";
	}
}
