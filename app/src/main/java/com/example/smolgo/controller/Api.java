package com.example.smolgo.controller;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.*;
import retrofit2.Call;

public interface Api {
    @POST("registration")
    Call<ResponseBody> signUp(@Body Map<String, String> data);
}