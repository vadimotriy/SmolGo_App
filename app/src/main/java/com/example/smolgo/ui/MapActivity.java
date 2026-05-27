package com.example.smolgo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.smolgo.R;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.IconStyle;
import com.yandex.mapkit.map.PlacemarkMapObject;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.runtime.image.ImageProvider;
import com.yandex.mapkit.map.TextStyle;

// Активность для работы с картой
public class MapActivity extends AppCompatActivity {
    MapView mapView;
    TextView title;
    private int titleText, objectName;
    private double cordX, cordY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_map);

        Intent intent = getIntent();
        cordX = intent.getDoubleExtra("cord_first", 0.0);
        cordY = intent.getDoubleExtra("cord_second", 0.0);
        titleText = intent.getIntExtra("title", 0);
        objectName = intent.getIntExtra("object", 0);

        mapView = findViewById(R.id.mapview);
        title = findViewById(R.id.titleView);
        title.setText(titleText);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, 0, systemBars.right, 0);
            return insets;
        });
    }

    // Запуск карты
    @Override
    protected void onStart() {
        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapView.onStart();

        PlacemarkMapObject placemark = mapView.getMap().getMapObjects().addPlacemark();
        placemark.setGeometry(new Point(cordX, cordY));

        // Текст и его стиль
        TextStyle textStyle = new TextStyle().setSize(15f).setPlacement(TextStyle.Placement.TOP).setOffset(5f);
        placemark.setText(getString(objectName), textStyle);

        // Иконка и ее стиль
        IconStyle iconStyle = new IconStyle().setScale(0.5f);
        placemark.setIcon(ImageProvider.fromResource(this, R.drawable.img_map_pin), iconStyle);

        // Приближение
        mapView.getMap().move(
                new CameraPosition(new Point(cordX, cordY), 18.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 2),
                null
        );
    }

    // Закрытие карты
    @Override
    protected void onStop() {
        mapView.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    // Выход из активности
    public void backActivity(View view) {
        finish();
    }
}