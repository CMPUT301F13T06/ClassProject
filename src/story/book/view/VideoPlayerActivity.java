package story.book.view;
 
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceView;
import android.widget.MediaController;
import android.widget.VideoView;
 
public class VideoPlayerActivity extends Activity {
    VideoView videoView;
    DisplayMetrics displayMetrics;
    SurfaceView surfaceView;
    MediaController mediaController;
    String videoPath;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_player);
        
        Bundle extras = getIntent().getExtras();
        videoPath = extras.getString("VIDEO_PATH");
        fetchVideo();
    }
 
    public void fetchVideo() {
        videoView = (VideoView) findViewById(R.id.video_player_view);
        mediaController = new MediaController(this);
        displayMetrics = new DisplayMetrics();
        
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        
        mediaController.setAnchorView(videoView);
        videoView.setMinimumWidth(width);
        videoView.setMinimumHeight(height);
        videoView.setMediaController(mediaController);
        Log.d("videbug", "Video path set to " + videoPath);
        videoView.setVideoPath(videoPath);
        videoView.start();
    }
}
