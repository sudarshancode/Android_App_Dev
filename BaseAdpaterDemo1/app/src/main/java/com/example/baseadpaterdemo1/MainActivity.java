package com.example.baseadpaterdemo1;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    String[] countryNames;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        listView=(ListView) findViewById(R.id.listViewId);
        countryNames=getResources().getStringArray(R.array.country_name);
        CustomAdapter customAdapter;
        customAdapter = new CustomAdapter(MainActivity.this,countryNames,flags);

        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String mess=countryNames[position];

                Toast.makeText(MainActivity.this,mess+" is clicked",Toast.LENGTH_SHORT).show();
            }
        });
    }
}