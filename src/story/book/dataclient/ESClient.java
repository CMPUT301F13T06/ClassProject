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
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	private void publishIllustrations(Story story){
		int SID = story.getStoryInfo().getSID();
		String stringSID = String.valueOf(SID);

		//Get list of binary files		
		HashMap<Integer, StoryFragment> fragmentMap = story.getStoryFragments();

		for (Integer key: fragmentMap.keySet()) {
			StoryFragment f = fragmentMap.get(key);

			//encode Illustrations
			ArrayList<Illustration> illusrations = f.getIllustrations();
			for (Illustration i: illusrations) {
				String path =  story_dir + stringSID	+ "/";

				if (i instanceof BinaryIllustration) {
					BinaryIllustration bi = (BinaryIllustration)i;
					BinaryFile b = new BinaryFile(bi.getContent(), bi.encodeIllustration(path));
					String binary_string = super.serialize(b);
					try {
						String filepath = b.getContent();
						filepath = FilenameUtils.removeExtension(filepath);
						String result = new ESWrite(Annotations_folder).execute(stringSID + "/" + filepath, binary_string).get();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
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
	private ArrayList<String> readSIDList() {
		String server_read;
		try {
			server_read = new ESRead(def_folder).execute("_search?fields=_id").get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			server_read = "";
		} 
		Type type = new TypeToken<ES_ID_Response>(){}.getType();
		ES_ID_Response es = (ES_ID_Response) super.unSerialize(server_read, type);
		return (ArrayList<String>) es.getSources();
	}

	private ArrayList<String> readIllustrations(String sid_string) {
		String server_read;
		Type type;

		ArrayList<String> list = new ArrayList<String>();

		try {
			server_read = new ESRead("").execute(sid_string + "/_search?fields=_id" ).get();
			type = new TypeToken<ES_ID_Response>(){}.getType();
			ES_ID_Response bl_response = (
					ES_ID_Response) this.unSerialize(server_read, type);		
			list  =  (ArrayList<String>) bl_response.getSources();
			Log.d(list.toString(), "more stuff");

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
			ArrayList<String> filenames = readIllustrations(sid_string);
			String path =  story_dir + sid_string + "/";

			// Now that we have both annotations and illustrations, decode the story
			for (String file: filenames) {
				String server_read = new ESRead(Annotations_folder).execute(sid_string+"/"+file).get();
				Type type = new TypeToken<ESData<BinaryFile>>(){}.getType();
				ESData<BinaryFile> es = (ESData<BinaryFile>) super.unSerialize(server_read, type);
				BinaryFile binFile = es.getSource();
				binFile.decode(path);
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
		ArrayList<String> list = readSIDList();
		if (!list.contains(Integer.valueOf(SID))) {
			return true;
		} else {
			return false;
		}
	}

	public int getSID() {
		ArrayList<String> list = readSIDList();
		for (Integer i = 0; i < Integer.MAX_VALUE; i++) {
			if (!list.contains(i.toString())) {
				return i;
			} 
		}

		return -1;
	}
}