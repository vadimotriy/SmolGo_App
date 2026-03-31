package com.example.smolgo.ui;

import static android.widget.Toast.LENGTH_SHORT;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.smolgo.controller.SharedManager;
import com.google.android.material.textfield.TextInputEditText;

public class Monument2QuestActivity extends AppCompatActivity {
    String[] labelsText = {
            "Инструкция",
            "Мемориал войны ... года",
            "Алея героев 1812 года",
            "Утраченные башни Крепостной стены",
            "Опаленный цветок",
            "Памятник героям СВО",
            "Памятный знак партизанам 1812 года"
    };

    String[] aboutText = {
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    String[] questionText = {
            "",
            "Героям какого года посвящен этот памятник?",
            "Какая фамилия у генерала, который написал письмо Смолянам?",
            "Сколько таких башенок находится в пределах этой территории?",
            "Сколько человек из Смоленской области было угнано в немецко-фашистское рабство?",
            "Вставьте пропуск «НЕСГИБАЕМЫЙ ДУХ ВСЁ ...»",
            "Какая фамилия у скульптора?"
    };

    String[] answersText = {
            "",
            "1812",
            "кутузов",
            "2",
            "164630",
            "превозможет",
            "барановская"
    };

    int[] monumentsImages = {
            R.drawable.img_monument2_quest_1,
            R.drawable.img_monument2_quest_2,
            R.drawable.img_monument2_quest_3,
            R.drawable.img_monument2_quest_4,
            R.drawable.img_monument2_quest_5,
            R.drawable.img_monument2_quest_6,
            R.drawable.img_monument2_quest_7
    };

    int num;
    SharedManager manager;
    TextView label, about, question;
    TextInputEditText answer;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_monumets2_quest);

        manager = SharedManager.getInstance(this);
        num = manager.getMonument();

        label = findViewById(R.id.info);
        about = findViewById(R.id.about);
        question = findViewById(R.id.question);
        answer = findViewById(R.id.answer);
        image = findViewById(R.id.imageWall);

        label.setText(labelsText[num]);
        about.setText(aboutText[num]);
        question.setText(questionText[num]);
        image.setImageResource(monumentsImages[num]);

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
        String userAnswer = answer.getText().toString().strip();

        if (userAnswer.toLowerCase().equals(answersText[num])) {
            ++num;
        } else {
            Toast.makeText(this, "Не правильно!", LENGTH_SHORT).show();
            return;
        }

        if (num == 7) { manager.setMonument(0); manager.setMonumentStatus2(2); startActivity(new Intent(this, FinalActivity.class)); }
        else {
            label.setText(labelsText[num]);
            about.setText(aboutText[num]);
            question.setText(questionText[num]);
            image.setImageResource(monumentsImages[num]);
            answer.setText("");
            manager.setMonument(num);
        }
    }
}