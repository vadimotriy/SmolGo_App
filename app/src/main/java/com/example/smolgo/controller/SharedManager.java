package com.example.smolgo.controller;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

// Класс для удобной работы с SharedPreferences
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


    // 2 метода для хранения состояния входа
    public boolean getIsLogin() { return prefs.getBoolean("isLogin", false); }
    public void setIsLogin(boolean state) { prefs.edit().putBoolean("isLogin", state).apply(); }


    // 2 метода для хранения количества достижений
    public int getAchievments() { return prefs.getInt("achievments", 0); }
    public void addAchievments() { prefs.edit().putInt("achievments", getAchievments() + 1).apply(); }


    // 2 метода для хранения имя пользователя
    public String getName() { return prefs.getString("name", ""); }
    public void setName(String name) { prefs.edit().putString("name", name).apply(); }


    // 4 метода для хранения данных про маршрута "ангелочки Смоленска"
    public int getAngel() { return prefs.getInt("angel", 0); }
    public void setAngel(int num) { prefs.edit().putInt("angel", num).apply(); }
    public int getAngelStatus() { return prefs.getInt("angel_status", 0); }
    public void setAngelStatus(int num) { prefs.edit().putInt("angel_status", num).apply(); }


    // 4 метода для хранения данных про квест "Памятники Смоленска"
    public int getMonument() { return prefs.getInt("monument", 0); }
    public void setMonument(int num) { prefs.edit().putInt("monument", num).apply(); }
    public int getMonumentStatus() { return prefs.getInt("monument_status", 0); }
    public void setMonumentStatus(int num) { prefs.edit().putInt("monument_status", num).apply(); }


    // 4 метода для хранения данных про маршрут "Крепостная стена"
    public int getWall() { return prefs.getInt("wall", 0); }
    public void setWall(int num) { prefs.edit().putInt("wall", num).apply(); }
    public int getWallStatus() { return prefs.getInt("wall_status", 0); }
    public void setWallStatus(int num) { prefs.edit().putInt("wall_status", num).apply(); }

}
