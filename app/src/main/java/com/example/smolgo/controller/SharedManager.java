package com.example.smolgo.controller;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedManager {
    private SharedPreferences prefs;
    private static SharedManager manager;

    private SharedManager(Context context) {
        prefs = context.getSharedPreferences("my_app_settings", MODE_PRIVATE);
    }

    public static SharedManager getInstance(Context context) {
        if (manager == null) {
            manager = new SharedManager(context);
        }

        return manager;
    }

    public boolean getIsLogin() {
        return prefs.getBoolean("isLogin", false);
    }

    public void setIsLogin(boolean state) {
        prefs.edit().putBoolean("isLogin", state).apply();
    }

    public int getQuests() {
        return prefs.getInt("quests", 0);
    }

    public void addQuests() {
        prefs.edit().putInt("quests", getQuests() + 1).apply();
    }

    public int getWays() {
        return prefs.getInt("ways", 0);
    }

    public void addWays() {
        prefs.edit().putInt("ways", getWays() + 1).apply();
    }

    public int getAchievments() {
        return prefs.getInt("achievments", 0);
    }

    public void addAchievments() {
        prefs.edit().putInt("achievments", getAchievments() + 1).apply();
    }

    public String getName() {
        return prefs.getString("name", "");
    }

    public void setName(String name) {
        prefs.edit().putString("name", name).apply();
    }



    public int getAngel() {
        return prefs.getInt("angel", 0);
    }

    public void setAngel(int num) {
        prefs.edit().putInt("angel", num).apply();
    }

    public int getAngelStatus() {
        return prefs.getInt("angel_status", 0);
    }

    public void setAngelStatus(int num) {
        prefs.edit().putInt("angel_status", num).apply();
    }

    public int getMonument() {
        return prefs.getInt("monument", 0);
    }

    public void setMonument(int num) {
        prefs.edit().putInt("monument", num).apply();
    }

    public int getMonumentStatus() {
        return prefs.getInt("monument_status", 0);
    }

    public void setMonumentStatus(int num) {
        prefs.edit().putInt("monument_status", num).apply();
    }


    public int getWall() {
        return prefs.getInt("wall", 0);
    }

    public void setWall(int num) {
        prefs.edit().putInt("wall", num).apply();
    }

    public int getWallStatus() {
        return prefs.getInt("wall_status", 0);
    }

    public void setWallStatus(int num) {
        prefs.edit().putInt("wall_status", num).apply();
    }

}
