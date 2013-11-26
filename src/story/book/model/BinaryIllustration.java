package story.book.model;

import java.io.File;
import java.io.IOException;

import android.util.Base64;
import android.util.Log;

import org.apache.commons.io.FileUtils;

/**
 * BinaryIllustration is an Illustration whose content is a
 * relative path to a file. It provides methods to encode/decode
 *  its file's contents into/from base64. This is an abstract class.
 * 
 * @author Vina Nguyen
 */

public abstract class BinaryIllustration extends Illustration<String> {

	protected String content;
	
	/**
	 * Encode the file associated with the BinaryIllustration
	 * into base64.
	 *
	 *@param path is the base file path that the binary file is stored at
	 */
	public String encodeIllustration(String path) {
		File file = new File(path + content.toString());
		
		String encoded = "";
		try {
			byte data[] = FileUtils.readFileToByteArray(file);
			encoded = Base64.encodeToString(data, Base64.DEFAULT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encoded;
	}
	
	/**
	 * Decode the given base64 string into the 
	 * file associated with the BinaryIllustration
	 * 
	 *@param path is the base file path that the binary file will be stored at
	 *@param encoded is the base64 string
	 */
	public void decodeIllustration(String path, String encoded) {
		File file = new File(path + content.toString());
		
		try {
			FileUtils.writeByteArrayToFile(file, Base64.decode(encoded, Base64.DEFAULT));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String getContent() {
		return content;
	}

	@Override
	public void setContent(String content) {
		this.content = content;
	}
	
}
