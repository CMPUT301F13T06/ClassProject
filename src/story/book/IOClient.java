package story.book;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

public class IOClient extends JSONClient {

    private String story_dir;

    /**
     * Unbuffered IO for writing a serialized story. Buffered IO for read a
     * serialized story string.
     * 
     * @param c
     *            the Context of the main application since the package name
     *            directory can change
     */
    public IOClient(Context c) {
	super();
	this.story_dir = c.getFilesDir() + "/storys/";
	new File(story_dir).mkdir(); // make the dir if it doesnt exist
    }

    /**
     * http://stackoverflow.com/questions/14376807/how-to-read-write-string-from
     * -a-file-in-android
     * 
     * @param SID
     *            A fileID
     * @param aStory
     *            The story to be saved
     * @throws IOException
     *             IO exceptions should be caught in the calling code since it
     *             doesn't make sense to try to handle it here.
     */
    public void saveStory(Story aStory) throws IOException {
	String serialStory = super.serialize(aStory);
	FileOutputStream fos = new FileOutputStream(story_dir
		+ String.valueOf(aStory.getStoryInfo().getSID()));
	fos.write(serialStory.getBytes());
	fos.flush();
	fos.close();
    }

    /**
     * 
     * @param SID
     *            the story to be deleted
     * @return true on success and false on failure
     */
    public boolean deleteStory(int SID) {
	// I add a slash here since getFilesDir doesnt include it at the end
	return new File(story_dir + String.valueOf(SID)).delete();
    }

    /**
     * 
     * @return a list of all the SIDs on the internal device.
     */
    public ArrayList<String> getStoryList() {
	ArrayList<String> listOfFileNames = new ArrayList<String>();
	File f = new File(story_dir);
	for (String temp : f.list()) {
	    listOfFileNames.add(temp);
	}
	return listOfFileNames;
    }

    /**
     * 
     * @return an array list of StoryInfos
     */
    public ArrayList<StoryInfo> getStoryInfoList() {
	ArrayList<StoryInfo> listOfStoryInfo = new ArrayList<StoryInfo>();
	for (String file : getStoryList()) {
	    Story aStory = null;
	    try {
		Log.d(file, "List Of Files");
		aStory = getStory(Integer.valueOf(file).intValue());
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    listOfStoryInfo.add(aStory.getStoryInfo());
	}
	return listOfStoryInfo;
    }

    /**
     * http://stackoverflow.com/questions/14376807/how-to-read-write-string-from
     * -a-file-in-android
     * 
     * @param SID
     *            Likely to the SID of the story or any other string
     * @returns A string representing a serialized story;
     * @throws IOException
     *             IO exceptions should be caught in the calling code since it
     *             doesn't make sense to try to handle it here.
     */
    public Story getStory(int SID) throws IOException {
	FileInputStream fis = new FileInputStream(story_dir
		+ String.valueOf(SID));
	InputStreamReader isr = new InputStreamReader(fis);
	char[] inputBuffer = new char[5048];
	StringBuilder sb = new StringBuilder(5048); // set the initial size of
						    // string builder to be a
						    // decent sized length
	int l;
	while ((l = isr.read(inputBuffer)) != -1) {
	    sb.append(inputBuffer, 0, l);
	}
	fis.close();
	isr.close();
	return super.unSerialize(sb.toString(), Story.class);
    }
}
