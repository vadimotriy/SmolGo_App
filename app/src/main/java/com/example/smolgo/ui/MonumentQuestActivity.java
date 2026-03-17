package com.example.smolgo.ui;

import static android.widget.Toast.LENGTH_SHORT;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.smolgo.R;
import com.example.smolgo.controller.SharedManager;
import com.google.android.material.textfield.TextInputEditText;

public class MonumentQuestActivity extends AppCompatActivity {

    String[] labelsText = {
            "Памятник Михаилу Глинке",
            "Памятник Борису и Глебу",
            "Памятник Александру Твардовскому",
            "Памятник Владимиру Ленину",
            "Памятник Минину и Пожарскому"
    };

    String[] aboutText = {
            "Парк Глинки, рядом с музыкальным училищем",
            "Соборный холм, у берега реки Днепр",
            "Городской сад, улица Теньшевой",
            "Площадь Ленина, административный центр",
            "Кремлевская стена, площадь Победы"
    };

    String[] yearsText = {
            "1906",
            "2011",
            "1993",
            "1939",
            "2005"
    };

    int num;
    SharedManager manager;
    TextView label, about;
    TextInputEditText year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_monument_quest);

        manager = SharedManager.getInstance(this);
        num = manager.getMonument();

        label = findViewById(R.id.info);
        about = findViewById(R.id.about);
        year = findViewById(R.id.year);

        label.setText(labelsText[num]);
        about.setText(aboutText[num]);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void backActivity(View view) {
        finish();
    }

    public void nextClick(View view) {
        String userYear = year.getText().toString().strip();

        if (userYear.equals(yearsText[num])) {
            ++num;
        } else {
            Toast.makeText(this, "Не правильно!", LENGTH_SHORT).show();
            return;
        }

        if (num == 5) { manager.setMonument(0); manager.setMonumentStatus(2); finish(); }
        else {
            label.setText(labelsText[num]);
            about.setText(aboutText[num]);
            year.setText("");
            manager.setMonument(num);
        }
    }
}