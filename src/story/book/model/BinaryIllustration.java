package story.book.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

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
	public byte[] encodeIllustration(String path) {
		File file = new File(path + content.toString());

		byte[] encoded = null;
		try {
			encoded = Base64.encode( 
					(FileUtils.readFileToByteArray(file)), Base64.NO_WRAP);
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
	 *@param bs is the base64 string
	 */
	public void decodeIllustration(String path, byte[] bs) {
		File file = new File(path + content.toString());

		try {
			FileUtils.writeByteArrayToFile(file, 
					Base64.decode((bs), Base64.NO_WRAP));
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
