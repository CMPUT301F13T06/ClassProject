package story.book;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;

public class IOClient{

	private Context context;

	/**
	 * Unbuffered IO for writing a serialized story.
	 * Buffered IO for read a serialized story string.
	 * 
	 * @param c the Context of the main application since
	 * the package name directory can change
	 */
	public IOClient(Context c) {
		this.context = c;
	}

	/**
	 * http://stackoverflow.com/questions/14376807/how-to-read-write-string-from-a-file-in-android
	 * 
	 * @param FileID A fileID
	 * @param aStory The story to be saved
	 * @throws IOException IO exceptions should be caught in the calling code
	 * since it doesn't make sense to try to handle it here.
	 */
	public void saveStory(String FileID, Story aStory) throws IOException{
		String serialStory = JSONClient.serializeStory(aStory);
		FileOutputStream fos =  context.openFileOutput(FileID, Context.MODE_PRIVATE);
		fos.write(serialStory.getBytes());
		fos.flush();
		fos.close();
	}

	/**
	 * http://stackoverflow.com/questions/14376807/how-to-read-write-string-from-a-file-in-android
	 * @param FileID Likely to the SID of the story or any other string
	 * @returns A string representing a serialized story;
	 * @throws IOException IO exceptions should be caught in the calling code
	 * since it doesn't make sense to try to handle it here.
	 */
	public Story getStory(String FileID) throws IOException{
		FileInputStream fis = context.openFileInput(FileID);
		InputStreamReader isr = new InputStreamReader(fis);
		char[] inputBuffer = new char[5048];
		StringBuilder sb = new StringBuilder(5048); //set the initial size of string builder to be a decent sized length
		int l;
		while ((l = isr.read(inputBuffer)) != -1){
			sb.append(inputBuffer, 0, l);
		}
		fis.close();
		isr.close();
		return JSONClient.unSerialize(sb.toString());
	}
}
