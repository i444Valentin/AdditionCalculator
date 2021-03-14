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
    TextView exceptionMessage;
    TextView exceptionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText firstTermEditText = findViewById(R.id.firstTerm);
        EditText secondTermEditText = findViewById(R.id.secondTerm);
        errorMessage = findViewById(R.id.errorMessage);
        exceptionMessage = findViewById(R.id.exceptionMessage);
        exceptionText = findViewById(R.id.exceptionText);
        addButton = (Button) findViewById(R.id.addButton);
        try {
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
        } catch (Exception exception) {
            exception.printStackTrace();
            exceptionMessage.setVisibility(View.VISIBLE);
            exceptionText.setVisibility(View.VISIBLE);
            exceptionText.setText(exception.getMessage());
        }

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
        outState.putInt("exc_msg_vis", exceptionMessage.getVisibility());
        outState.putInt("exc_text_vis", exceptionText.getVisibility());
        outState.putString("exc_msg", exceptionText.getText().toString());

    }

    /**
     * Restores all values which writes in here
     *
     * @param savedInstanceState saved state
     */
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (!savedInstanceState.isEmpty()) {
            int viewVisibilityErrorMsg = savedInstanceState.getInt("err_msg_vis");
            int viewVisibilityExceptionMsg = savedInstanceState.getInt("exc_msg_vis");
            int viewVisibilityExceptionText = savedInstanceState.getInt("exc_text_vis");
            String exceptionTextString = savedInstanceState.getString("exc_msg");
            textViewUpdate(viewVisibilityErrorMsg, viewVisibilityExceptionMsg, viewVisibilityExceptionText, exceptionTextString);
        }

    }

    /**
     * This method updates these TextViews
     *
     * @param viewVisibilityErrorMsg      -
     * @param viewVisibilityExceptionMsg  -
     * @param viewVisibilityExceptionText - key of TextView visibility
     * @param exceptionTextString         - Exception TextView text
     */
    private void textViewUpdate(int viewVisibilityErrorMsg, int viewVisibilityExceptionMsg, int viewVisibilityExceptionText, String exceptionTextString) {
        errorMessage.setVisibility(viewVisibilityErrorMsg);
        exceptionMessage.setVisibility(viewVisibilityExceptionMsg);
        exceptionText.setVisibility(viewVisibilityExceptionText);
        exceptionText.setText(exceptionTextString);
    }
}