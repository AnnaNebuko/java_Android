package com.nebuko.mycalculator;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import org.mariuszgromada.math.mxparser.*;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.textView);
        display.setShowSoftInputOnFocus(false);
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getString(R.string.display).equals(display.getText().toString())){
                    display.setText("");
                }
            }
        });
    }
    private void upDateText(String strToAdd){
        String oldString = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldString.substring(0, cursorPos);
        String rightStr = oldString.substring(cursorPos);
        if (getString(R.string.display).equals(display.getText().toString())){
            display.setText(strToAdd);
            display.setSelection(cursorPos + 1);
        }
        else {
            display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
            display.setSelection(cursorPos + 1);
        }
    }
    public void zeroBTN (View view){
        upDateText("0");
    }
    public void doubleZeroBTN (View view){
        upDateText("00");
    }
    public void oneBTN (View view){
        upDateText("1");
    }
    public void twoBTN (View view){
        upDateText("2");
    }
    public void threeBTN (View view){
        upDateText("3");
    }
    public void fourBTN (View view){
        upDateText("4");
    }
    public void fiveBTN (View view){
        upDateText("5");
    }
    public void sixBTN (View view){
        upDateText("6");
    }
    public void sevenBTN (View view){
        upDateText("7");
    }
    public void eightBTN (View view){
        upDateText("8");
    }
    public void nineBTN (View view){
        upDateText("9");
    }
    public void multiplyBTN (View view){
        upDateText("*");
    }
    public void divideBTN (View view){
        upDateText("/");
    }
    public void subtractBTN (View view){
        upDateText("-");
    }
    public void addBTN (View view){
        upDateText("+");
    }
    public void clearBTN (View view){
        display.setText("");
    }
    public void exponentBTN (View view){ upDateText("^");
    }
    public void logBTN (View view){
        int cursorPos = display.getSelectionStart();
        int open = 0;
        int close = 0;
        int textLen = display.getText().length();
        for (int i = 0; i < cursorPos; i++) {
            if (display.getText().toString().substring(i, i + 1).equals("(")) {
                open += 1;
            }
            if (display.getText().toString().substring(i, i + 1).equals(")")) {
                close += 1;
            }
        }
            if(open == close || display.getText().toString().substring(textLen-1,textLen).equals("(")){
                upDateText("(");
            }
            else if(close < open && !display.getText().toString().substring(textLen-1,textLen).equals(")")){
                upDateText(")");
            }
            display.setSelection(cursorPos+1);

    }
    public void pointBTN (View view){
        upDateText(".");
    }
    public void equalBTN (View view){
        String userExp = display.getText().toString();
        Expression exp = new Expression(userExp);
        String result = String.valueOf(exp.calculate());
        display.setText (result);
        display.setSelection(result.length());
    }
    public void backspaceBTN (View view){
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if (cursorPos != 0 && textLen != 0){
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos-1, cursorPos, "");
            display.setText(selection);
            display.setSelection(cursorPos - 1);
        }
    }

}