package com.example.smolgo.ui;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.smolgo.R;
import com.example.smolgo.controller.SharedManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.progressindicator.CircularProgressIndicator;

// Активность с достижениями
public class AchievmetsActivity extends AppCompatActivity {
    SharedManager manager;
    BottomNavigationView bottomNavigationView;
    ProgressBar barWays, barQuests;
    TextView numWays, numQuests;

    LinearLayout layoutNoneQuestions, layoutAllQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_achievmets);

        manager = SharedManager.getInstance(this);

        // Если человек не зарегестрирован, мы переводим его на onboarding1
        if (!manager.getIsLogin()) {
            Intent activity = new Intent(this, OnBoarding1Activity.class);
            startActivity(activity); finish();
        }

        // Настройка BottomNavigationView
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_achievmnets);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.navigation_achievmnets) {
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
            } else if (id == R.id.navigation_settings) {
                startActivity(new Intent(this, QuestionsActivity.class));
                overridePendingTransition(0, 0); finish();
                return true;
            }
            return false;
        });

        int waysNum = (manager.getWallStatus() == 2 ? 1 : 0) + (manager.getChurchStatus() == 2 ? 1 : 0)
                + (manager.getFoodStatus() == 2 ? 1 : 0);
        int questsNum = (manager.getMonumentStatus() == 2 ? 1 : 0) + (manager.getMonumentStatus2() == 2 ? 1 : 0);

        // Заполнение PorgresBar
        barWays = findViewById(R.id.progressBarWays);
        barWays.setProgress(waysNum);

        barQuests = findViewById(R.id.progressBarQuests);
        barQuests.setProgress(questsNum);

        // Заполнение количества пройденных маршрутов/квестов
        numWays = findViewById(R.id.ways_number);
        numWays.setText(Integer.toString(waysNum));

        numQuests = findViewById(R.id.quests_number);
        numQuests.setText(Integer.toString(questsNum));

        // Заполнение достижений
        layoutAllQuestions = findViewById(R.id.allQuestions);
        layoutNoneQuestions = findViewById(R.id.noneQuestions);

        if (manager.getWallQuestionResult() == 5 && manager.getHistoryQuestionResult() == 5) {
            layoutAllQuestions.setVisibility(VISIBLE);
            layoutNoneQuestions.setVisibility(GONE);
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, 0, systemBars.right, 0);
            return insets;
        });
    }

    // Обновление bottom navigation
    @Override
    protected void onResume() {
        super.onResume();
        bottomNavigationView.setSelectedItemId(R.id.navigation_achievmnets);
    }
}