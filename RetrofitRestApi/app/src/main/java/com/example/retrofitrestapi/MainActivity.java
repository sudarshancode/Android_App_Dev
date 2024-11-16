package com.example.retrofitrestapi;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitrestapi.Adapter.CustomAdapter;
import com.example.retrofitrestapi.Model.PhotoModel;
import com.example.retrofitrestapi.Network.PhotoServiceApi;
import com.example.retrofitrestapi.Network.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    CustomAdapter customAdapter;

    PhotoServiceApi photoServiceApi;
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

        recyclerView=findViewById(R.id.recylerViewId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        photoServiceApi=RetrofitService.getService();

        photoServiceApi.getAllPhotos().enqueue(new Callback<List<PhotoModel>>() {
            @Override
            public void onResponse(Call<List<PhotoModel>> call, Response<List<PhotoModel>> response) {
                List<PhotoModel> photoModels=response.body();
                customAdapter=new CustomAdapter(MainActivity.this,photoModels);

                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MainActivity.this);

                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(customAdapter);
                Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<PhotoModel>> call, Throwable throwable) {
                Toast.makeText(MainActivity.this,"Failure",Toast.LENGTH_LONG).show();
            }
        });
    }
}