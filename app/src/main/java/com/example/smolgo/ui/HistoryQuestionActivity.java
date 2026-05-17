package com.example.smolgo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.smolgo.R;
import com.example.smolgo.controller.SharedManager;

// Активность с викториной "История Смоленска"
public class HistoryQuestionActivity extends AppCompatActivity {
    // Массив с вопросами
    int[] questionsText = {
            R.string.question_history_questions_0,
            R.string.question_history_questions_1,
            R.string.question_history_questions_2,
            R.string.question_history_questions_3,
            R.string.question_history_questions_4
    };

    // Массив с вариантами ответов
    String[][] answersVariants = {
            {"982", "863", "862", "1012"},
            {"25 сентября", "12 октября", "29 сентября", "9 июня"},
            {"Юрий Фельтен", "Григорий Потемкин", "Михаил Шейн", "Федр Конь"},
            {"3 месяца", "12 месяцев", "16 месяцев", "20 месяцев"},
            {"Феникс", "Орел", "Гамаюн", "Воробей"}
    };

    // Массив с правильными ответами
    int[] answerRight = {
            R.id.variant2,
            R.id.variant1,
            R.id.variant4,
            R.id.variant4,
            R.id.variant3
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
        setContentView(R.layout.activity_history_question);

        manager = SharedManager.getInstance(this);
        num = manager.getHistoryQuestion();

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
            manager.setHistoryQuestionResult(0);
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
            manager.setHistoryQuestionResult(manager.getHistoryQuestionResult() + 1);
        }
        if (selectedBtn != -1) { ++num; }

        // Если все вопросы пройдены, то переход на активность финиша
        if (num == 5) {
            manager.setHistoryQuestion(0); manager.setHistoryQuestionStatus(2);
            startActivity(new Intent(this, FinalActivity.class)); finish();
        } else {
            manager.setHistoryQuestion(num);
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