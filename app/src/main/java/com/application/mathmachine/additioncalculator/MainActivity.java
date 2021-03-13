package com.application.mathmachine.additioncalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    Button addButton;
    Integer firstTerm;
    Integer secondTerm;
    TextView errorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText firstTermEditText = findViewById(R.id.firstTerm);
        EditText secondTermEditText = findViewById(R.id.secondTerm);
        errorMessage = findViewById(R.id.errorMessage);


        addButton = (Button) findViewById(R.id.addButton);

        View.OnClickListener oAddButton = v -> {
            if (!isNumber(firstTermEditText, secondTermEditText)) {
                errorMessage.setVisibility(View.VISIBLE);
                return;
            }
            errorMessage.setVisibility(View.INVISIBLE);
            firstTerm = Integer.parseInt(firstTermEditText.getText().toString());
            secondTerm = Integer.parseInt(secondTermEditText.getText().toString());
            Intent intentSecond = new Intent(this, SecondActivity.class);
            intentSecond.putExtra("first_term", firstTerm);
            intentSecond.putExtra("second_term", secondTerm);
            startActivity(intentSecond);

        };
        addButton.setOnClickListener(oAddButton);
    }

    /**
     * Check is number
     * This method checks two EditTexts (Inputs) on a number valid
     *
     * @param firstTerm  - first EditText
     * @param secondTerm - second EditText
     * @return - isNumber (true/false)
     */
    public boolean isNumber(EditText firstTerm, EditText secondTerm) {
        return (firstTerm.getText().toString().matches("^-?([0-9]+)+$")
                && secondTerm.getText().toString().matches("^-?([0-9]+)+$"));
    }

    /**
     * Saves all values which writes in here
     *
     * @param outState current state
     */
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("err_msg_vis", errorMessage.getVisibility());

    }

    /**
     * Restores all values which writes in here
     *
     * @param savedInstanceState saved state
     */
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int viewVisibility = savedInstanceState.getInt("err_msg_vis");
        errorMessage.setVisibility(viewVisibility);
    }
}