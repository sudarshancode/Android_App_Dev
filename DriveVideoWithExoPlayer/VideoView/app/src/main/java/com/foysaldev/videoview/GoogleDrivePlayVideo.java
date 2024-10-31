package com.foysaldev.videoview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class GoogleDrivePlayVideo extends AppCompatActivity {


    String driveLink = "https://drive.google.com/file/d/1uvHYZetRqUIBsBchr4PuUkGv9murhAuC/view";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.google_drive_play_video);


        findViewById(R.id.play_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentvideo = new Intent(GoogleDrivePlayVideo.this, DriveVideoPlayer.class);
                String videoUrl = GoogleDriveLinkExtractor.extractDirectDownloadLink(driveLink);
                intentvideo.putExtra("url", videoUrl);
                startActivity(intentvideo);
            }
        });

    }


    public static class GoogleDriveLinkExtractor {
        public static String extractDirectDownloadLink(String viewLink) {
            String fileId = extractFileId(viewLink);
            if (fileId != null) {
                return "https://drive.google.com/uc?export=download&id=" + fileId;
            }
            return null;
        }

        private static String extractFileId(String viewLink) {
            Uri uri = Uri.parse(viewLink);
            String path = uri.getPath();
            if (path != null) {
                if (path.startsWith("/file/d/")) {
                    int startIndex = "/file/d/".length();
                    int endIndex = path.indexOf('/', startIndex);
                    if (endIndex != -1) {
                        return path.substring(startIndex, endIndex);
                    }
                } else if (path.startsWith("/drive/folders/")) {
                    int startIndex = "/drive/folders/".length();
                    int endIndex = path.indexOf('/', startIndex);
                    if (endIndex != -1) {
                        return path.substring(startIndex, endIndex);
                    }
                } else if (path.startsWith("/file/")) {
                    String[] segments = path.split("/");
                    if (segments.length >= 4) {
                        return segments[3];
                    }
                }
            }
            return null;
        }
    }
}