package com.example.searchbardemo1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    String[] movieName;
    int[] poster={R.drawable.awara,R.drawable.bhootpori,R.drawable.bindas,R.drawable.black,
            R.drawable.devi,R.drawable.fida,R.drawable.genarationami,R.drawable.kabir,R.drawable.love,
            R.drawable.mirza,R.drawable.nandini,R.drawable.purush,R.drawable.sakkhi,R.drawable.villain
    };
    CustomAdapter customAdapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        gridView=(GridView) findViewById(R.id.gridViewId);

        movieName=getResources().getStringArray(R.array.movie_name);

        customAdapter=new CustomAdapter(MainActivity.this,movieName,poster);

        gridView.setAdapter(customAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String movie=movieName[position];

                Toast.makeText(MainActivity.this,movie+" is clicked",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.topmenu,menu);

        //Find search button item from menu

        MenuItem menuItem=menu.findItem(R.id.searchButtonId);
        SearchView searchView= (SearchView) menuItem.getActionView();

        assert searchView != null;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}