package story.book;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;

public class IOClient extends JSONClient{

	private Context context;

	/**
	 * Unbuffered IO for writing a serialized story.
	 * Buffered IO for read a serialized story string.
	 * 
	 * @param c the Context of the main application since
	 * the package name directory can change
	 */
	public IOClient(Context c) {
		super();
		this.context = c;
	}

	/**
	 * http://stackoverflow.com/questions/14376807/how-to-read-write-string-from-a-file-in-android
	 * 
	 * @param SID A fileID
	 * @param aStory The story to be saved
	 * @throws IOException IO exceptions should be caught in the calling code
	 * since it doesn't make sense to try to handle it here.
	 */
	public void saveStory(Story aStory) throws IOException{
		String serialStory = super.serializeStory(aStory);
		FileOutputStream fos =  context.openFileOutput(String.valueOf(aStory.getStoryInfo().getSID()), 
														Context.MODE_PRIVATE);
		fos.write(serialStory.getBytes());
		fos.flush();
		fos.close();
	}
	
	/**
	 * 
	 * @param SID the story to be deleted
	 * @return true on success and false on failure
	 */
	public boolean deleteStory(int SID)
	{
		//I add a slash here since getFilesDir doesnt include it at the end
		File file = new File(context.getFilesDir()+"/"+String.valueOf(SID));
		return file.delete();
	}
	/**
	 * http://stackoverflow.com/questions/14376807/how-to-read-write-string-from-a-file-in-android
	 * @param SID Likely to the SID of the story or any other string
	 * @returns A string representing a serialized story;
	 * @throws IOException IO exceptions should be caught in the calling code
	 * since it doesn't make sense to try to handle it here.
	 */
	public Story getStory(int SID) throws IOException{
		FileInputStream fis = context.openFileInput(String.valueOf(SID));
		InputStreamReader isr = new InputStreamReader(fis);
		char[] inputBuffer = new char[5048];
		StringBuilder sb = new StringBuilder(5048); //set the initial size of string builder to be a decent sized length
		int l;
		while ((l = isr.read(inputBuffer)) != -1){
			sb.append(inputBuffer, 0, l);
		}
		fis.close();
		isr.close();
		return super.unSerialize(sb.toString());
	}
}
