package com.example.myintentdatapass;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class country_activity extends AppCompatActivity {
    TextView textView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_country);
        textView=(TextView) findViewById(R.id.textViewId);
        Bundle bundle=getIntent().getExtras();

        if(bundle != null){
            String name=bundle.getString("tag");
            textView.setText("Welcome to "+name);
        }
    }
}