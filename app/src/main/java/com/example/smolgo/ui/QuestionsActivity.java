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

// Активность с викторинами
public class QuestionsActivity extends AppCompatActivity {
    SharedManager manager;
    BottomNavigationView bottomNavigationView;
    TextView statusWall, statusHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_questions);

        manager = SharedManager.getInstance(this);

        // Если человек не зарегестрирован, мы переводим его на onboarding1
        if (!manager.getIsLogin()) {
            Intent activity = new Intent(this, OnBoarding1Activity.class);
            startActivity(activity); finish();
        }

        // Настройка BottomNavigationView
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_settings);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.navigation_settings) {
                return true;
            } else if (id == R.id.navigation_home) {
                startActivity(new Intent(this, MainScreenActivity.class));
                overridePendingTransition(0, 0); finish();
                return true;
            } else if (id == R.id.navigation_ways) {
                startActivity(new Intent(this, WaysActivity.class));
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
            }
            return false;
        });

        statusWall = findViewById(R.id.status_wall);
        statusHistory = findViewById(R.id.status_history);

        // Выставление результатов викторин
        if (manager.getWallQuestionStatus() == 2) {
            statusWall.setText(manager.getWallQuestionResult() + " / 5");
        } else if (manager.getWallQuestionStatus() == 1) {
            statusWall.setText("В процессе");
        }

        if (manager.getHistoryQuestionStatus() == 2) {
            statusHistory.setText(manager.getHistoryQuestionResult() + " / 5");
        } else if (manager.getHistoryQuestionStatus() == 1) {
            statusHistory.setText("В процессе");
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, 0, systemBars.right, 0);
            return insets;
        });
    }

    // Обновление bottom navigation и результатов викторин
    @Override
    protected void onResume() {
        super.onResume();
        bottomNavigationView.setSelectedItemId(R.id.navigation_settings);

        // Выставление результатов викторин
        if (manager.getWallQuestionStatus() == 2) {
            statusWall.setText(manager.getWallQuestionResult() + " / 5");
        } else if (manager.getWallQuestionStatus() == 1) {
            statusWall.setText("В процессе");
        }

        if (manager.getHistoryQuestionStatus() == 2) {
            statusHistory.setText(manager.getHistoryQuestionResult() + " / 5");
        } else if (manager.getHistoryQuestionStatus() == 1) {
            statusHistory.setText("В процессе");
        }
    }

    // Переход на одну из викторин
    public void wallQuestions(View view) {
        manager.setWallQuestionStatus(1);
        startActivity(new Intent(this, WallQuestionActivity.class));
        overridePendingTransition(0, 0);
    }

    // Переход на одну из викторин
    public void historyQuestions(View view) {
        manager.setHistoryQuestionStatus(1);
        startActivity(new Intent(this, HistoryQuestionActivity.class));
        overridePendingTransition(0, 0);
    }
}