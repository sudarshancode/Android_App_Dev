package com.example.myintentdatapass;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button button;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        button=(Button) findViewById(R.id.buttonId);

        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        button.getBackground().setAlpha(100);
                        break;
                    case MotionEvent.ACTION_UP:
                        Intent intent=new Intent(MainActivity.this, country_activity.class);
                        intent.putExtra("tag","India");
                        startActivity(intent);
                        button.getBackground().setAlpha(255);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });


    }
}