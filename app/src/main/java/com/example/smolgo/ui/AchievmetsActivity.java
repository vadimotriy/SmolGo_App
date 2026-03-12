package com.example.smolgo.ui;

import android.content.Intent;
import android.os.Bundle;
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

public class AchievmetsActivity extends AppCompatActivity {
    SharedManager manager;
    BottomNavigationView bottomNavigationView;
    ProgressBar barWays, barQuests;
    TextView numWays, numQuests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_achievmets);

        manager = SharedManager.getInstance(this);

        if (!manager.getIsLogin()) {
            Intent activity = new Intent(this, OnBoarding1Activity.class);
            startActivity(activity);
        }

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_achievmnets);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.navigation_achievmnets) {
                return true;
            } else if (id == R.id.navigation_home) {
                startActivity(new Intent(this, MainScreenActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (id == R.id.navigation_ways) {
                startActivity(new Intent(this, WaysActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (id == R.id.navigation_quests) {
                startActivity(new Intent(this, QuestsActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (id == R.id.navigation_settings) {
                startActivity(new Intent(this, SettingsActivity.class));
                overridePendingTransition(0, 0);
                return true;
            }
            return false;
        });

        barWays = findViewById(R.id.progressBarWays);
        barWays.setProgress(manager.getWays());

        barQuests = findViewById(R.id.progressBarQuests);
        barQuests.setProgress(manager.getQuests());

        numWays = findViewById(R.id.ways_number);
        numWays.setText(Integer.toString(Math.min(manager.getWays(), 2)));

        numQuests = findViewById(R.id.quests_number);
        numQuests.setText(Integer.toString(Math.min(manager.getQuests(), 1)));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, 0, systemBars.right, 0);
            return insets;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        bottomNavigationView.setSelectedItemId(R.id.navigation_achievmnets);
    }
}