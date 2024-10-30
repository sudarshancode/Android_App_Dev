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
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    EditText text1,text2;
    Button submitButton,showButton;
    TextView textView;
    DatabaseReference databaseReference;
    int id;
    String name;
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
        text1=(EditText) findViewById(R.id.editTextId1);
        text2=(EditText) findViewById(R.id.editTextId2);
        submitButton=(Button) findViewById(R.id.submitButtonId);
        showButton=(Button) findViewById(R.id.showButtonId);

        databaseReference= FirebaseDatabase.getInstance().getReference().child("MyDb");




        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id=Integer.parseInt(text1.getText().toString());
                name=text2.getText().toString();
                HashMap hashMap=new HashMap();
                hashMap.put("id",id);
                hashMap.put("name",name);

                databaseReference.child("User1").setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(MainActivity.this,"On sucess",Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this,"On Failure",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

       showButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               databaseReference.child("User1").addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot snapshot) {
                       if(snapshot.exists()){
                           Map<String, Object> map= (Map<String, Object>) snapshot.getValue();

                           Object id=map.get("id");
                           String name=map.get("name").toString();

                           textView.setText(" "+id);
                       }

                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError error) {

                   }
               });
           }
       });
    }
}