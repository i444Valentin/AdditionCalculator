package com.application.mathmachine.additioncalculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    TextView resultView;
    Integer firstTerm;
    Integer secondTerm;
    String secondTermString;
    String firstTermString;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        resultView = findViewById(R.id.ResultText);
        Bundle terms = getIntent().getExtras(); //init storage values
        if (terms != null) {
            firstTerm = terms.getInt("first_term"); //get first term
            secondTerm = terms.getInt("second_term"); //get second term
            String builtText = resultTextBuild();
            printInTextView(builtText);
        }

    }

    /**
     * Builds result text for print in a TextView
     *
     * @return - built text
     */
    private String resultTextBuild() {
        if (secondTerm < 0) {
            secondTermString = "(" + secondTerm.toString() + ")";
        } else {
            secondTermString = secondTerm.toString();
        }
        firstTermString = firstTerm.toString();
        return firstTermString + " + " + secondTermString + " = " + (firstTerm + secondTerm);
    }

    /**
     * Prints text in a TextView
     *
     * @param text - string
     */
    private void printInTextView(String text) {

        resultView.setText(text);
    }
}