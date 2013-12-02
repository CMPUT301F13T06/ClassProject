package story.book.view;


import android.widget.SimpleAdapter;
import java.util.HashMap;

public class ListAdapter {
	private SimpleAdapter sAdapter;

	public SimpleAdapter getSAdapter() {
		return sAdapter;
	}

	public void setSAdapter(SimpleAdapter sAdapter) {
		this.sAdapter = sAdapter;
	}

	/**
	 * This method gets the position of the item of the adapter
	 */
	public int getFromAdapter(int pos) {
		return Integer.parseInt(((HashMap<String, String>) sAdapter
				.getItem(pos)).get("SID"));
	}
}