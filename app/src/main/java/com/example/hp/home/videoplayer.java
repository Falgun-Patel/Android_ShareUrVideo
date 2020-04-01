package com.example.hp.home;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.MediaController;
import android.widget.VideoView;

public class videoplayer extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoplayer);

       final VideoView videoView;
       videoView =findViewById(R.id.videoView);

        //Creating MediaController
        //final MediaController mediaController= new MediaController(this);
        //mediaController.setAnchorView(videoView);

        //specify the location of media file
       //Uri uri=Uri.parse("android:resource://"+getPackageName()+"//"+R.raw.dd);

        //Setting MediaController and URI, then starting the videoView
        //videoView.setMediaController(mediaController);
        //videoView.setVideoURI(uri);
        int rawId = getResources().getIdentifier("dd",  "raw", getPackageName());

// URI formation
        String path = "android.resource://" + getPackageName() + "/" + rawId;

// Set the URI to play video file
        videoView.setVideoURI(Uri.parse(path));

        videoView.start();

    }


}
