package story.book.dataclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;

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

			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		
			String server_read = "";
			String inputLine;
			while ((inputLine = reader.readLine()) != null) 
				server_read += inputLine;
			
			reader.close();
			closeConnection();
			
			return server_read;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			// Could not read from server
			return "";
		}
	}
}
