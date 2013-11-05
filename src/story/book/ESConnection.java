package story.book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 
 * @author Vina Nguyen
 * 
 */

public class ESConnection {
	private String base_url = "http://cmput301.softwareprocess.es:8080/cmput301f13t06/";
	private String def_folder = "testing/"; //"stories/";
	private String url_string;
	
	private HttpURLConnection conn;
	private OutputStreamWriter writer;
	
	public ESConnection() {
		resetFolder();
	}

	/**
	 * Switches the base folder to access our Elastic Search server from.
	 *
	 * @param folder name
	 */
	public void setFolder(String folder) {
		url_string = base_url + folder;
	}
	
	public void resetFolder() {
		setFolder(def_folder);
	}
	
	/**
	 * Opens the ES OutputStreamWriter to allow writes to the server.
	 * 
	 * @param location to write to
	 * @return an OutputStreamWriter
	 */
	public OutputStreamWriter getESWriter(String location) {
		try {
			openConnection(location);
			conn.setChunkedStreamingMode(0);
			
			writer = new OutputStreamWriter(conn.getOutputStream());
			
			return writer;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Opens the ES BufferedReader to allow reads from the server.
	 * 
	 * @param location to read from
	 * @return a BufferedReader
	 */
	public String getESRead(String location) {
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
	
	/**
	 * Closes the ES OutputStreamWriter.
	 */
	public void closeESWriter() {
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeConnection();
	}
	
	/**
	 * Opens the HttpURLConnection.
	 */
	private void openConnection(String location) {
		try {
			URL url = new URL(url_string + location);
			
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestProperty("Accept", "application/json");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Closes the HttpURLConnection.
	 */
	private void closeConnection() {
		conn.disconnect();
	}
}
