package story.book.dataclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import android.util.Log;

/**
 * ESRead is an ESCommand that reads from the Elastic Search server. 
 * 
 * @author Vina Nguyen
 * 
 */
public class ESRead extends ESCommand {

	public ESRead(String folder) {
		// sets the Elastic Search url to access
		url_string = base_url + folder;
	}

	@Override
	protected String doInBackground(String... params) {
		String result = doESRead(params[0]);
		return result;
	}

	/**
	 * Opens the ES BufferedReader to allow reads from the server.
	 * 
	 * @param location to read from
	 * @return the read string
	 */
	private String doESRead(String location) {
		try {
			openConnection(location);
			conn.setChunkedStreamingMode(0);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept","application/json");
			conn.setRequestProperty("Connection","keep-alive");
			conn.connect();
			String server_read = "";
			String inputLine;
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			do{
				while ((inputLine = reader.readLine()) != null)
					server_read += inputLine;
			}while(conn.getResponseCode() != HttpURLConnection.HTTP_OK);
			reader.close();
			closeConnection();

			return server_read;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// Could not read from server
			return "";
		}
	}
}
