package com.example.smolgo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.smolgo.R;
import com.example.smolgo.controller.SharedManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class QuestsActivity extends AppCompatActivity {
    SharedManager manager;
    BottomNavigationView bottomNavigationView;
    TextView statusMonument;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quests);

        manager = SharedManager.getInstance(this);

        // Если человек не зарегестрирован, мы переводим его на onboarding1
        if (!manager.getIsLogin()) {
            Intent activity = new Intent(this, OnBoarding1Activity.class);
            startActivity(activity);
        }

        // Настройка BottomNavigationView
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_quests);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.navigation_quests) {
                return true;
            } else if (id == R.id.navigation_home) {
                startActivity(new Intent(this, MainScreenActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (id == R.id.navigation_ways) {
                startActivity(new Intent(this, WaysActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (id == R.id.navigation_achievmnets) {
                startActivity(new Intent(this, AchievmetsActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (id == R.id.navigation_settings) {
                startActivity(new Intent(this, SettingsActivity.class));
                overridePendingTransition(0, 0);
                return true;
            }
            return false;
        });

        // Установка статусов квестов
        statusMonument = findViewById(R.id.status_monument);
        switch (manager.getMonumentStatus()) {
            case 0:
                statusMonument.setText("Не пройдено");  break;
            case 1:
                statusMonument.setText("В процессе");  break;
            case 2:
                statusMonument.setText("Пройдено");  break;
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, 0, systemBars.right, 0);
            return insets;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        bottomNavigationView.setSelectedItemId(R.id.navigation_quests);

        switch (manager.getMonumentStatus()) {
            case 0:
                statusMonument.setText("Не пройдено");  break;
            case 1:
                statusMonument.setText("В процессе");  break;
            case 2:
                statusMonument.setText("Пройдено");  break;
        }
    }

    // Переход на один из квестов по нажатию по нему
    public void monument(View view) {
        manager.setMonumentStatus(1);
        startActivity(new Intent(this, MonumentQuestActivity.class));
        overridePendingTransition(0, 0);
    }
}