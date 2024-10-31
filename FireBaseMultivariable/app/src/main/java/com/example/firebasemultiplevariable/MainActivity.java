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

public class MainActivity extends AppCompatActivity {
    EditText text1, text2;
    Button submitButton, showButton;
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
        textView = findViewById(R.id.textViewId);


        databaseReference = FirebaseDatabase.getInstance().getReference("Person");


        submitButton.setOnClickListener(view -> {
            try {
                age = Integer.parseInt(text1.getText().toString());
                name = text2.getText().toString();

                Person person = new Person(name, age);

                databaseReference.setValue(person)
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

                        Person person=snapshot.getValue(Person.class);

                        textView.setText("Name:"+person.name+"\n Age: "+person.age);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }
}
