package com.example.mooncalculator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.drawerlayout.widget.DrawerLayout;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Statics {

    public static void showExitDialog(Activity activity, DrawerLayout drawerLayout) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Exit")
                .setMessage("Are you sure want to Exit ?!")
                .setPositiveButton("YES", (dialogInterface, i) -> {
                    activity.finishActivity(0);
                    activity.finish();
                    System.exit(0);
                }).setNegativeButton("NO", (dialogInterface, i) -> {
            drawerLayout.closeDrawers();
            dialogInterface.dismiss();
        }).show();
    }

    public static void redirectActivity(Activity activity, Class<?> aClass) {
        Intent intent = new Intent(activity, aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_TASK_ON_HOME);
        activity.startActivity(intent);
    }

    public static void saveExpression(@NotNull SharedPreferences sharedPreferences, String expression) {
        Map<String,String> map = (Map<String, String>) sharedPreferences.getAll();
        int lastIndex = 0;
        if (!map.isEmpty()) {
            Object[] keys = map.keySet().toArray();
            lastIndex= Integer.parseInt((String) keys[keys.length-1]);
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(String.valueOf(++lastIndex),expression);
        editor.apply();
    }

    @NotNull
    @Contract("_ -> new")
    public static List<String> getHistoryData(@NotNull SharedPreferences sharedPreferences){
        return new ArrayList<>((Collection<? extends String>) sharedPreferences.getAll().values());
    }

    public static void updateDataSP(@NotNull SharedPreferences sharedPreferences, @NotNull List<String> data){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        AtomicInteger index = new AtomicInteger(0);
        editor.clear();
        for (String str : data)
            editor.putString(String.valueOf(index.getAndIncrement()),str);
        editor.apply();
    }
}
