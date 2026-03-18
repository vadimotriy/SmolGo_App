package com.example.smolgo.ui;

import static android.widget.Toast.LENGTH_SHORT;

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
import com.example.smolgo.controller.Api;
import com.example.smolgo.controller.SharedManager;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity {
    TextInputEditText nameInput, emailInput, passwordInput;
    SharedManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);

        nameInput = findViewById(R.id.name);
        emailInput = findViewById(R.id.email);
        passwordInput = findViewById(R.id.password);

        manager = SharedManager.getInstance(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Регистрация по нажатию на кнопку
    public void signUp(View view) {
        String name = nameInput.getText().toString();
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();
        Intent mainScreen = new Intent(this, MainScreenActivity.class);

        Retrofit builder = new Retrofit.Builder().baseUrl("https://web-production-2e91f.up.railway.app/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        builder.create(Api.class).signUp(Map.of("name", name, "email", email, "password", password))
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            JSONObject json = new JSONObject(response.body().string());
                            String result = json.getString("message");

                            if (result.equals("Succes")) {
                                manager.setIsLogin(true);
                                manager.setName(name);
                                startActivity(mainScreen);
                            } else {
                                Toast.makeText(SignUpActivity.this, "EMAIL уже используется!", LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(SignUpActivity.this, "ERROR_PARSING", LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(SignUpActivity.this, "ERROR", LENGTH_SHORT).show();
                    }
                });
    }

    // Переход на страницу входа
    public void login(View view) {
        Intent login = new Intent(this, LoginActivity.class);
        startActivity(login);
    }
}