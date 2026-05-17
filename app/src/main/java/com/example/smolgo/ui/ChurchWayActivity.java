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

// Активность с маршрутом "Храмы Смоленска"
public class ChurchWayActivity extends AppCompatActivity {
    // Массив с названиями храмов
    int[] labelsText = {
            R.string.way_church_labels_0,
            R.string.way_church_labels_1,
            R.string.way_church_labels_2,
            R.string.way_church_labels_3,
            R.string.way_church_labels_4,
            R.string.way_church_labels_5,
            R.string.way_church_labels_6,
            R.string.way_church_labels_7
    };

    // Массив с местоположением храмов
    int[] whereText = {
            R.string.way_church_where_0,
            R.string.way_church_where_1,
            R.string.way_church_where_2,
            R.string.way_church_where_3,
            R.string.way_church_where_4,
            R.string.way_church_where_5,
            R.string.way_church_where_6,
            R.string.way_church_where_7
    };

    // Массив с описанием храмов
    int[] aboutText = {
            R.string.way_church_about_0,
            R.string.way_church_about_1,
            R.string.way_church_about_2,
            R.string.way_church_about_3,
            R.string.way_church_about_4,
            R.string.way_church_about_5,
            R.string.way_church_about_6,
            R.string.way_church_about_7
    };

    // Массив с кординатами храмов
    double[][] cords = {
            {54.788829, 32.054602},
            {54.788316, 32.053506},
            {54.790661, 32.051611},
            {54.789789, 32.064645},
            {54.784291, 32.063729},
            {54.784660, 32.049554},
            {54.786293, 32.054236},
            {54.787147, 32.060100}
    };

    // Массив с изображениями храмов
    int[] towerImages = {
            R.drawable.img_church_1,
            R.drawable.img_church_2,
            R.drawable.img_church_3,
            R.drawable.img_church_4,
            R.drawable.img_church_5,
            R.drawable.img_church_6,
            R.drawable.img_church_7,
            R.drawable.img_church_8
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
        setContentView(R.layout.activity_church_way);

        manager = SharedManager.getInstance(this);
        num = manager.getChurch();

        label = findViewById(R.id.info);
        where = findViewById(R.id.where);
        about = findViewById(R.id.about);
        image = findViewById(R.id.imageChurch);
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

    // Переход на следующую храм
    public void nextClick(View view) {
        ++num;

        // Если все храмы пройдены, то переход на активнсоть финиша
        if (num == 8) {
            manager.setChurch(0); manager.setChurchStatus(2);
            startActivity(new Intent(this, FinalActivity.class)); finish();
        } else {
            label.setText(labelsText[num]);
            where.setText(whereText[num]);
            about.setText(aboutText[num]);
            image.setImageResource(towerImages[num]);
            manager.setChurch(num);

            scrollView.post(() -> scrollView.fullScroll(View.FOCUS_UP));
        }
    }

    // Переход к расположению храмов на карте
    public void showMap(View view) {
        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtra("cord_first", cords[num][0]);
        intent.putExtra("cord_second", cords[num][1]);
        intent.putExtra("title", R.string.smolenks_churchs);
        intent.putExtra("object", labelsText[num]);
        startActivity(intent);
    }
}