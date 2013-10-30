/**
 * 
 */
package story.book;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * @author jsurya
 *
 */

public class EditTextIllustration extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final View rootView = inflater.inflate(R.layout.edit_text_illustration, container, false);
		setTitle(R.string.FragmentTitle);
		
		// add VIEW to story handler
		// StoryHandler sh = StoryHandlerApplication.getStoryHandler();
		// sh.addView(this);
		Button newButton = (Button) rootView.findViewById(R.id.Button_SaveEditText);
		String text = (String) editTextIllustration.toString();
		newButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v) {
				
				//String textIllustration = (String) EditTextIllustration.toString();
				
				setContextText(text);
				//saveText(textIllustration);
			
			}});
	}

	private void setContentView(int editTextIllustration) {
		// TODO Auto-generated method stub
		
	}

	private void setTitle(int fragmenttitle) {
		// TODO Auto-generated method stub
		
	}

	private void setContentText(){
		//StoryFragmentEditActivity.setText(text);
	}
}