package com.example.myapplication;

import static java.lang.Integer.*;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public EditText nameText,ageText;
    public Button submitButton,displayButton;
    StringBuffer stringBuffer;
    DataBaseActivity dataBseActivity;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        dataBseActivity = new DataBaseActivity(this);

        SQLiteDatabase sqLiteDatabase = dataBseActivity.getWritableDatabase();

        nameText=(EditText) findViewById(R.id.nameTextId);
        ageText=(EditText) findViewById(R.id.ageTextId);

        submitButton=(Button) findViewById(R.id.submitButtonId);
        displayButton=(Button) findViewById(R.id.displayButtonId);

        submitButton.setOnClickListener(this);
        displayButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        String name=nameText.getText().toString();
        String age=ageText.getText().toString();


        if(v.getId() == R.id.submitButtonId){
            if(name.isEmpty() && age.isEmpty()){
                Toast.makeText(MainActivity.this,"Please enter value",Toast.LENGTH_SHORT).show();
            }else{
                long rowId=(long) dataBseActivity.insertData(name,age);

                if (rowId > 0) {
                    Toast.makeText(MainActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                    nameText.setText("");
                    ageText.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Has any Problem", Toast.LENGTH_SHORT).show();
                }
            }

        }

        if(v.getId() == R.id.displayButtonId){

            Cursor cursor=dataBseActivity.displayData();
            stringBuffer=new StringBuffer();
            if(cursor.getColumnCount()==0){
                Toast.makeText(MainActivity.this,"Value is not available",Toast.LENGTH_SHORT).show();
                return;
            }

            while(cursor.moveToNext()){
                stringBuffer.append("Id: "+cursor.getString(0)+"\n");
                stringBuffer.append("Name: "+cursor.getString(1)+"\n");
                stringBuffer.append("Age: "+cursor.getString(2)+"\n\n\n");

            }
            showData("Student Detials",stringBuffer.toString());
        }

    }

    public  void showData(String title, String value){
        AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);

        alertDialog.setTitle(title);
        alertDialog.setMessage(value);
        alertDialog.setCancelable(true);
        alertDialog.show();
    }
}