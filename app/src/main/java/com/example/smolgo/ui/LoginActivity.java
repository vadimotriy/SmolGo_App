package com.example.smolgo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.smolgo.R;
import com.example.smolgo.controller.SharedManager;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText emailInput, passwordInput;
    SharedManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        emailInput = findViewById(R.id.email);
        passwordInput = findViewById(R.id.password);

        manager = SharedManager.getInstance(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Вход по нажатию на кнопку
    public void login(View view) {
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();

        if (email != "vblinov2009@mail.ru" || email != "vblinov2009.rus@yandex.ru") {
            Toast.makeText(this, "Email не зарегестрирован", Toast.LENGTH_SHORT).show();
            return;
        }

        manager.setIsLogin(true);
        Intent mainScreen = new Intent(this, MainScreenActivity.class);
        startActivity(mainScreen);
    }

    // Переход на страницу регистрации
    public void signUp(View view) {
        finish();
    }
}