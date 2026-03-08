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
}
