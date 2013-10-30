package story.book;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;

public class IOClient{
	
	private Context context;
	private JSONClient Jsc;
	
	public IOClient(Context c) {
		this.context = c;
		Jsc = new JSONClient();
	}
	
	/**
	 * http://stackoverflow.com/questions/14376807/how-to-read-write-string-from-a-file-in-android
	 * @param FileID
	 * @param serialStory
	 * @throws IOException
	 */
	public void saveStory(String FileID, Story aStory) throws IOException{
		String serialStory = Jsc.serializeStory(aStory);
		FileOutputStream fos =  context.openFileOutput(FileID, Context.MODE_PRIVATE);
		fos.write(serialStory.getBytes());
		fos.flush();
		fos.close();
	}
	
	/**
	 * http://stackoverflow.com/questions/14376807/how-to-read-write-string-from-a-file-in-android
	 * @param FileID
	 * @returns A string representing a serialized story;
	 * @throws IOException
	 */
	public Story getStory(String FileID) throws IOException{
		FileInputStream fis = context.openFileInput(FileID);
		InputStreamReader isr = new InputStreamReader(fis);
		char[] inputBuffer = new char[5048];
		StringBuilder sb = new StringBuilder();
		int l;
		while ((l = isr.read(inputBuffer)) != -1){
			sb.append(inputBuffer, 0, l);
		}
		fis.close();
		return Jsc.unSerialize(sb.toString());
	}
}
