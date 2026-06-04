package com.example.smolgo.ui;

import static android.view.View.VISIBLE;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.smolgo.R;
import com.example.smolgo.controller.SharedManager;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Collections;

public class NearDneprQuest3Activity extends AppCompatActivity {

    int num;
    SharedManager manager;

    private ImageView[][] tiles = new ImageView[3][3];
    private ArrayList<Bitmap> originalBitmaps = new ArrayList<>();
    private ImageView draggedTile = null;
    private int tileWidth, tileHeight;
    boolean state = true;
    MaterialButton button;

    GridLayout grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_dnepr_quest_puzzle);

        manager = SharedManager.getInstance(this);
        num = manager.getDnepr();
        button = findViewById(R.id.materialButton);

        grid = findViewById(R.id.gridLayout);
        // Загружаем картинку
        Bitmap original = BitmapFactory.decodeResource(getResources(), com.example.smolgo.R.drawable.img_dnepr_6);
        int screenSize = Math.min(getResources().getDisplayMetrics().widthPixels, getResources().getDisplayMetrics().heightPixels) - 200;
        Bitmap scaled = Bitmap.createScaledBitmap(original, screenSize, screenSize, true);

        tileWidth = scaled.getWidth() / 3;
        tileHeight = scaled.getHeight() / 3;

        // Нарезаем на 9 частей
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Bitmap tile = Bitmap.createBitmap(scaled, j * tileWidth, i * tileHeight, tileWidth, tileHeight);
                originalBitmaps.add(tile);
            }
        }

        // Перемешиваем
        ArrayList<Bitmap> shuffledBitmaps = new ArrayList<>(originalBitmaps);
        Collections.shuffle(shuffledBitmaps);

        // Создаем сетку
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ImageView iv = new ImageView(this);
                iv.setImageBitmap(shuffledBitmaps.get(i * 3 + j));
                iv.setScaleType(ImageView.ScaleType.FIT_XY);

                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.width = tileWidth; params.height = tileHeight;
                params.setMargins(2, 2, 2, 2);
                iv.setLayoutParams(params);

                iv.setOnTouchListener(new TileTouchListener());
                grid.addView(iv);
                tiles[i][j] = iv;
            }
        }
    }

    private class TileTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (!state) return true;
            ImageView touchedTile = (ImageView) v;

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    // Начинаем перетаскивание
                    draggedTile = touchedTile;
                    touchedTile.setAlpha(0.6f); // Визуальный отклик
                    return true;

                case MotionEvent.ACTION_UP:
                    // Завершаем перетаскивание
                    touchedTile.setAlpha(1.0f);

                    // Проверяем, над каким тайлом отпустили
                    int[] location = new int[2];
                    touchedTile.getLocationOnScreen(location);
                    float touchX = event.getRawX();
                    float touchY = event.getRawY();

                    // Ищем тайл под пальцем
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            ImageView targetTile = tiles[i][j];
                            if (targetTile != draggedTile) {
                                targetTile.getLocationOnScreen(location);
                                int tileLeft = location[0], tileTop = location[1];
                                int tileRight = tileLeft + targetTile.getWidth();
                                int tileBottom = tileTop + targetTile.getHeight();

                                // Если отпустили над этим тайлом — меняем местами
                                if (touchX >= tileLeft && touchX <= tileRight &&
                                        touchY >= tileTop && touchY <= tileBottom) {
                                    swapTiles(draggedTile, targetTile);
                                    return true;
                                }
                            }
                        }
                    }
                    draggedTile = null;
                    return true;

                case MotionEvent.ACTION_CANCEL:
                    touchedTile.setAlpha(1.0f);
                    draggedTile = null;
                    return true;
            }
            return false;
        }
    }

    private void swapTiles(ImageView tile1, ImageView tile2) {
        if (tile1 == tile2) return;

        Bitmap bitmap1 = ((BitmapDrawable) tile1.getDrawable()).getBitmap();
        Bitmap bitmap2 = ((BitmapDrawable) tile2.getDrawable()).getBitmap();
        tile1.setImageBitmap(bitmap2);
        tile2.setImageBitmap(bitmap1);

        checkWin();
    }

    private void checkWin() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Bitmap current = ((BitmapDrawable) tiles[row][col].getDrawable()).getBitmap();
                if (current != originalBitmaps.get(row * 3 + col)) {
                    return;
                }
            }
        }


        Toast.makeText(this, "Пазл собран! 🎉", Toast.LENGTH_LONG).show();
        button.setVisibility(VISIBLE);
        state = false;
    }

    public void backActivity(View view) {
        finish();
    }


    public void nextClick(View view) {
        manager.setDnepr(0); manager.setDneprStatus(2);
        startActivity(new Intent(this, FinalActivity.class));  finish();
    }
}