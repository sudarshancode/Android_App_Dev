package com.example.simplecalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button add,sub,mul,div;
    EditText num1,num2;
    TextView result;


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

        num1=(EditText) findViewById(R.id.firstNumber);
        num2=(EditText) findViewById(R.id.secondNumber);

        result=(TextView) findViewById(R.id.result);
        add=(Button) findViewById(R.id.addButton);
        sub=(Button) findViewById(R.id.subButton);
        mul=(Button) findViewById(R.id.mulButton);
        div=(Button) findViewById(R.id.divButton);

        add.setOnClickListener(this);
        sub.setOnClickListener(this);
        mul.setOnClickListener(this);
        div.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        float operationResult= 0 ;
        String number1=num1.getText().toString();
        String number2=num2.getText().toString();

        float firstNumber = Float.parseFloat(number1);
        float secondNumber = Float.parseFloat(number2);

        if(v.getId()==R.id.addButton){
            operationResult = firstNumber+secondNumber;
            String text = String.valueOf(operationResult);
            result.setText(text);

        }
        if (v.getId()==R.id.subButton) {
            operationResult = firstNumber-secondNumber;
            String text = String.valueOf(operationResult);
            result.setText(text);
        }
        if (v.getId()==R.id.mulButton) {
            operationResult = firstNumber*secondNumber;
            String text = String.valueOf(operationResult);
            result.setText(text);
        }
        if (v.getId() == R.id.divButton){
            operationResult = firstNumber/secondNumber;
            String text = String.valueOf(operationResult);
            result.setText(text);
        }
    }
}