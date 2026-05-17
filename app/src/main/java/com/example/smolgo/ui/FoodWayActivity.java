package com.example.smolgo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.smolgo.R;
import com.example.smolgo.controller.SharedManager;

// Активность с маршрутом "Гастрономический маршрут"
public class FoodWayActivity extends AppCompatActivity {
    // Массив с названиями заведений
    int[] labelsText = {
            R.string.way_food_label_0,
            R.string.way_food_label_1,
            R.string.way_food_label_2
    };

    // Массив с местоположением заведений
    int[] whereText = {
            R.string.way_food_where_0,
            R.string.way_food_where_1,
            R.string.way_food_where_2
    };

    // Массив с описанием заведений
    int[] aboutText = {
            R.string.way_food_about_0,
            R.string.way_food_about_1,
            R.string.way_food_about_2
    };

    // Массив с кординатами заведений
    double[][] cords = {
            {54.781363, 32.045116},
            {54.781326, 32.043649},
            {54.781455, 32.037711}
    };

    // Массив с изображениями заведений
    int[] towerImages = {
            R.drawable.img_food_1,
            R.drawable.img_food_2,
            R.drawable.img_food_3
    };

    int num;
    SharedManager manager;
    TextView label, about, where;
    ImageView image;
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_food_way);

        manager = SharedManager.getInstance(this);
        num = manager.getFood();

        label = findViewById(R.id.info);
        where = findViewById(R.id.where);
        about = findViewById(R.id.about);
        image = findViewById(R.id.imageFood);
        scrollView = findViewById(R.id.scrollView);

        label.setText(labelsText[num]);
        where.setText(whereText[num]);
        about.setText(aboutText[num]);
        image.setImageResource(towerImages[num]);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Выход из активности
    public void backActivity(View view) {
        finish();
    }

    // Переход на следующую заведение
    public void nextClick(View view) {
        ++num;

        // Если все заведения пройдены, то переход на активнсоть финиша
        if (num == 3) {
            manager.setFood(0); manager.setFoodStatus(2);
            startActivity(new Intent(this, FinalActivity.class)); finish();
        } else {
            label.setText(labelsText[num]);
            where.setText(whereText[num]);
            about.setText(aboutText[num]);
            image.setImageResource(towerImages[num]);
            manager.setFood(num);

            scrollView.post(() -> scrollView.fullScroll(View.FOCUS_UP));
        }
    }

    // Переход к расположению заведений на карте
    public void showMap(View view) {
        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtra("cord_first", cords[num][0]);
        intent.putExtra("cord_second", cords[num][1]);
        intent.putExtra("title", R.string.gastronom);
        intent.putExtra("object", labelsText[num]);
        startActivity(intent);
    }
}