package com.example.googledrivevideoplayer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button movie1,movie2;
    String movie1Url1="https://www.googleapis.com/drive/v3/files/1PMGM7NhoqENlqfDe_yQuu4qWyuojPTuM?alt=media&key=AIzaSyAQF1FRwLVRwTsV8B_SWXZ7nzuNH0oxWFY";
    String movieUrl2="https://www.googleapis.com/drive/v3/files/1xwjS50z2hSYlzXG4SDRyhC836MdWo1Ll?alt=media&key=AIzaSyAQF1FRwLVRwTsV8B_SWXZ7nzuNH0oxWFY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        movie1=(Button) findViewById(R.id.button1);
        movie2=(Button) findViewById(R.id.button2);

        movie1.setOnClickListener(this);
        movie2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button1){
            Intent intent=new Intent(MainActivity.this, MoviePlayer.class);
            intent.putExtra("url",movie1Url1);
            startActivity(intent);
        }
        if(view.getId() == R.id.button2){
            Intent intent=new Intent(MainActivity.this, MoviePlayer.class);
            intent.putExtra("url",movieUrl2);
            startActivity(intent);
        }
    }
}