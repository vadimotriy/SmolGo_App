package com.example.smolgo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

// Активность с викториной "Крепостная стена"
public class WallQuestionActivity extends AppCompatActivity {
    // Массив с вопросами
    int[] questionsText = {
            R.string.question_wall_questions_0,
            R.string.question_wall_questions_1,
            R.string.question_wall_questions_2,
            R.string.question_wall_questions_3,
            R.string.question_wall_questions_4
    };

    // Массив с вариантами ответов
    String[][] answersVariants = {
            {"38", "17", "67", "26"},
            {"17", "13", "14", "11"},
            {"1592", "1602", "1595", "1600"},
            {"Пётр I", "Екатерина I", "Пётр III", "Павел I"},
            {"Башня Зимбулка", "Башня Авраамиевские ворота", "Башня Костыревская (Красная)", "Башня Донец"}
    };

    // Массив с правильными ответами
    int[] answerRight = {
            R.id.variant1,
            R.id.variant3,
            R.id.variant3,
            R.id.variant1,
            R.id.variant2
    };

    int num;
    SharedManager manager;
    TextView question;
    ScrollView scrollView;
    RadioButton btn1, btn2, btn3, btn4;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_wall_question);

        manager = SharedManager.getInstance(this);
        num = manager.getWallQuestion();

        scrollView = findViewById(R.id.scrollView);
        question = findViewById(R.id.question);
        btn1 = findViewById(R.id.variant1);
        btn2 = findViewById(R.id.variant2);
        btn3 = findViewById(R.id.variant3);
        btn4 = findViewById(R.id.variant4);
        radioGroup = findViewById(R.id.radioGroup);

        question.setText(questionsText[num]);
        btn1.setText(answersVariants[num][0]);
        btn2.setText(answersVariants[num][1]);
        btn3.setText(answersVariants[num][2]);
        btn4.setText(answersVariants[num][3]);

        if (num == 0) {
            manager.setWallQuestionResult(0);
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, 0, systemBars.right, 0);
            return insets;
        });
    }

    // Выход из активности
    public void backActivity(View view) {
        finish();
    }

    // Переход на следующий вопрос
    public void nextClick(View view) {
        int selectedBtn = radioGroup.getCheckedRadioButtonId();

        if (selectedBtn == answerRight[num]) {
            manager.setWallQuestionResult(manager.getWallQuestionResult() + 1);
        }
        if (selectedBtn != -1) { ++num; }

        // Если все вопросы пройдены, то переход на активность финиша
        if (num == 5) {
            manager.setWallQuestion(0); manager.setWallQuestionStatus(2);
            startActivity(new Intent(this, FinalActivity.class)); finish();
        } else {
            manager.setWallQuestion(num);
            question.setText(questionsText[num]);
            btn1.setText(answersVariants[num][0]);
            btn2.setText(answersVariants[num][1]);
            btn3.setText(answersVariants[num][2]);
            btn4.setText(answersVariants[num][3]);
            scrollView.post(() -> scrollView.fullScroll(View.FOCUS_UP));
            radioGroup.clearCheck();
        }
    }
}