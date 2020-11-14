package com.example.mooncalculator;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
//import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class AboutUsActivity extends AppCompatActivity {

    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    public void onClickHome(MenuItem item) {
        Statics.redirectActivity(this, MainActivity.class);
    }

    public void onClickConverter(MenuItem item) {
        Statics.redirectActivity(this, ConvertorActivity.class);
    }


//    public void onClickOurWebsite(MenuItem item) {
//
//    }

    public void onClickHistory(MenuItem item) {
        Statics.redirectActivity(this, HistoryActivity.class);
    }

//    public void onClickThemes(MenuItem item) {
//        Statics.redirectActivity(this, ThemeActivity.class);
//    }

//    public void onClickAboutUs(MenuItem item) {
//        drawerLayout.closeDrawer(GravityCompat.START);
//    }

    public void onClickExit(MenuItem item) {
        Statics.showExitDialog(this, drawerLayout);
    }

    @Override
    protected void onPause() {
        System.out.println(this.getLocalClassName());
        drawerLayout.closeDrawers();
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        Statics.showExitDialog(this,drawerLayout);
    }
}