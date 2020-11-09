package com.example.mooncalculator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mooncalculator.Fragments.Converter.AreaFragment;
import com.example.mooncalculator.Fragments.Converter.DataStorageFragment;
import com.example.mooncalculator.Fragments.Converter.TemperatureFragment;
import com.example.mooncalculator.Fragments.Converter.VolumeFragment;
import com.example.mooncalculator.Fragments.Converter.LengthFragment;
import com.example.mooncalculator.Fragments.Converter.MassFragment;
import com.example.mooncalculator.Fragments.Converter.SpeedFragment;
import com.example.mooncalculator.Fragments.Converter.TimeFragment;
import com.google.android.material.navigation.NavigationView;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class ConvertorActivity extends AppCompatActivity {


    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convertor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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



        //tab view
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add(R.string.areaFragmentTitle, AreaFragment.class)
                .add(R.string.dataStorageFragmentTitle, DataStorageFragment.class)
                .add(R.string.dataTransferRateFragmentTitle, TemperatureFragment.class)
                .add(R.string.energyFragmentTitle, VolumeFragment.class)
                .add(R.string.lengthFragmentTitle,LengthFragment.class)
                .add(R.string.massFragmentTitle,MassFragment.class)
                .add(R.string.speedFragmentTitle, SpeedFragment.class)
                .add(R.string.timeFragmentTitle, TimeFragment.class)
                .create());

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPagerConverter);
        viewPager.setAdapter(adapter);

        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewPagerTabConverter);
        viewPagerTab.setViewPager(viewPager);

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
        drawerLayout.closeDrawer(GravityCompat.START);
    }


    public void onClickOurWebsite(MenuItem item) {

    }

    public void onClickHistory(MenuItem item) {
        Statics.redirectActivity(this, HistoryActivity.class);
    }

    public void onClickThemes(MenuItem item) {
        Statics.redirectActivity(this,ThemeActivity.class);
    }

    public void onClickAboutUs(MenuItem item) {
        Statics.redirectActivity(this, AboutUsActivity.class);
    }

    public void onClickExit(MenuItem item) {
        Statics.showExitDialog(this,drawerLayout);
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