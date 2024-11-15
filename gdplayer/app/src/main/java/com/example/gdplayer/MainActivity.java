package com.example.gdplayer;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.ui.PlayerView;

public class MainActivity extends AppCompatActivity {
    PlayerView playerView;
    ExoPlayer player;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        playerView = findViewById(R.id.playerId);

        // Initialize the ExoPlayer
        player = new ExoPlayer.Builder(this).build();
        playerView.setPlayer(player);

        String googleDriveUrl = "https://drive.google.com/uc?export=download&id=1R0-w-ys7kK360VBy_gieSYfwgm96c_i6";

        // Create a MediaItem and set it to the player
        MediaItem mediaItem = MediaItem.fromUri(Uri.parse(googleDriveUrl));
        player.setMediaItem(mediaItem);
        player.prepare();
        player.play();
    }
    protected void onStop() {
        super.onStop();
        player.release();
    }
}