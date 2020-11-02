package com.example.mooncalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import org.mariuszgromada.math.mxparser.*;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {
    private View decorView;
    private EditText display;
    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    //todo make best structure for navigation menu with static class and inheritance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.nav_view);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        display = findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getString(R.string.display).equals(display.getText().toString())) {
                    display.setText("");
                }
            }
        });
        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if (visibility == 0)
                    decorView.setSystemUiVisibility(hideSystemBars());
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    public void onClickHome(MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    public void onClickConverter(MenuItem item) {
        Statics.redirectActivity(this, ConvertorActivity.class);
    }


    public void onClickOurWebsite(MenuItem item) {

    }

    public void onClickHistory(MenuItem item) {
        Statics.redirectActivity(this, HistoryActivity.class);
    }

    public void onClickThemes(MenuItem item) {
        Statics.redirectActivity(this, ThemeActivity.class);
    }

    public void onClickAboutUs(MenuItem item) {
        Statics.redirectActivity(this, AboutUsActivity.class);
    }

    public void onClickExit(MenuItem item) {
        Statics.showExitDialog(this, drawerLayout);
    }


    private static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    private static void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    protected void onPause() {
        System.out.println(this.getLocalClassName());
        drawerLayout.closeDrawers();
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        Statics.showExitDialog(this, drawerLayout);
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            decorView.setSystemUiVisibility(hideSystemBars());
        }
    }

    private int hideSystemBars() {
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                 | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                 | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
    }

    private void updateText(String strToAdd) {
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        if (getString(R.string.display).equals(display.getText().toString())) {
            display.setText(strToAdd);
            display.setSelection(cursorPos + 1);
        } else {
            display.setText(String.format("%s%S%S", leftStr, strToAdd, rightStr));
            display.setSelection(cursorPos + 1);
        }

    }

    public void ZeroBTN(View view) {
        updateText("0");

    }

    public void DotBTN(View view) {
        updateText(".");

    }

    public void EqualsBTN(View view) {
        String userExp = display.getText().toString();
        userExp = userExp.replaceAll("÷", "/");
        userExp = userExp.replaceAll("×", "*");
        Expression exp = new Expression(userExp);
        String result = String.valueOf(exp.calculate());
        display.setText(result);
        display.setSelection(result.length());
    }

    public void OneBTN(View view) {
        updateText("1");

    }

    public void TwoBTN(View view) {
        updateText("2");

    }

    public void ThreeBTN(View view) {
        updateText("3");

    }

    public void FourBTN(View view) {
        updateText("4");

    }

    public void FiveBTN(View view) {
        updateText("5");

    }

    public void SixBTN(View view) {
        updateText("6");
    }

    public void SevenBTN(View view) {
        updateText("7");
    }

    public void EightBTN(View view) {
        updateText("8");

    }

    public void NineBTN(View view) {
        updateText("9");

    }

    public void PlusBtn(View view) {
        updateText("+");

    }

    public void MinusBtn(View view) {
        updateText("-");

    }

    public void MultiplyBtn(View view) {
        updateText("×");

    }

    public void DivideBtn(View view) {
        updateText("÷");

    }

    public void ClearBtn(View view) {
        display.setText("");


    }

    public void PlusMinusBtn(View view) {

    }

    public void PercentBtn(View view) {
        updateText("%");

    }

    public void BackSpaceBtn(View view) {
        int cursorPos = display.getSelectionStart();
        int textlen = display.getText().length();
        if (cursorPos != 0 && textlen != 0) {
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos - 1, cursorPos, "");
            display.setText(selection);
            display.setSelection(cursorPos - 1);
        }

    }


}