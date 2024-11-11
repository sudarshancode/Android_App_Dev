package com.example.firebasemultiplevariable;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText text1, text2;
    Button submitButton, showButton,updateButton,deleteButton;
    TextView textView;
    DatabaseReference databaseReference;
    String name;
    int age;

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

        text1 = findViewById(R.id.editTextId1);
        text2 = findViewById(R.id.editTextId2);
        submitButton = findViewById(R.id.submitButtonId);
        showButton = findViewById(R.id.showButtonId);
        updateButton=(Button) findViewById(R.id.updateButtonId);
        deleteButton=(Button) findViewById(R.id.deleteButtonId);
        textView = findViewById(R.id.textViewId);


        databaseReference = FirebaseDatabase.getInstance().getReference("Person");


        submitButton.setOnClickListener(view -> {
            try {
                age = Integer.parseInt(text1.getText().toString());
                name = text2.getText().toString();
                HashMap hashMap=new HashMap();

                hashMap.put("age",age);
                hashMap.put("name",name);
                databaseReference.setValue(hashMap)
                            .addOnSuccessListener(unused -> Toast.makeText(MainActivity.this, "On success", Toast.LENGTH_LONG).show())
                            .addOnFailureListener(e -> Toast.makeText(MainActivity.this, "On Failure", Toast.LENGTH_LONG).show());
            } catch (NumberFormatException e) {
                Toast.makeText(MainActivity.this, "Invalid age entered", Toast.LENGTH_SHORT).show();
            }
        });

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Map<String,Object> map=(Map<String, Object>) snapshot.getValue();

                        Object age=map.get("age");
                        String name= (String) map.get("name");

                        textView.setText("Name:"+name+"\nAge:"+age);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                age = Integer.parseInt(text1.getText().toString());
                name = text2.getText().toString();
                HashMap hashMap=new HashMap();

                hashMap.put("age",age);
                hashMap.put("name",name);

                databaseReference.updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(MainActivity.this,"Update Success",Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Update Fail",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.child("name").removeValue();
            }
        });
    }
}
