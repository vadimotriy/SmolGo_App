package com.example.smolgo.ui;

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

public class WallWayActivity extends AppCompatActivity {

    String[] labelsText = {
            "Громовая",
            "Копытенская",
            "Зимбулка",
            "Никольская",
            "Орёл",
            "Пятницкая",
            "Бублейка",
            "Долгочевская",
            "Авраамиевская",
            "Заалтарная",
            "Веселуха",
            "Воронина",
            "Костыревская",
            "Молоховская",
            "Острожная",
            "Свисловая",
            "Донинская",
            "Поздравляем!"
    };

    String[] aboutText = {
            "Набережная Днепра (юго-запад)",
            "Улица Тимирязева (запад)",
            "Сквер им. М.И. Глинки (запад)",
            "Улица Большая Советская (северо-запад)",
            "Улица Октябрьской Революции (север)",
            "Улица Большая Советская (север)",
            "Улица Дзержинского (северо-восток)",
            "Улица Дзержинского (северо-восток)",
            "Улица Тухачевского (восток)",
            "Крепостной вал (восток)",
            "Улица Маршала Ерёменко (юго-восток)",
            "Улица Маршала Ерёменко (юго-восток)",
            "Улица Кашена (юг)",
            "Улица Кашена (юг)",
            "Улица Студенческая (юг)",
            "Улица Студенческая (юго-запад)",
            "Набережная Днепра (юго-запад)",
            "Вы прошли весь путь \"Смоленские ангелы\"! У вас +1 маршрут в достижениях"
    };

    int num;
    SharedManager manager;

    TextView label, about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_wall_way);
        manager = SharedManager.getInstance(this);

        num = manager.getWall();

        label = findViewById(R.id.info);
        about = findViewById(R.id.about);

        label.setText(labelsText[num]);
        about.setText(aboutText[num]);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void backActivity(View view) {
        finish();
    }

    public void nextClick(View view) {
        ++num;

        if (num == 18) { manager.setWall(0); manager.setWallStatus(2); manager.addWays(); finish(); }
        else {
            label.setText(labelsText[num]);
            about.setText(aboutText[num]);
            manager.setWall(num);
        }
    }
}