package com.example.mooncalculator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class HistoryActivity extends AppCompatActivity {


    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private List<String> data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
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

        //get data from sharedpref
//        SharedPreferences sharedPreferences = getSharedPreferences("history",MODE_PRIVATE);
//        Map<String,String> data = (Map<String, String>) sharedPreferences.getAll();

        data = new ArrayList<>();

        //some EXs
        data.add("12+12+12");
        data.add("I love you fati");
        data.add("you are very beautiful");

        //

        recyclerView = findViewById(R.id.historyRecyclerView);
        recyclerAdapter = new RecyclerAdapter(data);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(recyclerAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }


    String selectedRow = null;
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT |
            ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            final int position = viewHolder.getAdapterPosition();
            selectedRow = data.get(position);
            switch (direction) {
                case ItemTouchHelper.LEFT:
                    data.remove(position);
                    recyclerAdapter.notifyItemRemoved(position);
                    Snackbar.make(recyclerView, selectedRow, Snackbar.LENGTH_LONG).setAction("Undo", view -> {
                        data.add(position, selectedRow);
                        recyclerAdapter.notifyItemInserted(position);
                    }).show();
                    break;
                case ItemTouchHelper.RIGHT:
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("text", data.get(position).toString());
                    clipboard.setPrimaryClip(clip);
                    data.remove(position);
                    recyclerAdapter.notifyItemRemoved(position);
                    data.add(position, selectedRow);
                    recyclerAdapter.notifyItemInserted(position);
                    Snackbar.make(recyclerView, "Expression Copied to clipboard !", Snackbar.LENGTH_SHORT).show();
                    break;
            }
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(HistoryActivity.this, R.color.swipeLeftBackground))
                    .addSwipeLeftActionIcon(R.drawable.ic_sharp_delete_sweep_24)
                    .addSwipeRightBackgroundColor(ContextCompat.getColor(HistoryActivity.this, R.color.swipeRightBackground))
                    .addSwipeRightActionIcon(R.drawable.ic_sharp_content_copy_24).create()
                    .decorate();

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };

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


    public void onClickOurWebsite(MenuItem item) {

    }

    public void onClickHistory(MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
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
}