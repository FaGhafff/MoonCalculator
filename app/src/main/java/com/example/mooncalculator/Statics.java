package com.example.mooncalculator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class Statics {
    public static void showExitDialog(Activity activity,DrawerLayout drawerLayout) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Exit")
                .setMessage("Are you sure want to Exit ?!")
                .setPositiveButton("YES", (dialogInterface, i) -> {
                    activity.finishActivity(0);
                    System.exit(0);
                }).setNegativeButton("NO", (dialogInterface, i) -> {
                    drawerLayout.closeDrawers();
                    dialogInterface.dismiss();
                }).show();
    }

    public static void redirectActivity(Activity activity, Class<?> aClass) {
        Intent intent = new Intent(activity, aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

}
