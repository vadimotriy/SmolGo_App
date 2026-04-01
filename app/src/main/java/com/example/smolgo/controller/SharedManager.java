package com.example.smolgo.controller;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

// Класс для удобной работы с SharedPreferences
public class SharedManager {
    private SharedPreferences prefs;
    private static SharedManager manager;

    private SharedManager(Context context) {
        prefs = context.getSharedPreferences("settings", MODE_PRIVATE);
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


    // 2 метода для хранения имя пользователя
    public String getName() { return prefs.getString("name", ""); }
    public void setName(String name) { prefs.edit().putString("name", name).apply(); }


    // 4 метода для хранения данных про квест "Памятники 2"
    public int getMonument2() { return prefs.getInt("monument2", 0); }
    public void setMonument2(int num) { prefs.edit().putInt("monument2", num).apply(); }
    public int getMonumentStatus2() { return prefs.getInt("monument_status2", 0); }
    public void setMonumentStatus2(int num) { prefs.edit().putInt("monument_status2", num).apply(); }


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


    // 4 метода для хранения данных про маршрут "Храмы Смоленска"
    public int getChurch() { return prefs.getInt("church", 0); }
    public void setChurch(int num) { prefs.edit().putInt("church", num).apply(); }
    public int getChurchStatus() { return prefs.getInt("church_status", 0); }
    public void setChurchStatus(int num) { prefs.edit().putInt("church_status", num).apply(); }


    // 4 метода для хранения данных про маршрут "Гастрономический маршрут"
    public int getFood() { return prefs.getInt("food", 0); }
    public void setFood(int num) { prefs.edit().putInt("food", num).apply(); }
    public int getFoodStatus() { return prefs.getInt("food_status", 0); }
    public void setFoodStatus(int num) { prefs.edit().putInt("food_status", num).apply(); }

}
