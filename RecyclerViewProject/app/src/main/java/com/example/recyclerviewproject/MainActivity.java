package com.example.recyclerviewproject;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    int[] movies={R.drawable.adven,R.drawable.awara,R.drawable.babu,R.drawable.bachchan,R.drawable.guru,
            R.drawable.harry,R.drawable.hero,R.drawable.kunmfu,R.drawable.layla,
            R.drawable.panther,R.drawable.swapno,R.drawable.woman};

    String[] movieNames;
    RecyclerView recyclerView;
    CustomAdapter customAdapter;
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
        movieNames=getResources().getStringArray(R.array.movie_name);

        recyclerView=(RecyclerView) findViewById(R.id.recyclerViewId);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        customAdapter=new CustomAdapter(MainActivity.this,movieNames,movies);
        recyclerView.setAdapter(customAdapter);

    }
}