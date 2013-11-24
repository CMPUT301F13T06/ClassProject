package story.book.dataclient;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import story.book.model.Story;
import story.book.model.StoryInfo;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

/**
 * This class is used mainly to save stories on device, retrieve a list of stories on device,
 * or to simply retrieve a story.
 * @author Anthony Ou
 * 
 * 
 */
public class IOClient extends DataClient {

	private String story_dir;
	private Context context;

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
		context = c; //I dont need the context but its nice to have for the future

		this.story_dir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/StoryBook/";
		new File(story_dir).mkdir();
	}

	/**
	 * http://stackoverflow.com/questions/14376807/how-to-read-write-string-from
	 * -a-file-in-android
	 * 
	 * @param SID
	 *            A fileID
	 * @param aStory
	 *            The story to be saved
	 */
	public void saveStory(Story aStory) {
		try {
			String SID = String.valueOf(aStory.getStoryInfo().getSID());
			new File(story_dir + SID).mkdir();
			FileOutputStream fos = new FileOutputStream(story_dir + SID + "/" + SID);
			fos.write(super.serialize(aStory).getBytes());
			fos.flush();
			fos.close();
		} catch (IOException e) {
			Log.d("error saving a story", "IOclient errors");
		}

	}

	/**
	 * 
	 * @param SID
	 *            the story to be deleted
	 * @return true on success and false on failure
	 */
	public boolean deleteStory(int SID) {
		File dir = new File(story_dir + "/" +String.valueOf(SID));
		String[] children = dir.list();
		for (String i : children) {
			new File(dir, i).delete();
		}
		return dir.delete();
	}
	
	/**
	 * 
	 * @param SID
	 * @return
	 */
	public Uri URIhandler(int SID, String Extension) {
		File dir = new File(story_dir+String.valueOf(SID));
		ArrayList<String> listOfFiles = new ArrayList<String>();
		String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
		for(String i : dir.list())
			if(i.contains(date))
				listOfFiles.add(i);

		return Uri.fromFile(
				new File(dir, 
						"ID-"+listOfFiles.size()+"-"+date+'-'+
						Calendar.getInstance().getTimeInMillis()+Extension));
	}
	
	/**
	 * 
	 * @return a list of all the SIDs on the internal device.
	 */
	public ArrayList<String> getStoryList() {
		ArrayList<String> listOfFileNames = new ArrayList<String>();
		for (String temp : new File(story_dir).list()) {
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
			try{
				Story s = getStory(Integer.valueOf(file).intValue());
				listOfStoryInfo.add(s.getStoryInfo());
			}
			catch (Exception e)
			{
				new File(story_dir,file).delete();
			}
		}
		return listOfStoryInfo;
	}
	
	/**
	 * @return the file path to the application's storage directory
	 */
	public String getLocalDirectory() {
		return this.story_dir;
	}

	public Boolean checkSID(int SID) {
		return getStoryList().contains(String.valueOf(SID)) ? false : true;
	}


	public int getSID() {
		ArrayList<String> StoryInfo = getStoryList();
		for (Integer i = 1; i < Integer.MAX_VALUE; ++i) {
			if (!StoryInfo.contains(i.toString())) {
				return i;
			}
		}
		return -1;
	}

	public StringBuilder readFile(String Path) throws IOException {
		char[] inputBuffer = new char[1024];
		StringBuilder sb = new StringBuilder(1024); // set the initial size
		FileInputStream fis = new FileInputStream(Path);
		InputStreamReader isr = new InputStreamReader(fis);

		int l;
		while ((l = isr.read(inputBuffer)) != -1) {
			sb.append(inputBuffer, 0, l);
		}
		fis.close();
		isr.close();
		return sb;
	}
	/**
	 * http://stackoverflow.com/questions/14376807/how-to-read-write-string-from
	 * -a-file-in-android
	 * 
	 * @param SID
	 *            The SID of a story
	 * @returns A string representing a serialized story;
	 */
	public Story getStory(int SID) {

		StringBuilder sb;
		// of string builder to be
		// same size as the buffer
		try {
			sb = readFile(story_dir
				+ String.valueOf(SID) + "/" + String.valueOf(SID));
		} catch (Exception e) {
			Log.d("reading file error", "getStory() error");
			e.printStackTrace();
			return null;
		}

		return super.unSerialize(sb.toString(), Story.class);
	}
}
