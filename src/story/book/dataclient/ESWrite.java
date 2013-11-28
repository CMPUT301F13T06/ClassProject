package story.book.dataclient;

import java.io.IOException;
import java.io.OutputStreamWriter;

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
			conn.setChunkedStreamingMode(0);

			OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
			writer.write(data);
			writer.flush();
			writer.close();
			closeConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
