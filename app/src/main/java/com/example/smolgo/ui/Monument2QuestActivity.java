package com.example.smolgo.ui;

import static android.view.View.VISIBLE;
import static android.widget.Toast.LENGTH_SHORT;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
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

// Активность с квестом "Памятники 2"
public class Monument2QuestActivity extends AppCompatActivity {
    // Массив с названиями памятников
    int[] labelsText = {
            R.string.quest_monument2_label_0,
            R.string.quest_monument2_label_1,
            R.string.quest_monument2_label_2,
            R.string.quest_monument2_label_3,
            R.string.quest_monument2_label_4,
            R.string.quest_monument2_label_5,
            R.string.quest_monument2_label_6
    };

    // Массив с описанием памятников
    int[] aboutText = {
            R.string.quest_monument2_about_0,
            R.string.quest_monument2_about_1,
            R.string.quest_monument2_about_2,
            R.string.quest_monument2_about_3,
            R.string.quest_monument2_about_4,
            R.string.quest_monument2_about_5,
            R.string.quest_monument2_about_6
    };

    // Массив с вопросами
    int[] questionText = {
            R.string.quest_monument2_question_0,
            R.string.quest_monument2_question_1,
            R.string.quest_monument2_question_2,
            R.string.quest_monument2_question_3,
            R.string.quest_monument2_question_4,
            R.string.quest_monument2_question_5,
            R.string.quest_monument2_question_6
    };

    // Массив с ответами
    String[] answersText = {
            "",
            "1812",
            "кутузов",
            "2",
            "164630",
            "превозможет",
            "барановская"
    };

    // Массив с изображениями
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
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_monumets2_quest);

        manager = SharedManager.getInstance(this);
        num = manager.getMonument2();

        label = findViewById(R.id.info);
        about = findViewById(R.id.about);
        question = findViewById(R.id.question);
        answer = findViewById(R.id.answer);
        image = findViewById(R.id.imageWall);
        scrollView = findViewById(R.id.scrollView);

        label.setText(labelsText[num]);
        about.setText(aboutText[num]);
        question.setText(questionText[num]);
        image.setImageResource(monumentsImages[num]);

        if (num != 0) {
            answer.setVisibility(VISIBLE);
        }

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

    // Переход на следующий памятник
    public void nextClick(View view) {
        String userAnswer = answer.getText().toString().strip();

        if (userAnswer.toLowerCase().equals(answersText[num])) {
            ++num;
        } else {
            Toast.makeText(this, R.string.not_correct, LENGTH_SHORT).show();
            return;
        }

        // Если все памятники пройдены, то переход на активность финиша
        if (num == 7) {
            manager.setMonument2(0); manager.setMonumentStatus2(2);
            startActivity(new Intent(this, FinalActivity.class));  finish();
        } else {
            label.setText(labelsText[num]);
            about.setText(aboutText[num]);
            question.setText(questionText[num]);
            image.setImageResource(monumentsImages[num]);
            answer.setText("");
            answer.setVisibility(VISIBLE);
            manager.setMonument2(num);

            scrollView.post(() -> scrollView.fullScroll(View.FOCUS_UP));
        }
    }
}