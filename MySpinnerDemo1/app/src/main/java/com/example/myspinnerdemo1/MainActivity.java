package com.example.myspinnerdemo1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    String[] countryName;
    int[] flags={R.drawable.india,
            R.drawable.bangladesh,
            R.drawable.nepal,
            R.drawable.bhutan,
            R.drawable.america,
            R.drawable.germany,
            R.drawable.arabia,
            R.drawable.indonesia,
            R.drawable.america,
            R.drawable.italy,
            R.drawable.lanka,
            R.drawable.china};
    public boolean isSelected=true;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        spinner=(Spinner) findViewById(R.id.spinnerId);
        countryName= getResources().getStringArray(R.array.country_name);

        CustomAdapter customAdapter=new CustomAdapter(MainActivity.this,countryName,flags);

        spinner.setAdapter(customAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(isSelected == true){
                    isSelected=false;
                }else{
                    String country_name=countryName[position];
                    Toast.makeText(MainActivity.this,country_name+" is selected",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}