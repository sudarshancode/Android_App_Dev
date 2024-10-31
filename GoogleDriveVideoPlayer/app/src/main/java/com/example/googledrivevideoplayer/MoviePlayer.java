package com.example.googledrivevideoplayer;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.ui.PlayerView;

public class MoviePlayer extends AppCompatActivity {

    ExoPlayer exoPlayer;
    PlayerView playerView;
    String videoUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_movie_player);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        videoUri=getIntent().getStringExtra("url");

        playerView=(PlayerView) findViewById(R.id.playerViewId);

        exoPlayer= new ExoPlayer.Builder(MoviePlayer.this).build();

        // Bind the player to the view.
        playerView.setPlayer(exoPlayer);

        // Build the media item.
        MediaItem mediaItem = MediaItem.fromUri(videoUri);
        // Set the media item to be played.
        exoPlayer.setMediaItem(mediaItem);
        // Prepare the player.
        exoPlayer.prepare();
        // Start the playback.
        exoPlayer.play();

    }
}