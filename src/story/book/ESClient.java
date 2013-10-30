package story.book;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


public class ESClient {
	private String url_string = "http://cmput301.softwareprocess.es:8080/cmput301f13t06/testing/";
	private JSONClient jsonclient = new JSONClient();
	
	/**
	 * 
	 * @param SID is a Story's SID
	 * @return a null on failure or a HttpURLConnection 
	 */
	private HttpURLConnection getConnection(int SID) {
		try {
			URL url = new URL(url_string + String.valueOf(SID));
			
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setDoOutput(true);
			urlConnection.setRequestProperty("Accept", "application/json");
			
			return urlConnection;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * @param Closes a HttpURLConnection
	 */
	private void closeConnection(HttpURLConnection conn) {
		conn.disconnect();
	}
	
	/**
	 *  Publishes a story.	 
	 *  
	 * @param SID is a Story's SID
	 */
	public void openSaveConnection(Story story) {
		try {
			HttpURLConnection conn = getConnection(story.getStoryInfo().getSID());
			conn.setChunkedStreamingMode(0);
			
			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
			
			String story_string = jsonclient.serializeStory(story);
			out.write(story_string);
			out.close();
			 
			closeConnection(conn);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Retrieves an online story.	 
	 *  
	 * @param SID is a Story's SID
	 * @return a null on failure or a Story object 
	 */
	public Story openGetConnection(int SID) {
		try {
			HttpURLConnection conn = getConnection(SID);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String story_string = "";
			String inputLine;
			while ((inputLine = in.readLine()) != null) 
		            story_string += inputLine;
			in.close();
			 
			closeConnection(conn);

			return jsonclient.unSerializeESResponse(story_string);			
			 
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
}