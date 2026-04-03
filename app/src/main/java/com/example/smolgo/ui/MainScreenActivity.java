package com.example.smolgo.ui;

import static android.widget.Toast.LENGTH_SHORT;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.smolgo.R;
import com.example.smolgo.controller.Api;
import com.example.smolgo.controller.SharedManager;
import com.example.smolgo.models.NewsItem;
import com.example.smolgo.models.NewsResponce;
import com.example.smolgo.models.RegisterResponce;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// Активность главного экрана
public class MainScreenActivity extends AppCompatActivity {
    SharedManager manager;
    BottomNavigationView bottomNavigationView;
    TextView ways, quests, achievments;
    ImageView image1, image2, image3;
    TextView title1, text1, date1, title2, text2, date2, title3, text3, date3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_screen);

        manager = SharedManager.getInstance(this);

        // Если человек не зарегестрирован, мы переводим его на onboarding1
        if (!manager.getIsLogin()) {
            Intent activity = new Intent(this, OnBoarding1Activity.class);
            startActivity(activity); finish();
        }

        // Настройка BottomNavigationView
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.navigation_home) {
                return true;
            } else if (id == R.id.navigation_ways) {
                startActivity(new Intent(this, WaysActivity.class));
                overridePendingTransition(0, 0); finish();
                return true;
            } else if (id == R.id.navigation_quests) {
                startActivity(new Intent(this, QuestsActivity.class));
                overridePendingTransition(0, 0); finish();
                return true;
            } else if (id == R.id.navigation_achievmnets) {
                startActivity(new Intent(this, AchievmetsActivity.class));
                overridePendingTransition(0, 0); finish();
                return true;
            } else if (id == R.id.navigation_settings) {
                startActivity(new Intent(this, QuestionsActivity.class));
                overridePendingTransition(0, 0); finish();
                return true;
            }
            return false;
        });

        image1 = findViewById(R.id.NewsImageView1);
        title1 = findViewById(R.id.NewsTitle1);
        text1 = findViewById(R.id.NewsText1);
        date1 = findViewById(R.id.NewsDate1);

        image2 = findViewById(R.id.NewsImageView2);
        title2 = findViewById(R.id.NewsTitle2);
        text2 = findViewById(R.id.NewsText2);
        date2 = findViewById(R.id.NewsDate2);

        image3 = findViewById(R.id.NewsImageView3);
        title3 = findViewById(R.id.NewsTitle3);
        text3 = findViewById(R.id.NewsText3);
        date3 = findViewById(R.id.NewsDate3);

        // Заполнение краткой информации
        ways = findViewById(R.id.ways_number);
        quests = findViewById(R.id.quests_number);
        achievments = findViewById(R.id.achievments_number);

        int waysNum = (manager.getWallStatus() == 2 ? 1 : 0) + (manager.getChurchStatus() == 2 ? 1 : 0)
                + (manager.getFoodStatus() == 2 ? 1 : 0);
        int questsNum = (manager.getMonumentStatus() == 2 ? 1 : 0) + (manager.getMonumentStatus2() == 2 ? 1 : 0);

        ways.setText(Integer.toString(waysNum));
        quests.setText(Integer.toString(questsNum));
        if (manager.getWallQuestionResult() == 5 && manager.getHistoryQuestionResult() == 5) {
            achievments.setText("1");
        }

        // При первом запуске приложения, сразу загружает новости
        if (!manager.getIsNews()) {
            loadNews(new View(this));
        } else {
            updateInformation();
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(0, 0, 0, 0);
            return insets;
        });
    }

    // Обновление bottom navigation и проверка на авторизацию
    @Override
    protected void onResume() {
        super.onResume();

        if (!manager.getIsLogin()) { finish(); }
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
    }

    // Загрузка новых новостей
    public void loadNews(View view) {
        Retrofit builder = new Retrofit.Builder().baseUrl("https://web-production-2e91f.up.railway.app/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        builder.create(Api.class).getNews().enqueue(new Callback<NewsResponce>() {
            @Override
            public void onResponse(Call<NewsResponce> call, Response<NewsResponce> response) {
                // Сервер успешно отправил три актуальных новости
                if ("Succes".equals(response.body().message)) {
                    NewsItem news1 = response.body().news_1;
                    NewsItem news2 = response.body().news_2;
                    NewsItem news3 = response.body().news_3;

                    manager.setLink1(news1.link);
                    manager.setLink2(news2.link);
                    manager.setLink3(news3.link);

                    manager.setTitle1(news1.title);
                    manager.setTitle2(news2.title);
                    manager.setTitle3(news3.title);

                    manager.setText1(news1.text);
                    manager.setText2(news2.text);
                    manager.setText3(news3.text);

                    manager.setDate1(news1.date);
                    manager.setDate2(news2.date);
                    manager.setDate3(news3.date);

                    manager.setIsNews(true);
                    updateInformation();

                    Toast.makeText(MainScreenActivity.this, "Обновлено!", LENGTH_SHORT).show();
                }
                // Произошла ошибка с БД на сервере
                else {
                    Toast.makeText(MainScreenActivity.this, "Новости сейчас недоступны. Попробуйте позже!", LENGTH_SHORT).show();
                }
            }

            // Нет соединения с интернетом
            @Override
            public void onFailure(Call<NewsResponce> call, Throwable t) {
                Log.e("SmolGo_getNews", t.toString());
                Toast.makeText(MainScreenActivity.this, "Нет соединения с сервером. Проверьте наличия интернета", LENGTH_SHORT).show();
            }
        });
    }

    // Переход на URL ВК сообщества
    public void firstNews(View view) {
        String url = "https://vk.com/club235677777";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    // Открытие настроек
    public void openSettings(View view) {
        startActivity(new Intent(this, SettingsActivity.class));
        overridePendingTransition(0, 0);
    }

    // Обновление информации новостей
    public void updateInformation() {
        Picasso.get().load(manager.getLink1()).placeholder(R.drawable.img_news_load).error(R.drawable.img_news_error).into(image1);
        Picasso.get().load(manager.getLink2()).placeholder(R.drawable.img_news_load).error(R.drawable.img_news_error).into(image2);
        Picasso.get().load(manager.getLink3()).placeholder(R.drawable.img_news_load).error(R.drawable.img_news_error).into(image3);

        title1.setText(manager.getTitle1());
        text1.setText(manager.getText1());
        date1.setText(manager.getDate1());

        title2.setText(manager.getTitle2());
        text2.setText(manager.getText2());
        date2.setText(manager.getDate2());

        title3.setText(manager.getTitle3());
        text3.setText(manager.getText3());
        date3.setText(manager.getDate3());
    }
}