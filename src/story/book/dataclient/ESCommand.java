package story.book.dataclient;

import java.net.HttpURLConnection;
import java.net.URL;

import android.os.AsyncTask;

/**
 * ESCommand as an abstract class that is an AsyncTask.
 * Classes that inherit from this class perform a 
 * command to the Elastic Search server.
 * 
 * @author Vina Nguyen
 * 
 */

public abstract class ESCommand extends AsyncTask<String, String, String>{
	protected String base_url = "http://cmput301.softwareprocess.es:8080/cmput301f13t06/";
	protected String url_string;
	protected HttpURLConnection conn;
	
	/**
	 * Opens the HttpURLConnection.
	 */
	protected void openConnection(String location) {
		try {
			URL url = new URL(url_string + location);
			
			conn = (HttpURLConnection) url.openConnection();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Closes the HttpURLConnection.
	 */
	protected void closeConnection() {
		conn.disconnect();
	}

	/**
	 * Called on new ESCommand().execute()
	 */
	@Override
	protected abstract String doInBackground(String... params);

}
