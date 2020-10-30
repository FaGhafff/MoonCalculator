package com.example.mooncalculator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class AboutUsActivity extends AppCompatActivity {

    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.nav_view);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(item -> {
            Toast.makeText(this, item.getTitle().toString(), Toast.LENGTH_SHORT).show();
            return true;
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    public void onClickHome(MenuItem item) {
        Statics.redirectActivity(this, MenuActivity.class);
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
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    public void onClickExit(MenuItem item) {
        Statics.showExitDialog(this, drawerLayout);
    }

    @Override
    protected void onPause() {
        System.out.println(this.getLocalClassName());
        drawerLayout.closeDrawers();
        super.onPause();
    }

}