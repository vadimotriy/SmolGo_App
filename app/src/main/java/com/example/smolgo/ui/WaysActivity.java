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

public class WaysActivity extends AppCompatActivity {
    SharedManager manager;
    BottomNavigationView bottomNavigationView;

    TextView statusAngel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ways);

        manager = SharedManager.getInstance(this);

        if (!manager.getIsLogin()) {
            Intent activity = new Intent(this, OnBoarding1Activity.class);
            startActivity(activity);
        }

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_ways);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.navigation_ways) {
                return true;
            } else if (id == R.id.navigation_home) {
                startActivity(new Intent(this, MainScreenActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (id == R.id.navigation_quests) {
                startActivity(new Intent(this, QuestsActivity.class));
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

        statusAngel = findViewById(R.id.status_angel);

        switch (manager.getAngelStatus()) {
            case 0:
                statusAngel.setText("Не пройдено");  break;
            case 1:
                statusAngel.setText("В процессе");  break;
            case 2:
                statusAngel.setText("Пройдено");  break;
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
        bottomNavigationView.setSelectedItemId(R.id.navigation_ways);

        switch (manager.getAngelStatus()) {
            case 0:
                statusAngel.setText("Не пройдено");  break;
            case 1:
                statusAngel.setText("В процессе");  break;
            case 2:
                statusAngel.setText("Пройдено");  break;
        }
    }

    public void angelWay(View view) {
        manager.setAngelStatus(1);
        startActivity(new Intent(this, AngelWayActivity.class));
        overridePendingTransition(0, 0);
    }

    public void wallWay(View view) {
        Toast.makeText(this, "В разработке", LENGTH_SHORT);
//        startActivity(new Intent(this, WallWayActivity.class));
//        overridePendingTransition(0, 0);
    }
}