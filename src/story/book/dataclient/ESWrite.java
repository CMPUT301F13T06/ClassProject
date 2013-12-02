package story.book.dataclient;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import android.util.Log;

/**
 * ESWrite is an ESCommand that writes to the Elastic Search server. 
 * 
 * @author Vina Nguyen
 * 
 */
public class ESWrite extends ESCommand {

	public ESWrite(String folder) {
		// sets the Elastic Search url to access
		url_string = base_url + folder;
	}
	
	@Override
	protected String doInBackground(String... params) {
		doESWrite(params[0], params[1]);
		//Just return what was written
		return params[1];
	}

	/**
	 * Opens the ES OutputStreamWriter to allow writes to the server.
	 * 
	 * @param location to write to
	 */
	public void doESWrite(String location, String data) {
		try {
			openConnection(location);
			conn.setDoOutput(true);
			conn.setFixedLengthStreamingMode(data.length());
			conn.setRequestMethod("PUT");
			conn.setRequestProperty("Accept","application/json");
			conn.setRequestProperty("Connection","keep-alive");
			conn.connect();
			OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
			do{
				writer.write(data);
				writer.flush();
				writer.close();
				
			}while(conn.getResponseCode() != HttpURLConnection.HTTP_OK);
			closeConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
