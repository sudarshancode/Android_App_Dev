package com.example.retrofitapisenddata;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText name, email;
    Button submitButton;
    String admin_name,admin_email;
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
        name = (EditText) findViewById(R.id.nameId);
        email =(EditText) findViewById(R.id.emailId);
        submitButton = findViewById(R.id.buttonId);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                admin_name=name.getText().toString();
                admin_email=email.getText().toString();

                insertData(admin_name,admin_email);

            }
        });

    }
    public void initialValue(){
        name.setText(" ");
        email.setText(" ");
    }
    private void insertData(String name, String email) {

        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://192.168.0.105/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        ApiInterface apiInterface=retrofit.create(ApiInterface.class);

        Call<Model> call=apiInterface.addData(name,email);

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                if (response.isSuccessful()) {

                    initialValue();
                    // Handle successful response
                    Toast.makeText(getApplicationContext(), "Insert Successful", Toast.LENGTH_SHORT).show();
                } else {
                    // Handle unsuccessful response (e.g., 404, 500 errors)
                    initialValue();
                    Toast.makeText(getApplicationContext(), "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable throwable) {
                Toast.makeText(getApplicationContext(),throwable.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}