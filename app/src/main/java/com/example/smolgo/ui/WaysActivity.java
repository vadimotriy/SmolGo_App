package com.example.smolgo.ui;

import static android.widget.Toast.LENGTH_SHORT;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.smolgo.R;
import com.example.smolgo.controller.SharedManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

// Активность с маршрутами
public class WaysActivity extends AppCompatActivity {
    SharedManager manager;
    BottomNavigationView bottomNavigationView;
    TextView statusChurch, statusWall, statusFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ways);

        manager = SharedManager.getInstance(this);

        // Если человек не зарегестрирован, мы переводим его на onboarding1
        if (!manager.getIsLogin()) {
            Intent activity = new Intent(this, OnBoarding1Activity.class);
            startActivity(activity); finish();
        }

        // Настройка BottomNavigationView
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_ways);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.navigation_ways) {
                return true;
            } else if (id == R.id.navigation_home) {
                startActivity(new Intent(this, MainScreenActivity.class));
                overridePendingTransition(0, 0); finish();
                return true;
            } else if (id == R.id.navigation_quests) {
                startActivity(new Intent(this, QuestsActivity.class));
                overridePendingTransition(0, 0); finish();
                return true;
            } else if (id == R.id.navigation_achievmnets) {
                startActivity(new Intent(this, AchievmetsActivity.class));
                overridePendingTransition(0, 0); finish();
                return true;
            } else if (id == R.id.navigation_settings) {
                startActivity(new Intent(this, QuestionsActivity.class));
                overridePendingTransition(0, 0); finish();
                return true;
            }
            return false;
        });

        // Установка статусов маршрутов
        statusChurch = findViewById(R.id.status_church);
        switch (manager.getChurchStatus()) {
            case 0:
                statusChurch.setText(R.string.un_completed);  break;
            case 1:
                statusChurch.setText(R.string.in_process);  break;
            case 2:
                statusChurch.setText(R.string.completed);  break;
        }

        statusWall = findViewById(R.id.status_wall);
        switch (manager.getWallStatus()) {
            case 0:
                statusWall.setText(R.string.un_completed);  break;
            case 1:
                statusWall.setText(R.string.in_process);  break;
            case 2:
                statusWall.setText(R.string.completed);  break;
        }

        statusFood = findViewById(R.id.status_food);
        switch (manager.getFoodStatus()) {
            case 0:
                statusFood.setText(R.string.un_completed);  break;
            case 1:
                statusFood.setText(R.string.in_process);  break;
            case 2:
                statusFood.setText(R.string.completed);  break;
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, 0, systemBars.right, 0);
            return insets;
        });
    }

    // Обновление bottom navigation и статусов маршрутов
    @Override
    protected void onResume() {
        super.onResume();
        bottomNavigationView.setSelectedItemId(R.id.navigation_ways);

        switch (manager.getChurchStatus()) {
            case 0:
                statusChurch.setText(R.string.un_completed);  break;
            case 1:
                statusChurch.setText(R.string.in_process);  break;
            case 2:
                statusChurch.setText(R.string.completed);  break;
        }

        switch (manager.getWallStatus()) {
            case 0:
                statusWall.setText(R.string.un_completed);  break;
            case 1:
                statusWall.setText(R.string.in_process);  break;
            case 2:
                statusWall.setText(R.string.completed);  break;
        }

        statusFood = findViewById(R.id.status_food);
        switch (manager.getFoodStatus()) {
            case 0:
                statusFood.setText(R.string.un_completed);  break;
            case 1:
                statusFood.setText(R.string.in_process);  break;
            case 2:
                statusFood.setText(R.string.completed);  break;
        }
    }

    // Переход на один из маршрутов по нажатию по нему
    public void wallWay(View view) {
        manager.setWallStatus(1);
        startActivity(new Intent(this, WallWayActivity.class));
        overridePendingTransition(0, 0);
    }
    
    // Переход на один из маршрутов по нажатию по нему
    public void churchWay(View view) {
        manager.setChurchStatus(1);
        startActivity(new Intent(this, ChurchWayActivity.class));
        overridePendingTransition(0, 0);
    }

    // Переход на один из маршрутов по нажатию по нему
    public void foodWay(View view) {
        manager.setFoodStatus(1);
        startActivity(new Intent(this, FoodWayActivity.class));
        overridePendingTransition(0, 0);
    }
}