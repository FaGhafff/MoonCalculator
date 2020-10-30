package com.example.mooncalculator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;



public class MenuActivity extends AppCompatActivity {
    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

//todo make best structure for navigation menu with static class and inheritance
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
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
        Statics.showExitDialog(this,drawerLayout);
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
}
