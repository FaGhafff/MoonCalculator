package com.example.mooncalculator;

import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private View decorView;
    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);
         display.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (getString(R.string.display).equals(display.getText().toString())){
                     display.setText("");
                 }
             }
         });

        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if(visibility==0)
                    decorView.setSystemUiVisibility(hideSystemBars());
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
         if (hasFocus){
             decorView.setSystemUiVisibility(hideSystemBars());
    }
}
    private int hideSystemBars(){
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
               // | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
               // | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
    }
    private void updateText(String strToAdd){
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0,cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        if (getString(R.string.display).equals(display.getText().toString()) ){
            display.setText(strToAdd);
            display.setSelection(cursorPos +1);
        }
        else{
            display.setText(String.format("%s%S%S",leftStr,strToAdd,rightStr));
            display.setSelection(cursorPos +1);
        }

    }
    public void ZeroBTN(View view){
        updateText("0");

    }
    public void DotBTN(View view){
        updateText(".");

    }
    public void EqualsBTN(View view){
        String userExp = display.getText().toString();
        userExp = userExp.replaceAll("÷" ,"/");
        userExp = userExp.replaceAll("×","*");
        Expression exp = new Expression(userExp);
        String result = String.valueOf(exp.calculate());
        display.setText(result);
        display.setSelection(result.length());
    }
    public void OneBTN(View view){
        updateText("1");

    }
    public void TwoBTN(View view){
        updateText("2");

    }public void ThreeBTN(View view){
        updateText("3");

    }public void FourBTN(View view){
        updateText("4");

    }public void FiveBTN(View view){
        updateText("5");

    }public void SixBTN(View view) {
        updateText("6");
    }
    public void SevenBTN(View view){
        updateText("7");
    }
    public void EightBTN(View view){
        updateText("8");

    }
    public void NineBTN(View view){
        updateText("9");

    }
    public void PlusBtn(View view){
        updateText("+");

    }
    public void MinusBtn(View view){
        updateText("-");

    }  public void MultiplyBtn(View view){
        updateText("×");

    }  public void DivideBtn(View view){
        updateText("÷");

    }  public void ClearBtn(View view){
        display.setText("");


    }  public void PlusMinusBtn(View view){

    }  public void PercentBtn(View view){
        updateText("%");

    }  public void BackSpaceBtn(View view){
        int cursorPos = display.getSelectionStart();
        int textlen = display.getText().length();
        if (cursorPos !=0 && textlen !=0){
            SpannableStringBuilder selection = (SpannableStringBuilder)display.getText();
            selection.replace(cursorPos-1,cursorPos,"");
            display.setText(selection);
            display.setSelection(cursorPos-1);
        }

    }





}