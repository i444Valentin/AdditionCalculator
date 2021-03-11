package com.application.mathmachine.additioncalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button addButton;
    Integer firstTerm;
    Integer secondTerm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText firstTermEditText = findViewById(R.id.firstTerm);
        EditText secondTermEditText = findViewById(R.id.secondTerm);

        firstTerm = Integer.parseInt(firstTermEditText.getText().toString());
        secondTerm = Integer.parseInt(secondTermEditText.getText().toString());
        addButton = (Button) findViewById(R.id.addButton);

        View.OnClickListener oAddButton = v -> {
            Intent intentSecond = new Intent(this,SecondActivity.class);
            startActivity(intentSecond);
            intentSecond.putExtra("first_term",firstTerm);
            intentSecond.putExtra("second_term",secondTerm);

        };
        addButton.setOnClickListener(oAddButton);
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }
}