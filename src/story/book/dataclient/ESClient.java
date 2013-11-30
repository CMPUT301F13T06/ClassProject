package story.book.dataclient;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import org.apache.commons.io.FilenameUtils;

import story.book.model.Annotation;
import story.book.model.BinaryIllustration;
import story.book.model.Illustration;
import story.book.model.Story;
import story.book.model.StoryFragment;
import story.book.model.StoryInfo;
import story.book.view.StoryApplication;

import android.util.Log;

import com.google.gson.reflect.TypeToken;

/**
 * ESClient is a DataClient class that represents 
 * an online storage location for Stories. Stories
 * are saved to an Elastic Search server.
 * 
 * @author Vina Nguyen
 * 
 */

public class ESClient extends DataClient {	
	private String def_folder = "stories/";
	private String SID_folder = "SIDs/0";
	private String Binary_folder = "binaries2/";
	private String Annotations_folder="";
	
	/**
	 *  Publishes a story.	 
	 *  
	 * @param SID is a Story's SID
	 */
	public void saveStory(Story story) {
		publishStory(story);
		publishIllustrations(story);
	}
	
	private void publishStory(Story story){
		try {
			int SID = story.getStoryInfo().getSID();
			String stringSID = String.valueOf(SID);
			String story_string = super.serialize(story);
			
			// Write the Story to the server
			String result = new ESWrite(def_folder).execute(stringSID, story_string).get();

			// Read the existing SIDList, if any
			SIDList list = readSIDList();
			
			ArrayList<Integer> SIDs = list.getSIDs();
			// If we are publishing a story for the first time, add the SID to the list
			if (!SIDs.contains(Integer.valueOf(SID))) {
				SIDs.add(Integer.valueOf((SID)));
				String SID_string = super.serialize(list);
				
				// Write the updated SID list to the server
				new ESWrite(SID_folder).execute("", SID_string);
			} 

		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	private void publishIllustrations(Story story){
		int SID = story.getStoryInfo().getSID();
		String stringSID = String.valueOf(SID);
		
		//Get list of binary files
		ArrayList<BinaryFile> files = new ArrayList<BinaryFile>();
		
		HashMap<Integer, StoryFragment> fragmentMap = story.getStoryFragments();
		
		for (Integer key: fragmentMap.keySet()) {
			StoryFragment f = fragmentMap.get(key);
			
			//encode Illustrations
			ArrayList<Illustration> illusrations = f.getIllustrations();
			for (Illustration i: illusrations) {
				String path =  StoryApplication.getIOClient().getLocalDirectory() 
						+ stringSID
						+ "/";
				
				if (i instanceof BinaryIllustration) {
					BinaryIllustration bi = (BinaryIllustration)i;
					BinaryFile b = new BinaryFile(bi.getContent(), bi.encodeIllustration(path));
					files.add(b);
				}
			}
			
		}
		
		for (BinaryFile b: files) {
			String binary_string = super.serialize(b);
			try {
				String path = b.getContent();
				path = FilenameUtils.removeExtension(path);
				String result = new ESWrite(Annotations_folder).execute(stringSID + "/" + path, binary_string).get();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// Write the binary data to the server
		/*BinaryList binaryList = new BinaryList(SID);
		binaryList.encodeStoryIllustrations(story);

		String binary_string = super.serialize(binaryList);
		try {
			String result = new ESWrite(Binary_folder).execute(stringSID, binary_string).get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	/**
	 *  Publishes an annotation to a story.	 
	 *  TODO FIX
	 * @param annotation to be published
	 * @param story that the annotation is part of
	 */
	public void publishAnnotation(Annotation a, Story story) {
		int SID = story.getStoryInfo().getSID();
		String stringSID = String.valueOf(SID);
		
		//publish the annotation
		BinaryList binaryList = new BinaryList(SID);
		String path = binaryList.encodeStoryAnnotation(a, story);
		if (path != "") {
			path = FilenameUtils.removeExtension(path);
			String binary_string = super.serialize(binaryList);
			try {
				String result = new ESWrite(Annotations_folder).execute(stringSID + "/" + path, binary_string).get();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//re-publish the story as fragment has new annotation
		publishStory(story);
	}
	
	/**
	 *  Reads the in-use SID list from the server. 
	 *  
	 * @return SIDList
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	private SIDList readSIDList() {
		String server_read;
		try {
			server_read = new ESRead(SID_folder).execute("").get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			server_read = "";
		} 
		
		SIDList list = new SIDList();
		
		if (!server_read.equals("")) {
			Type type = new TypeToken<ESData<SIDList>>(){}.getType();
			ESData<SIDList> es = (ESData<SIDList>) super.unSerialize(server_read, type);
			list = es.getSource();
		}
		
		return list;
	}
	
	private ArrayList<BinaryFile> readIllustrations(String sid_string) {
		String server_read = "";
		Type type;
		BinaryList bl = new BinaryList(Integer.valueOf(sid_string));
		
		ArrayList<BinaryFile> list = new ArrayList<BinaryFile>();
		
		try {
			// Get the illustrations from the server
			/*server_read = new ESRead(Binary_folder).execute(sid_string ).get();
			if (server_read != "") {
				type = new TypeToken<ESData<BinaryList>>(){}.getType();
				ESData<BinaryList> es_bl = (ESData<BinaryList>) super.unSerialize(server_read, type);
				bl = es_bl.getSource();
			}*/
			
			// Get the annotations from the server
			server_read = new ESRead(Annotations_folder).execute(sid_string + "/_search?" ).get();
		
			if (server_read != "") {
				type = new TypeToken<ESResponse<BinaryFile>>(){}.getType();
				ESResponse<BinaryFile> bl_response = (ESResponse<BinaryFile>) super.unSerialize(server_read, type);		
				list  = (ArrayList<BinaryFile>) bl_response.getSources();

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	private Story readStory (String sid_string) {
		Story story = null;
		// Get the story from the server
		try {
			String server_read = new ESRead(def_folder).execute(sid_string ).get();
			Type type = new TypeToken<ESData<Story>>(){}.getType();
			ESData<Story> es = (ESData<Story>) super.unSerialize(server_read, type);
			
			story = es.getSource();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return story;
	}
	
	/**
	 * Retrieves an online story (to be used for download or viewing).	 
	 *  
	 * @param SID is a Story's SID
	 * @return a null on failure or a Story object 
	 */
	public Story getStory(int SID) {
		try {			
			String sid_string = String.valueOf(SID);
			
			// Read the story from the server
			Story story = readStory(sid_string);
			// Read the illustrations from the server
			ArrayList<BinaryFile> bl = readIllustrations(sid_string);
			
			
			// Now that we have both annotations and illustrations, decode the story
			if (bl != null && bl.size() > 0 && story != null) {
				for (BinaryFile b: bl) {
					String path =  StoryApplication.getIOClient().getLocalDirectory() 
							+ sid_string
							+ "/";
					b.decode(path);
				}
			}
			
			return  story;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public ArrayList<StoryInfo> getStoryInfoList() {
		try {
			String server_read = new ESRead(def_folder).execute("_search?").get();
			
			Type type = new TypeToken<ESResponse<Story>>(){}.getType();
			ESResponse<Story> es = (ESResponse<Story>) super.unSerialize(server_read, type);			
			
			ArrayList<StoryInfo> storyInfoList = new ArrayList<StoryInfo>();
			ArrayList<Story> stories = (ArrayList<Story>) es.getSources();
			for(Story s: stories) {
				storyInfoList.add(s.getStoryInfo());
			}
			
			return storyInfoList;			
			 
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Boolean checkSID(int SID) {
		SIDList list = readSIDList();
		ArrayList<Integer> SIDs = list.getSIDs();
		if (!SIDs.contains(Integer.valueOf(SID))) {
			return true;
		} else {
			return false;
		}
	}

	public int getSID() {
		SIDList list = readSIDList();
		ArrayList<Integer> SIDs = list.getSIDs();
		
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			if (!SIDs.contains(i)) {
				return i;
			} 
		}
		
		return -1;
	}
}