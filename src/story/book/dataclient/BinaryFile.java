package story.book.dataclient;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;

import org.apache.commons.io.FileUtils;

import android.util.Base64;
import android.util.Log;

public class BinaryFile {

	private String content;
	private byte[] base64data;
	boolean include_in_all = false;
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public byte[] getBase64data() {
		return base64data;
	}

	public void setBase64data(byte[] base64data) {
		this.base64data = base64data;
	}

	public BinaryFile(String content, byte[] bs) {
		this.content = content;
		this.base64data = bs;
	}

	/**
	 * Decode the given base64 string into the 
	 * file associated with the BinaryIllustration
	 * 
	 *@param path is the base file path that the binary file will be stored at
	 *@param bs is the base64 string
	 */
	public void decode(String path) {
		Log.d(content,"some content");
		File file = new File(path + content);
		
		try {
			FileUtils.writeByteArrayToFile(file, unpackRaw(Base64.decode(base64data,Base64.NO_WRAP)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * http://www.java2s.com/Code/Java/File-Input-Output/Compressbytearray.htm
	 * @param b
	 * @return
	 * @throws IOException
	 */
	public static byte[] unpackRaw(byte[] b) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ByteArrayInputStream bais = new ByteArrayInputStream(b);

		GZIPInputStream zis = new GZIPInputStream(bais);
		byte[] tmpBuffer = new byte[256];
		int n;
		while ((n = zis.read(tmpBuffer)) >= 0)
			baos.write(tmpBuffer, 0, n);
		zis.close();

		return baos.toByteArray();
	}
}
