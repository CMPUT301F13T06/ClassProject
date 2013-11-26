package story.book.dataclient;

import java.util.ArrayList;
import java.util.HashMap;

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
	ArrayList<String> contents;
	ArrayList<String> base64data;
	
	public BinaryList() {
		contents = new ArrayList<String>();
		base64data = new ArrayList<String>();
	}
	
	/**
	 * Encode all the files associated with  BinaryIllustrations 
	 * of a story into base64.
	 * 
	 * @param Story to encode illustrations for
	 */
	public void encodeStoryIllustrations(Story s) {
		int SID = s.getStoryInfo().getSID();
		
		HashMap<Integer, StoryFragment> fragmentMap = s.getStoryFragments();
		
		for (Integer key: fragmentMap.keySet()) {
			StoryFragment f = fragmentMap.get(key);
			ArrayList<Illustration> illusrations = f.getIllustrations();
			for (Illustration i: illusrations) {
				if (i instanceof BinaryIllustration) {
					encode((BinaryIllustration)i, SID);
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
	public void encodeStoryAnnotation(Annotation a, Story s) {
		int SID = s.getStoryInfo().getSID();
		
		Illustration i = a.getIllustration();
		if (i instanceof BinaryIllustration) {
			encode((BinaryIllustration)i, SID);
		}
	}
	
	/**
	 * Decode all the base64 data (ie. files for BinaryIllustrations) 
	 * for a story into the appropriate files.
	 * 
	 * @param Story to decode
	 */
	public void decodeStory(Story s) {
		int SID = s.getStoryInfo().getSID();
		
		HashMap<Integer, StoryFragment> fragmentMap = s.getStoryFragments();
		
		for (Integer key: fragmentMap.keySet()) {
			StoryFragment f = fragmentMap.get(key);
			
			//decode Illustrations
			ArrayList<Illustration> illusrations = f.getIllustrations();
			for (Illustration i: illusrations) {
				if (i instanceof BinaryIllustration) {
					decode((BinaryIllustration)i, SID);
				}
			}
			
			//decode Annotations
			ArrayList<Annotation> annotations = f.getAnnotations();
			for (Annotation a: annotations) {
				Illustration i = a.getIllustration();
				
				if (i instanceof BinaryIllustration) {
					decode((BinaryIllustration)i, SID);
				}
			}
		}
	}
	
	private void encode(BinaryIllustration b, int SID) {
		contents.add(b.getContent());
		base64data.add(b.encodeIllustration(getStoryPath(SID)));
	}
	
	private void decode(BinaryIllustration b, int SID) {
		int i = contents.indexOf(b.getContent());
		b.decodeIllustration(getStoryPath(SID), base64data.get(i));
	}
	
	private String getStoryPath(int SID) {
		return StoryApplication.getIOClient().getLocalDirectory() 
				+ SID
				+ "/";
	}
}
