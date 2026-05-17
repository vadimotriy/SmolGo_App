package com.example.smolgo.ui;

import static android.widget.Toast.LENGTH_SHORT;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.smolgo.R;
import com.example.smolgo.controller.Api;
import com.example.smolgo.controller.SharedManager;
import com.example.smolgo.models.LoginRequest;
import com.example.smolgo.models.LoginResponce;
import com.example.smolgo.models.RegisterRequest;
import com.example.smolgo.models.RegisterResponce;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// Активность для входа
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
        Intent mainScreen = new Intent(this, MainScreenActivity.class);

        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();
        LoginRequest request = new LoginRequest(email, password);

        Retrofit builder = new Retrofit.Builder().baseUrl("http://82.23.249.75/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        builder.create(Api.class).login(request).enqueue(new Callback<LoginResponce>() {
            @Override
            public void onResponse(Call<LoginResponce> call, Response<LoginResponce> response) {
                // Успешный вход
                if (response.body().message.equals("Succes")) {
                    String name = response.body().name;
                    manager.setIsLogin(true); manager.setName(name);
                    startActivity(mainScreen); finish();
                }
                // Email еще не зарегестрирован
                else if (response.body().message.equals("Email has not been used")) {
                    Toast.makeText(LoginActivity.this, String.valueOf(R.string.email_error), LENGTH_SHORT).show();
                }
                // Неверный пароль
                else {
                    Toast.makeText(LoginActivity.this, String.valueOf(R.string.password_error), LENGTH_SHORT).show();
                }
            }

            // Нет соединения с интернетом
            @Override
            public void onFailure(Call<LoginResponce> call, Throwable t) {
                Log.e("SmolGo_Login", t.toString());
                Toast.makeText(LoginActivity.this, String.valueOf(R.string.network_error), LENGTH_SHORT).show();
            }
        });
    }

    // Переход на страницу регистрации
    public void signUp(View view) {
        Intent login = new Intent(this, SignUpActivity.class);
        startActivity(login); finish();
    }
}