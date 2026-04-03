package com.example.smolgo.ui;

import android.content.Intent;
import android.net.Uri;
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

// Активность настроек
public class SettingsActivity extends AppCompatActivity {
    SharedManager manager;
    TextView helloName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);

        manager = SharedManager.getInstance(this);

        // Если человек не зарегестрирован, мы переводим его на onboarding1
        if (!manager.getIsLogin()) {
            Intent activity = new Intent(this, OnBoarding1Activity.class);
            startActivity(activity); finish();
        }

        helloName = findViewById(R.id.helloName);
        helloName.setText(helloName.getText().toString() + " " + manager.getName() + "!");

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, 0, systemBars.right, 0);
            return insets;
        });
    }

    // Выход из аккаунта
    public void logout(View view) {
        manager.setIsLogin(false);
        startActivity(new Intent(this, SignUpActivity.class));
        overridePendingTransition(0, 0); finish();
    }

    // Переход на URL yandexAPI
    public void yandexLink(View view) {
        String url = "https://yandex.ru/legal/maps_api/";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    // Переход на URL ВК сообщества
    public void vkLink(View view) {
        String url = "https://vk.com/club235677777";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    // Выход из активности
    public void backActivity(View view) {
        finish();
    }
}