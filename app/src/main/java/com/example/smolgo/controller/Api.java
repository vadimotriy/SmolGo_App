package com.example.smolgo.controller;

import com.example.smolgo.models.LoginRequest;
import com.example.smolgo.models.LoginResponce;
import com.example.smolgo.models.RegisterRequest;
import com.example.smolgo.models.RegisterResponce;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.*;
import retrofit2.Call;

// Интерфейс Api для работы с Retrofit
public interface Api {
    // Регистрация
    @POST("registration")
    Call<RegisterResponce> signUp(@Body RegisterRequest request);

    // Вход
    @POST("login")
    Call<LoginResponce> login(@Body LoginRequest request);
}