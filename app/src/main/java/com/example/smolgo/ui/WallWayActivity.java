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

// Активность с маршрутом "Крепостная стена"
public class WallWayActivity extends AppCompatActivity {
    // Массив с названиями башен
    int[] labelsText = {
            R.string.way_wall_label_0,
            R.string.way_wall_label_1,
            R.string.way_wall_label_2,
            R.string.way_wall_label_3,
            R.string.way_wall_label_4,
            R.string.way_wall_label_5,
            R.string.way_wall_label_6,
            R.string.way_wall_label_7,
            R.string.way_wall_label_8,
            R.string.way_wall_label_9,
            R.string.way_wall_label_10,
            R.string.way_wall_label_11,
            R.string.way_wall_label_12,
            R.string.way_wall_label_13,
            R.string.way_wall_label_14,
            R.string.way_wall_label_15,
            R.string.way_wall_label_16
    };

    // Массив с местоположением башен
    int[] whereText = {
            R.string.way_wall_where_0,
            R.string.way_wall_where_1,
            R.string.way_wall_where_2,
            R.string.way_wall_where_3,
            R.string.way_wall_where_4,
            R.string.way_wall_where_5,
            R.string.way_wall_where_6,
            R.string.way_wall_where_7,
            R.string.way_wall_where_8,
            R.string.way_wall_where_9,
            R.string.way_wall_where_10,
            R.string.way_wall_where_11,
            R.string.way_wall_where_12,
            R.string.way_wall_where_13,
            R.string.way_wall_where_14,
            R.string.way_wall_where_15,
            R.string.way_wall_where_16
    };

    // Массив с описанием башен
    int[] aboutText = {
            R.string.way_wall_about_0,
            R.string.way_wall_about_1,
            R.string.way_wall_about_2,
            R.string.way_wall_about_3,
            R.string.way_wall_about_4,
            R.string.way_wall_about_5,
            R.string.way_wall_about_6,
            R.string.way_wall_about_7,
            R.string.way_wall_about_8,
            R.string.way_wall_about_9,
            R.string.way_wall_about_10,
            R.string.way_wall_about_11,
            R.string.way_wall_about_12,
            R.string.way_wall_about_13,
            R.string.way_wall_about_14,
            R.string.way_wall_about_15,
            R.string.way_wall_about_16
    };

    // Массив с кординатами башен
    double[][] cords = {
            {54.781271, 32.040031},
            {54.780583, 32.041865},
            {54.780008, 32.043706},
            {54.779634, 32.045751},
            {54.778502, 32.053103},
            {54.780343, 32.059058},
            {54.781136, 32.059958},
            {54.781826, 32.060936},
            {54.782685, 32.062950},
            {54.783479, 32.065369},
            {54.785015, 32.065204},
            {54.786463, 32.065279},
            {54.788107, 32.064297},
            {54.789848, 32.065111},
            {54.791070, 32.059463},
            {54.790930, 32.054287},
            {54.790556, 32.047481}
    };

    // Массив с изображениями башен
    int[] towerImages = {
            R.drawable.img_wall_1,
            R.drawable.img_wall_2,
            R.drawable.img_wall_3,
            R.drawable.img_wall_4,
            R.drawable.img_wall_5,
            R.drawable.img_wall_6,
            R.drawable.img_wall_7,
            R.drawable.img_wall_8,
            R.drawable.img_wall_9,
            R.drawable.img_wall_10,
            R.drawable.img_wall_11,
            R.drawable.img_wall_12,
            R.drawable.img_wall_13,
            R.drawable.img_wall_14,
            R.drawable.img_wall_15,
            R.drawable.img_wall_16,
            R.drawable.img_wall_17
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
        setContentView(R.layout.activity_wall_way);

        manager = SharedManager.getInstance(this);
        num = manager.getWall();

        label = findViewById(R.id.info);
        where = findViewById(R.id.where);
        about = findViewById(R.id.about);
        image = findViewById(R.id.imageWall);
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

    // Переход на следующую башню
    public void nextClick(View view) {
        ++num;

        // Если все башни пройдены, то переход на активнсоть финиша
        if (num == 17) {
            manager.setWall(0); manager.setWallStatus(2);
            startActivity(new Intent(this, FinalActivity.class)); finish();
        } else {
            label.setText(labelsText[num]);
            where.setText(whereText[num]);
            about.setText(aboutText[num]);
            image.setImageResource(towerImages[num]);
            manager.setWall(num);

            scrollView.post(() -> scrollView.fullScroll(View.FOCUS_UP));
        }
    }

    // Переход к расположению башен на карте
    public void showMap(View view) {
        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtra("cord_first", cords[num][0]);
        intent.putExtra("cord_second", cords[num][1]);
        intent.putExtra("title", R.string.wall);
        intent.putExtra("object", labelsText[num]);
        startActivity(intent);
    }
}