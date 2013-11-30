package story.book.dataclient;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

import android.util.Base64;

public class BinaryFile {

	private String content;
	private byte[] base64data;

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

	public void decode(String path) {
File file = new File(path + content.toString());
		
		try {
			FileUtils.writeByteArrayToFile(file, Base64.decode(base64data, Base64.DEFAULT));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
