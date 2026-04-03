package com.example.smolgo.controller;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

// Класс для удобной работы с SharedPreferences
public class SharedManager {
    private SharedPreferences prefs;
    private static SharedManager manager;
    private String URL = "https://sun9-61.userapi.com/s/v1/ig2/4AMb_GxUlDBfcLHL7Owhq01RFKOFx6w4ZhupG8e1qGdUCdFGXJZLL37XjaSYl8iUipTSZmT_VrwZVxhBLkOW00zQ.jpg?quality=95&crop=0,0,1280,853&as=32x21,48x32,72x48,108x72,160x107,240x160,360x240,480x320,540x360,640x426,720x480,1080x720,1280x853&from=bu&u=NPtitNTRi-5sRwPAYCC9sMR3fVd1_hCY1aCqbYu9BTY&cs=1280x0";

    // Приватный конструктор класса
    private SharedManager(Context context) {
        prefs = context.getSharedPreferences("settings", MODE_PRIVATE);
    }

    // Публичный конструктор класса
    public static SharedManager getInstance(Context context) {
        if (manager == null) {
            manager = new SharedManager(context);
        }

        return manager;
    }


    // 2 метода для хранения состояния входа
    public boolean getIsLogin() { return prefs.getBoolean("isLogin", false); }
    public void setIsLogin(boolean state) { prefs.edit().putBoolean("isLogin", state).apply(); }

    // 2 метода для хранения состояния новостей
    public boolean getIsNews() { return prefs.getBoolean("isNews", false); }
    public void setIsNews(boolean state) { prefs.edit().putBoolean("isNews", state).apply(); }


    // 2 метода для хранения имя пользователя
    public String getName() { return prefs.getString("name", ""); }
    public void setName(String name) { prefs.edit().putString("name", name).apply(); }


    // 2 метода для хранения имя пользователя
    public int getAchievments() { return prefs.getInt("achievments", 0); }
    public void setAchievments(int num) { prefs.edit().putInt("achievments", num).apply(); }


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


    // 6 методов для хранения данных про викторину "Крепостная стена"
    public int getWallQuestion() { return prefs.getInt("wall_question", 0); }
    public void setWallQuestion(int num) { prefs.edit().putInt("wall_question", num).apply(); }
    public int getWallQuestionStatus() { return prefs.getInt("wall_question_status", 0); }
    public void setWallQuestionStatus(int num) { prefs.edit().putInt("wall_question_status", num).apply(); }
    public int getWallQuestionResult() { return prefs.getInt("wall_question_result", 0); }
    public void setWallQuestionResult(int num) { prefs.edit().putInt("wall_question_result", num).apply(); }


    // 6 методов для хранения данных про викторину "История Смоленска"
    public int getHistoryQuestion() { return prefs.getInt("history_question", 0); }
    public void setHistoryQuestion(int num) { prefs.edit().putInt("history_question", num).apply(); }
    public int getHistoryQuestionStatus() { return prefs.getInt("history_question_status", 0); }
    public void setHistoryQuestionStatus(int num) { prefs.edit().putInt("history_question_status", num).apply(); }
    public int getHistoryQuestionResult() { return prefs.getInt("history_question_result", 0); }
    public void setHistoryQuestionResult(int num) { prefs.edit().putInt("history_question_result", num).apply(); }


    // 24 методов для хранения данных Новостей
    public String getTitle1() { return prefs.getString("title1", ""); }
    public String getTitle2() { return prefs.getString("title2", ""); }
    public String getTitle3() { return prefs.getString("title3", ""); }
    public void setTitle1(String text) { prefs.edit().putString("title1", text).apply(); }
    public void setTitle2(String text) { prefs.edit().putString("title2", text).apply(); }
    public void setTitle3(String text) { prefs.edit().putString("title3", text).apply(); }

    public String getText1() { return prefs.getString("text1", ""); }
    public String getText2() { return prefs.getString("text2", ""); }
    public String getText3() { return prefs.getString("text3", ""); }
    public void setText1(String text) { prefs.edit().putString("text1", text).apply(); }
    public void setText2(String text) { prefs.edit().putString("text2", text).apply(); }
    public void setText3(String text) { prefs.edit().putString("text3", text).apply(); }

    public String getDate1() { return prefs.getString("date1", "01.01.1970"); }
    public String getDate2() { return prefs.getString("date2", "01.01.1970"); }
    public String getDate3() { return prefs.getString("date3", "01.01.1970"); }
    public void setDate1(String text) { prefs.edit().putString("date1", text).apply(); }
    public void setDate2(String text) { prefs.edit().putString("date2", text).apply(); }
    public void setDate3(String text) { prefs.edit().putString("date3", text).apply(); }

    public String getLink1() { return prefs.getString("link1", URL); }
    public String getLink2() { return prefs.getString("link2", URL); }
    public String getLink3() { return prefs.getString("link3", URL); }
    public void setLink1(String text) { prefs.edit().putString("link1", text).apply(); }
    public void setLink2(String text) { prefs.edit().putString("link2", text).apply(); }
    public void setLink3(String text) { prefs.edit().putString("link3", text).apply(); }
}
