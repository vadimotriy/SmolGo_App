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
import com.example.smolgo.controller.Api;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);

        nameInput = findViewById(R.id.name);
        emailInput = findViewById(R.id.email);
        passwordInput = findViewById(R.id.password);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void signUp(View view) {
        String name = nameInput.getText().toString();
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();

        Retrofit r = new Retrofit.Builder().baseUrl("http://10.0.0.2:8000/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        r.create(Api.class).signUp(Map.of("name", name, "email", email, "password", password))
                .enqueue(new Callback<>() {
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            JSONObject json = new JSONObject(response.body().string());
                            String result = json.getString("message");

                            Toast.makeText(SignUpActivity.this, result, Toast.LENGTH_SHORT).show();

                        } catch (Exception e) {
                            Toast.makeText(SignUpActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                    public void onFailure(Call<ResponseBody> c, Throwable t) {
                        Toast.makeText(SignUpActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }

                });

    }

    public void login(View view) {
        Intent login = new Intent(this, LoginActivity.class);
        startActivity(login);
    }
}