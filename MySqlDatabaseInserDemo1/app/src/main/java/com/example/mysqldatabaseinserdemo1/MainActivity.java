package com.example.mysqldatabaseinserdemo1;

import static android.widget.Toast.LENGTH_SHORT;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText email, password;
    Button submitButton;
    DataBaseActivity dataBaseActivity;
    @SuppressLint("MissingInflatedId")
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

        dataBaseActivity=new DataBaseActivity(this);

        SQLiteDatabase sqLiteDatabase= dataBaseActivity.getWritableDatabase();

        email=(EditText) findViewById(R.id.emailId);
        password=(EditText) findViewById(R.id.passwordId);
        submitButton=(Button) findViewById(R.id.submitbuttonId);

        submitButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        submitButton.getBackground().setAlpha(100);
                        break;

                    case MotionEvent.ACTION_UP:
                        String emailId = email.getText().toString();
                        String passWord = password.getText().toString();
                        if (emailId == "" && passWord == "") {
                            Toast.makeText(MainActivity.this, "Empty Value", LENGTH_SHORT).show();
                        } else {
                            long rowId = (long) dataBaseActivity.insertData(emailId, passWord);

                            if (rowId > 0) {
                                Toast.makeText(MainActivity.this, "Account Created", LENGTH_SHORT).show();
                                email.setText("");
                                password.setText("");
                            } else {
                                Toast.makeText(MainActivity.this, "Has any Problem", LENGTH_SHORT).show();
                            }
                            submitButton.getBackground().setAlpha(255);
                            break;
                        }
                    default:
                        break;
                }
                return true;
            }
        });

    }
}