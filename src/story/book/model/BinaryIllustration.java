package story.book.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.io.FileUtils;

import android.util.Base64;

/**
 * BinaryIllustration is an Illustration whose content is a
 * relative path to a file. It provides methods to encode/decode
 *  its file's contents into/from base64. This is an abstract class.
 * 
 * @author Vina Nguyen
 * @author Anthony Ou
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
		File file = new File(path + content);

		byte[] encoded = null;
		try {
			encoded =  Base64.encode(packRaw(FileUtils.readFileToByteArray(file)), Base64.NO_WRAP);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encoded;
	}
	
	@Override
	public String getContent() {
		return content;
	}

	@Override
	public void setContent(String content) {
		this.content = content;
	}

	public static byte[] packRaw(byte[] b) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		GZIPOutputStream zos = new GZIPOutputStream(baos);
		zos.write(b);
		zos.close();

		return baos.toByteArray();
	}
	
	public void deleteIllustration(String path) {
		File file = new File(path + content);
		FileUtils.deleteQuietly(file);
	}
}
