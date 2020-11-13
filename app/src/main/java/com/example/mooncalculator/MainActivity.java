package com.example.mooncalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import org.mariuszgromada.math.mxparser.*;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.mooncalculator.Fragments.Main.BasicFragment;
import com.example.mooncalculator.Fragments.Main.EquationFragment;
import com.example.mooncalculator.Fragments.Main.ExpertFragment;
import com.google.android.material.navigation.NavigationView;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;


public class MainActivity extends AppCompatActivity {
    private View decorView;
    private EditText display;
    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    String Npi = "3.14159265";

    //todo make best structure for navigation menu with static class and inheritance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //drawer
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.nav_view);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //tab view
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add(R.string.expertFragmentTitle, ExpertFragment.class)
                .add(R.string.basicFragmentTitle, BasicFragment.class)
                .add(R.string.equationFragmentTitle, EquationFragment.class)
                .create());
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPagerMain);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(1);
        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewPagerTabMain);
        viewPagerTab.setViewPager(viewPager);


        //calculator
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


    //Navigation Menu
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

    //Navigation Menu END
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
//        String userExp = display.getText().toString();
//        userExp = userExp.replaceAll("÷", "/");
//        userExp = userExp.replaceAll("×", "*");
//        Expression exp = new Expression(userExp);
//        String result = String.valueOf(exp.calculate());
//        display.setText(result);
//        display.setSelection(result.length());
        String val = display.getText().toString();
        val = val.replace('÷', '/').replace('×', '*');
        Calculate calculate = new Calculate(val);
        display.setText(calculate.getResult());
        Statics.saveExpression(getSharedPreferences(Statics.SPName,MODE_PRIVATE),calculate.getExpression());

//        double result =  eval(replacedtr);
//        display.setText(String.valueOf(result));

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
        String string = display.getText().toString();
        if (string.length() == 0)
            updateText("-");
        else {
            for (int i = string.length() - 1; i > -1; i--) {
                char c = string.charAt(i);
                if (c == '(' || !Character.isLetterOrDigit(c)) {
                    string = string.substring(0, i + 1).concat("(-").concat(string.substring(i + 1));
                    break;
                }
            }
            display.setText(string);
        }

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

    //equation
    public void TabeYekMajhuli(View view) {
        updateText("f(X)");
    }

    public void TabeDoMajhuli(View view) {
        updateText("f(x,y)");
    }

    public void TabeSeMajhuli(View view) {
        updateText("f(x,y,z)");
    }

    public void X(View view) {
        updateText("x");
    }

    public void Y(View view) {
        updateText("y");
    }

    public void Z(View view) {
        updateText("z");
    }

    public void MoshtagTabe(View view) {
        updateText("f'(");
    }

    public void vurudi(View view) {
        updateText("f(");
    }

    void Camma(View view) {
        updateText(",");
    }

    public void QuestionMark(View view) {
        updateText("?");
    }

    public void Angah(View view) {
        updateText("→");
    }

    public void Eequals(View view) {
        updateText("=");
    }


    //scientific
    public void parantez(View view) {

    }

    public void dividedByOne(View view) {
        updateText("1/(");

    }

    public void onThePowerOfTwo(View view) {
        updateText("^2");
    }

    public void OnThePowerOfN(View view) {
        updateText("^(");
    }

    public void radicalrishe2(View view) {
        updateText("sqrt(");
    }

    public void radicalN(View view) {
        updateText("^(1/");
    }

    public void Sin(View view) {
        updateText("sin(");
    }

    public void Cos(View view) {
        updateText("");
    }

    public void Tan(View view) {
        updateText("tan(");
    }

    public void Round(View view) {
        updateText("round(");
    }

    public void DivideBySin(View view) {
        updateText("arcsin(");
    }

    public void DivideByCos(View view) {
        updateText("arccos(");
    }

    public void DivideByTan(View view) {
        updateText("arctan(");
    }

    public void AbsoluteValue(View view) {
        updateText("abs(");
    }

    public void Neper(View view) {
        updateText("e");
    }

    public void NeperOnThePowerOfX(View view) {
        updateText("e^(");
    }

    public void pi(View view) {
        updateText("pi");
    }

    public void fact(View view) {
        updateText("!");
    }

    public void max(View view) {
        updateText("max(");
    }

    public void min(View view) {
        updateText("min(");
    }

    public void logaritm(View view) {
        updateText("log(");
    }

    public void ln(View view) {
        updateText("ln(");
    }

    //factorial
    int factorial(int n) {

        // find factorial
        return (n == 1 || n == 0) ? 1 : n * factorial(n - 1);

    }
    //evaluation

    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (; ; ) {
                    if (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (; ; ) {
                    if (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("Sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("Cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("Tan")) x = Math.tan(Math.toRadians(x));
                    else if (func.equals("Logaritm")) x = Math.log10(x);
                    else if (func.equals("ln")) x = Math.log(x);
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }
}